/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.emf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.base.Function;

import de.cau.cs.kieler.klighd.ui.modifymodel.KlighdModifyModelHandler;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * Handler to modify EMF models using their editing domain.
 * 
 * @author ckru
 * 
 */
public class ModifyEmfModelHandler extends KlighdModifyModelHandler {

    /**
     * {@inheritDoc}
     */
    public boolean canHandle(final IWorkbenchPart workbenchPart) {
        if (workbenchPart instanceof IEditingDomainProvider) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(final IWorkbenchPart workbenchPart, final Method m,
            final Object classObject, final List<Object> params) {
        IEditingDomainProvider edProvider = (IEditingDomainProvider) workbenchPart;
        EditingDomain domain = edProvider.getEditingDomain();
        final ContextViewer vp = this.getDiagramViewPart(workbenchPart);
        if (!(domain instanceof TransactionalEditingDomain)) {
            domain.getCommandStack().execute(new AbstractCommand() {
                public void execute() {
                    try {
                        m.invoke(classObject, params.toArray());
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                public void redo() {
                    try {
                        m.invoke(classObject, params.toArray());
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                protected boolean prepare() {
                    return true;
                }

                public boolean canUndo() {
                    return false;
                }

            });
        } else {
            AbstractEMFOperation emfOp =
                    new AbstractEMFOperation((TransactionalEditingDomain) domain,
                            "xtend2 transformation", Collections.singletonMap(
                                    Transaction.OPTION_UNPROTECTED, true)) {

                        @Override
                        protected IStatus doExecute(final IProgressMonitor monitor,
                                final IAdaptable info) throws ExecutionException {

                            try {
                                m.invoke(classObject, params.toArray());
                            } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IllegalArgumentException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            return Status.OK_STATUS;
                        }

                    };
            try {
                // execute above operation
                OperationHistoryFactory.getOperationHistory().execute(emfOp, null, null);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                vp.getViewContext().update(null);
            }
        });
    }

    @Override
    public void execute(final IWorkbenchPart workbenchPart, final Function<String, Void> m,
            final String param) {
        IEditingDomainProvider edProvider = (IEditingDomainProvider) workbenchPart;
        EditingDomain domain = edProvider.getEditingDomain();
        if (!(domain instanceof TransactionalEditingDomain)) {
            domain.getCommandStack().execute(new AbstractCommand() {
                public void execute() {
                    m.apply(param);
                }

                public void redo() {
                    m.apply(param);
                }

                protected boolean prepare() {
                    return true;
                }

                public boolean canUndo() {
                    return false;
                }

            });
        } else {
            AbstractEMFOperation emfOp =
                    new AbstractEMFOperation((TransactionalEditingDomain) domain,
                            "xtend2 transformation", Collections.singletonMap(
                                    Transaction.OPTION_UNPROTECTED, true)) {

                        @Override
                        protected IStatus doExecute(final IProgressMonitor monitor,
                                final IAdaptable info) throws ExecutionException {

                            m.apply(param);
                            return Status.OK_STATUS;
                        }

                    };
            try {
                // execute above operation
                OperationHistoryFactory.getOperationHistory().execute(emfOp, null, null);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

}
