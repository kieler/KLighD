/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.gson_utils

import com.google.gson.GsonBuilder
import de.cau.cs.kieler.klighd.kgraph.KGraphData
import de.cau.cs.kieler.klighd.kgraph.impl.KIdentifierImpl
import de.cau.cs.kieler.klighd.krendering.KStyle
import de.cau.cs.kieler.klighd.krendering.KXPosition
import de.cau.cs.kieler.klighd.krendering.KYPosition
import de.cau.cs.kieler.klighd.krendering.impl.KArcImpl
import de.cau.cs.kieler.klighd.krendering.impl.KBackgroundImpl
import de.cau.cs.kieler.klighd.krendering.impl.KBottomPositionImpl
import de.cau.cs.kieler.klighd.krendering.impl.KChildAreaImpl
import de.cau.cs.kieler.klighd.krendering.impl.KCustomRenderingImpl
import de.cau.cs.kieler.klighd.krendering.impl.KEllipseImpl
import de.cau.cs.kieler.klighd.krendering.impl.KFontBoldImpl
import de.cau.cs.kieler.klighd.krendering.impl.KFontItalicImpl
import de.cau.cs.kieler.klighd.krendering.impl.KFontNameImpl
import de.cau.cs.kieler.klighd.krendering.impl.KFontSizeImpl
import de.cau.cs.kieler.klighd.krendering.impl.KForegroundImpl
import de.cau.cs.kieler.klighd.krendering.impl.KHorizontalAlignmentImpl
import de.cau.cs.kieler.klighd.krendering.impl.KImageImpl
import de.cau.cs.kieler.klighd.krendering.impl.KInvisibilityImpl
import de.cau.cs.kieler.klighd.krendering.impl.KLeftPositionImpl
import de.cau.cs.kieler.klighd.krendering.impl.KLineCapImpl
import de.cau.cs.kieler.klighd.krendering.impl.KLineJoinImpl
import de.cau.cs.kieler.klighd.krendering.impl.KLineStyleImpl
import de.cau.cs.kieler.klighd.krendering.impl.KLineWidthImpl
import de.cau.cs.kieler.klighd.krendering.impl.KPolygonImpl
import de.cau.cs.kieler.klighd.krendering.impl.KPolylineImpl
import de.cau.cs.kieler.klighd.krendering.impl.KRectangleImpl
import de.cau.cs.kieler.klighd.krendering.impl.KRenderingLibraryImpl
import de.cau.cs.kieler.klighd.krendering.impl.KRenderingRefImpl
import de.cau.cs.kieler.klighd.krendering.impl.KRightPositionImpl
import de.cau.cs.kieler.klighd.krendering.impl.KRotationImpl
import de.cau.cs.kieler.klighd.krendering.impl.KRoundedBendsPolylineImpl
import de.cau.cs.kieler.klighd.krendering.impl.KRoundedRectangleImpl
import de.cau.cs.kieler.klighd.krendering.impl.KShadowImpl
import de.cau.cs.kieler.klighd.krendering.impl.KSplineImpl
import de.cau.cs.kieler.klighd.krendering.impl.KStyleRefImpl
import de.cau.cs.kieler.klighd.krendering.impl.KTextImpl
import de.cau.cs.kieler.klighd.krendering.impl.KTextStrikeoutImpl
import de.cau.cs.kieler.klighd.krendering.impl.KTextUnderlineImpl
import de.cau.cs.kieler.klighd.krendering.impl.KTopPositionImpl
import de.cau.cs.kieler.klighd.krendering.impl.KVerticalAlignmentImpl
import de.cau.cs.kieler.klighd.lsp.model.ComputedTextBoundsAction
import de.cau.cs.kieler.klighd.lsp.model.RequestTextBoundsAction
import io.typefox.sprotty.server.json.ActionTypeAdapter
import io.typefox.sprotty.server.json.EnumTypeAdapter
import java.awt.geom.Point2D

/**
 * Static util class to configure needed gson type adapters for KGraph serialization.
 * 
 * @author nir
 */
public class KGraphTypeAdapterUtil {
    
    public def static GsonBuilder configureGson(GsonBuilder gsonBuilder) {
        gsonBuilder
        .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(KGraphData)
            .registerSubtype(KIdentifierImpl)
            .registerSubtype(KChildAreaImpl)
            .registerSubtype(KArcImpl)
            .registerSubtype(KCustomRenderingImpl)
            .registerSubtype(KEllipseImpl)
            .registerSubtype(KImageImpl)
            .registerSubtype(KPolylineImpl)
            .registerSubtype(KPolygonImpl)
            .registerSubtype(KRoundedBendsPolylineImpl)
            .registerSubtype(KSplineImpl)
            .registerSubtype(KRectangleImpl)
            .registerSubtype(KRoundedRectangleImpl)
            .registerSubtype(KRenderingRefImpl)
            .registerSubtype(KTextImpl)
            .registerSubtype(KRenderingLibraryImpl)
        )
        .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(KStyle)
            .registerSubtype(KBackgroundImpl)
            .registerSubtype(KForegroundImpl)
            .registerSubtype(KFontBoldImpl)
            .registerSubtype(KFontItalicImpl)
            .registerSubtype(KFontNameImpl)
            .registerSubtype(KFontSizeImpl)
            .registerSubtype(KHorizontalAlignmentImpl)
            .registerSubtype(KInvisibilityImpl)
            .registerSubtype(KLineCapImpl)
            .registerSubtype(KLineJoinImpl)
            .registerSubtype(KLineStyleImpl)
            .registerSubtype(KLineWidthImpl)
            .registerSubtype(KRotationImpl)
            .registerSubtype(KShadowImpl)
            .registerSubtype(KStyleRefImpl)
            .registerSubtype(KTextStrikeoutImpl)
            .registerSubtype(KTextUnderlineImpl)
            .registerSubtype(KVerticalAlignmentImpl)
        )
        .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(KXPosition)
            .registerSubtype(KLeftPositionImpl)
            .registerSubtype(KRightPositionImpl)
        )
        .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(KYPosition)
            .registerSubtype(KTopPositionImpl)
            .registerSubtype(KBottomPositionImpl)
        )
        .registerTypeAdapterFactory(
            new ActionTypeAdapter.Factory => [
                addActionKind(RequestTextBoundsAction.KIND, RequestTextBoundsAction)
                addActionKind(ComputedTextBoundsAction.KIND, ComputedTextBoundsAction)
            ]
        )
        .registerTypeAdapter(Point2D, new Point2DTypeAdapter)
//        .registerTypeAdapterFactory(new EnumTypeAdapter.Factory())
        .registerTypeAdapterFactory(new EMapPropertyHolderTypeAdapterFactory(gsonBuilder))
        .setExclusionStrategies(
            new EObjectFieldExclusionStrategy, 
            new KRenderingRefFieldExclusionStrategy,
            new KRenderingFieldExclusionStrategy,
            new KGraphDataExclusionStrategy, // TODO: with this, Gson will still put a null value if this is contained in
            // a list and then excluded. Remove this null value
            new InsetExclusionStrategy
        )
    }
}