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
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
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
            { // Constructor of the anonymous subclass
                // registers a print action by means of the global action bars

                final IActionBars actions;
                final IDiagramWorkbenchPart part = getViewContext().getDiagramWorkbenchPart();

                if (part instanceof IEditorPart) {
                    actions = ((IEditorPart) part).getEditorSite().getActionBars();

                } else if (getViewContext().getDiagramWorkbenchPart() instanceof IViewPart) {
                    actions = ((IViewPart) part).getViewSite().getActionBars();

                } else {
                    actions = null;
                }

                // register print action
                if (actions != null) {
                    final PiccoloViewer thisViewer = this;

                    actions.setGlobalActionHandler(ActionFactory.PRINT.getId(), new Action() {
                        private final PrintAction printer = new PrintAction(thisViewer);

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
     * A subclass of the {@link PiccoloOutlinePage} that implements the required
     * {@link IContentOutlinePage} interface.<br>
     * <br>
     * Since that interface requires an additional UI dependency but luckily is simply a composition
     * of {@link org.eclipse.ui.part.IPage IPage} and
     * {@link org.eclipse.jface.viewers.ISelectionProvider ISelectionProvider}, and we do not
     * support any selection providing functionality, {@link PiccoloOutlinePage} only implements
     * {@link org.eclipse.ui.part.IPage IPage} for the sake of reducing dependencies. The required
     * (empty) {@link org.eclipse.jface.viewers.ISelectionProvider ISelectionProvider} methods are
     * than contributed by this sub class.
     * 
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
