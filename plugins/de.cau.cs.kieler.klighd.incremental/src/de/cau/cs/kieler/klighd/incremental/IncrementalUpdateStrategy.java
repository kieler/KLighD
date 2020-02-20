/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.incremental;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.IUpdateStrategy;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.incremental.diff.KComparison;
import de.cau.cs.kieler.klighd.incremental.merge.KGraphDataFilter;
import de.cau.cs.kieler.klighd.incremental.merge.KGraphMerger;
import de.cau.cs.kieler.klighd.incremental.util.UIDAdapter;
import de.cau.cs.kieler.klighd.incremental.util.UIDAdapters;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;

/**
 * Implements an incremental update strategy which uses {@link UIDAdapter}s to match and merge the
 * KGraph view models.
 * 
 * @author csp
 */
public class IncrementalUpdateStrategy implements IUpdateStrategy {

    /** The id used at registration of this strategy in the plugin.xml. */
    public static final String ID = IncrementalUpdateStrategy.class.getCanonicalName();

    /** This plugin's id. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd.incremental";

    /** the priority for this update strategy. */
    public static final int PRIORITY = 10;

    private IUpdateStrategy fallbackDelegate = null;

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final KNode baseModel, final KNode newModel, final ViewContext viewContext) {
        if (baseModel.getChildren().isEmpty()) {
            // Disable logging in this case, as this is no real problem but expected behaviour
            // logFallback(IStatus.INFO, "Empty base model.");
            fallback(baseModel, newModel, viewContext);
            return;
        }

        UIDAdapter baseAdapter = UIDAdapters.retrieveAdapter(baseModel);
        UIDAdapter newAdapter = UIDAdapters.retrieveAdapter(newModel);

        try {
            KComparison comparison = new KComparison(baseAdapter, newAdapter);
            KGraphMerger merger = new KGraphMerger(comparison, new KGraphDataFilter());
            merger.merge();

        } catch (RuntimeException e) {
            final String msg = "KLighD: Incremental update of diagram failed.";
            StatusManager.getManager().handle(new Status(IStatus.ERROR, PLUGIN_ID, msg, e),
                    StatusManager.LOG);
            e.printStackTrace();

            // if incremental updating failed, apply the SimpleUpdateStrategy
            fallback(baseModel, newModel, viewContext);
        }

        UIDAdapters.removeAdapter(baseModel);
        UIDAdapters.removeAdapter(newModel);
    }

    private void fallback(final KNode baseModel, final KNode newModel,
            final ViewContext viewContext) {

        if (fallbackDelegate == null) {
            fallbackDelegate =
                    KlighdDataManager.getInstance().getNextPrioritizedUpdateStrategy(this);
            if (fallbackDelegate == null) {
                fallbackDelegate = new SimpleUpdateStrategy();
            }
        }
        fallbackDelegate.update(baseModel, newModel, viewContext);
    }

    /**
     * {@inheritDoc}
     */
    public boolean requiresDiagramSynthesisReRun(final ViewContext viewContext) {
        return true;
    }
}
