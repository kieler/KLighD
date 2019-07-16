/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.constraints

import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import org.eclipse.lsp4j.jsonrpc.services.JsonSegment

/**
 * @author jet, cos
 * 
 */
@JsonSegment('keith/constraints')
interface ConstraintsCommandExtension {

    @JsonNotification('sayhello')
    def void sayHello(String msg);

    @JsonNotification('setLayerConstraint')
    def void setLayerConstraint(LayerConstraint lc);

    @JsonNotification('setStaticConstraint')
    def void setStaticConstraint(StaticConstraint sc);

    @JsonNotification('setPositionConstraint')
    def void setPositionConstraint(PositionConstraint pc);

    /**
     * Deletes a position constraint from the node that is specified 
     * in the sent PositionConstraint container 
     * by setting the property to its default value -1.
     */
    @JsonNotification('deletePositionConstraint')
    def void deletePositionConstraint(DeleteConstraint dc);

    /**
     * Deletes a layer constraint from the node that is specified 
     * in the sent PositionConstraint container 
     * by setting the property to its default value -1.
     */
    @JsonNotification('deleteLayerConstraint')
    def void deleteLayerConstraint(DeleteConstraint dc);

    @JsonNotification('deleteStaticConstraint')
    def void deleteStaticConstraint(DeleteConstraint dc);

    /**
     * Triggers a new layout of the unchanged KGraph. 
     * This method is used in order to let a node snap back 
     * if the interactive action set no constraint.
     */
    @JsonNotification('refreshLayout')
    def void refreshLayout(String uri);
}
