/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
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
 * <p>The implementation has been copied from {@link http://wiki.eclipse.org/EMF/Recipes}.</p>
 * 
 * @author EMF, mri
 * @kieler.ignore The first rule of copied code is: you don't review copied code.
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
