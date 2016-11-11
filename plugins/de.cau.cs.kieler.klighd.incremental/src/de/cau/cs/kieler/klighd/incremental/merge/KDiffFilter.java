package de.cau.cs.kieler.klighd.incremental.merge;

import java.util.List;

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.ReferenceChange;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;

import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.kgraph.impl.IPropertyToObjectMapImpl;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.microlayout.GridPlacementUtil;

/**
 * Filters, which differences are to be merged.
 *
 * @author csp
 */
public class KDiffFilter implements Predicate<Diff> {
    
    private final List<IProperty<?>> MERGE_PROPERTIES = ImmutableList.<IProperty<?>> of(
//            GridPlacementUtil.CHILD_AREA_POSITION,
            GridPlacementUtil.ESTIMATED_GRID_DATA,
            KlighdInternalProperties.MODEL_ELEMEMT);

    public boolean apply(Diff diff) {
        if (diff instanceof ReferenceChange) {
            ReferenceChange refChange = (ReferenceChange) diff;
            if (refChange.getValue() instanceof IPropertyToObjectMapImpl
                    && refChange.getValue().eContainer().eClass().getEPackage()
                            .equals(KRenderingPackage.eINSTANCE)
                    && !MERGE_PROPERTIES.contains(((IPropertyToObjectMapImpl) refChange.getValue())
                            .getKey())) {
                // Omit all IPropertyToObjectMapImpls that are attached to KRendering-related
                // data structures except for those mentioned in MERGE_PROPERTIES.
                // Those are set by the KLighD translation infrastructure rather than the
                // Piccolo-based rendering binding; they must be updated/replaced during the merge!
                // The remaining ones have been attached to the displayed view model to cache
                // information and shall be preserved!
                return false;
            }
//            else if (refChange.getValue() instanceof IPropertyToObjectMapImpl
//                    && refChange.getKind() == DifferenceKind.DELETE) {
//                IPropertyToObjectMapImpl map = (IPropertyToObjectMapImpl) refChange.getValue();
//                if (map.getKey().equals(KlighdProperties.INITIAL_NODE_SIZE)) {
//                    KShapeLayoutImpl layout = (KShapeLayoutImpl) refChange.getMatch().getLeft();
//                    // if (layout != null && layout.getHeight() == 0 && layout.getWidth() == 0)
//                    if (layout != null && !layout.eIsSet(KLayoutDataPackage.KSHAPE_LAYOUT__HEIGHT)
//                            && !layout.eIsSet(KLayoutDataPackage.KSHAPE_LAYOUT__WIDTH))
//                        // Keep the INITIAL_NODE_SIZE flag (which is false after the first
//                        // Layoutiteration) (i.e. delete the difference), if no size is set in
//                        // the newly generated Knode.
//                        return false;
//                    // If there is a size set, reset the flag (i.e. keep the difference, so the
//                    // property gets deleted), such that the set size gets layouted.
//                }
//            }
        }
        return true;
    }
}
