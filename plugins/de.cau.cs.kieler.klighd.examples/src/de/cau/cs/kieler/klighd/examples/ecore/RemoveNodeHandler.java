/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.examples.ecore;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * 
 * @author chsch
 *
 */
public class RemoveNodeHandler extends AbstractHandler {

    /**
     * 
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        ISelection s = HandlerUtil.getCurrentSelection(event);
        if (s instanceof Iterable<?>) {
            Iterator<?> i = ((Iterable<?>) s).iterator();
            if (i.hasNext()) {
                ElkNode n = (ElkNode) i.next();
                ((ElkNode) n.eContainer()).getChildren().remove(n);
            }
        }
        return null;
    }
}
