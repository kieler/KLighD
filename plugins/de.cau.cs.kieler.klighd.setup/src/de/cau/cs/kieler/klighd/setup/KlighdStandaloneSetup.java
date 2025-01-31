/*
 * KLighD - KIELER Lightweight Diagrams
 *
 * https://github.com/Kieler/KLighD
 *
 * Copyright 2020-2024 by TypeFox GmbH (typefox.io) and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.setup;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.util.Collection;

import org.eclipse.elk.alg.force.options.ForceMetaDataProvider;
import org.eclipse.elk.alg.graphviz.layouter.GraphvizMetaDataProvider;
import org.eclipse.elk.alg.layered.options.LayeredMetaDataProvider;
import org.eclipse.elk.alg.mrtree.options.MrTreeMetaDataProvider;
import org.eclipse.elk.alg.radial.options.RadialMetaDataProvider;
import org.eclipse.elk.alg.vertiflex.options.VertiFlexMetaDataProvider;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.graph.util.ElkReflect;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.KlighdOptions;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption;

public class KlighdStandaloneSetup {

    public static void initialize() {
        new KlighdStandaloneSetup().doInitialize();
    }

    public void doInitialize() {
        EPackage.Registry.INSTANCE.put(KGraphPackage.eNS_URI, KGraphPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(KRenderingPackage.eNS_URI, KRenderingPackage.eINSTANCE);
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("kgx", new XMIResourceFactoryImpl());
        registerFonts();

        ElkReflect.register(ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData.class,
                () -> new ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData(),
                (v) -> new ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData(
                        (ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData) v));

        final Collection<ILayoutMetaDataProvider> providers = Lists.newArrayList(
                new KlighdOptions(),
                getForceMetaDataProvider(),
                getGraphvizMetaDataProvider(),
                getLayeredMetaDataProvider(),
                getMrTreeMetaDataProvider(),
                getRadialMetaDataProvider(),
                getVertiFlexDataProvider()
        );
        
        LayoutMetaDataService.getInstance().registerLayoutMetaDataProviders(
                Collections2.filter(providers, Predicates.notNull()).toArray(new ILayoutMetaDataProvider[0])
        );
        
        // Make sure that all extensions are loaded via forcing to execute the static code in KlighdDataManager.
        @SuppressWarnings("unused")
        KlighdDataManager _kdm = KlighdDataManager.getInstance();
    }

    protected ILayoutMetaDataProvider getForceMetaDataProvider() {
        try {
            return new ForceMetaDataProvider();
        } catch (Throwable t) {
            return null;
        }
    }

    protected ILayoutMetaDataProvider getGraphvizMetaDataProvider() {
        try {
            return new GraphvizMetaDataProvider();
        } catch (Throwable t) {
            return null;
        }
    }

    protected ILayoutMetaDataProvider getLayeredMetaDataProvider() {
        try {
            return new LayeredMetaDataProvider();
        } catch (Throwable t) {
            return null;
        }
    }

    protected ILayoutMetaDataProvider getMrTreeMetaDataProvider() {
        try {
            return new MrTreeMetaDataProvider();
        } catch (Throwable t) {
            return null;
        }
    }

    protected ILayoutMetaDataProvider getRadialMetaDataProvider() {
        try {
            return new RadialMetaDataProvider();
        } catch (Throwable t) {
            return null;
        }
    }
    
    protected ILayoutMetaDataProvider getVertiFlexDataProvider() {
        try {
            return new VertiFlexMetaDataProvider();
        } catch (Throwable t) {
            return null;
        }
    }
    
    /**
     * Registers all TrueType (.ttf) and OpenType (.otf) font files placed in the resources/fonts folder to the AWT
     * {@link GraphicsEnvironment} to be used as the default font in external/standalone applications.
     */
    protected void registerFonts() {
        String[] filePaths = {"/fonts/overpass-regular.otf", "/fonts/overpass-mono-regular.otf"};
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (int i = 0; i < filePaths.length; ++i) {
            try (InputStream fontStream = this.getClass().getResourceAsStream(filePaths[i])) {
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontStream));
            } catch (Throwable e) {
                System.out.println("could not load font file " + filePaths[i]);
            }
        }
    }
}
