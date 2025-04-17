
/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016-2025 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.kgraph.text.ui.random;

import de.cau.cs.kieler.kgraph.text.grandom.RandGraph;
import java.util.Collections;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

public class GRandomHandler extends AbstractHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        PlatformUI.getWorkbench().saveAllEditors(true);
        final ISelection selection = HandlerUtil.getCurrentSelection(event);
        if ((selection instanceof IStructuredSelection)) {
            final Object element = ((IStructuredSelection) selection).getFirstElement();
            if ((element instanceof IFile)) {
                try {
                    // load the file into a resource
                    final ResourceSetImpl resourceSet = new ResourceSetImpl();
                    final Resource r = resourceSet.createResource(URI.createFileURI("dummy.rdg"));
                    r.load(((IFile) element).getContents(),
                            Collections.<Object, Object> emptyMap());
                    final IProject project = ((IFile) element).getProject();
                    if (((!r.getContents().isEmpty())
                            && (r.getContents().get(0) instanceof RandGraph))) {
                        EObject _head = IterableExtensions.<EObject> head(r.getContents());
                        final RandGraph rand = ((RandGraph) _head);
                        final Job job = new Job("Generate Random Graphs") {
                            protected IStatus run(final IProgressMonitor monitor) {
                                monitor.beginTask("Generate Random Graphs", 1);
                                try {
                                    new GRandomGraphMaker(rand).gen(project);
                                } catch (final Throwable _t) {
                                    if (_t instanceof Exception) {
                                        final Exception e = (Exception) _t;
                                        final Status status = new Status(IStatus.ERROR,
                                                "de.cau.cs.kiel.kgraph.randgraph", 0,
                                                "Random Graph Generation failed", e);
                                        StatusManager.getManager().handle(status,
                                                (StatusManager.SHOW | StatusManager.LOG));
                                    } else {
                                        throw Exceptions.sneakyThrow(_t);
                                    }
                                } finally {
                                    monitor.done();
                                }
                                return Status.OK_STATUS;
                            }
                        };
                        job.setUser(true);
                        job.schedule();
                    }
                } catch (final Throwable _t) {
                    if (_t instanceof Exception) {
                        // StatusManager.getManager().handle(
                        // new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID, MESSAGE_BATCH_FAILED, e));
                    } else {
                        throw Exceptions.sneakyThrow(_t);
                    }
                }
            }
        }
        return null;
    }
}
