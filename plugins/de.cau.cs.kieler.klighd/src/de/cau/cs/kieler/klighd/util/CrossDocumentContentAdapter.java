/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
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
package de.cau.cs.kieler.klighd.util;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EContentsEList;

/**
 * This adapter refines the {@code EContentAdapter} so that it is able to follow
 * non-containment references.
 * 
 * <p>The implementation has been copied from <a href="http://wiki.eclipse.org/EMF/Recipes">http://wiki.eclipse.org/EMF/Recipes</a>.
 * 
 * @author EMF, mri
 */
public class CrossDocumentContentAdapter extends EContentAdapter {

    /**
     * Constructs a cross document content adapter.
     */
    public CrossDocumentContentAdapter() {
        super();
    }

    /**
     * By default, all cross document references are followed. Usually this is not a great idea so
     * this class can be subclassed to customize.
     * 
     * @param feature
     *            a cross document reference
     * @return whether the adapter should follow it
     */
    protected boolean shouldAdapt(final EStructuralFeature feature) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setTarget(final EObject target) {
        super.setTarget(target);
        for (EContentsEList.FeatureIterator<EObject> featureIterator =
                (EContentsEList.FeatureIterator<EObject>) target.eCrossReferences().iterator();
                featureIterator.hasNext();) {
            Notifier notifier = featureIterator.next();
            EStructuralFeature feature = featureIterator.feature();
            if (shouldAdapt(feature) && !notifier.eAdapters().contains(this)) {
                addAdapter(notifier);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unsetTarget(final EObject target) {
        super.unsetTarget(target);
        for (EContentsEList.FeatureIterator<EObject> featureIterator =
                (EContentsEList.FeatureIterator<EObject>) target.eCrossReferences().iterator();
                featureIterator.hasNext();) {
            Notifier notifier = featureIterator.next();
            EStructuralFeature feature = featureIterator.feature();
            if (shouldAdapt(feature)) {
                removeAdapter(notifier);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void selfAdapt(final Notification notification) {
        super.selfAdapt(notification);
        if (notification.getNotifier() instanceof EObject) {
            Object feature = notification.getFeature();
            if (feature instanceof EReference) {
                EReference eReference = (EReference) feature;
                if (!eReference.isContainment() && shouldAdapt(eReference)) {
                    handleContainment(notification);
                }
            }
        }
    }

}
