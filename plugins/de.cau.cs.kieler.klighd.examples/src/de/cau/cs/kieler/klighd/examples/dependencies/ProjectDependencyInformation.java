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
package de.cau.cs.kieler.klighd.examples.dependencies;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.xtext.xbase.lib.Pair;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

/**
 * Collector class for some information on project dependencies.
 * 
 * @author uru
 */
public class ProjectDependencyInformation {

    private Multimap<IProject, String> projectDeps = HashMultimap.create();
    private Map<String, IProject> projectMap = Maps.newHashMap();

    public void addProject(IProject proj) {
        projectMap.put(proj.getName(), proj);
    }

    public void addDependency(IProject proj, String dep) {
        projectDeps.put(proj, dep);
    }

    public Iterable<IProject> getProjects() {
        return projectMap.values();
    }

    public Pair<List<IProject>, List<String>> getDependencies(IProject proj) {
        List<IProject> internalDeps = Lists.newLinkedList();
        List<String> externalDeps = Lists.newLinkedList();

        for (String dep : projectDeps.get(proj)) {
            IProject isItInternal = projectMap.get(dep);
            if (isItInternal != null) {
                internalDeps.add(isItInternal);
            } else {
                externalDeps.add(dep);
            }
        }

        return Pair.of(internalDeps, externalDeps);
    }
}
