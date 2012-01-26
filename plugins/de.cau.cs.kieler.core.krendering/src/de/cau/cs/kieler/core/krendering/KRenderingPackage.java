/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see de.cau.cs.kieler.core.krendering.KRenderingFactory
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
    KRenderingPackage eINSTANCE = de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl.init();

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KPositionImpl <em>KPosition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KPositionImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPosition()
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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KRenderingImpl <em>KRendering</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRendering()
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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING__PARENT = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING__REFERENCES = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING__PLACEMENT_DATA = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING__STYLES = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>KRendering</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_FEATURE_COUNT = KGraphPackage.KGRAPH_DATA_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KContainerRenderingImpl <em>KContainer Rendering</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KContainerRenderingImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKContainerRendering()
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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__PARENT = KRENDERING__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__REFERENCES = KRENDERING__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__PLACEMENT_DATA = KRENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCONTAINER_RENDERING__STYLES = KRENDERING__STYLES;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KEllipseImpl <em>KEllipse</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KEllipseImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKEllipse()
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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__REFERENCES = KCONTAINER_RENDERING__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KELLIPSE__STYLES = KCONTAINER_RENDERING__STYLES;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KRectangleImpl <em>KRectangle</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KRectangleImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRectangle()
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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__REFERENCES = KCONTAINER_RENDERING__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRECTANGLE__STYLES = KCONTAINER_RENDERING__STYLES;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KRoundedRectangleImpl <em>KRounded Rectangle</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KRoundedRectangleImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRoundedRectangle()
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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__REFERENCES = KCONTAINER_RENDERING__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KROUNDED_RECTANGLE__STYLES = KCONTAINER_RENDERING__STYLES;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KPolylineImpl <em>KPolyline</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KPolylineImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPolyline()
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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__REFERENCES = KCONTAINER_RENDERING__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE__STYLES = KCONTAINER_RENDERING__STYLES;

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
     * The number of structural features of the '<em>KPolyline</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KPolygonImpl <em>KPolygon</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KPolygonImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPolygon()
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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__PARENT = KPOLYLINE__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__REFERENCES = KPOLYLINE__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__PLACEMENT_DATA = KPOLYLINE__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON__STYLES = KPOLYLINE__STYLES;

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
     * The number of structural features of the '<em>KPolygon</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYGON_FEATURE_COUNT = KPOLYLINE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KImageImpl <em>KImage</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KImageImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKImage()
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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__REFERENCES = KCONTAINER_RENDERING__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE__STYLES = KCONTAINER_RENDERING__STYLES;

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
     * The number of structural features of the '<em>KImage</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KIMAGE_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.KPlacementData <em>KPlacement Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.KPlacementData
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPlacementData()
     * @generated
     */
    int KPLACEMENT_DATA = 19;

    /**
     * The number of structural features of the '<em>KPlacement Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPLACEMENT_DATA_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KDecoratorPlacementDataImpl <em>KDecorator Placement Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KDecoratorPlacementDataImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKDecoratorPlacementData()
     * @generated
     */
    int KDECORATOR_PLACEMENT_DATA = 8;

    /**
     * The feature id for the '<em><b>Location</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDECORATOR_PLACEMENT_DATA__LOCATION = KPLACEMENT_DATA_FEATURE_COUNT + 0;

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
     * The feature id for the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDECORATOR_PLACEMENT_DATA__RELATIVE = KPLACEMENT_DATA_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>KDecorator Placement Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDECORATOR_PLACEMENT_DATA_FEATURE_COUNT = KPLACEMENT_DATA_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KArcImpl <em>KArc</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KArcImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKArc()
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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__REFERENCES = KCONTAINER_RENDERING__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC__STYLES = KCONTAINER_RENDERING__STYLES;

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
     * The number of structural features of the '<em>KArc</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KARC_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KStyleImpl <em>KStyle</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KStyleImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKStyle()
     * @generated
     */
    int KSTYLE = 11;

    /**
     * The feature id for the '<em><b>Rendering</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE__RENDERING = 0;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE__PROPAGATE_TO_CHILDREN = 1;

    /**
     * The number of structural features of the '<em>KStyle</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTYLE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KRenderingLibraryImpl <em>Library</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingLibraryImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRenderingLibrary()
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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KRenderingRefImpl <em>Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingRefImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRenderingRef()
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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__PARENT = KRENDERING__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__REFERENCES = KRENDERING__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__PLACEMENT_DATA = KRENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KRENDERING_REF__STYLES = KRENDERING__STYLES;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KChildAreaImpl <em>KChild Area</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KChildAreaImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKChildArea()
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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA__PARENT = KRENDERING__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA__REFERENCES = KRENDERING__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA__PLACEMENT_DATA = KRENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA__STYLES = KRENDERING__STYLES;

    /**
     * The number of structural features of the '<em>KChild Area</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCHILD_AREA_FEATURE_COUNT = KRENDERING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KTextImpl <em>KText</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KTextImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKText()
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
    int KTEXT__PROPERTIES = KCONTAINER_RENDERING__PROPERTIES;

    /**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__PERSISTENT_ENTRIES = KCONTAINER_RENDERING__PERSISTENT_ENTRIES;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__REFERENCES = KCONTAINER_RENDERING__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__STYLES = KCONTAINER_RENDERING__STYLES;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__CHILDREN = KCONTAINER_RENDERING__CHILDREN;

    /**
     * The feature id for the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__CHILD_PLACEMENT = KCONTAINER_RENDERING__CHILD_PLACEMENT;

    /**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__TEXT = KCONTAINER_RENDERING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Clip</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT__CLIP = KCONTAINER_RENDERING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KText</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KTEXT_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.KPlacement <em>KPlacement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.KPlacement
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPlacement()
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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementImpl <em>KGrid Placement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KGridPlacementImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKGridPlacement()
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
     * The number of structural features of the '<em>KGrid Placement</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_FEATURE_COUNT = KPLACEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KStackPlacementImpl <em>KStack Placement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KStackPlacementImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKStackPlacement()
     * @generated
     */
    int KSTACK_PLACEMENT = 18;

    /**
     * The number of structural features of the '<em>KStack Placement</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTACK_PLACEMENT_FEATURE_COUNT = KPLACEMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl <em>KGrid Placement Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKGridPlacementData()
     * @generated
     */
    int KGRID_PLACEMENT_DATA = 20;

    /**
     * The feature id for the '<em><b>Width Hint</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA__WIDTH_HINT = KPLACEMENT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height Hint</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA__HEIGHT_HINT = KPLACEMENT_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Horizontal Indent</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA__HORIZONTAL_INDENT = KPLACEMENT_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Vertical Indent</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA__VERTICAL_INDENT = KPLACEMENT_DATA_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>KGrid Placement Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRID_PLACEMENT_DATA_FEATURE_COUNT = KPLACEMENT_DATA_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KStackPlacementDataImpl <em>KStack Placement Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KStackPlacementDataImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKStackPlacementData()
     * @generated
     */
    int KSTACK_PLACEMENT_DATA = 21;

    /**
     * The feature id for the '<em><b>Inset Right</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTACK_PLACEMENT_DATA__INSET_RIGHT = KPLACEMENT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Inset Bottom</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTACK_PLACEMENT_DATA__INSET_BOTTOM = KPLACEMENT_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Inset Left</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTACK_PLACEMENT_DATA__INSET_LEFT = KPLACEMENT_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Inset Top</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTACK_PLACEMENT_DATA__INSET_TOP = KPLACEMENT_DATA_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>KStack Placement Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSTACK_PLACEMENT_DATA_FEATURE_COUNT = KPLACEMENT_DATA_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KDirectPlacementDataImpl <em>KDirect Placement Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KDirectPlacementDataImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKDirectPlacementData()
     * @generated
     */
    int KDIRECT_PLACEMENT_DATA = 22;

    /**
     * The feature id for the '<em><b>Top Left</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDIRECT_PLACEMENT_DATA__TOP_LEFT = KPLACEMENT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Bottom Right</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDIRECT_PLACEMENT_DATA__BOTTOM_RIGHT = KPLACEMENT_DATA_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KDirect Placement Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KDIRECT_PLACEMENT_DATA_FEATURE_COUNT = KPLACEMENT_DATA_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KPolylinePlacementDataImpl <em>KPolyline Placement Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KPolylinePlacementDataImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPolylinePlacementData()
     * @generated
     */
    int KPOLYLINE_PLACEMENT_DATA = 23;

    /**
     * The feature id for the '<em><b>Points</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE_PLACEMENT_DATA__POINTS = KPLACEMENT_DATA_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KPolyline Placement Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPOLYLINE_PLACEMENT_DATA_FEATURE_COUNT = KPLACEMENT_DATA_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KCustomRenderingImpl <em>KCustom Rendering</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KCustomRenderingImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKCustomRendering()
     * @generated
     */
    int KCUSTOM_RENDERING = 24;

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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__PARENT = KCONTAINER_RENDERING__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__REFERENCES = KCONTAINER_RENDERING__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__PLACEMENT_DATA = KCONTAINER_RENDERING__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING__STYLES = KCONTAINER_RENDERING__STYLES;

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
     * The number of structural features of the '<em>KCustom Rendering</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCUSTOM_RENDERING_FEATURE_COUNT = KCONTAINER_RENDERING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KColorImpl <em>KColor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KColorImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKColor()
     * @generated
     */
    int KCOLOR = 26;

    /**
     * The feature id for the '<em><b>Rendering</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLOR__RENDERING = KSTYLE__RENDERING;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLOR__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLOR__RED = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLOR__GREEN = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLOR__BLUE = KSTYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>KColor</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KCOLOR_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KForegroundColorImpl <em>KForeground Color</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KForegroundColorImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKForegroundColor()
     * @generated
     */
    int KFOREGROUND_COLOR = 25;

    /**
     * The feature id for the '<em><b>Rendering</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND_COLOR__RENDERING = KCOLOR__RENDERING;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND_COLOR__PROPAGATE_TO_CHILDREN = KCOLOR__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND_COLOR__RED = KCOLOR__RED;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND_COLOR__GREEN = KCOLOR__GREEN;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND_COLOR__BLUE = KCOLOR__BLUE;

    /**
     * The number of structural features of the '<em>KForeground Color</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KFOREGROUND_COLOR_FEATURE_COUNT = KCOLOR_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KBackgroundColorImpl <em>KBackground Color</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KBackgroundColorImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKBackgroundColor()
     * @generated
     */
    int KBACKGROUND_COLOR = 27;

    /**
     * The feature id for the '<em><b>Rendering</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND_COLOR__RENDERING = KCOLOR__RENDERING;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND_COLOR__PROPAGATE_TO_CHILDREN = KCOLOR__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND_COLOR__RED = KCOLOR__RED;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND_COLOR__GREEN = KCOLOR__GREEN;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND_COLOR__BLUE = KCOLOR__BLUE;

    /**
     * The number of structural features of the '<em>KBackground Color</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KBACKGROUND_COLOR_FEATURE_COUNT = KCOLOR_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KLineWidthImpl <em>KLine Width</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KLineWidthImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKLineWidth()
     * @generated
     */
    int KLINE_WIDTH = 28;

    /**
     * The feature id for the '<em><b>Rendering</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_WIDTH__RENDERING = KSTYLE__RENDERING;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_WIDTH__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KVisibilityImpl <em>KVisibility</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KVisibilityImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKVisibility()
     * @generated
     */
    int KVISIBILITY = 29;

    /**
     * The feature id for the '<em><b>Rendering</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVISIBILITY__RENDERING = KSTYLE__RENDERING;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVISIBILITY__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Line Visible</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVISIBILITY__LINE_VISIBLE = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVISIBILITY__FILLED = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KVisibility</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVISIBILITY_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KLineStyleImpl <em>KLine Style</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KLineStyleImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKLineStyle()
     * @generated
     */
    int KLINE_STYLE = 30;

    /**
     * The feature id for the '<em><b>Rendering</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE__RENDERING = KSTYLE__RENDERING;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE__LINE_STYLE = KSTYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>KLine Style</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLINE_STYLE_FEATURE_COUNT = KSTYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KVerticalAlignmentImpl <em>KVertical Alignment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KVerticalAlignmentImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKVerticalAlignment()
     * @generated
     */
    int KVERTICAL_ALIGNMENT = 31;

    /**
     * The feature id for the '<em><b>Rendering</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVERTICAL_ALIGNMENT__RENDERING = KSTYLE__RENDERING;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KVERTICAL_ALIGNMENT__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KHorizontalAlignmentImpl <em>KHorizontal Alignment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KHorizontalAlignmentImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKHorizontalAlignment()
     * @generated
     */
    int KHORIZONTAL_ALIGNMENT = 32;

    /**
     * The feature id for the '<em><b>Rendering</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KHORIZONTAL_ALIGNMENT__RENDERING = KSTYLE__RENDERING;

    /**
     * The feature id for the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KHORIZONTAL_ALIGNMENT__PROPAGATE_TO_CHILDREN = KSTYLE__PROPAGATE_TO_CHILDREN;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KXPositionImpl <em>KX Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KXPositionImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKXPosition()
     * @generated
     */
    int KX_POSITION = 33;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KYPositionImpl <em>KY Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KYPositionImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKYPosition()
     * @generated
     */
    int KY_POSITION = 34;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KLeftPositionImpl <em>KLeft Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KLeftPositionImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKLeftPosition()
     * @generated
     */
    int KLEFT_POSITION = 35;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KRightPositionImpl <em>KRight Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KRightPositionImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRightPosition()
     * @generated
     */
    int KRIGHT_POSITION = 36;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KTopPositionImpl <em>KTop Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KTopPositionImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKTopPosition()
     * @generated
     */
    int KTOP_POSITION = 37;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KBottomPositionImpl <em>KBottom Position</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KBottomPositionImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKBottomPosition()
     * @generated
     */
    int KBOTTOM_POSITION = 38;

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
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.impl.KSplineImpl <em>KSpline</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.impl.KSplineImpl
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKSpline()
     * @generated
     */
    int KSPLINE = 39;

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
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__PARENT = KPOLYLINE__PARENT;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__REFERENCES = KPOLYLINE__REFERENCES;

    /**
     * The feature id for the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__PLACEMENT_DATA = KPOLYLINE__PLACEMENT_DATA;

    /**
     * The feature id for the '<em><b>Styles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE__STYLES = KPOLYLINE__STYLES;

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
     * The number of structural features of the '<em>KSpline</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KSPLINE_FEATURE_COUNT = KPOLYLINE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.LineStyle <em>Line Style</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.LineStyle
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getLineStyle()
     * @generated
     */
    int LINE_STYLE = 40;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.HorizontalAlignment <em>Horizontal Alignment</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.HorizontalAlignment
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getHorizontalAlignment()
     * @generated
     */
    int HORIZONTAL_ALIGNMENT = 41;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.krendering.VerticalAlignment <em>Vertical Alignment</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.krendering.VerticalAlignment
     * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getVerticalAlignment()
     * @generated
     */
    int VERTICAL_ALIGNMENT = 42;


    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KPosition <em>KPosition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPosition</em>'.
     * @see de.cau.cs.kieler.core.krendering.KPosition
     * @generated
     */
    EClass getKPosition();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.core.krendering.KPosition#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>X</em>'.
     * @see de.cau.cs.kieler.core.krendering.KPosition#getX()
     * @see #getKPosition()
     * @generated
     */
    EReference getKPosition_X();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.core.krendering.KPosition#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Y</em>'.
     * @see de.cau.cs.kieler.core.krendering.KPosition#getY()
     * @see #getKPosition()
     * @generated
     */
    EReference getKPosition_Y();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KRendering <em>KRendering</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KRendering</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRendering
     * @generated
     */
    EClass getKRendering();

    /**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.core.krendering.KRendering#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRendering#getParent()
     * @see #getKRendering()
     * @generated
     */
    EReference getKRendering_Parent();

    /**
     * Returns the meta object for the reference list '{@link de.cau.cs.kieler.core.krendering.KRendering#getReferences <em>References</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>References</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRendering#getReferences()
     * @see #getKRendering()
     * @generated
     */
    EReference getKRendering_References();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.core.krendering.KRendering#getPlacementData <em>Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Placement Data</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRendering#getPlacementData()
     * @see #getKRendering()
     * @generated
     */
    EReference getKRendering_PlacementData();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.core.krendering.KRendering#getStyles <em>Styles</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Styles</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRendering#getStyles()
     * @see #getKRendering()
     * @generated
     */
    EReference getKRendering_Styles();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KEllipse <em>KEllipse</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KEllipse</em>'.
     * @see de.cau.cs.kieler.core.krendering.KEllipse
     * @generated
     */
    EClass getKEllipse();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KRectangle <em>KRectangle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KRectangle</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRectangle
     * @generated
     */
    EClass getKRectangle();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KRoundedRectangle <em>KRounded Rectangle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KRounded Rectangle</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRoundedRectangle
     * @generated
     */
    EClass getKRoundedRectangle();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KRoundedRectangle#getCornerWidth <em>Corner Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Corner Width</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRoundedRectangle#getCornerWidth()
     * @see #getKRoundedRectangle()
     * @generated
     */
    EAttribute getKRoundedRectangle_CornerWidth();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KRoundedRectangle#getCornerHeight <em>Corner Height</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Corner Height</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRoundedRectangle#getCornerHeight()
     * @see #getKRoundedRectangle()
     * @generated
     */
    EAttribute getKRoundedRectangle_CornerHeight();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KPolyline <em>KPolyline</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPolyline</em>'.
     * @see de.cau.cs.kieler.core.krendering.KPolyline
     * @generated
     */
    EClass getKPolyline();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KPolygon <em>KPolygon</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPolygon</em>'.
     * @see de.cau.cs.kieler.core.krendering.KPolygon
     * @generated
     */
    EClass getKPolygon();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KImage <em>KImage</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KImage</em>'.
     * @see de.cau.cs.kieler.core.krendering.KImage
     * @generated
     */
    EClass getKImage();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KImage#getBundleName <em>Bundle Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bundle Name</em>'.
     * @see de.cau.cs.kieler.core.krendering.KImage#getBundleName()
     * @see #getKImage()
     * @generated
     */
    EAttribute getKImage_BundleName();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KImage#getImagePath <em>Image Path</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Image Path</em>'.
     * @see de.cau.cs.kieler.core.krendering.KImage#getImagePath()
     * @see #getKImage()
     * @generated
     */
    EAttribute getKImage_ImagePath();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KDecoratorPlacementData <em>KDecorator Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KDecorator Placement Data</em>'.
     * @see de.cau.cs.kieler.core.krendering.KDecoratorPlacementData
     * @generated
     */
    EClass getKDecoratorPlacementData();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KDecoratorPlacementData#getLocation <em>Location</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Location</em>'.
     * @see de.cau.cs.kieler.core.krendering.KDecoratorPlacementData#getLocation()
     * @see #getKDecoratorPlacementData()
     * @generated
     */
    EAttribute getKDecoratorPlacementData_Location();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KDecoratorPlacementData#getXOffset <em>XOffset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>XOffset</em>'.
     * @see de.cau.cs.kieler.core.krendering.KDecoratorPlacementData#getXOffset()
     * @see #getKDecoratorPlacementData()
     * @generated
     */
    EAttribute getKDecoratorPlacementData_XOffset();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KDecoratorPlacementData#getYOffset <em>YOffset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>YOffset</em>'.
     * @see de.cau.cs.kieler.core.krendering.KDecoratorPlacementData#getYOffset()
     * @see #getKDecoratorPlacementData()
     * @generated
     */
    EAttribute getKDecoratorPlacementData_YOffset();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KDecoratorPlacementData#isRelative <em>Relative</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relative</em>'.
     * @see de.cau.cs.kieler.core.krendering.KDecoratorPlacementData#isRelative()
     * @see #getKDecoratorPlacementData()
     * @generated
     */
    EAttribute getKDecoratorPlacementData_Relative();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KContainerRendering <em>KContainer Rendering</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KContainer Rendering</em>'.
     * @see de.cau.cs.kieler.core.krendering.KContainerRendering
     * @generated
     */
    EClass getKContainerRendering();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.core.krendering.KContainerRendering#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see de.cau.cs.kieler.core.krendering.KContainerRendering#getChildren()
     * @see #getKContainerRendering()
     * @generated
     */
    EReference getKContainerRendering_Children();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.core.krendering.KContainerRendering#getChildPlacement <em>Child Placement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Child Placement</em>'.
     * @see de.cau.cs.kieler.core.krendering.KContainerRendering#getChildPlacement()
     * @see #getKContainerRendering()
     * @generated
     */
    EReference getKContainerRendering_ChildPlacement();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KArc <em>KArc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KArc</em>'.
     * @see de.cau.cs.kieler.core.krendering.KArc
     * @generated
     */
    EClass getKArc();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KArc#getStartAngle <em>Start Angle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Start Angle</em>'.
     * @see de.cau.cs.kieler.core.krendering.KArc#getStartAngle()
     * @see #getKArc()
     * @generated
     */
    EAttribute getKArc_StartAngle();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KArc#getArcAngle <em>Arc Angle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Arc Angle</em>'.
     * @see de.cau.cs.kieler.core.krendering.KArc#getArcAngle()
     * @see #getKArc()
     * @generated
     */
    EAttribute getKArc_ArcAngle();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KStyle <em>KStyle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KStyle</em>'.
     * @see de.cau.cs.kieler.core.krendering.KStyle
     * @generated
     */
    EClass getKStyle();

    /**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.core.krendering.KStyle#getRendering <em>Rendering</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Rendering</em>'.
     * @see de.cau.cs.kieler.core.krendering.KStyle#getRendering()
     * @see #getKStyle()
     * @generated
     */
    EReference getKStyle_Rendering();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KStyle#isPropagateToChildren <em>Propagate To Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Propagate To Children</em>'.
     * @see de.cau.cs.kieler.core.krendering.KStyle#isPropagateToChildren()
     * @see #getKStyle()
     * @generated
     */
    EAttribute getKStyle_PropagateToChildren();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KRenderingLibrary <em>Library</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Library</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRenderingLibrary
     * @generated
     */
    EClass getKRenderingLibrary();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.core.krendering.KRenderingLibrary#getRenderings <em>Renderings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Renderings</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRenderingLibrary#getRenderings()
     * @see #getKRenderingLibrary()
     * @generated
     */
    EReference getKRenderingLibrary_Renderings();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KRenderingRef <em>Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Ref</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRenderingRef
     * @generated
     */
    EClass getKRenderingRef();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.core.krendering.KRenderingRef#getRendering <em>Rendering</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Rendering</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRenderingRef#getRendering()
     * @see #getKRenderingRef()
     * @generated
     */
    EReference getKRenderingRef_Rendering();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KChildArea <em>KChild Area</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KChild Area</em>'.
     * @see de.cau.cs.kieler.core.krendering.KChildArea
     * @generated
     */
    EClass getKChildArea();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KText <em>KText</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KText</em>'.
     * @see de.cau.cs.kieler.core.krendering.KText
     * @generated
     */
    EClass getKText();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KText#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see de.cau.cs.kieler.core.krendering.KText#getText()
     * @see #getKText()
     * @generated
     */
    EAttribute getKText_Text();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KText#isClip <em>Clip</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Clip</em>'.
     * @see de.cau.cs.kieler.core.krendering.KText#isClip()
     * @see #getKText()
     * @generated
     */
    EAttribute getKText_Clip();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KPlacement <em>KPlacement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPlacement</em>'.
     * @see de.cau.cs.kieler.core.krendering.KPlacement
     * @generated
     */
    EClass getKPlacement();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KGridPlacement <em>KGrid Placement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KGrid Placement</em>'.
     * @see de.cau.cs.kieler.core.krendering.KGridPlacement
     * @generated
     */
    EClass getKGridPlacement();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KGridPlacement#getNumColumns <em>Num Columns</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Num Columns</em>'.
     * @see de.cau.cs.kieler.core.krendering.KGridPlacement#getNumColumns()
     * @see #getKGridPlacement()
     * @generated
     */
    EAttribute getKGridPlacement_NumColumns();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KStackPlacement <em>KStack Placement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KStack Placement</em>'.
     * @see de.cau.cs.kieler.core.krendering.KStackPlacement
     * @generated
     */
    EClass getKStackPlacement();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KPlacementData <em>KPlacement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPlacement Data</em>'.
     * @see de.cau.cs.kieler.core.krendering.KPlacementData
     * @generated
     */
    EClass getKPlacementData();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KGridPlacementData <em>KGrid Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KGrid Placement Data</em>'.
     * @see de.cau.cs.kieler.core.krendering.KGridPlacementData
     * @generated
     */
    EClass getKGridPlacementData();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getWidthHint <em>Width Hint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width Hint</em>'.
     * @see de.cau.cs.kieler.core.krendering.KGridPlacementData#getWidthHint()
     * @see #getKGridPlacementData()
     * @generated
     */
    EAttribute getKGridPlacementData_WidthHint();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getHeightHint <em>Height Hint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height Hint</em>'.
     * @see de.cau.cs.kieler.core.krendering.KGridPlacementData#getHeightHint()
     * @see #getKGridPlacementData()
     * @generated
     */
    EAttribute getKGridPlacementData_HeightHint();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getHorizontalIndent <em>Horizontal Indent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Horizontal Indent</em>'.
     * @see de.cau.cs.kieler.core.krendering.KGridPlacementData#getHorizontalIndent()
     * @see #getKGridPlacementData()
     * @generated
     */
    EAttribute getKGridPlacementData_HorizontalIndent();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getVerticalIndent <em>Vertical Indent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Vertical Indent</em>'.
     * @see de.cau.cs.kieler.core.krendering.KGridPlacementData#getVerticalIndent()
     * @see #getKGridPlacementData()
     * @generated
     */
    EAttribute getKGridPlacementData_VerticalIndent();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KStackPlacementData <em>KStack Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KStack Placement Data</em>'.
     * @see de.cau.cs.kieler.core.krendering.KStackPlacementData
     * @generated
     */
    EClass getKStackPlacementData();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KStackPlacementData#getInsetRight <em>Inset Right</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Inset Right</em>'.
     * @see de.cau.cs.kieler.core.krendering.KStackPlacementData#getInsetRight()
     * @see #getKStackPlacementData()
     * @generated
     */
    EAttribute getKStackPlacementData_InsetRight();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KStackPlacementData#getInsetBottom <em>Inset Bottom</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Inset Bottom</em>'.
     * @see de.cau.cs.kieler.core.krendering.KStackPlacementData#getInsetBottom()
     * @see #getKStackPlacementData()
     * @generated
     */
    EAttribute getKStackPlacementData_InsetBottom();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KStackPlacementData#getInsetLeft <em>Inset Left</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Inset Left</em>'.
     * @see de.cau.cs.kieler.core.krendering.KStackPlacementData#getInsetLeft()
     * @see #getKStackPlacementData()
     * @generated
     */
    EAttribute getKStackPlacementData_InsetLeft();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KStackPlacementData#getInsetTop <em>Inset Top</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Inset Top</em>'.
     * @see de.cau.cs.kieler.core.krendering.KStackPlacementData#getInsetTop()
     * @see #getKStackPlacementData()
     * @generated
     */
    EAttribute getKStackPlacementData_InsetTop();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KDirectPlacementData <em>KDirect Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KDirect Placement Data</em>'.
     * @see de.cau.cs.kieler.core.krendering.KDirectPlacementData
     * @generated
     */
    EClass getKDirectPlacementData();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.core.krendering.KDirectPlacementData#getTopLeft <em>Top Left</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Top Left</em>'.
     * @see de.cau.cs.kieler.core.krendering.KDirectPlacementData#getTopLeft()
     * @see #getKDirectPlacementData()
     * @generated
     */
    EReference getKDirectPlacementData_TopLeft();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.core.krendering.KDirectPlacementData#getBottomRight <em>Bottom Right</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Bottom Right</em>'.
     * @see de.cau.cs.kieler.core.krendering.KDirectPlacementData#getBottomRight()
     * @see #getKDirectPlacementData()
     * @generated
     */
    EReference getKDirectPlacementData_BottomRight();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KPolylinePlacementData <em>KPolyline Placement Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPolyline Placement Data</em>'.
     * @see de.cau.cs.kieler.core.krendering.KPolylinePlacementData
     * @generated
     */
    EClass getKPolylinePlacementData();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.core.krendering.KPolylinePlacementData#getPoints <em>Points</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Points</em>'.
     * @see de.cau.cs.kieler.core.krendering.KPolylinePlacementData#getPoints()
     * @see #getKPolylinePlacementData()
     * @generated
     */
    EReference getKPolylinePlacementData_Points();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KCustomRendering <em>KCustom Rendering</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KCustom Rendering</em>'.
     * @see de.cau.cs.kieler.core.krendering.KCustomRendering
     * @generated
     */
    EClass getKCustomRendering();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KCustomRendering#getClassName <em>Class Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Class Name</em>'.
     * @see de.cau.cs.kieler.core.krendering.KCustomRendering#getClassName()
     * @see #getKCustomRendering()
     * @generated
     */
    EAttribute getKCustomRendering_ClassName();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KCustomRendering#getBundleName <em>Bundle Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bundle Name</em>'.
     * @see de.cau.cs.kieler.core.krendering.KCustomRendering#getBundleName()
     * @see #getKCustomRendering()
     * @generated
     */
    EAttribute getKCustomRendering_BundleName();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KForegroundColor <em>KForeground Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KForeground Color</em>'.
     * @see de.cau.cs.kieler.core.krendering.KForegroundColor
     * @generated
     */
    EClass getKForegroundColor();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KColor <em>KColor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KColor</em>'.
     * @see de.cau.cs.kieler.core.krendering.KColor
     * @generated
     */
    EClass getKColor();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KColor#getRed <em>Red</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Red</em>'.
     * @see de.cau.cs.kieler.core.krendering.KColor#getRed()
     * @see #getKColor()
     * @generated
     */
    EAttribute getKColor_Red();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KColor#getGreen <em>Green</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Green</em>'.
     * @see de.cau.cs.kieler.core.krendering.KColor#getGreen()
     * @see #getKColor()
     * @generated
     */
    EAttribute getKColor_Green();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KColor#getBlue <em>Blue</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Blue</em>'.
     * @see de.cau.cs.kieler.core.krendering.KColor#getBlue()
     * @see #getKColor()
     * @generated
     */
    EAttribute getKColor_Blue();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KBackgroundColor <em>KBackground Color</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KBackground Color</em>'.
     * @see de.cau.cs.kieler.core.krendering.KBackgroundColor
     * @generated
     */
    EClass getKBackgroundColor();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KLineWidth <em>KLine Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLine Width</em>'.
     * @see de.cau.cs.kieler.core.krendering.KLineWidth
     * @generated
     */
    EClass getKLineWidth();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KLineWidth#getLineWidth <em>Line Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Line Width</em>'.
     * @see de.cau.cs.kieler.core.krendering.KLineWidth#getLineWidth()
     * @see #getKLineWidth()
     * @generated
     */
    EAttribute getKLineWidth_LineWidth();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KVisibility <em>KVisibility</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KVisibility</em>'.
     * @see de.cau.cs.kieler.core.krendering.KVisibility
     * @generated
     */
    EClass getKVisibility();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KVisibility#isLineVisible <em>Line Visible</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Line Visible</em>'.
     * @see de.cau.cs.kieler.core.krendering.KVisibility#isLineVisible()
     * @see #getKVisibility()
     * @generated
     */
    EAttribute getKVisibility_LineVisible();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KVisibility#isFilled <em>Filled</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Filled</em>'.
     * @see de.cau.cs.kieler.core.krendering.KVisibility#isFilled()
     * @see #getKVisibility()
     * @generated
     */
    EAttribute getKVisibility_Filled();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KLineStyle <em>KLine Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLine Style</em>'.
     * @see de.cau.cs.kieler.core.krendering.KLineStyle
     * @generated
     */
    EClass getKLineStyle();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KLineStyle#getLineStyle <em>Line Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Line Style</em>'.
     * @see de.cau.cs.kieler.core.krendering.KLineStyle#getLineStyle()
     * @see #getKLineStyle()
     * @generated
     */
    EAttribute getKLineStyle_LineStyle();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KVerticalAlignment <em>KVertical Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KVertical Alignment</em>'.
     * @see de.cau.cs.kieler.core.krendering.KVerticalAlignment
     * @generated
     */
    EClass getKVerticalAlignment();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KVerticalAlignment#getVerticalAlignment <em>Vertical Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Vertical Alignment</em>'.
     * @see de.cau.cs.kieler.core.krendering.KVerticalAlignment#getVerticalAlignment()
     * @see #getKVerticalAlignment()
     * @generated
     */
    EAttribute getKVerticalAlignment_VerticalAlignment();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KHorizontalAlignment <em>KHorizontal Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KHorizontal Alignment</em>'.
     * @see de.cau.cs.kieler.core.krendering.KHorizontalAlignment
     * @generated
     */
    EClass getKHorizontalAlignment();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KHorizontalAlignment#getHorizontalAlignment <em>Horizontal Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Horizontal Alignment</em>'.
     * @see de.cau.cs.kieler.core.krendering.KHorizontalAlignment#getHorizontalAlignment()
     * @see #getKHorizontalAlignment()
     * @generated
     */
    EAttribute getKHorizontalAlignment_HorizontalAlignment();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KXPosition <em>KX Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KX Position</em>'.
     * @see de.cau.cs.kieler.core.krendering.KXPosition
     * @generated
     */
    EClass getKXPosition();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KXPosition#getAbsolute <em>Absolute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Absolute</em>'.
     * @see de.cau.cs.kieler.core.krendering.KXPosition#getAbsolute()
     * @see #getKXPosition()
     * @generated
     */
    EAttribute getKXPosition_Absolute();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KXPosition#getRelative <em>Relative</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relative</em>'.
     * @see de.cau.cs.kieler.core.krendering.KXPosition#getRelative()
     * @see #getKXPosition()
     * @generated
     */
    EAttribute getKXPosition_Relative();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KYPosition <em>KY Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KY Position</em>'.
     * @see de.cau.cs.kieler.core.krendering.KYPosition
     * @generated
     */
    EClass getKYPosition();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KYPosition#getAbsolute <em>Absolute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Absolute</em>'.
     * @see de.cau.cs.kieler.core.krendering.KYPosition#getAbsolute()
     * @see #getKYPosition()
     * @generated
     */
    EAttribute getKYPosition_Absolute();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.krendering.KYPosition#getRelative <em>Relative</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Relative</em>'.
     * @see de.cau.cs.kieler.core.krendering.KYPosition#getRelative()
     * @see #getKYPosition()
     * @generated
     */
    EAttribute getKYPosition_Relative();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KLeftPosition <em>KLeft Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLeft Position</em>'.
     * @see de.cau.cs.kieler.core.krendering.KLeftPosition
     * @generated
     */
    EClass getKLeftPosition();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KRightPosition <em>KRight Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KRight Position</em>'.
     * @see de.cau.cs.kieler.core.krendering.KRightPosition
     * @generated
     */
    EClass getKRightPosition();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KTopPosition <em>KTop Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KTop Position</em>'.
     * @see de.cau.cs.kieler.core.krendering.KTopPosition
     * @generated
     */
    EClass getKTopPosition();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KBottomPosition <em>KBottom Position</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KBottom Position</em>'.
     * @see de.cau.cs.kieler.core.krendering.KBottomPosition
     * @generated
     */
    EClass getKBottomPosition();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.krendering.KSpline <em>KSpline</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KSpline</em>'.
     * @see de.cau.cs.kieler.core.krendering.KSpline
     * @generated
     */
    EClass getKSpline();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.core.krendering.LineStyle <em>Line Style</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Line Style</em>'.
     * @see de.cau.cs.kieler.core.krendering.LineStyle
     * @generated
     */
    EEnum getLineStyle();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.core.krendering.HorizontalAlignment <em>Horizontal Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Horizontal Alignment</em>'.
     * @see de.cau.cs.kieler.core.krendering.HorizontalAlignment
     * @generated
     */
    EEnum getHorizontalAlignment();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.core.krendering.VerticalAlignment <em>Vertical Alignment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Vertical Alignment</em>'.
     * @see de.cau.cs.kieler.core.krendering.VerticalAlignment
     * @generated
     */
    EEnum getVerticalAlignment();

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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KPositionImpl <em>KPosition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KPositionImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPosition()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KRenderingImpl <em>KRendering</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRendering()
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
         * The meta object literal for the '<em><b>References</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KRENDERING__REFERENCES = eINSTANCE.getKRendering_References();

        /**
         * The meta object literal for the '<em><b>Placement Data</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KRENDERING__PLACEMENT_DATA = eINSTANCE.getKRendering_PlacementData();

        /**
         * The meta object literal for the '<em><b>Styles</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KRENDERING__STYLES = eINSTANCE.getKRendering_Styles();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KEllipseImpl <em>KEllipse</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KEllipseImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKEllipse()
         * @generated
         */
        EClass KELLIPSE = eINSTANCE.getKEllipse();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KRectangleImpl <em>KRectangle</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KRectangleImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRectangle()
         * @generated
         */
        EClass KRECTANGLE = eINSTANCE.getKRectangle();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KRoundedRectangleImpl <em>KRounded Rectangle</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KRoundedRectangleImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRoundedRectangle()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KPolylineImpl <em>KPolyline</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KPolylineImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPolyline()
         * @generated
         */
        EClass KPOLYLINE = eINSTANCE.getKPolyline();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KPolygonImpl <em>KPolygon</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KPolygonImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPolygon()
         * @generated
         */
        EClass KPOLYGON = eINSTANCE.getKPolygon();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KImageImpl <em>KImage</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KImageImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKImage()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KDecoratorPlacementDataImpl <em>KDecorator Placement Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KDecoratorPlacementDataImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKDecoratorPlacementData()
         * @generated
         */
        EClass KDECORATOR_PLACEMENT_DATA = eINSTANCE.getKDecoratorPlacementData();

        /**
         * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KDECORATOR_PLACEMENT_DATA__LOCATION = eINSTANCE.getKDecoratorPlacementData_Location();

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
         * The meta object literal for the '<em><b>Relative</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KDECORATOR_PLACEMENT_DATA__RELATIVE = eINSTANCE.getKDecoratorPlacementData_Relative();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KContainerRenderingImpl <em>KContainer Rendering</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KContainerRenderingImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKContainerRendering()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KArcImpl <em>KArc</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KArcImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKArc()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KStyleImpl <em>KStyle</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KStyleImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKStyle()
         * @generated
         */
        EClass KSTYLE = eINSTANCE.getKStyle();

        /**
         * The meta object literal for the '<em><b>Rendering</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KSTYLE__RENDERING = eINSTANCE.getKStyle_Rendering();

        /**
         * The meta object literal for the '<em><b>Propagate To Children</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTYLE__PROPAGATE_TO_CHILDREN = eINSTANCE.getKStyle_PropagateToChildren();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KRenderingLibraryImpl <em>Library</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingLibraryImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRenderingLibrary()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KRenderingRefImpl <em>Ref</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingRefImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRenderingRef()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KChildAreaImpl <em>KChild Area</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KChildAreaImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKChildArea()
         * @generated
         */
        EClass KCHILD_AREA = eINSTANCE.getKChildArea();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KTextImpl <em>KText</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KTextImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKText()
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
         * The meta object literal for the '<em><b>Clip</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KTEXT__CLIP = eINSTANCE.getKText_Clip();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.KPlacement <em>KPlacement</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.KPlacement
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPlacement()
         * @generated
         */
        EClass KPLACEMENT = eINSTANCE.getKPlacement();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementImpl <em>KGrid Placement</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KGridPlacementImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKGridPlacement()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KStackPlacementImpl <em>KStack Placement</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KStackPlacementImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKStackPlacement()
         * @generated
         */
        EClass KSTACK_PLACEMENT = eINSTANCE.getKStackPlacement();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.KPlacementData <em>KPlacement Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.KPlacementData
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPlacementData()
         * @generated
         */
        EClass KPLACEMENT_DATA = eINSTANCE.getKPlacementData();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl <em>KGrid Placement Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKGridPlacementData()
         * @generated
         */
        EClass KGRID_PLACEMENT_DATA = eINSTANCE.getKGridPlacementData();

        /**
         * The meta object literal for the '<em><b>Width Hint</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KGRID_PLACEMENT_DATA__WIDTH_HINT = eINSTANCE.getKGridPlacementData_WidthHint();

        /**
         * The meta object literal for the '<em><b>Height Hint</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KGRID_PLACEMENT_DATA__HEIGHT_HINT = eINSTANCE.getKGridPlacementData_HeightHint();

        /**
         * The meta object literal for the '<em><b>Horizontal Indent</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KGRID_PLACEMENT_DATA__HORIZONTAL_INDENT = eINSTANCE.getKGridPlacementData_HorizontalIndent();

        /**
         * The meta object literal for the '<em><b>Vertical Indent</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KGRID_PLACEMENT_DATA__VERTICAL_INDENT = eINSTANCE.getKGridPlacementData_VerticalIndent();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KStackPlacementDataImpl <em>KStack Placement Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KStackPlacementDataImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKStackPlacementData()
         * @generated
         */
        EClass KSTACK_PLACEMENT_DATA = eINSTANCE.getKStackPlacementData();

        /**
         * The meta object literal for the '<em><b>Inset Right</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTACK_PLACEMENT_DATA__INSET_RIGHT = eINSTANCE.getKStackPlacementData_InsetRight();

        /**
         * The meta object literal for the '<em><b>Inset Bottom</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTACK_PLACEMENT_DATA__INSET_BOTTOM = eINSTANCE.getKStackPlacementData_InsetBottom();

        /**
         * The meta object literal for the '<em><b>Inset Left</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTACK_PLACEMENT_DATA__INSET_LEFT = eINSTANCE.getKStackPlacementData_InsetLeft();

        /**
         * The meta object literal for the '<em><b>Inset Top</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KSTACK_PLACEMENT_DATA__INSET_TOP = eINSTANCE.getKStackPlacementData_InsetTop();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KDirectPlacementDataImpl <em>KDirect Placement Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KDirectPlacementDataImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKDirectPlacementData()
         * @generated
         */
        EClass KDIRECT_PLACEMENT_DATA = eINSTANCE.getKDirectPlacementData();

        /**
         * The meta object literal for the '<em><b>Top Left</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KDIRECT_PLACEMENT_DATA__TOP_LEFT = eINSTANCE.getKDirectPlacementData_TopLeft();

        /**
         * The meta object literal for the '<em><b>Bottom Right</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KDIRECT_PLACEMENT_DATA__BOTTOM_RIGHT = eINSTANCE.getKDirectPlacementData_BottomRight();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KPolylinePlacementDataImpl <em>KPolyline Placement Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KPolylinePlacementDataImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKPolylinePlacementData()
         * @generated
         */
        EClass KPOLYLINE_PLACEMENT_DATA = eINSTANCE.getKPolylinePlacementData();

        /**
         * The meta object literal for the '<em><b>Points</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KPOLYLINE_PLACEMENT_DATA__POINTS = eINSTANCE.getKPolylinePlacementData_Points();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KCustomRenderingImpl <em>KCustom Rendering</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KCustomRenderingImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKCustomRendering()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KForegroundColorImpl <em>KForeground Color</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KForegroundColorImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKForegroundColor()
         * @generated
         */
        EClass KFOREGROUND_COLOR = eINSTANCE.getKForegroundColor();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KColorImpl <em>KColor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KColorImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKColor()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KBackgroundColorImpl <em>KBackground Color</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KBackgroundColorImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKBackgroundColor()
         * @generated
         */
        EClass KBACKGROUND_COLOR = eINSTANCE.getKBackgroundColor();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KLineWidthImpl <em>KLine Width</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KLineWidthImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKLineWidth()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KVisibilityImpl <em>KVisibility</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KVisibilityImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKVisibility()
         * @generated
         */
        EClass KVISIBILITY = eINSTANCE.getKVisibility();

        /**
         * The meta object literal for the '<em><b>Line Visible</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KVISIBILITY__LINE_VISIBLE = eINSTANCE.getKVisibility_LineVisible();

        /**
         * The meta object literal for the '<em><b>Filled</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KVISIBILITY__FILLED = eINSTANCE.getKVisibility_Filled();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KLineStyleImpl <em>KLine Style</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KLineStyleImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKLineStyle()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KVerticalAlignmentImpl <em>KVertical Alignment</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KVerticalAlignmentImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKVerticalAlignment()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KHorizontalAlignmentImpl <em>KHorizontal Alignment</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KHorizontalAlignmentImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKHorizontalAlignment()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KXPositionImpl <em>KX Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KXPositionImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKXPosition()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KYPositionImpl <em>KY Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KYPositionImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKYPosition()
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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KLeftPositionImpl <em>KLeft Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KLeftPositionImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKLeftPosition()
         * @generated
         */
        EClass KLEFT_POSITION = eINSTANCE.getKLeftPosition();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KRightPositionImpl <em>KRight Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KRightPositionImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKRightPosition()
         * @generated
         */
        EClass KRIGHT_POSITION = eINSTANCE.getKRightPosition();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KTopPositionImpl <em>KTop Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KTopPositionImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKTopPosition()
         * @generated
         */
        EClass KTOP_POSITION = eINSTANCE.getKTopPosition();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KBottomPositionImpl <em>KBottom Position</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KBottomPositionImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKBottomPosition()
         * @generated
         */
        EClass KBOTTOM_POSITION = eINSTANCE.getKBottomPosition();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.impl.KSplineImpl <em>KSpline</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.impl.KSplineImpl
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getKSpline()
         * @generated
         */
        EClass KSPLINE = eINSTANCE.getKSpline();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.LineStyle <em>Line Style</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.LineStyle
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getLineStyle()
         * @generated
         */
        EEnum LINE_STYLE = eINSTANCE.getLineStyle();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.HorizontalAlignment <em>Horizontal Alignment</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.HorizontalAlignment
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getHorizontalAlignment()
         * @generated
         */
        EEnum HORIZONTAL_ALIGNMENT = eINSTANCE.getHorizontalAlignment();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.krendering.VerticalAlignment <em>Vertical Alignment</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.krendering.VerticalAlignment
         * @see de.cau.cs.kieler.core.krendering.impl.KRenderingPackageImpl#getVerticalAlignment()
         * @generated
         */
        EEnum VERTICAL_ALIGNMENT = eINSTANCE.getVerticalAlignment();

    }

} //KRenderingPackage
