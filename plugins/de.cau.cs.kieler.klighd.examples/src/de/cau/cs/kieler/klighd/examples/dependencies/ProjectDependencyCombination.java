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

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.osgi.util.ManifestElement;
import org.osgi.framework.Constants;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.triggers.PartTrigger;
import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.klighd.kivi.effects.KlighdUpdateDiagramEffect;

/**
 * Upon selection of {@link IProject}s gathers their dependency information and triggers a klighd
 * synthesis.
 * 
 * @author uru
 */
public class ProjectDependencyCombination extends AbstractCombination {

    public void execute(final PartTrigger.PartState es, final SelectionState selState) {

        if (this.latestState() == es || es.getEventType() == PartTrigger.EventType.VIEW_DEACTIVATED) {
            // dont react
            return;
        }

        List<?> elements = selState.getSelectionElements();

        ProjectDependencyInformation pdi = new ProjectDependencyInformation();

        for (Object o : elements) {

            if (!(o instanceof IProject)) {
                // if there is a non project element dont do anything
                return;
            } else {

                try {
                    IProject proj = ((IProject) o);
                    pdi.addProject(proj);

                    // retrieve the dependencies

                    // kind of ugly ..
                    String path = proj.getLocation().toString() + "/META-INF/MANIFEST.MF";
                    InputStream is = new FileInputStream(path);
                    Map<String, String> headers = Maps.newHashMap();
                    ManifestElement.parseBundleManifest(is, headers);

                    String require = headers.get(Constants.REQUIRE_BUNDLE);
                    if (require != null) {
                        for (String e : ManifestElement.getArrayFromList(require)) {
                            String onlyPlugin =
                                    e.substring(0,
                                            e.indexOf(";") != -1 ? e.indexOf(";") : e.length());
                            pdi.addDependency(proj, onlyPlugin.trim());
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        // schedule klighd
        this.schedule(new KlighdUpdateDiagramEffect("de.cau.cs.kieler.examples.dependencies",
                "Workspace Dependencies", pdi));

    }
}
