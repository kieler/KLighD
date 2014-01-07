/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.viewers;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.IViewerProvider;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloOutlinePage;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.piccolo.viewer.PrintAction;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * A viewer provider for the Piccolo2D viewer for KGraphs with attached KRendering data.
 * 
 * @author mri
 * @author chsch
 */
public class PiccoloViewerProvider implements IViewerProvider<KNode> {

    /** the identifier for this viewer provider as specified in the extension point. */
    public static final String ID = "de.cau.cs.kieler.klighd.piccolo.piccoloViewer";

    /**
     * {@inheritDoc}
     */
    public Class<KNode> getModelClass() {
        return KNode.class;
    }
    
    /**
     * {@inheritDoc}
     */
    public IViewer<KNode> createViewer(final ContextViewer parentViewer, final Composite parent) {
        return new PiccoloViewer(parentViewer, parent) {
            {
                // register a print action with the global action bars
                if (getViewContext().getDiagramWorkbenchPart() instanceof IViewPart) {
                    final IViewPart viewPart = (IViewPart) getViewContext().getDiagramWorkbenchPart();
                    final PrintAction printer = new PrintAction(this);

                    // register print action
                    viewPart.getViewSite().getActionBars()
                            .setGlobalActionHandler(ActionFactory.PRINT.getId(), new Action() {
                                public void run() {
                                    printer.run();
                                }
                            });
                }
            }

            @Override
            protected PiccoloOutlinePage createDiagramOutlinePage() {
                return new PiccoloContentOutlinePage();
            }
        };
    }
    
    

    /**
     * A. 
     * @author chsch
     */
    private static class PiccoloContentOutlinePage extends PiccoloOutlinePage implements
            IContentOutlinePage {

        /**
         * {@inheritDoc}
         */
        public void addSelectionChangedListener(final ISelectionChangedListener listener) {
            // selection is not supported by this outline page
        }

        /**
         * {@inheritDoc}
         */
        public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
            // selection is not supported by this outline page
        }

        /**
         * {@inheritDoc}
         */
        public ISelection getSelection() {
            // selection is not supported by this outline page
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public void setSelection(final ISelection selection) {
            // selection is not supported by this outline page
        }
    }
}
