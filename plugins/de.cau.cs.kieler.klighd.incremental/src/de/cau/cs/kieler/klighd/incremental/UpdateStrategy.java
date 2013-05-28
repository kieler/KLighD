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
package de.cau.cs.kieler.klighd.incremental;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.merge.service.MergeService;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.service.DiffService;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.engine.IMatchScope;
import org.eclipse.emf.compare.match.engine.IMatchScopeProvider;
import org.eclipse.emf.compare.match.filter.IResourceFilter;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.IUpdateStrategy;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * The update strategy for KGraph models with attached KRendering data.
 * 
 * @author mri, chsch
 */
public class UpdateStrategy implements IUpdateStrategy<KNode> {

    /** The id used at registration of the strategy in the plugin.xml. */
    public static final String ID = UpdateStrategy.class.getCanonicalName();
    
    /** the priority for this update strategy. */
    public static final int PRIORITY = 20;
    
    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }
    
    /** the match scope provider for the EMF compare matching. */
    private MatchScopeProvider scopeProvider = new MatchScopeProvider();
    
    /**
     * {@inheritDoc}
     */
    public KNode getInitialBaseModel(final ViewContext viewContext) {
        KNode baseModel = KimlUtil.createInitializedNode();
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.createResource(URI.createURI("dummy.kgraph"));
        resource.getContents().add(baseModel);
        return baseModel;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final KNode baseModel, final KNode newModel, final ViewContext viewContext) {
        try {
            //TODO RESOURCE HACK
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource = resourceSet.createResource(URI.createURI("newDummy.kgraph"));
            resource.getContents().add(newModel);

            // DEBUG
            //Path path = new Path("/test2/diff.xmi");
            //System.out.println("baseModel begin");
            //serialize(path, EcoreUtil.copy(baseModel));
            //System.out.println("baseModel end\n");
            //System.out.println("newModel begin");
            //serialize(path, EcoreUtil.copy(newModel));
            //System.out.println("newModel end\n");
            
            // match the base and the new model
            Map<String, Object> matchOptions = Maps.newHashMap();
            //matchOptions.put(MatchOptions.OPTION_DISTINCT_METAMODELS, false);
            matchOptions.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER, scopeProvider);
            MatchModel match = MatchService.doMatch(baseModel, newModel, matchOptions);
            
            // compute differences
            DiffModel diff = DiffService.doDiff(match, false);
            
            // DEBUG           
            //System.out.println("Diff begin");
            //serialize(path, EcoreUtil.copy(diff));
            //System.out.println("Diff end\n");
            
            // merge differences
            MergeService.merge(diff.getOwnedElements(), false);

        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to update KGraph");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getModelClass() {
        return KNode.class;
    }
    
    /**
     * The match scope provider for the KGraph with KRendering and KLayoutData matching.
     */
    private static class MatchScopeProvider implements IMatchScopeProvider {

        /** the match scope. */
        private IMatchScope leftScope = new MatchScope();
        private IMatchScope rightScope = new MatchScope();
        
        /**
         * {@inheritDoc}
         */
        public IMatchScope getLeftScope() {
            return leftScope;
        }

        /**
         * {@inheritDoc}
         */
        public IMatchScope getRightScope() {
            return rightScope;
        }

        /**
         * {@inheritDoc}
         */
        public IMatchScope getAncestorScope() {
            // no three-way matching
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public void applyResourceFilter(final IResourceFilter filter) {
            // no resources involved
        }
        
    }
    
    /**
     * The match scope for the KGraph with KRendering and KLayoutData matching.
     */
    private static class MatchScope implements IMatchScope {

        /**
         * {@inheritDoc}
         */        
        public boolean isInScope(final EObject eObject) {
            int id = eObject.eClass().getClassifierID();

            EPackage p = eObject.eClass().getEPackage();

            if (p == KGraphPackage.eINSTANCE) {

                switch (id) {
                case KGraphPackage.KLABEL:
                case KGraphPackage.KNODE:
                case KGraphPackage.KEDGE:
                case KGraphPackage.KPORT:
                case KGraphPackage.IPROPERTY_TO_OBJECT_MAP:
                    return true;
                default: /* nothing */
                }
            } else if (p == KLayoutDataPackage.eINSTANCE) {

                switch (id) {
                case KLayoutDataPackage.KSHAPE_LAYOUT:
                case KLayoutDataPackage.KEDGE_LAYOUT:
                case KLayoutDataPackage.KINSETS:
                    return true;
                case KLayoutDataPackage.KPOINT:
                    // KPoint point = (KPoint) eObject;
                    return true; //(point.getX() == 0 && point.getY() == 0);
                default: /* nothing */
                }
            } else if (p == KRenderingPackage.eINSTANCE) {
                return true;
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        public boolean isInScope(final Resource resource) {
            return true;
        }
        
    }
    
    @SuppressWarnings("unused") // helper for debugging purposes!
    private void serialize(final IPath path, final EObject... objects) {
        URI fileURI = URI.createPlatformResourceURI(path.toOSString(), true);
        
        ResourceSet set = new ResourceSetImpl();
        Resource resource = set.createResource(fileURI);

        for (EObject object : objects) {
            if (object instanceof KNode) {
                KimlUtil.persistDataElements((KNode) object);
            }
            resource.getContents().add(object);
        }
        
        try {
            resource.save(System.out, Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        resource.unload();
    }

}
