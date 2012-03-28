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
package de.cau.cs.kieler.klighd.krendering;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.merge.service.MergeService;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.service.DiffService;
import org.eclipse.emf.compare.match.MatchOptions;
import org.eclipse.emf.compare.match.engine.IMatchScope;
import org.eclipse.emf.compare.match.engine.IMatchScopeProvider;
import org.eclipse.emf.compare.match.filter.IResourceFilter;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.service.MatchService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingLibrary;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klighd.IUpdateStrategy;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * The update strategy for KGraph models with attached KRendering data.
 * 
 * @author mri
 */
public class UpdateStrategy implements IUpdateStrategy<KNode> {

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
//        serialize(new Path("/test/test/oldbase.kgraphx"), EcoreUtil.copy(baseModel));
//        serialize(new Path("/test/test/new.kgraphx"), EcoreUtil.copy(newModel));
        try {
            // match the base and the new model
            Map<String, Object> matchOptions = Maps.newHashMap();
            //matchOptions.put(MatchOptions.OPTION_DISTINCT_METAMODELS, false);
            matchOptions.put(MatchOptions.OPTION_MATCH_SCOPE_PROVIDER, scopeProvider);
            MatchModel match = MatchService.doMatch(baseModel, newModel, matchOptions);

            // compute differences
            DiffModel diff = DiffService.doDiff(match, false);
            
            // merge differences
            List<DiffElement> differences = new ArrayList<DiffElement>(diff.getOwnedElements());
            MergeService.merge(differences, false);
            
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to update KGraph");
        }
//        serialize(new Path("/test/test/newbase.kgraphx"), EcoreUtil.copy(baseModel));
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
    private class MatchScopeProvider implements IMatchScopeProvider {

        /** the match scope. */
        private IMatchScope scope = new MatchScope();
        
        /**
         * {@inheritDoc}
         */
        public IMatchScope getLeftScope() {
            return scope;
        }

        /**
         * {@inheritDoc}
         */
        public IMatchScope getRightScope() {
            return scope;
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
    private class MatchScope implements IMatchScope {

        /**
         * {@inheritDoc}
         */
        public boolean isInScope(final EObject eObject) {
            if (eObject instanceof KGraphData) {
                // exclude all graph data except rendering libraries and renderings
                return eObject instanceof KRendering || eObject instanceof KRenderingLibrary;
            }
            return true;
        }

        /**
         * {@inheritDoc}
         */
        public boolean isInScope(final Resource resource) {
            return true;
        }
        
    }
    
    private void serialize(final IPath path, final EObject object) {
        URI fileURI = URI.createPlatformResourceURI(path.toOSString(), true);
        
        ResourceSet set = new ResourceSetImpl();
        Resource resource = set.createResource(fileURI);

        if (object instanceof KNode) {
            KimlUtil.persistDataElements((KNode) object);   
        }
        resource.getContents().add(object);

        try {
            resource.save(Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        resource.unload();
    }

}
