/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.ui.view.controllers;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.util.ResourceUtil;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

/**
 * This class contains utility methods for access to editor content.
 * 
 * @author als
 * @kieler.design 2015-09-30 proposed
 * @kieler.rating 2015-09-30 proposed yellow
 *
 */
public final class EditorUtil {

    /**
     * Prevent instantiation.
     */
    private EditorUtil() {
    }

    /**
     * Get the model from a given Xtext editor.
     * 
     * @param xtextEditor
     *            the Xtext editor
     * @return the EObject of the root part of the model
     */
    public static EObject readModelFromXtextEditor(final XtextEditor xtextEditor) {
        IXtextDocument xtextDocument = xtextEditor.getDocument();

        if (xtextDocument instanceof XtextDocument) {
            IParseResult result = null;
            IUnitOfWork<IParseResult, XtextResource> work =
                    new IUnitOfWork<IParseResult, XtextResource>() {
                        public IParseResult exec(final XtextResource xtextResource)
                                throws Exception {
                            return xtextResource.getParseResult();
                        }
                    };
            result = xtextDocument.readOnly(work);
            return result.getRootASTElement();
        }
        return null;
    }

    /**
     * Get the model from a given EMF tree editor.
     * 
     * @param editor
     *            the EMF tree editor
     * @return the EObject of the root part of the model
     */
    public static EObject readModelFromEMFEditor(final IEditingDomainProvider editor) {
        List<Resource> resources = editor.getEditingDomain().getResourceSet().getResources();

        if (!resources.isEmpty() && !resources.get(0).getContents().isEmpty()) {
            return EcoreUtil.getRootContainer(resources.get(0).getContents().get(0));
        }
        return null;
    }
    

    /**
     * Checks the given resource for error markers.
     * 
     * @see XtextBasedEditorActivationChangeTrigger#checkAndIndicateErrors(final XtextResource resource)
     * 
     * @param resource
     *            the resource
     * @return true if any errors are present otherwise false
     */
    public static boolean hasErrorMarkers(final XtextResource resource) {
        XtextResource xResource = (XtextResource) resource;
        IFile underlyingFile = ResourceUtil.getUnderlyingFile(xResource);

        if (underlyingFile == null) {
            // this happens in case models being part of installed bundles (e.g. Xtend files)
            // are opened; it doesn't make sense to attach any markers to them
            return false;
        }

        try {
            /* examine the files error markers, whether one of is created by this mechanisms */
            List<IMarker> currentMarkers = Arrays.asList(
                    underlyingFile.findMarkers(IMarker.PROBLEM, false, IResource.DEPTH_INFINITE));

            /* if model is correct... */
            if (xResource.getErrors().isEmpty() && currentMarkers.isEmpty()) {
                return false;
            }
        } catch (CoreException e) {
            /* in this case something went heavily wrong */
            throw new WrappedException(e);
        }
        return true;
    }
}
