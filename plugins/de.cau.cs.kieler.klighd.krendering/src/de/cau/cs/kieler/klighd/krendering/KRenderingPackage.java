/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.krendering;

import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * This package contains the classes of the KRendering language.<br>
 * This languages extends {@link KGraphPackage KGraph} and provides the means for describing the
 * figures the elements of a KGraph instance are depicted by. This involves
 * primitive figures to be composed to complex ones, <i>micro layout</i> directives,
 * styles, as well as actions for expressing interactivity of KGraph/KRendering-based diagrams.
 * <!-- end-model-doc -->
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingFactory
 * @model kind="package"
 * @generated
 */
public interface KRenderingPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "krendering";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://kieler.cs.cau.de/KRendering";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "krendering";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KRenderingPackage eINSTANCE = de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl.init();

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KPositionImpl <em>KPosition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KPositionImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPosition()
     * @generated
     */
    int KPOSITION = 0;

    /**
     * The feature id for the '<em><b>X</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOSITION__X = 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOSITION__Y = 1;

    /**
     * The number of structural features of the '<em>KPosition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOSITION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KStyleHolderImpl <em>KStyle Holder</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KStyleHolderImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKStyleHolder()
     * @generated
     */
    int KSTYLE_HOLDER = 46;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl <em>KRendering</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRendering()
     * @generated
     */
    int KRENDERING = 1;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING__PROPERTIES = KGraphPackage.KGRAPH_DATA__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING__PERSISTENT_ENTRIES = KGraphPackage.KGRAPH_DATA__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING__STYLES = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING__ID = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING__PARENT = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING__PLACEMENT_DATA = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING__ACTIONS = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>KRendering</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_FEATURE_COUNT = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KContainerRenderingImpl <em>KContainer Rendering</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KContainerRenderingImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKContainerRendering()
     * @generated
     */
    int KCONTAINER_RENDERING = 9;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__PROPERTIES = KRENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__PERSISTENT_ENTRIES = KRENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__STYLES = KRENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__ID = KRENDERING__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__PARENT = KRENDERING__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__PLACEMENT_DATA = KRENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__ACTIONS = KRENDERING__ACTIONS;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__CHILDREN = KRENDERING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__CHILD_PLACEMENT = KRENDERING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KContainer Rendering</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING_FEATURE_COUNT = KRENDERING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KEllipseImpl <em>KEllipse</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KEllipseImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKEllipse()
     * @generated
     */
    int KELLIPSE = 2;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__PROPERTIES = KCONTAINER_RENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__PERSISTENT_ENTRIES = KCONTAINER_RENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__STYLES = KCONTAINER_RENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__ID = KCONTAINER_RENDERING__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__ACTIONS = KCONTAINER_RENDERING__ACTIONS;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__CHILDREN = KCONTAINER_RENDERING__CHILDREN;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__CHILD_PLACEMENT = KCONTAINER_RENDERING__CHILD_PLACEMENT;

    /**
     * The number of structural features of the '<em>KEllipse</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRectangleImpl <em>KRectangle</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRectangleImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRectangle()
     * @generated
     */
    int KRECTANGLE = 3;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__PROPERTIES = KCONTAINER_RENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__PERSISTENT_ENTRIES = KCONTAINER_RENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__STYLES = KCONTAINER_RENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__ID = KCONTAINER_RENDERING__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__ACTIONS = KCONTAINER_RENDERING__ACTIONS;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__CHILDREN = KCONTAINER_RENDERING__CHILDREN;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__CHILD_PLACEMENT = KCONTAINER_RENDERING__CHILD_PLACEMENT;

    /**
     * The number of structural features of the '<em>KRectangle</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRoundedRectangleImpl <em>KRounded Rectangle</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRoundedRectangleImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRoundedRectangle()
     * @generated
     */
    int KROUNDED_RECTANGLE = 4;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__PROPERTIES = KCONTAINER_RENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__PERSISTENT_ENTRIES = KCONTAINER_RENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__STYLES = KCONTAINER_RENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__ID = KCONTAINER_RENDERING__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__ACTIONS = KCONTAINER_RENDERING__ACTIONS;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__CHILDREN = KCONTAINER_RENDERING__CHILDREN;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__CHILD_PLACEMENT = KCONTAINER_RENDERING__CHILD_PLACEMENT;

    /**
     * The feature id for the '<em><b>Corner Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__CORNER_WIDTH = KCONTAINER_RENDERING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Corner Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__CORNER_HEIGHT = KCONTAINER_RENDERING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KRounded Rectangle</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KPolylineImpl <em>KPolyline</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KPolylineImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPolyline()
     * @generated
     */
    int KPOLYLINE = 5;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__PROPERTIES = KCONTAINER_RENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__PERSISTENT_ENTRIES = KCONTAINER_RENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__STYLES = KCONTAINER_RENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__ID = KCONTAINER_RENDERING__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__ACTIONS = KCONTAINER_RENDERING__ACTIONS;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__CHILDREN = KCONTAINER_RENDERING__CHILDREN;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__CHILD_PLACEMENT = KCONTAINER_RENDERING__CHILD_PLACEMENT;

    /**
     * The feature id for the '<em><b>Points</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__POINTS = KCONTAINER_RENDERING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Junction Point Rendering</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__JUNCTION_POINT_RENDERING = KCONTAINER_RENDERING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KPolyline</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KPolygonImpl <em>KPolygon</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KPolygonImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPolygon()
     * @generated
     */
    int KPOLYGON = 6;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__PROPERTIES = KPOLYLINE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__PERSISTENT_ENTRIES = KPOLYLINE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__STYLES = KPOLYLINE__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__ID = KPOLYLINE__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__PARENT = KPOLYLINE__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__PLACEMENT_DATA = KPOLYLINE__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__ACTIONS = KPOLYLINE__ACTIONS;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__CHILDREN = KPOLYLINE__CHILDREN;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__CHILD_PLACEMENT = KPOLYLINE__CHILD_PLACEMENT;

    /**
     * The feature id for the '<em><b>Points</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__POINTS = KPOLYLINE__POINTS;

    /**
     * The feature id for the '<em><b>Junction Point Rendering</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__JUNCTION_POINT_RENDERING = KPOLYLINE__JUNCTION_POINT_RENDERING;

    /**
     * The number of structural features of the '<em>KPolygon</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON_FEATURE_COUNT = KPOLYLINE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KImageImpl <em>KImage</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KImageImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKImage()
     * @generated
     */
    int KIMAGE = 7;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__PROPERTIES = KCONTAINER_RENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__PERSISTENT_ENTRIES = KCONTAINER_RENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__STYLES = KCONTAINER_RENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__ID = KCONTAINER_RENDERING__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__ACTIONS = KCONTAINER_RENDERING__ACTIONS;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__CHILDREN = KCONTAINER_RENDERING__CHILDREN;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__CHILD_PLACEMENT = KCONTAINER_RENDERING__CHILD_PLACEMENT;

    /**
     * The feature id for the '<em><b>Bundle Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__BUNDLE_NAME = KCONTAINER_RENDERING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Image Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__IMAGE_PATH = KCONTAINER_RENDERING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Image Object</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__IMAGE_OBJECT = KCONTAINER_RENDERING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Clip Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__CLIP_SHAPE = KCONTAINER_RENDERING_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>KImage</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.KPlacementData <em>KPlacement Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.KPlacementData
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPlacementData()
     * @generated
     */
    int KPLACEMENT_DATA = 18;

    /**
     * The number of structural features of the '<em>KPlacement Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPLACEMENT_DATA_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KDecoratorPlacementDataImpl <em>KDecorator Placement Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KDecoratorPlacementDataImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKDecoratorPlacementData()
     * @generated
     */
    int KDECORATOR_PLACEMENT_DATA = 8;

    /**
     * The feature id for the '<em><b>Absolute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDECORATOR_PLACEMENT_DATA__ABSOLUTE = KPLACEMENT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>XOffset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDECORATOR_PLACEMENT_DATA__XOFFSET = KPLACEMENT_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>YOffset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDECORATOR_PLACEMENT_DATA__YOFFSET = KPLACEMENT_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Rotate With Line</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDECORATOR_PLACEMENT_DATA__ROTATE_WITH_LINE = KPLACEMENT_DATA_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDECORATOR_PLACEMENT_DATA__WIDTH = KPLACEMENT_DATA_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDECORATOR_PLACEMENT_DATA__HEIGHT = KPLACEMENT_DATA_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDECORATOR_PLACEMENT_DATA__RELATIVE = KPLACEMENT_DATA_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>KDecorator Placement Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDECORATOR_PLACEMENT_DATA_FEATURE_COUNT = KPLACEMENT_DATA_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KArcImpl <em>KArc</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KArcImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKArc()
     * @generated
     */
    int KARC = 10;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__PROPERTIES = KCONTAINER_RENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__PERSISTENT_ENTRIES = KCONTAINER_RENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__STYLES = KCONTAINER_RENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__ID = KCONTAINER_RENDERING__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__ACTIONS = KCONTAINER_RENDERING__ACTIONS;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__CHILDREN = KCONTAINER_RENDERING__CHILDREN;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__CHILD_PLACEMENT = KCONTAINER_RENDERING__CHILD_PLACEMENT;

    /**
     * The feature id for the '<em><b>Start Angle</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__START_ANGLE = KCONTAINER_RENDERING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Arc Angle</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__ARC_ANGLE = KCONTAINER_RENDERING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Arc Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__ARC_TYPE = KCONTAINER_RENDERING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>KArc</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KStyleImpl <em>KStyle</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KStyleImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKStyle()
     * @generated
     */
    int KSTYLE = 11;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE__PROPERTIES = KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE__PERSISTENT_ENTRIES = KGraphPackage.EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE__PROPAGATE_TO_CHILDREN = KGraphPackage.EMAP_PROPERTY_HOLDER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE__MODIFIER_ID = KGraphPackage.EMAP_PROPERTY_HOLDER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE__SELECTION = KGraphPackage.EMAP_PROPERTY_HOLDER_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>KStyle</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_FEATURE_COUNT = KGraphPackage.EMAP_PROPERTY_HOLDER_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingLibraryImpl <em>Library</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingLibraryImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRenderingLibrary()
     * @generated
     */
    int KRENDERING_LIBRARY = 12;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_LIBRARY__PROPERTIES = KGraphPackage.KGRAPH_DATA__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_LIBRARY__PERSISTENT_ENTRIES = KGraphPackage.KGRAPH_DATA__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Renderings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_LIBRARY__RENDERINGS = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Library</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_LIBRARY_FEATURE_COUNT = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingRefImpl <em>Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingRefImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRenderingRef()
     * @generated
     */
    int KRENDERING_REF = 13;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__PROPERTIES = KRENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__PERSISTENT_ENTRIES = KRENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__STYLES = KRENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__ID = KRENDERING__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__PARENT = KRENDERING__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__PLACEMENT_DATA = KRENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__ACTIONS = KRENDERING__ACTIONS;

    /**
     * The feature id for the '<em><b>Rendering</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__RENDERING = KRENDERING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Ref</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF_FEATURE_COUNT = KRENDERING_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KChildAreaImpl <em>KChild Area</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KChildAreaImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKChildArea()
     * @generated
     */
    int KCHILD_AREA = 14;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA__PROPERTIES = KRENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA__PERSISTENT_ENTRIES = KRENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA__STYLES = KRENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA__ID = KRENDERING__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA__PARENT = KRENDERING__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA__PLACEMENT_DATA = KRENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA__ACTIONS = KRENDERING__ACTIONS;

    /**
     * The number of structural features of the '<em>KChild Area</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA_FEATURE_COUNT = KRENDERING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KTextImpl <em>KText</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KTextImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKText()
     * @generated
     */
    int KTEXT = 15;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__PROPERTIES = KRENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__PERSISTENT_ENTRIES = KRENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__STYLES = KRENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__ID = KRENDERING__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__PARENT = KRENDERING__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__PLACEMENT_DATA = KRENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__ACTIONS = KRENDERING__ACTIONS;

    /**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__TEXT = KRENDERING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Cursor Selectable</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__CURSOR_SELECTABLE = KRENDERING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Editable</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__EDITABLE = KRENDERING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>KText</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_FEATURE_COUNT = KRENDERING_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.KPlacement <em>KPlacement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.KPlacement
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPlacement()
     * @generated
     */
    int KPLACEMENT = 16;

    /**
     * The number of structural features of the '<em>KPlacement</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPLACEMENT_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KGridPlacementImpl <em>KGrid Placement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KGridPlacementImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKGridPlacement()
     * @generated
     */
    int KGRID_PLACEMENT = 17;

    /**
     * The feature id for the '<em><b>Num Columns</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT__NUM_COLUMNS = KPLACEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Top Left</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT__TOP_LEFT = KPLACEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Bottom Right</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT__BOTTOM_RIGHT = KPLACEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>KGrid Placement</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_FEATURE_COUNT = KPLACEMENT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KAreaPlacementDataImpl <em>KArea Placement Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KAreaPlacementDataImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKAreaPlacementData()
     * @generated
     */
    int KAREA_PLACEMENT_DATA = 20;

    /**
     * The feature id for the '<em><b>Top Left</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KAREA_PLACEMENT_DATA__TOP_LEFT = KPLACEMENT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Bottom Right</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KAREA_PLACEMENT_DATA__BOTTOM_RIGHT = KPLACEMENT_DATA_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KArea Placement Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KAREA_PLACEMENT_DATA_FEATURE_COUNT = KPLACEMENT_DATA_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KGridPlacementDataImpl <em>KGrid Placement Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KGridPlacementDataImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKGridPlacementData()
     * @generated
     */
    int KGRID_PLACEMENT_DATA = 19;

    /**
     * The feature id for the '<em><b>Top Left</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA__TOP_LEFT = KAREA_PLACEMENT_DATA__TOP_LEFT;

    /**
     * The feature id for the '<em><b>Bottom Right</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA__BOTTOM_RIGHT = KAREA_PLACEMENT_DATA__BOTTOM_RIGHT;

    /**
     * The feature id for the '<em><b>Min Cell Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA__MIN_CELL_WIDTH = KAREA_PLACEMENT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Min Cell Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA__MIN_CELL_HEIGHT = KAREA_PLACEMENT_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Flexible Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA__FLEXIBLE_WIDTH = KAREA_PLACEMENT_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Flexible Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA__FLEXIBLE_HEIGHT = KAREA_PLACEMENT_DATA_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>KGrid Placement Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA_FEATURE_COUNT = KAREA_PLACEMENT_DATA_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KCustomRenderingImpl <em>KCustom Rendering</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KCustomRenderingImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKCustomRendering()
     * @generated
     */
    int KCUSTOM_RENDERING = 21;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__PROPERTIES = KCONTAINER_RENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__PERSISTENT_ENTRIES = KCONTAINER_RENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__STYLES = KCONTAINER_RENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__ID = KCONTAINER_RENDERING__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__ACTIONS = KCONTAINER_RENDERING__ACTIONS;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__CHILDREN = KCONTAINER_RENDERING__CHILDREN;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__CHILD_PLACEMENT = KCONTAINER_RENDERING__CHILD_PLACEMENT;

    /**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__CLASS_NAME = KCONTAINER_RENDERING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Bundle Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__BUNDLE_NAME = KCONTAINER_RENDERING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Figure Object</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__FIGURE_OBJECT = KCONTAINER_RENDERING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>KCustom Rendering</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KColorImpl <em>KColor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KColorImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKColor()
     * @generated
     */
    int KCOLOR = 22;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLOR__RED = 0;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLOR__GREEN = 1;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLOR__BLUE = 2;

    /**
     * The number of structural features of the '<em>KColor</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLOR_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KLineWidthImpl <em>KLine Width</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KLineWidthImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKLineWidth()
     * @generated
     */
    int KLINE_WIDTH = 23;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_WIDTH__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_WIDTH__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_WIDTH__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_WIDTH__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_WIDTH__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Line Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_WIDTH__LINE_WIDTH = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KLine Width</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_WIDTH_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KLineStyleImpl <em>KLine Style</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KLineStyleImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKLineStyle()
     * @generated
     */
    int KLINE_STYLE = 24;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE__LINE_STYLE = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Dash Pattern</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE__DASH_PATTERN = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Dash Offset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE__DASH_OFFSET = KSTYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>KLine Style</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KVerticalAlignmentImpl <em>KVertical Alignment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KVerticalAlignmentImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKVerticalAlignment()
     * @generated
     */
    int KVERTICAL_ALIGNMENT = 25;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVERTICAL_ALIGNMENT__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVERTICAL_ALIGNMENT__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVERTICAL_ALIGNMENT__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVERTICAL_ALIGNMENT__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVERTICAL_ALIGNMENT__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Vertical Alignment</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVERTICAL_ALIGNMENT__VERTICAL_ALIGNMENT = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KVertical Alignment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVERTICAL_ALIGNMENT_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KHorizontalAlignmentImpl <em>KHorizontal Alignment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KHorizontalAlignmentImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKHorizontalAlignment()
     * @generated
     */
    int KHORIZONTAL_ALIGNMENT = 26;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KHORIZONTAL_ALIGNMENT__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KHORIZONTAL_ALIGNMENT__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KHORIZONTAL_ALIGNMENT__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KHORIZONTAL_ALIGNMENT__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KHORIZONTAL_ALIGNMENT__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Horizontal Alignment</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KHORIZONTAL_ALIGNMENT__HORIZONTAL_ALIGNMENT = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KHorizontal Alignment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KHORIZONTAL_ALIGNMENT_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KXPositionImpl <em>KX Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KXPositionImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKXPosition()
     * @generated
     */
    int KX_POSITION = 27;

    /**
     * The feature id for the '<em><b>Absolute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KX_POSITION__ABSOLUTE = 0;

    /**
     * The feature id for the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KX_POSITION__RELATIVE = 1;

    /**
     * The number of structural features of the '<em>KX Position</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KX_POSITION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KYPositionImpl <em>KY Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KYPositionImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKYPosition()
     * @generated
     */
    int KY_POSITION = 28;

    /**
     * The feature id for the '<em><b>Absolute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KY_POSITION__ABSOLUTE = 0;

    /**
     * The feature id for the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KY_POSITION__RELATIVE = 1;

    /**
     * The number of structural features of the '<em>KY Position</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KY_POSITION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KLeftPositionImpl <em>KLeft Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KLeftPositionImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKLeftPosition()
     * @generated
     */
    int KLEFT_POSITION = 29;

    /**
     * The feature id for the '<em><b>Absolute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLEFT_POSITION__ABSOLUTE = KX_POSITION__ABSOLUTE;

    /**
     * The feature id for the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLEFT_POSITION__RELATIVE = KX_POSITION__RELATIVE;

    /**
     * The number of structural features of the '<em>KLeft Position</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLEFT_POSITION_FEATURE_COUNT = KX_POSITION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRightPositionImpl <em>KRight Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRightPositionImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRightPosition()
     * @generated
     */
    int KRIGHT_POSITION = 30;

    /**
     * The feature id for the '<em><b>Absolute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRIGHT_POSITION__ABSOLUTE = KX_POSITION__ABSOLUTE;

    /**
     * The feature id for the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRIGHT_POSITION__RELATIVE = KX_POSITION__RELATIVE;

    /**
     * The number of structural features of the '<em>KRight Position</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRIGHT_POSITION_FEATURE_COUNT = KX_POSITION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KTopPositionImpl <em>KTop Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KTopPositionImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKTopPosition()
     * @generated
     */
    int KTOP_POSITION = 31;

    /**
     * The feature id for the '<em><b>Absolute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTOP_POSITION__ABSOLUTE = KY_POSITION__ABSOLUTE;

    /**
     * The feature id for the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTOP_POSITION__RELATIVE = KY_POSITION__RELATIVE;

    /**
     * The number of structural features of the '<em>KTop Position</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTOP_POSITION_FEATURE_COUNT = KY_POSITION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KBottomPositionImpl <em>KBottom Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KBottomPositionImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKBottomPosition()
     * @generated
     */
    int KBOTTOM_POSITION = 32;

    /**
     * The feature id for the '<em><b>Absolute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBOTTOM_POSITION__ABSOLUTE = KY_POSITION__ABSOLUTE;

    /**
     * The feature id for the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBOTTOM_POSITION__RELATIVE = KY_POSITION__RELATIVE;

    /**
     * The number of structural features of the '<em>KBottom Position</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBOTTOM_POSITION_FEATURE_COUNT = KY_POSITION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KSplineImpl <em>KSpline</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KSplineImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKSpline()
     * @generated
     */
    int KSPLINE = 33;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__PROPERTIES = KPOLYLINE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__PERSISTENT_ENTRIES = KPOLYLINE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__STYLES = KPOLYLINE__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__ID = KPOLYLINE__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__PARENT = KPOLYLINE__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__PLACEMENT_DATA = KPOLYLINE__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__ACTIONS = KPOLYLINE__ACTIONS;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__CHILDREN = KPOLYLINE__CHILDREN;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__CHILD_PLACEMENT = KPOLYLINE__CHILD_PLACEMENT;

    /**
     * The feature id for the '<em><b>Points</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__POINTS = KPOLYLINE__POINTS;

    /**
     * The feature id for the '<em><b>Junction Point Rendering</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__JUNCTION_POINT_RENDERING = KPOLYLINE__JUNCTION_POINT_RENDERING;

    /**
     * The number of structural features of the '<em>KSpline</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE_FEATURE_COUNT = KPOLYLINE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KColoringImpl <em>KColoring</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KColoringImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKColoring()
     * @generated
     */
    int KCOLORING = 35;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLORING__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLORING__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLORING__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLORING__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLORING__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLORING__COLOR = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Alpha</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLORING__ALPHA = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLORING__TARGET_COLOR = KSTYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target Alpha</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLORING__TARGET_ALPHA = KSTYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Gradient Angle</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLORING__GRADIENT_ANGLE = KSTYLE_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>KColoring</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLORING_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KForegroundImpl <em>KForeground</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KForegroundImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKForeground()
     * @generated
     */
    int KFOREGROUND = 34;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND__PROPERTIES = KCOLORING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND__PERSISTENT_ENTRIES = KCOLORING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND__PROPAGATE_TO_CHILDREN = KCOLORING__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND__MODIFIER_ID = KCOLORING__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND__SELECTION = KCOLORING__SELECTION;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND__COLOR = KCOLORING__COLOR;

    /**
     * The feature id for the '<em><b>Alpha</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND__ALPHA = KCOLORING__ALPHA;

    /**
     * The feature id for the '<em><b>Target Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND__TARGET_COLOR = KCOLORING__TARGET_COLOR;

    /**
     * The feature id for the '<em><b>Target Alpha</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND__TARGET_ALPHA = KCOLORING__TARGET_ALPHA;

    /**
     * The feature id for the '<em><b>Gradient Angle</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND__GRADIENT_ANGLE = KCOLORING__GRADIENT_ANGLE;

    /**
     * The number of structural features of the '<em>KForeground</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND_FEATURE_COUNT = KCOLORING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KBackgroundImpl <em>KBackground</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KBackgroundImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKBackground()
     * @generated
     */
    int KBACKGROUND = 36;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND__PROPERTIES = KCOLORING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND__PERSISTENT_ENTRIES = KCOLORING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND__PROPAGATE_TO_CHILDREN = KCOLORING__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND__MODIFIER_ID = KCOLORING__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND__SELECTION = KCOLORING__SELECTION;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND__COLOR = KCOLORING__COLOR;

    /**
     * The feature id for the '<em><b>Alpha</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND__ALPHA = KCOLORING__ALPHA;

    /**
     * The feature id for the '<em><b>Target Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND__TARGET_COLOR = KCOLORING__TARGET_COLOR;

    /**
     * The feature id for the '<em><b>Target Alpha</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND__TARGET_ALPHA = KCOLORING__TARGET_ALPHA;

    /**
     * The feature id for the '<em><b>Gradient Angle</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND__GRADIENT_ANGLE = KCOLORING__GRADIENT_ANGLE;

    /**
     * The number of structural features of the '<em>KBackground</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND_FEATURE_COUNT = KCOLORING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KFontBoldImpl <em>KFont Bold</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KFontBoldImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKFontBold()
     * @generated
     */
    int KFONT_BOLD = 37;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_BOLD__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_BOLD__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_BOLD__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_BOLD__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_BOLD__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Bold</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_BOLD__BOLD = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KFont Bold</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_BOLD_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KFontItalicImpl <em>KFont Italic</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KFontItalicImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKFontItalic()
     * @generated
     */
    int KFONT_ITALIC = 38;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_ITALIC__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_ITALIC__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_ITALIC__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_ITALIC__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_ITALIC__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Italic</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_ITALIC__ITALIC = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KFont Italic</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_ITALIC_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KFontNameImpl <em>KFont Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KFontNameImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKFontName()
     * @generated
     */
    int KFONT_NAME = 39;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_NAME__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_NAME__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_NAME__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_NAME__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_NAME__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_NAME__NAME = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KFont Name</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_NAME_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KFontSizeImpl <em>KFont Size</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KFontSizeImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKFontSize()
     * @generated
     */
    int KFONT_SIZE = 40;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_SIZE__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_SIZE__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_SIZE__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_SIZE__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_SIZE__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_SIZE__SIZE = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Scale With Zoom</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_SIZE__SCALE_WITH_ZOOM = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KFont Size</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFONT_SIZE_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRoundedBendsPolylineImpl <em>KRounded Bends Polyline</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRoundedBendsPolylineImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRoundedBendsPolyline()
     * @generated
     */
    int KROUNDED_BENDS_POLYLINE = 41;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__PROPERTIES = KPOLYLINE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__PERSISTENT_ENTRIES = KPOLYLINE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__STYLES = KPOLYLINE__STYLES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__ID = KPOLYLINE__ID;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__PARENT = KPOLYLINE__PARENT;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__PLACEMENT_DATA = KPOLYLINE__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__ACTIONS = KPOLYLINE__ACTIONS;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__CHILDREN = KPOLYLINE__CHILDREN;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__CHILD_PLACEMENT = KPOLYLINE__CHILD_PLACEMENT;

    /**
     * The feature id for the '<em><b>Points</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__POINTS = KPOLYLINE__POINTS;

    /**
     * The feature id for the '<em><b>Junction Point Rendering</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__JUNCTION_POINT_RENDERING = KPOLYLINE__JUNCTION_POINT_RENDERING;

    /**
     * The feature id for the '<em><b>Bend Radius</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE__BEND_RADIUS = KPOLYLINE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KRounded Bends Polyline</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_BENDS_POLYLINE_FEATURE_COUNT = KPOLYLINE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRotationImpl <em>KRotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRotationImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRotation()
     * @generated
     */
    int KROTATION = 42;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROTATION__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROTATION__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROTATION__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROTATION__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROTATION__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Rotation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROTATION__ROTATION = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Rotation Anchor</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROTATION__ROTATION_ANCHOR = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KRotation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROTATION_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KLineCapImpl <em>KLine Cap</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KLineCapImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKLineCap()
     * @generated
     */
    int KLINE_CAP = 43;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_CAP__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_CAP__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_CAP__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_CAP__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_CAP__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Line Cap</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_CAP__LINE_CAP = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KLine Cap</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_CAP_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KActionImpl <em>KAction</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KActionImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKAction()
     * @generated
     */
    int KACTION = 44;

    /**
     * The feature id for the '<em><b>Action Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KACTION__ACTION_ID = 0;

    /**
     * The feature id for the '<em><b>Trigger</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KACTION__TRIGGER = 1;

    /**
     * The feature id for the '<em><b>Alt Pressed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KACTION__ALT_PRESSED = 2;

    /**
     * The feature id for the '<em><b>Ctrl Cmd Pressed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KACTION__CTRL_CMD_PRESSED = 3;

    /**
     * The feature id for the '<em><b>Shift Pressed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KACTION__SHIFT_PRESSED = 4;

    /**
     * The number of structural features of the '<em>KAction</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KACTION_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KPointPlacementDataImpl <em>KPoint Placement Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KPointPlacementDataImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPointPlacementData()
     * @generated
     */
    int KPOINT_PLACEMENT_DATA = 45;

    /**
     * The feature id for the '<em><b>Reference Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOINT_PLACEMENT_DATA__REFERENCE_POINT = KPLACEMENT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Horizontal Alignment</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOINT_PLACEMENT_DATA__HORIZONTAL_ALIGNMENT = KPLACEMENT_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Vertical Alignment</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOINT_PLACEMENT_DATA__VERTICAL_ALIGNMENT = KPLACEMENT_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Horizontal Margin</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOINT_PLACEMENT_DATA__HORIZONTAL_MARGIN = KPLACEMENT_DATA_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Vertical Margin</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOINT_PLACEMENT_DATA__VERTICAL_MARGIN = KPLACEMENT_DATA_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Min Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOINT_PLACEMENT_DATA__MIN_WIDTH = KPLACEMENT_DATA_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Min Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOINT_PLACEMENT_DATA__MIN_HEIGHT = KPLACEMENT_DATA_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>KPoint Placement Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOINT_PLACEMENT_DATA_FEATURE_COUNT = KPLACEMENT_DATA_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_HOLDER__STYLES = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_HOLDER__ID = 1;

    /**
     * The number of structural features of the '<em>KStyle Holder</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_HOLDER_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KInvisibilityImpl <em>KInvisibility</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KInvisibilityImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKInvisibility()
     * @generated
     */
    int KINVISIBILITY = 47;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINVISIBILITY__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINVISIBILITY__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINVISIBILITY__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINVISIBILITY__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINVISIBILITY__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Invisible</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINVISIBILITY__INVISIBLE = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KInvisibility</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KINVISIBILITY_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KShadowImpl <em>KShadow</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KShadowImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKShadow()
     * @generated
     */
    int KSHADOW = 48;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHADOW__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHADOW__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHADOW__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHADOW__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHADOW__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>XOffset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHADOW__XOFFSET = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>YOffset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHADOW__YOFFSET = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Blur</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHADOW__BLUR = KSTYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHADOW__COLOR = KSTYLE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>KShadow</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSHADOW_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KTextUnderlineImpl <em>KText Underline</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KTextUnderlineImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKTextUnderline()
     * @generated
     */
    int KTEXT_UNDERLINE = 49;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_UNDERLINE__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_UNDERLINE__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_UNDERLINE__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_UNDERLINE__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_UNDERLINE__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Underline</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_UNDERLINE__UNDERLINE = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_UNDERLINE__COLOR = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KText Underline</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_UNDERLINE_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KStyleRefImpl <em>KStyle Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KStyleRefImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKStyleRef()
     * @generated
     */
    int KSTYLE_REF = 50;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_REF__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_REF__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_REF__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_REF__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_REF__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Style Holder</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_REF__STYLE_HOLDER = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Referenced Types</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_REF__REFERENCED_TYPES = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KStyle Ref</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_REF_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KTextStrikeoutImpl <em>KText Strikeout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KTextStrikeoutImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKTextStrikeout()
     * @generated
     */
    int KTEXT_STRIKEOUT = 51;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_STRIKEOUT__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_STRIKEOUT__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_STRIKEOUT__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_STRIKEOUT__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_STRIKEOUT__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Struck Out</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_STRIKEOUT__STRUCK_OUT = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_STRIKEOUT__COLOR = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KText Strikeout</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_STRIKEOUT_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KLineJoinImpl <em>KLine Join</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.impl.KLineJoinImpl
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKLineJoin()
     * @generated
     */
    int KLINE_JOIN = 52;

    /**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_JOIN__PROPERTIES = KSTYLE__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_JOIN__PERSISTENT_ENTRIES = KSTYLE__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_JOIN__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Modifier Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_JOIN__MODIFIER_ID = KSTYLE__MODIFIER_ID;

    /**
     * The feature id for the '<em><b>Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_JOIN__SELECTION = KSTYLE__SELECTION;

    /**
     * The feature id for the '<em><b>Line Join</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_JOIN__LINE_JOIN = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Miter Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_JOIN__MITER_LIMIT = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KLine Join</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_JOIN_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.LineStyle <em>Line Style</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.LineStyle
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getLineStyle()
     * @generated
     */
    int LINE_STYLE = 53;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.HorizontalAlignment <em>Horizontal Alignment</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.HorizontalAlignment
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getHorizontalAlignment()
     * @generated
     */
    int HORIZONTAL_ALIGNMENT = 54;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.VerticalAlignment <em>Vertical Alignment</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.VerticalAlignment
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getVerticalAlignment()
     * @generated
     */
    int VERTICAL_ALIGNMENT = 55;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.LineCap <em>Line Cap</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.LineCap
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getLineCap()
     * @generated
     */
    int LINE_CAP = 56;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.ModifierState <em>Modifier State</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.ModifierState
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getModifierState()
     * @generated
     */
    int MODIFIER_STATE = 57;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.Trigger <em>Trigger</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.Trigger
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getTrigger()
     * @generated
     */
    int TRIGGER = 58;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.Underline <em>Underline</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.Underline
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getUnderline()
     * @generated
     */
    int UNDERLINE = 59;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.LineJoin <em>Line Join</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.LineJoin
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getLineJoin()
     * @generated
     */
    int LINE_JOIN = 60;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.krendering.Arc <em>Arc</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.Arc
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getArc()
     * @generated
     */
    int ARC = 61;

    /**
     * The meta object id for the '<em>Colors</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.krendering.Colors
     * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getColors()
     * @generated
     */
    int COLORS = 62;


    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KPosition <em>KPosition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPosition</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPosition
     * @generated
     */
    EClass getKPosition();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KPosition#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>X</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPosition#getX()
     * @see #getKPosition()
     * @generated
     */
    EReference getKPosition_X();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KPosition#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Y</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPosition#getY()
     * @see #getKPosition()
     * @generated
     */
    EReference getKPosition_Y();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KRendering <em>KRendering</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KRendering</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRendering
     * @generated
     */
    EClass getKRendering();

    /**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.klighd.krendering.KRendering#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRendering#getParent()
     * @see #getKRendering()
     * @generated
     */
    EReference getKRendering_Parent();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KRendering#getPlacementData <em>Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Placement Data</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRendering#getPlacementData()
     * @see #getKRendering()
     * @generated
     */
    EReference getKRendering_PlacementData();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.krendering.KRendering#getActions <em>Actions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRendering#getActions()
     * @see #getKRendering()
     * @generated
     */
    EReference getKRendering_Actions();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KEllipse <em>KEllipse</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KEllipse</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KEllipse
     * @generated
     */
    EClass getKEllipse();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KRectangle <em>KRectangle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KRectangle</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRectangle
     * @generated
     */
    EClass getKRectangle();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KRoundedRectangle <em>KRounded Rectangle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KRounded Rectangle</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRoundedRectangle
     * @generated
     */
    EClass getKRoundedRectangle();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KRoundedRectangle#getCornerWidth <em>Corner Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Corner Width</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRoundedRectangle#getCornerWidth()
     * @see #getKRoundedRectangle()
     * @generated
     */
    EAttribute getKRoundedRectangle_CornerWidth();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KRoundedRectangle#getCornerHeight <em>Corner Height</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Corner Height</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRoundedRectangle#getCornerHeight()
     * @see #getKRoundedRectangle()
     * @generated
     */
    EAttribute getKRoundedRectangle_CornerHeight();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KPolyline <em>KPolyline</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPolyline</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPolyline
     * @generated
     */
    EClass getKPolyline();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.krendering.KPolyline#getPoints <em>Points</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Points</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPolyline#getPoints()
     * @see #getKPolyline()
     * @generated
     */
    EReference getKPolyline_Points();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KPolyline#getJunctionPointRendering <em>Junction Point Rendering</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Junction Point Rendering</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPolyline#getJunctionPointRendering()
     * @see #getKPolyline()
     * @generated
     */
    EReference getKPolyline_JunctionPointRendering();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KPolygon <em>KPolygon</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPolygon</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPolygon
     * @generated
     */
    EClass getKPolygon();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KImage <em>KImage</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KImage</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KImage
     * @generated
     */
    EClass getKImage();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KImage#getBundleName <em>Bundle Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bundle Name</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KImage#getBundleName()
     * @see #getKImage()
     * @generated
     */
    EAttribute getKImage_BundleName();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KImage#getImagePath <em>Image Path</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Image Path</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KImage#getImagePath()
     * @see #getKImage()
     * @generated
     */
    EAttribute getKImage_ImagePath();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KImage#getImageObject <em>Image Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Image Object</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KImage#getImageObject()
     * @see #getKImage()
     * @generated
     */
    EAttribute getKImage_ImageObject();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KImage#getClipShape <em>Clip Shape</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Clip Shape</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KImage#getClipShape()
     * @see #getKImage()
     * @generated
     */
    EReference getKImage_ClipShape();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData <em>KDecorator Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KDecorator Placement Data</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData
     * @generated
     */
    EClass getKDecoratorPlacementData();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getAbsolute <em>Absolute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Absolute</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getAbsolute()
     * @see #getKDecoratorPlacementData()
     * @generated
     */
    EAttribute getKDecoratorPlacementData_Absolute();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getXOffset <em>XOffset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>XOffset</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getXOffset()
     * @see #getKDecoratorPlacementData()
     * @generated
     */
    EAttribute getKDecoratorPlacementData_XOffset();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getYOffset <em>YOffset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>YOffset</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getYOffset()
     * @see #getKDecoratorPlacementData()
     * @generated
     */
    EAttribute getKDecoratorPlacementData_YOffset();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#isRotateWithLine <em>Rotate With Line</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Rotate With Line</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#isRotateWithLine()
     * @see #getKDecoratorPlacementData()
     * @generated
     */
    EAttribute getKDecoratorPlacementData_RotateWithLine();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getWidth()
     * @see #getKDecoratorPlacementData()
     * @generated
     */
    EAttribute getKDecoratorPlacementData_Width();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getHeight()
     * @see #getKDecoratorPlacementData()
     * @generated
     */
    EAttribute getKDecoratorPlacementData_Height();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getRelative <em>Relative</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relative</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getRelative()
     * @see #getKDecoratorPlacementData()
     * @generated
     */
    EAttribute getKDecoratorPlacementData_Relative();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KContainerRendering <em>KContainer Rendering</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KContainer Rendering</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KContainerRendering
     * @generated
     */
    EClass getKContainerRendering();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.krendering.KContainerRendering#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KContainerRendering#getChildren()
     * @see #getKContainerRendering()
     * @generated
     */
    EReference getKContainerRendering_Children();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KContainerRendering#getChildPlacement <em>Child Placement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Child Placement</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KContainerRendering#getChildPlacement()
     * @see #getKContainerRendering()
     * @generated
     */
    EReference getKContainerRendering_ChildPlacement();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KArc <em>KArc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KArc</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KArc
     * @generated
     */
    EClass getKArc();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KArc#getStartAngle <em>Start Angle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Start Angle</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KArc#getStartAngle()
     * @see #getKArc()
     * @generated
     */
    EAttribute getKArc_StartAngle();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KArc#getArcAngle <em>Arc Angle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Arc Angle</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KArc#getArcAngle()
     * @see #getKArc()
     * @generated
     */
    EAttribute getKArc_ArcAngle();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KArc#getArcType <em>Arc Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Arc Type</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KArc#getArcType()
     * @see #getKArc()
     * @generated
     */
    EAttribute getKArc_ArcType();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KStyle <em>KStyle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KStyle</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KStyle
     * @generated
     */
    EClass getKStyle();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KStyle#isPropagateToChildren <em>Propagate To Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Propagate To Children</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KStyle#isPropagateToChildren()
     * @see #getKStyle()
     * @generated
     */
    EAttribute getKStyle_PropagateToChildren();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KStyle#getModifierId <em>Modifier Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Modifier Id</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KStyle#getModifierId()
     * @see #getKStyle()
     * @generated
     */
    EAttribute getKStyle_ModifierId();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KStyle#isSelection <em>Selection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Selection</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KStyle#isSelection()
     * @see #getKStyle()
     * @generated
     */
    EAttribute getKStyle_Selection();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KRenderingLibrary <em>Library</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Library</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
     * @generated
     */
    EClass getKRenderingLibrary();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.krendering.KRenderingLibrary#getRenderings <em>Renderings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Renderings</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingLibrary#getRenderings()
     * @see #getKRenderingLibrary()
     * @generated
     */
    EReference getKRenderingLibrary_Renderings();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KRenderingRef <em>Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Ref</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingRef
     * @generated
     */
    EClass getKRenderingRef();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.klighd.krendering.KRenderingRef#getRendering <em>Rendering</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Rendering</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingRef#getRendering()
     * @see #getKRenderingRef()
     * @generated
     */
    EReference getKRenderingRef_Rendering();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KChildArea <em>KChild Area</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KChild Area</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KChildArea
     * @generated
     */
    EClass getKChildArea();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KText <em>KText</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KText</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KText
     * @generated
     */
    EClass getKText();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KText#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KText#getText()
     * @see #getKText()
     * @generated
     */
    EAttribute getKText_Text();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KText#isCursorSelectable <em>Cursor Selectable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Cursor Selectable</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KText#isCursorSelectable()
     * @see #getKText()
     * @generated
     */
    EAttribute getKText_CursorSelectable();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KText#isEditable <em>Editable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Editable</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KText#isEditable()
     * @see #getKText()
     * @generated
     */
    EAttribute getKText_Editable();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KPlacement <em>KPlacement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPlacement</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPlacement
     * @generated
     */
    EClass getKPlacement();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacement <em>KGrid Placement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KGrid Placement</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KGridPlacement
     * @generated
     */
    EClass getKGridPlacement();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacement#getNumColumns <em>Num Columns</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Num Columns</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KGridPlacement#getNumColumns()
     * @see #getKGridPlacement()
     * @generated
     */
    EAttribute getKGridPlacement_NumColumns();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacement#getTopLeft <em>Top Left</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Top Left</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KGridPlacement#getTopLeft()
     * @see #getKGridPlacement()
     * @generated
     */
    EReference getKGridPlacement_TopLeft();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacement#getBottomRight <em>Bottom Right</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Bottom Right</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KGridPlacement#getBottomRight()
     * @see #getKGridPlacement()
     * @generated
     */
    EReference getKGridPlacement_BottomRight();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KPlacementData <em>KPlacement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPlacement Data</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPlacementData
     * @generated
     */
    EClass getKPlacementData();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData <em>KGrid Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KGrid Placement Data</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KGridPlacementData
     * @generated
     */
    EClass getKGridPlacementData();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getMinCellWidth <em>Min Cell Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Min Cell Width</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getMinCellWidth()
     * @see #getKGridPlacementData()
     * @generated
     */
    EAttribute getKGridPlacementData_MinCellWidth();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getMinCellHeight <em>Min Cell Height</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Min Cell Height</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getMinCellHeight()
     * @see #getKGridPlacementData()
     * @generated
     */
    EAttribute getKGridPlacementData_MinCellHeight();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getFlexibleWidth <em>Flexible Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Flexible Width</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getFlexibleWidth()
     * @see #getKGridPlacementData()
     * @generated
     */
    EAttribute getKGridPlacementData_FlexibleWidth();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getFlexibleHeight <em>Flexible Height</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Flexible Height</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getFlexibleHeight()
     * @see #getKGridPlacementData()
     * @generated
     */
    EAttribute getKGridPlacementData_FlexibleHeight();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KAreaPlacementData <em>KArea Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KArea Placement Data</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KAreaPlacementData
     * @generated
     */
    EClass getKAreaPlacementData();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KAreaPlacementData#getTopLeft <em>Top Left</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Top Left</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KAreaPlacementData#getTopLeft()
     * @see #getKAreaPlacementData()
     * @generated
     */
    EReference getKAreaPlacementData_TopLeft();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KAreaPlacementData#getBottomRight <em>Bottom Right</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Bottom Right</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KAreaPlacementData#getBottomRight()
     * @see #getKAreaPlacementData()
     * @generated
     */
    EReference getKAreaPlacementData_BottomRight();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KCustomRendering <em>KCustom Rendering</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KCustom Rendering</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KCustomRendering
     * @generated
     */
    EClass getKCustomRendering();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KCustomRendering#getClassName <em>Class Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Class Name</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KCustomRendering#getClassName()
     * @see #getKCustomRendering()
     * @generated
     */
    EAttribute getKCustomRendering_ClassName();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KCustomRendering#getBundleName <em>Bundle Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bundle Name</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KCustomRendering#getBundleName()
     * @see #getKCustomRendering()
     * @generated
     */
    EAttribute getKCustomRendering_BundleName();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KCustomRendering#getFigureObject <em>Figure Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Figure Object</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KCustomRendering#getFigureObject()
     * @see #getKCustomRendering()
     * @generated
     */
    EAttribute getKCustomRendering_FigureObject();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KColor <em>KColor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KColor</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KColor
     * @generated
     */
    EClass getKColor();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KColor#getRed <em>Red</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Red</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KColor#getRed()
     * @see #getKColor()
     * @generated
     */
    EAttribute getKColor_Red();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KColor#getGreen <em>Green</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Green</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KColor#getGreen()
     * @see #getKColor()
     * @generated
     */
    EAttribute getKColor_Green();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KColor#getBlue <em>Blue</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Blue</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KColor#getBlue()
     * @see #getKColor()
     * @generated
     */
    EAttribute getKColor_Blue();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KLineWidth <em>KLine Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLine Width</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLineWidth
     * @generated
     */
    EClass getKLineWidth();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KLineWidth#getLineWidth <em>Line Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Line Width</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLineWidth#getLineWidth()
     * @see #getKLineWidth()
     * @generated
     */
    EAttribute getKLineWidth_LineWidth();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KLineStyle <em>KLine Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLine Style</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLineStyle
     * @generated
     */
    EClass getKLineStyle();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KLineStyle#getLineStyle <em>Line Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Line Style</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLineStyle#getLineStyle()
     * @see #getKLineStyle()
     * @generated
     */
    EAttribute getKLineStyle_LineStyle();

    /**
     * Returns the meta object for the attribute list '{@link de.cau.cs.kieler.klighd.krendering.KLineStyle#getDashPattern <em>Dash Pattern</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Dash Pattern</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLineStyle#getDashPattern()
     * @see #getKLineStyle()
     * @generated
     */
    EAttribute getKLineStyle_DashPattern();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KLineStyle#getDashOffset <em>Dash Offset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Dash Offset</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLineStyle#getDashOffset()
     * @see #getKLineStyle()
     * @generated
     */
    EAttribute getKLineStyle_DashOffset();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KVerticalAlignment <em>KVertical Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KVertical Alignment</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KVerticalAlignment
     * @generated
     */
    EClass getKVerticalAlignment();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KVerticalAlignment#getVerticalAlignment <em>Vertical Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Vertical Alignment</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KVerticalAlignment#getVerticalAlignment()
     * @see #getKVerticalAlignment()
     * @generated
     */
    EAttribute getKVerticalAlignment_VerticalAlignment();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KHorizontalAlignment <em>KHorizontal Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KHorizontal Alignment</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KHorizontalAlignment
     * @generated
     */
    EClass getKHorizontalAlignment();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KHorizontalAlignment#getHorizontalAlignment <em>Horizontal Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Horizontal Alignment</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KHorizontalAlignment#getHorizontalAlignment()
     * @see #getKHorizontalAlignment()
     * @generated
     */
    EAttribute getKHorizontalAlignment_HorizontalAlignment();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KXPosition <em>KX Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KX Position</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KXPosition
     * @generated
     */
    EClass getKXPosition();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KXPosition#getAbsolute <em>Absolute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Absolute</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KXPosition#getAbsolute()
     * @see #getKXPosition()
     * @generated
     */
    EAttribute getKXPosition_Absolute();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KXPosition#getRelative <em>Relative</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relative</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KXPosition#getRelative()
     * @see #getKXPosition()
     * @generated
     */
    EAttribute getKXPosition_Relative();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KYPosition <em>KY Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KY Position</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KYPosition
     * @generated
     */
    EClass getKYPosition();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KYPosition#getAbsolute <em>Absolute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Absolute</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KYPosition#getAbsolute()
     * @see #getKYPosition()
     * @generated
     */
    EAttribute getKYPosition_Absolute();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KYPosition#getRelative <em>Relative</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relative</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KYPosition#getRelative()
     * @see #getKYPosition()
     * @generated
     */
    EAttribute getKYPosition_Relative();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KLeftPosition <em>KLeft Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLeft Position</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLeftPosition
     * @generated
     */
    EClass getKLeftPosition();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KRightPosition <em>KRight Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KRight Position</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRightPosition
     * @generated
     */
    EClass getKRightPosition();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KTopPosition <em>KTop Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KTop Position</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KTopPosition
     * @generated
     */
    EClass getKTopPosition();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KBottomPosition <em>KBottom Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KBottom Position</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KBottomPosition
     * @generated
     */
    EClass getKBottomPosition();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KSpline <em>KSpline</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KSpline</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KSpline
     * @generated
     */
    EClass getKSpline();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KForeground <em>KForeground</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KForeground</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KForeground
     * @generated
     */
    EClass getKForeground();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KColoring <em>KColoring</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KColoring</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KColoring
     * @generated
     */
    EClass getKColoring();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KColoring#getColor <em>Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KColoring#getColor()
     * @see #getKColoring()
     * @generated
     */
    EReference getKColoring_Color();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KColoring#getAlpha <em>Alpha</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Alpha</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KColoring#getAlpha()
     * @see #getKColoring()
     * @generated
     */
    EAttribute getKColoring_Alpha();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KColoring#getTargetColor <em>Target Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Color</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KColoring#getTargetColor()
     * @see #getKColoring()
     * @generated
     */
    EReference getKColoring_TargetColor();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KColoring#getTargetAlpha <em>Target Alpha</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Target Alpha</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KColoring#getTargetAlpha()
     * @see #getKColoring()
     * @generated
     */
    EAttribute getKColoring_TargetAlpha();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KColoring#getGradientAngle <em>Gradient Angle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Gradient Angle</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KColoring#getGradientAngle()
     * @see #getKColoring()
     * @generated
     */
    EAttribute getKColoring_GradientAngle();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KBackground <em>KBackground</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KBackground</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KBackground
     * @generated
     */
    EClass getKBackground();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KFontBold <em>KFont Bold</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KFont Bold</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KFontBold
     * @generated
     */
    EClass getKFontBold();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KFontBold#isBold <em>Bold</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bold</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KFontBold#isBold()
     * @see #getKFontBold()
     * @generated
     */
    EAttribute getKFontBold_Bold();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KFontItalic <em>KFont Italic</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KFont Italic</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KFontItalic
     * @generated
     */
    EClass getKFontItalic();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KFontItalic#isItalic <em>Italic</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Italic</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KFontItalic#isItalic()
     * @see #getKFontItalic()
     * @generated
     */
    EAttribute getKFontItalic_Italic();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KFontName <em>KFont Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KFont Name</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KFontName
     * @generated
     */
    EClass getKFontName();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KFontName#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KFontName#getName()
     * @see #getKFontName()
     * @generated
     */
    EAttribute getKFontName_Name();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KFontSize <em>KFont Size</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KFont Size</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KFontSize
     * @generated
     */
    EClass getKFontSize();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KFontSize#getSize <em>Size</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KFontSize#getSize()
     * @see #getKFontSize()
     * @generated
     */
    EAttribute getKFontSize_Size();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KFontSize#isScaleWithZoom <em>Scale With Zoom</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Scale With Zoom</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KFontSize#isScaleWithZoom()
     * @see #getKFontSize()
     * @generated
     */
    EAttribute getKFontSize_ScaleWithZoom();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KRoundedBendsPolyline <em>KRounded Bends Polyline</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KRounded Bends Polyline</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRoundedBendsPolyline
     * @generated
     */
    EClass getKRoundedBendsPolyline();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KRoundedBendsPolyline#getBendRadius <em>Bend Radius</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bend Radius</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRoundedBendsPolyline#getBendRadius()
     * @see #getKRoundedBendsPolyline()
     * @generated
     */
    EAttribute getKRoundedBendsPolyline_BendRadius();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KRotation <em>KRotation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KRotation</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRotation
     * @generated
     */
    EClass getKRotation();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KRotation#getRotation <em>Rotation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Rotation</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRotation#getRotation()
     * @see #getKRotation()
     * @generated
     */
    EAttribute getKRotation_Rotation();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KRotation#getRotationAnchor <em>Rotation Anchor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Rotation Anchor</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KRotation#getRotationAnchor()
     * @see #getKRotation()
     * @generated
     */
    EReference getKRotation_RotationAnchor();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KLineCap <em>KLine Cap</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLine Cap</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLineCap
     * @generated
     */
    EClass getKLineCap();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KLineCap#getLineCap <em>Line Cap</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Line Cap</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLineCap#getLineCap()
     * @see #getKLineCap()
     * @generated
     */
    EAttribute getKLineCap_LineCap();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KAction <em>KAction</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KAction</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KAction
     * @generated
     */
    EClass getKAction();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KAction#getActionId <em>Action Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Action Id</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KAction#getActionId()
     * @see #getKAction()
     * @generated
     */
    EAttribute getKAction_ActionId();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KAction#getTrigger <em>Trigger</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Trigger</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KAction#getTrigger()
     * @see #getKAction()
     * @generated
     */
    EAttribute getKAction_Trigger();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KAction#getAltPressed <em>Alt Pressed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Alt Pressed</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KAction#getAltPressed()
     * @see #getKAction()
     * @generated
     */
    EAttribute getKAction_AltPressed();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KAction#getCtrlCmdPressed <em>Ctrl Cmd Pressed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ctrl Cmd Pressed</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KAction#getCtrlCmdPressed()
     * @see #getKAction()
     * @generated
     */
    EAttribute getKAction_CtrlCmdPressed();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KAction#getShiftPressed <em>Shift Pressed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Shift Pressed</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KAction#getShiftPressed()
     * @see #getKAction()
     * @generated
     */
    EAttribute getKAction_ShiftPressed();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KPointPlacementData <em>KPoint Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPoint Placement Data</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPointPlacementData
     * @generated
     */
    EClass getKPointPlacementData();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getReferencePoint <em>Reference Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Reference Point</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getReferencePoint()
     * @see #getKPointPlacementData()
     * @generated
     */
    EReference getKPointPlacementData_ReferencePoint();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getHorizontalAlignment <em>Horizontal Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Horizontal Alignment</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getHorizontalAlignment()
     * @see #getKPointPlacementData()
     * @generated
     */
    EAttribute getKPointPlacementData_HorizontalAlignment();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getVerticalAlignment <em>Vertical Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Vertical Alignment</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getVerticalAlignment()
     * @see #getKPointPlacementData()
     * @generated
     */
    EAttribute getKPointPlacementData_VerticalAlignment();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getHorizontalMargin <em>Horizontal Margin</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Horizontal Margin</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getHorizontalMargin()
     * @see #getKPointPlacementData()
     * @generated
     */
    EAttribute getKPointPlacementData_HorizontalMargin();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getVerticalMargin <em>Vertical Margin</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Vertical Margin</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getVerticalMargin()
     * @see #getKPointPlacementData()
     * @generated
     */
    EAttribute getKPointPlacementData_VerticalMargin();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getMinWidth <em>Min Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Min Width</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getMinWidth()
     * @see #getKPointPlacementData()
     * @generated
     */
    EAttribute getKPointPlacementData_MinWidth();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getMinHeight <em>Min Height</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Min Height</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KPointPlacementData#getMinHeight()
     * @see #getKPointPlacementData()
     * @generated
     */
    EAttribute getKPointPlacementData_MinHeight();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KStyleHolder <em>KStyle Holder</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KStyle Holder</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KStyleHolder
     * @generated
     */
    EClass getKStyleHolder();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.krendering.KStyleHolder#getStyles <em>Styles</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Styles</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KStyleHolder#getStyles()
     * @see #getKStyleHolder()
     * @generated
     */
    EReference getKStyleHolder_Styles();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KStyleHolder#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KStyleHolder#getId()
     * @see #getKStyleHolder()
     * @generated
     */
    EAttribute getKStyleHolder_Id();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KInvisibility <em>KInvisibility</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KInvisibility</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KInvisibility
     * @generated
     */
    EClass getKInvisibility();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KInvisibility#isInvisible <em>Invisible</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Invisible</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KInvisibility#isInvisible()
     * @see #getKInvisibility()
     * @generated
     */
    EAttribute getKInvisibility_Invisible();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KShadow <em>KShadow</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KShadow</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KShadow
     * @generated
     */
    EClass getKShadow();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KShadow#getXOffset <em>XOffset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>XOffset</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KShadow#getXOffset()
     * @see #getKShadow()
     * @generated
     */
    EAttribute getKShadow_XOffset();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KShadow#getYOffset <em>YOffset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>YOffset</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KShadow#getYOffset()
     * @see #getKShadow()
     * @generated
     */
    EAttribute getKShadow_YOffset();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KShadow#getBlur <em>Blur</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Blur</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KShadow#getBlur()
     * @see #getKShadow()
     * @generated
     */
    EAttribute getKShadow_Blur();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KShadow#getColor <em>Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KShadow#getColor()
     * @see #getKShadow()
     * @generated
     */
    EReference getKShadow_Color();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KTextUnderline <em>KText Underline</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KText Underline</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KTextUnderline
     * @generated
     */
    EClass getKTextUnderline();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KTextUnderline#getUnderline <em>Underline</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Underline</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KTextUnderline#getUnderline()
     * @see #getKTextUnderline()
     * @generated
     */
    EAttribute getKTextUnderline_Underline();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KTextUnderline#getColor <em>Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KTextUnderline#getColor()
     * @see #getKTextUnderline()
     * @generated
     */
    EReference getKTextUnderline_Color();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KStyleRef <em>KStyle Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KStyle Ref</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KStyleRef
     * @generated
     */
    EClass getKStyleRef();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.klighd.krendering.KStyleRef#getStyleHolder <em>Style Holder</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Style Holder</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KStyleRef#getStyleHolder()
     * @see #getKStyleRef()
     * @generated
     */
    EReference getKStyleRef_StyleHolder();

    /**
     * Returns the meta object for the attribute list '{@link de.cau.cs.kieler.klighd.krendering.KStyleRef#getReferencedTypes <em>Referenced Types</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Referenced Types</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KStyleRef#getReferencedTypes()
     * @see #getKStyleRef()
     * @generated
     */
    EAttribute getKStyleRef_ReferencedTypes();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KTextStrikeout <em>KText Strikeout</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KText Strikeout</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KTextStrikeout
     * @generated
     */
    EClass getKTextStrikeout();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KTextStrikeout#getStruckOut <em>Struck Out</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Struck Out</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KTextStrikeout#getStruckOut()
     * @see #getKTextStrikeout()
     * @generated
     */
    EAttribute getKTextStrikeout_StruckOut();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.krendering.KTextStrikeout#getColor <em>Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KTextStrikeout#getColor()
     * @see #getKTextStrikeout()
     * @generated
     */
    EReference getKTextStrikeout_Color();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.krendering.KLineJoin <em>KLine Join</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLine Join</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLineJoin
     * @generated
     */
    EClass getKLineJoin();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KLineJoin#getLineJoin <em>Line Join</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Line Join</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLineJoin#getLineJoin()
     * @see #getKLineJoin()
     * @generated
     */
    EAttribute getKLineJoin_LineJoin();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.krendering.KLineJoin#getMiterLimit <em>Miter Limit</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Miter Limit</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.KLineJoin#getMiterLimit()
     * @see #getKLineJoin()
     * @generated
     */
    EAttribute getKLineJoin_MiterLimit();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.klighd.krendering.LineStyle <em>Line Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Line Style</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.LineStyle
     * @generated
     */
    EEnum getLineStyle();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.klighd.krendering.HorizontalAlignment <em>Horizontal Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Horizontal Alignment</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.HorizontalAlignment
     * @generated
     */
    EEnum getHorizontalAlignment();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.klighd.krendering.VerticalAlignment <em>Vertical Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Vertical Alignment</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.VerticalAlignment
     * @generated
     */
    EEnum getVerticalAlignment();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.klighd.krendering.LineCap <em>Line Cap</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Line Cap</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.LineCap
     * @generated
     */
    EEnum getLineCap();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.klighd.krendering.ModifierState <em>Modifier State</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Modifier State</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.ModifierState
     * @generated
     */
    EEnum getModifierState();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.klighd.krendering.Trigger <em>Trigger</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Trigger</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.Trigger
     * @generated
     */
    EEnum getTrigger();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.klighd.krendering.Underline <em>Underline</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Underline</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.Underline
     * @generated
     */
    EEnum getUnderline();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.klighd.krendering.LineJoin <em>Line Join</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Line Join</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.LineJoin
     * @generated
     */
    EEnum getLineJoin();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.klighd.krendering.Arc <em>Arc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Arc</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.Arc
     * @generated
     */
    EEnum getArc();

    /**
     * Returns the meta object for data type '{@link de.cau.cs.kieler.klighd.krendering.Colors <em>Colors</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Colors</em>'.
     * @see de.cau.cs.kieler.klighd.krendering.Colors
     * @model instanceClass="de.cau.cs.kieler.klighd.krendering.Colors"
     * @generated
     */
    EDataType getColors();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    KRenderingFactory getKRenderingFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KPositionImpl <em>KPosition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KPositionImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPosition()
         * @generated
         */
        EClass KPOSITION = eINSTANCE.getKPosition();

        /**
         * The meta object literal for the '<em><b>X</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KPOSITION__X = eINSTANCE.getKPosition_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KPOSITION__Y = eINSTANCE.getKPosition_Y();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl <em>KRendering</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRendering()
         * @generated
         */
        EClass KRENDERING = eINSTANCE.getKRendering();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KRENDERING__PARENT = eINSTANCE.getKRendering_Parent();

        /**
         * The meta object literal for the '<em><b>Placement Data</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KRENDERING__PLACEMENT_DATA = eINSTANCE.getKRendering_PlacementData();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KRENDERING__ACTIONS = eINSTANCE.getKRendering_Actions();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KEllipseImpl <em>KEllipse</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KEllipseImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKEllipse()
         * @generated
         */
        EClass KELLIPSE = eINSTANCE.getKEllipse();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRectangleImpl <em>KRectangle</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRectangleImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRectangle()
         * @generated
         */
        EClass KRECTANGLE = eINSTANCE.getKRectangle();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRoundedRectangleImpl <em>KRounded Rectangle</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRoundedRectangleImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRoundedRectangle()
         * @generated
         */
        EClass KROUNDED_RECTANGLE = eINSTANCE.getKRoundedRectangle();

        /**
         * The meta object literal for the '<em><b>Corner Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KROUNDED_RECTANGLE__CORNER_WIDTH = eINSTANCE.getKRoundedRectangle_CornerWidth();

        /**
         * The meta object literal for the '<em><b>Corner Height</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KROUNDED_RECTANGLE__CORNER_HEIGHT = eINSTANCE.getKRoundedRectangle_CornerHeight();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KPolylineImpl <em>KPolyline</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KPolylineImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPolyline()
         * @generated
         */
        EClass KPOLYLINE = eINSTANCE.getKPolyline();

        /**
         * The meta object literal for the '<em><b>Points</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KPOLYLINE__POINTS = eINSTANCE.getKPolyline_Points();

        /**
         * The meta object literal for the '<em><b>Junction Point Rendering</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KPOLYLINE__JUNCTION_POINT_RENDERING = eINSTANCE.getKPolyline_JunctionPointRendering();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KPolygonImpl <em>KPolygon</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KPolygonImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPolygon()
         * @generated
         */
        EClass KPOLYGON = eINSTANCE.getKPolygon();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KImageImpl <em>KImage</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KImageImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKImage()
         * @generated
         */
        EClass KIMAGE = eINSTANCE.getKImage();

        /**
         * The meta object literal for the '<em><b>Bundle Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KIMAGE__BUNDLE_NAME = eINSTANCE.getKImage_BundleName();

        /**
         * The meta object literal for the '<em><b>Image Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KIMAGE__IMAGE_PATH = eINSTANCE.getKImage_ImagePath();

        /**
         * The meta object literal for the '<em><b>Image Object</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KIMAGE__IMAGE_OBJECT = eINSTANCE.getKImage_ImageObject();

        /**
         * The meta object literal for the '<em><b>Clip Shape</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KIMAGE__CLIP_SHAPE = eINSTANCE.getKImage_ClipShape();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KDecoratorPlacementDataImpl <em>KDecorator Placement Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KDecoratorPlacementDataImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKDecoratorPlacementData()
         * @generated
         */
        EClass KDECORATOR_PLACEMENT_DATA = eINSTANCE.getKDecoratorPlacementData();

        /**
         * The meta object literal for the '<em><b>Absolute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KDECORATOR_PLACEMENT_DATA__ABSOLUTE = eINSTANCE.getKDecoratorPlacementData_Absolute();

        /**
         * The meta object literal for the '<em><b>XOffset</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KDECORATOR_PLACEMENT_DATA__XOFFSET = eINSTANCE.getKDecoratorPlacementData_XOffset();

        /**
         * The meta object literal for the '<em><b>YOffset</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KDECORATOR_PLACEMENT_DATA__YOFFSET = eINSTANCE.getKDecoratorPlacementData_YOffset();

        /**
         * The meta object literal for the '<em><b>Rotate With Line</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KDECORATOR_PLACEMENT_DATA__ROTATE_WITH_LINE = eINSTANCE.getKDecoratorPlacementData_RotateWithLine();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KDECORATOR_PLACEMENT_DATA__WIDTH = eINSTANCE.getKDecoratorPlacementData_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KDECORATOR_PLACEMENT_DATA__HEIGHT = eINSTANCE.getKDecoratorPlacementData_Height();

        /**
         * The meta object literal for the '<em><b>Relative</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KDECORATOR_PLACEMENT_DATA__RELATIVE = eINSTANCE.getKDecoratorPlacementData_Relative();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KContainerRenderingImpl <em>KContainer Rendering</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KContainerRenderingImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKContainerRendering()
         * @generated
         */
        EClass KCONTAINER_RENDERING = eINSTANCE.getKContainerRendering();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KCONTAINER_RENDERING__CHILDREN = eINSTANCE.getKContainerRendering_Children();

        /**
         * The meta object literal for the '<em><b>Child Placement</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KCONTAINER_RENDERING__CHILD_PLACEMENT = eINSTANCE.getKContainerRendering_ChildPlacement();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KArcImpl <em>KArc</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KArcImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKArc()
         * @generated
         */
        EClass KARC = eINSTANCE.getKArc();

        /**
         * The meta object literal for the '<em><b>Start Angle</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KARC__START_ANGLE = eINSTANCE.getKArc_StartAngle();

        /**
         * The meta object literal for the '<em><b>Arc Angle</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KARC__ARC_ANGLE = eINSTANCE.getKArc_ArcAngle();

        /**
         * The meta object literal for the '<em><b>Arc Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KARC__ARC_TYPE = eINSTANCE.getKArc_ArcType();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KStyleImpl <em>KStyle</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KStyleImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKStyle()
         * @generated
         */
        EClass KSTYLE = eINSTANCE.getKStyle();

        /**
         * The meta object literal for the '<em><b>Propagate To Children</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTYLE__PROPAGATE_TO_CHILDREN = eINSTANCE.getKStyle_PropagateToChildren();

        /**
         * The meta object literal for the '<em><b>Modifier Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTYLE__MODIFIER_ID = eINSTANCE.getKStyle_ModifierId();

        /**
         * The meta object literal for the '<em><b>Selection</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTYLE__SELECTION = eINSTANCE.getKStyle_Selection();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingLibraryImpl <em>Library</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingLibraryImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRenderingLibrary()
         * @generated
         */
        EClass KRENDERING_LIBRARY = eINSTANCE.getKRenderingLibrary();

        /**
         * The meta object literal for the '<em><b>Renderings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KRENDERING_LIBRARY__RENDERINGS = eINSTANCE.getKRenderingLibrary_Renderings();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingRefImpl <em>Ref</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingRefImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRenderingRef()
         * @generated
         */
        EClass KRENDERING_REF = eINSTANCE.getKRenderingRef();

        /**
         * The meta object literal for the '<em><b>Rendering</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KRENDERING_REF__RENDERING = eINSTANCE.getKRenderingRef_Rendering();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KChildAreaImpl <em>KChild Area</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KChildAreaImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKChildArea()
         * @generated
         */
        EClass KCHILD_AREA = eINSTANCE.getKChildArea();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KTextImpl <em>KText</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KTextImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKText()
         * @generated
         */
        EClass KTEXT = eINSTANCE.getKText();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KTEXT__TEXT = eINSTANCE.getKText_Text();

        /**
         * The meta object literal for the '<em><b>Cursor Selectable</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KTEXT__CURSOR_SELECTABLE = eINSTANCE.getKText_CursorSelectable();

        /**
         * The meta object literal for the '<em><b>Editable</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KTEXT__EDITABLE = eINSTANCE.getKText_Editable();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.KPlacement <em>KPlacement</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.KPlacement
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPlacement()
         * @generated
         */
        EClass KPLACEMENT = eINSTANCE.getKPlacement();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KGridPlacementImpl <em>KGrid Placement</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KGridPlacementImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKGridPlacement()
         * @generated
         */
        EClass KGRID_PLACEMENT = eINSTANCE.getKGridPlacement();

        /**
         * The meta object literal for the '<em><b>Num Columns</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KGRID_PLACEMENT__NUM_COLUMNS = eINSTANCE.getKGridPlacement_NumColumns();

        /**
         * The meta object literal for the '<em><b>Top Left</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KGRID_PLACEMENT__TOP_LEFT = eINSTANCE.getKGridPlacement_TopLeft();

        /**
         * The meta object literal for the '<em><b>Bottom Right</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KGRID_PLACEMENT__BOTTOM_RIGHT = eINSTANCE.getKGridPlacement_BottomRight();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.KPlacementData <em>KPlacement Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.KPlacementData
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPlacementData()
         * @generated
         */
        EClass KPLACEMENT_DATA = eINSTANCE.getKPlacementData();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KGridPlacementDataImpl <em>KGrid Placement Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KGridPlacementDataImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKGridPlacementData()
         * @generated
         */
        EClass KGRID_PLACEMENT_DATA = eINSTANCE.getKGridPlacementData();

        /**
         * The meta object literal for the '<em><b>Min Cell Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KGRID_PLACEMENT_DATA__MIN_CELL_WIDTH = eINSTANCE.getKGridPlacementData_MinCellWidth();

        /**
         * The meta object literal for the '<em><b>Min Cell Height</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KGRID_PLACEMENT_DATA__MIN_CELL_HEIGHT = eINSTANCE.getKGridPlacementData_MinCellHeight();

        /**
         * The meta object literal for the '<em><b>Flexible Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KGRID_PLACEMENT_DATA__FLEXIBLE_WIDTH = eINSTANCE.getKGridPlacementData_FlexibleWidth();

        /**
         * The meta object literal for the '<em><b>Flexible Height</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KGRID_PLACEMENT_DATA__FLEXIBLE_HEIGHT = eINSTANCE.getKGridPlacementData_FlexibleHeight();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KAreaPlacementDataImpl <em>KArea Placement Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KAreaPlacementDataImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKAreaPlacementData()
         * @generated
         */
        EClass KAREA_PLACEMENT_DATA = eINSTANCE.getKAreaPlacementData();

        /**
         * The meta object literal for the '<em><b>Top Left</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KAREA_PLACEMENT_DATA__TOP_LEFT = eINSTANCE.getKAreaPlacementData_TopLeft();

        /**
         * The meta object literal for the '<em><b>Bottom Right</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KAREA_PLACEMENT_DATA__BOTTOM_RIGHT = eINSTANCE.getKAreaPlacementData_BottomRight();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KCustomRenderingImpl <em>KCustom Rendering</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KCustomRenderingImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKCustomRendering()
         * @generated
         */
        EClass KCUSTOM_RENDERING = eINSTANCE.getKCustomRendering();

        /**
         * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KCUSTOM_RENDERING__CLASS_NAME = eINSTANCE.getKCustomRendering_ClassName();

        /**
         * The meta object literal for the '<em><b>Bundle Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KCUSTOM_RENDERING__BUNDLE_NAME = eINSTANCE.getKCustomRendering_BundleName();

        /**
         * The meta object literal for the '<em><b>Figure Object</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KCUSTOM_RENDERING__FIGURE_OBJECT = eINSTANCE.getKCustomRendering_FigureObject();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KColorImpl <em>KColor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KColorImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKColor()
         * @generated
         */
        EClass KCOLOR = eINSTANCE.getKColor();

        /**
         * The meta object literal for the '<em><b>Red</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KCOLOR__RED = eINSTANCE.getKColor_Red();

        /**
         * The meta object literal for the '<em><b>Green</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KCOLOR__GREEN = eINSTANCE.getKColor_Green();

        /**
         * The meta object literal for the '<em><b>Blue</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KCOLOR__BLUE = eINSTANCE.getKColor_Blue();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KLineWidthImpl <em>KLine Width</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KLineWidthImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKLineWidth()
         * @generated
         */
        EClass KLINE_WIDTH = eINSTANCE.getKLineWidth();

        /**
         * The meta object literal for the '<em><b>Line Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KLINE_WIDTH__LINE_WIDTH = eINSTANCE.getKLineWidth_LineWidth();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KLineStyleImpl <em>KLine Style</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KLineStyleImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKLineStyle()
         * @generated
         */
        EClass KLINE_STYLE = eINSTANCE.getKLineStyle();

        /**
         * The meta object literal for the '<em><b>Line Style</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KLINE_STYLE__LINE_STYLE = eINSTANCE.getKLineStyle_LineStyle();

        /**
         * The meta object literal for the '<em><b>Dash Pattern</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KLINE_STYLE__DASH_PATTERN = eINSTANCE.getKLineStyle_DashPattern();

        /**
         * The meta object literal for the '<em><b>Dash Offset</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KLINE_STYLE__DASH_OFFSET = eINSTANCE.getKLineStyle_DashOffset();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KVerticalAlignmentImpl <em>KVertical Alignment</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KVerticalAlignmentImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKVerticalAlignment()
         * @generated
         */
        EClass KVERTICAL_ALIGNMENT = eINSTANCE.getKVerticalAlignment();

        /**
         * The meta object literal for the '<em><b>Vertical Alignment</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KVERTICAL_ALIGNMENT__VERTICAL_ALIGNMENT = eINSTANCE.getKVerticalAlignment_VerticalAlignment();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KHorizontalAlignmentImpl <em>KHorizontal Alignment</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KHorizontalAlignmentImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKHorizontalAlignment()
         * @generated
         */
        EClass KHORIZONTAL_ALIGNMENT = eINSTANCE.getKHorizontalAlignment();

        /**
         * The meta object literal for the '<em><b>Horizontal Alignment</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KHORIZONTAL_ALIGNMENT__HORIZONTAL_ALIGNMENT = eINSTANCE.getKHorizontalAlignment_HorizontalAlignment();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KXPositionImpl <em>KX Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KXPositionImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKXPosition()
         * @generated
         */
        EClass KX_POSITION = eINSTANCE.getKXPosition();

        /**
         * The meta object literal for the '<em><b>Absolute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KX_POSITION__ABSOLUTE = eINSTANCE.getKXPosition_Absolute();

        /**
         * The meta object literal for the '<em><b>Relative</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KX_POSITION__RELATIVE = eINSTANCE.getKXPosition_Relative();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KYPositionImpl <em>KY Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KYPositionImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKYPosition()
         * @generated
         */
        EClass KY_POSITION = eINSTANCE.getKYPosition();

        /**
         * The meta object literal for the '<em><b>Absolute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KY_POSITION__ABSOLUTE = eINSTANCE.getKYPosition_Absolute();

        /**
         * The meta object literal for the '<em><b>Relative</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KY_POSITION__RELATIVE = eINSTANCE.getKYPosition_Relative();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KLeftPositionImpl <em>KLeft Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KLeftPositionImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKLeftPosition()
         * @generated
         */
        EClass KLEFT_POSITION = eINSTANCE.getKLeftPosition();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRightPositionImpl <em>KRight Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRightPositionImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRightPosition()
         * @generated
         */
        EClass KRIGHT_POSITION = eINSTANCE.getKRightPosition();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KTopPositionImpl <em>KTop Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KTopPositionImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKTopPosition()
         * @generated
         */
        EClass KTOP_POSITION = eINSTANCE.getKTopPosition();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KBottomPositionImpl <em>KBottom Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KBottomPositionImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKBottomPosition()
         * @generated
         */
        EClass KBOTTOM_POSITION = eINSTANCE.getKBottomPosition();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KSplineImpl <em>KSpline</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KSplineImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKSpline()
         * @generated
         */
        EClass KSPLINE = eINSTANCE.getKSpline();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KForegroundImpl <em>KForeground</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KForegroundImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKForeground()
         * @generated
         */
        EClass KFOREGROUND = eINSTANCE.getKForeground();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KColoringImpl <em>KColoring</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KColoringImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKColoring()
         * @generated
         */
        EClass KCOLORING = eINSTANCE.getKColoring();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KCOLORING__COLOR = eINSTANCE.getKColoring_Color();

        /**
         * The meta object literal for the '<em><b>Alpha</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KCOLORING__ALPHA = eINSTANCE.getKColoring_Alpha();

        /**
         * The meta object literal for the '<em><b>Target Color</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KCOLORING__TARGET_COLOR = eINSTANCE.getKColoring_TargetColor();

        /**
         * The meta object literal for the '<em><b>Target Alpha</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KCOLORING__TARGET_ALPHA = eINSTANCE.getKColoring_TargetAlpha();

        /**
         * The meta object literal for the '<em><b>Gradient Angle</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KCOLORING__GRADIENT_ANGLE = eINSTANCE.getKColoring_GradientAngle();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KBackgroundImpl <em>KBackground</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KBackgroundImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKBackground()
         * @generated
         */
        EClass KBACKGROUND = eINSTANCE.getKBackground();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KFontBoldImpl <em>KFont Bold</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KFontBoldImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKFontBold()
         * @generated
         */
        EClass KFONT_BOLD = eINSTANCE.getKFontBold();

        /**
         * The meta object literal for the '<em><b>Bold</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KFONT_BOLD__BOLD = eINSTANCE.getKFontBold_Bold();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KFontItalicImpl <em>KFont Italic</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KFontItalicImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKFontItalic()
         * @generated
         */
        EClass KFONT_ITALIC = eINSTANCE.getKFontItalic();

        /**
         * The meta object literal for the '<em><b>Italic</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KFONT_ITALIC__ITALIC = eINSTANCE.getKFontItalic_Italic();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KFontNameImpl <em>KFont Name</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KFontNameImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKFontName()
         * @generated
         */
        EClass KFONT_NAME = eINSTANCE.getKFontName();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KFONT_NAME__NAME = eINSTANCE.getKFontName_Name();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KFontSizeImpl <em>KFont Size</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KFontSizeImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKFontSize()
         * @generated
         */
        EClass KFONT_SIZE = eINSTANCE.getKFontSize();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KFONT_SIZE__SIZE = eINSTANCE.getKFontSize_Size();

        /**
         * The meta object literal for the '<em><b>Scale With Zoom</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KFONT_SIZE__SCALE_WITH_ZOOM = eINSTANCE.getKFontSize_ScaleWithZoom();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRoundedBendsPolylineImpl <em>KRounded Bends Polyline</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRoundedBendsPolylineImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRoundedBendsPolyline()
         * @generated
         */
        EClass KROUNDED_BENDS_POLYLINE = eINSTANCE.getKRoundedBendsPolyline();

        /**
         * The meta object literal for the '<em><b>Bend Radius</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KROUNDED_BENDS_POLYLINE__BEND_RADIUS = eINSTANCE.getKRoundedBendsPolyline_BendRadius();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KRotationImpl <em>KRotation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRotationImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKRotation()
         * @generated
         */
        EClass KROTATION = eINSTANCE.getKRotation();

        /**
         * The meta object literal for the '<em><b>Rotation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KROTATION__ROTATION = eINSTANCE.getKRotation_Rotation();

        /**
         * The meta object literal for the '<em><b>Rotation Anchor</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KROTATION__ROTATION_ANCHOR = eINSTANCE.getKRotation_RotationAnchor();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KLineCapImpl <em>KLine Cap</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KLineCapImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKLineCap()
         * @generated
         */
        EClass KLINE_CAP = eINSTANCE.getKLineCap();

        /**
         * The meta object literal for the '<em><b>Line Cap</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KLINE_CAP__LINE_CAP = eINSTANCE.getKLineCap_LineCap();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KActionImpl <em>KAction</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KActionImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKAction()
         * @generated
         */
        EClass KACTION = eINSTANCE.getKAction();

        /**
         * The meta object literal for the '<em><b>Action Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KACTION__ACTION_ID = eINSTANCE.getKAction_ActionId();

        /**
         * The meta object literal for the '<em><b>Trigger</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KACTION__TRIGGER = eINSTANCE.getKAction_Trigger();

        /**
         * The meta object literal for the '<em><b>Alt Pressed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KACTION__ALT_PRESSED = eINSTANCE.getKAction_AltPressed();

        /**
         * The meta object literal for the '<em><b>Ctrl Cmd Pressed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KACTION__CTRL_CMD_PRESSED = eINSTANCE.getKAction_CtrlCmdPressed();

        /**
         * The meta object literal for the '<em><b>Shift Pressed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KACTION__SHIFT_PRESSED = eINSTANCE.getKAction_ShiftPressed();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KPointPlacementDataImpl <em>KPoint Placement Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KPointPlacementDataImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKPointPlacementData()
         * @generated
         */
        EClass KPOINT_PLACEMENT_DATA = eINSTANCE.getKPointPlacementData();

        /**
         * The meta object literal for the '<em><b>Reference Point</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KPOINT_PLACEMENT_DATA__REFERENCE_POINT = eINSTANCE.getKPointPlacementData_ReferencePoint();

        /**
         * The meta object literal for the '<em><b>Horizontal Alignment</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KPOINT_PLACEMENT_DATA__HORIZONTAL_ALIGNMENT = eINSTANCE.getKPointPlacementData_HorizontalAlignment();

        /**
         * The meta object literal for the '<em><b>Vertical Alignment</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KPOINT_PLACEMENT_DATA__VERTICAL_ALIGNMENT = eINSTANCE.getKPointPlacementData_VerticalAlignment();

        /**
         * The meta object literal for the '<em><b>Horizontal Margin</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KPOINT_PLACEMENT_DATA__HORIZONTAL_MARGIN = eINSTANCE.getKPointPlacementData_HorizontalMargin();

        /**
         * The meta object literal for the '<em><b>Vertical Margin</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KPOINT_PLACEMENT_DATA__VERTICAL_MARGIN = eINSTANCE.getKPointPlacementData_VerticalMargin();

        /**
         * The meta object literal for the '<em><b>Min Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KPOINT_PLACEMENT_DATA__MIN_WIDTH = eINSTANCE.getKPointPlacementData_MinWidth();

        /**
         * The meta object literal for the '<em><b>Min Height</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KPOINT_PLACEMENT_DATA__MIN_HEIGHT = eINSTANCE.getKPointPlacementData_MinHeight();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KStyleHolderImpl <em>KStyle Holder</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KStyleHolderImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKStyleHolder()
         * @generated
         */
        EClass KSTYLE_HOLDER = eINSTANCE.getKStyleHolder();

        /**
         * The meta object literal for the '<em><b>Styles</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KSTYLE_HOLDER__STYLES = eINSTANCE.getKStyleHolder_Styles();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTYLE_HOLDER__ID = eINSTANCE.getKStyleHolder_Id();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KInvisibilityImpl <em>KInvisibility</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KInvisibilityImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKInvisibility()
         * @generated
         */
        EClass KINVISIBILITY = eINSTANCE.getKInvisibility();

        /**
         * The meta object literal for the '<em><b>Invisible</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KINVISIBILITY__INVISIBLE = eINSTANCE.getKInvisibility_Invisible();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KShadowImpl <em>KShadow</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KShadowImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKShadow()
         * @generated
         */
        EClass KSHADOW = eINSTANCE.getKShadow();

        /**
         * The meta object literal for the '<em><b>XOffset</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSHADOW__XOFFSET = eINSTANCE.getKShadow_XOffset();

        /**
         * The meta object literal for the '<em><b>YOffset</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSHADOW__YOFFSET = eINSTANCE.getKShadow_YOffset();

        /**
         * The meta object literal for the '<em><b>Blur</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSHADOW__BLUR = eINSTANCE.getKShadow_Blur();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KSHADOW__COLOR = eINSTANCE.getKShadow_Color();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KTextUnderlineImpl <em>KText Underline</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KTextUnderlineImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKTextUnderline()
         * @generated
         */
        EClass KTEXT_UNDERLINE = eINSTANCE.getKTextUnderline();

        /**
         * The meta object literal for the '<em><b>Underline</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KTEXT_UNDERLINE__UNDERLINE = eINSTANCE.getKTextUnderline_Underline();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KTEXT_UNDERLINE__COLOR = eINSTANCE.getKTextUnderline_Color();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KStyleRefImpl <em>KStyle Ref</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KStyleRefImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKStyleRef()
         * @generated
         */
        EClass KSTYLE_REF = eINSTANCE.getKStyleRef();

        /**
         * The meta object literal for the '<em><b>Style Holder</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KSTYLE_REF__STYLE_HOLDER = eINSTANCE.getKStyleRef_StyleHolder();

        /**
         * The meta object literal for the '<em><b>Referenced Types</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTYLE_REF__REFERENCED_TYPES = eINSTANCE.getKStyleRef_ReferencedTypes();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KTextStrikeoutImpl <em>KText Strikeout</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KTextStrikeoutImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKTextStrikeout()
         * @generated
         */
        EClass KTEXT_STRIKEOUT = eINSTANCE.getKTextStrikeout();

        /**
         * The meta object literal for the '<em><b>Struck Out</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KTEXT_STRIKEOUT__STRUCK_OUT = eINSTANCE.getKTextStrikeout_StruckOut();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KTEXT_STRIKEOUT__COLOR = eINSTANCE.getKTextStrikeout_Color();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.impl.KLineJoinImpl <em>KLine Join</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.impl.KLineJoinImpl
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getKLineJoin()
         * @generated
         */
        EClass KLINE_JOIN = eINSTANCE.getKLineJoin();

        /**
         * The meta object literal for the '<em><b>Line Join</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KLINE_JOIN__LINE_JOIN = eINSTANCE.getKLineJoin_LineJoin();

        /**
         * The meta object literal for the '<em><b>Miter Limit</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KLINE_JOIN__MITER_LIMIT = eINSTANCE.getKLineJoin_MiterLimit();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.LineStyle <em>Line Style</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.LineStyle
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getLineStyle()
         * @generated
         */
        EEnum LINE_STYLE = eINSTANCE.getLineStyle();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.HorizontalAlignment <em>Horizontal Alignment</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.HorizontalAlignment
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getHorizontalAlignment()
         * @generated
         */
        EEnum HORIZONTAL_ALIGNMENT = eINSTANCE.getHorizontalAlignment();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.VerticalAlignment <em>Vertical Alignment</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.VerticalAlignment
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getVerticalAlignment()
         * @generated
         */
        EEnum VERTICAL_ALIGNMENT = eINSTANCE.getVerticalAlignment();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.LineCap <em>Line Cap</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.LineCap
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getLineCap()
         * @generated
         */
        EEnum LINE_CAP = eINSTANCE.getLineCap();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.ModifierState <em>Modifier State</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.ModifierState
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getModifierState()
         * @generated
         */
        EEnum MODIFIER_STATE = eINSTANCE.getModifierState();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.Trigger <em>Trigger</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.Trigger
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getTrigger()
         * @generated
         */
        EEnum TRIGGER = eINSTANCE.getTrigger();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.Underline <em>Underline</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.Underline
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getUnderline()
         * @generated
         */
        EEnum UNDERLINE = eINSTANCE.getUnderline();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.LineJoin <em>Line Join</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.LineJoin
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getLineJoin()
         * @generated
         */
        EEnum LINE_JOIN = eINSTANCE.getLineJoin();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.krendering.Arc <em>Arc</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.Arc
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getArc()
         * @generated
         */
        EEnum ARC = eINSTANCE.getArc();

        /**
         * The meta object literal for the '<em>Colors</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.krendering.Colors
         * @see de.cau.cs.kieler.klighd.krendering.impl.KRenderingPackageImpl#getColors()
         * @generated
         */
        EDataType COLORS = eINSTANCE.getColors();

    }

} //KRenderingPackage
