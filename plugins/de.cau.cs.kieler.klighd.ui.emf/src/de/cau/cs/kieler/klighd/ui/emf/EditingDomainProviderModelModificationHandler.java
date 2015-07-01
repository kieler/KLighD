/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.emf;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.base.Function;

import de.cau.cs.kieler.klighd.ui.modifymodel.AbstractKlighdModelModificationHandler;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * Handler to modify EMF models using their editing domain.
 * 
 * @author ckru
 */
public class EditingDomainProviderModelModificationHandler extends
        AbstractKlighdModelModificationHandler {

    /**
     * {@inheritDoc}
     */
    public boolean canHandle(final IWorkbenchPart workbenchPart) {
        if (workbenchPart instanceof IEditingDomainProvider) {
            return true;
        }
        return false;
    }

    /**
     * Command that executes a given method.
     * 
     * @author ckru
     */
    private class InvokeMethodCommand extends AbstractCommand {

        /**
         * The method to execute.
         */
        private Method m;

        /**
         * The parameters to execute the method with
         */
        private List<Object> params;

        /**
         * The class this method belongs to.
         */
        private Object classObject;

        /**
         * Create command that executes a given method.
         * 
         * @param classObject
         *            the class the method to execute belongs to.
         * @param m
         *            the method to execute.
         * @param params
         *            the parameters to execute the method with.
         */
        protected InvokeMethodCommand(final Object classObject, final Method m,
                final List<Object> params) {
            super();
            this.classObject = classObject;
            this.m = m;
            this.params = params;

        }

        /**
         * {@inheritDoc}
         */
        public void execute() {
            try {
                m.invoke(classObject, params.toArray());
            } catch (Exception e) {
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, "de.cau.cs.kieler.klighd.ui.emf",
                                "An error has occured while trying to execute ksbase method "
                                        + m.getName() + "of class " + classObject.toString(), e));
            }
        }

        /**
         * {@inheritDoc}
         */
        public void redo() {
            try {
                m.invoke(classObject, params.toArray());
            } catch (Exception e) {
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, "de.cau.cs.kieler.klighd.ui.emf",
                                "An error has occured while trying to execute ksbase method "
                                        + m.getName() + "of class " + classObject.toString(), e));
            }
        }

        /**
         * {@inheritDoc}
         */
        protected boolean prepare() {
            return true;
        }

        /**
         * {@inheritDoc}
         */
        public boolean canUndo() {
            return false;
        }
    }

    /**
     * Command that executes a given function.
     * 
     * @author ckru
     */
    private class InvokeFunctionCommand extends AbstractCommand {

        /**
         * The function to execute.
         */
        private Function<String, Void> f;

        /**
         * The parameters to execute the function with
         */
        private String params;

        /**
         * Create command that executes a given function.
         * 
         * @param classObject
         *            the class the method to execute belongs to.
         * @param m
         *            the method to execute.
         * @param params
         *            the parameters to execute the method with.
         */
        protected InvokeFunctionCommand(final Function<String, Void> f, final String params) {
            super();
            this.f = f;
            this.params = params;
        }

        /**
         * {@inheritDoc}
         */
        public void execute() {
            f.apply(params);
        }

        /**
         * {@inheritDoc}
         */
        public void redo() {
            f.apply(params);
        }

        /**
         * {@inheritDoc}
         */
        protected boolean prepare() {
            return true;
        }

        /**
         * {@inheritDoc}
         */
        public boolean canUndo() {
            return false;
        }
    }

    /**
     * Operation that executes a given method.
     * 
     * @author ckru
     */
    private class InvokeMethodOperation extends AbstractEMFOperation {

        /**
         * The method to execute.
         */
        private Method m;

        /**
         * The parameters to execute the method with
         */
        private List<Object> params;

        /**
         * The class this method belongs to.
         */
        private Object classObject;

        /**
         * Create Operation that executes a given method.
         * 
         * @param classObject
         *            the class the method to execute belongs to.
         * @param m
         *            the method to execute.
         * @param params
         *            the parameters to execute the method with.
         */
        protected InvokeMethodOperation(final TransactionalEditingDomain domain,
                final String label, final Map<?, ?> options, final Method m,
                final Object classObject, final List<Object> params) {
            super(domain, label, options);
            this.m = m;
            this.classObject = classObject;
            this.params = params;
        }

        @Override
        protected IStatus doExecute(final IProgressMonitor monitor, final IAdaptable info)
                throws ExecutionException {
            try {
                m.invoke(classObject, params.toArray());
            } catch (Exception e) {
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, "de.cau.cs.kieler.klighd.ui.emf",
                                "An error has occured while trying to execute ksbase method "
                                        + m.getName() + "of class " + classObject.toString(), e));
            }
            return Status.OK_STATUS;
        }
    }

    /**
     * Operation that executes a given function.
     * 
     * @author ckru
     */
    private class InvokeFunctionOperation extends AbstractEMFOperation {

        /**
         * The function to execute.
         */
        private Function<String, Void> f;

        /**
         * The parameters to execute the function with
         */
        private String params;

        /**
         * Create Operation that executes a given method.
         * 
         * @param classObject
         *            the class the method to execute belongs to.
         * @param m
         *            the method to execute.
         * @param params
         *            the parameters to execute the method with.
         */
        protected InvokeFunctionOperation(final TransactionalEditingDomain domain,
                final String label, final Map<?, ?> options, final Function<String, Void> f,
                final String params) {
            super(domain, label, options);
            this.f = f;
            this.params = params;
        }

        @Override
        protected IStatus doExecute(final IProgressMonitor monitor, final IAdaptable info)
                throws ExecutionException {
            f.apply(params);
            return Status.OK_STATUS;
        }
    }

    @Override
    public void execute(final IWorkbenchPart workbenchPart, final Method m,
            final Object classObject, final List<Object> params) {
        IEditingDomainProvider edProvider = (IEditingDomainProvider) workbenchPart;
        EditingDomain domain = edProvider.getEditingDomain();
        final ContextViewer vp = this.getDiagramViewPart(workbenchPart);
        if (!(domain instanceof TransactionalEditingDomain)) {
            domain.getCommandStack().execute(new InvokeMethodCommand(classObject, m, params));
        } else {
            AbstractEMFOperation emfOp =
                    new InvokeMethodOperation((TransactionalEditingDomain) domain,
                            "xtend2 transformation", Collections.singletonMap(
                                    Transaction.OPTION_UNPROTECTED, true), m, classObject, params);
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
            domain.getCommandStack().execute(new InvokeFunctionCommand(m, param));
        } else {
            AbstractEMFOperation emfOp =
                    new InvokeFunctionOperation((TransactionalEditingDomain) domain,
                            "xtend2 transformation", Collections.singletonMap(
                                    Transaction.OPTION_UNPROTECTED, true), m, param);
            try {
                // execute above operation
                OperationHistoryFactory.getOperationHistory().execute(emfOp, null, null);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

}
