/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.kgraph;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphFactory
 * @model kind="package"
 * @generated
 */
public interface KGraphPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "kgraph";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://kieler.cs.cau.de/KlighdGraph";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "klighdgraph";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	KGraphPackage eINSTANCE = de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl.init();

	/**
     * The meta object id for the '{@link org.eclipse.elk.graph.properties.IPropertyHolder <em>IProperty Holder</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.elk.graph.properties.IPropertyHolder
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getIPropertyHolder()
     * @generated
     */
	int IPROPERTY_HOLDER = 9;

	/**
     * The number of structural features of the '<em>IProperty Holder</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IPROPERTY_HOLDER_FEATURE_COUNT = 0;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.EMapPropertyHolderImpl <em>EMap Property Holder</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.EMapPropertyHolderImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getEMapPropertyHolder()
     * @generated
     */
	int EMAP_PROPERTY_HOLDER = 7;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EMAP_PROPERTY_HOLDER__PROPERTIES = IPROPERTY_HOLDER_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES = IPROPERTY_HOLDER_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>EMap Property Holder</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EMAP_PROPERTY_HOLDER_FEATURE_COUNT = IPROPERTY_HOLDER_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KGraphElementImpl <em>Element</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphElementImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKGraphElement()
     * @generated
     */
	int KGRAPH_ELEMENT = 0;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KGRAPH_ELEMENT__PROPERTIES = EMAP_PROPERTY_HOLDER__PROPERTIES;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KGRAPH_ELEMENT__PERSISTENT_ENTRIES = EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KGRAPH_ELEMENT__DATA = EMAP_PROPERTY_HOLDER_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KGRAPH_ELEMENT_FEATURE_COUNT = EMAP_PROPERTY_HOLDER_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabeledGraphElementImpl <em>KLabeled Graph Element</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KLabeledGraphElementImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKLabeledGraphElement()
     * @generated
     */
	int KLABELED_GRAPH_ELEMENT = 1;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABELED_GRAPH_ELEMENT__PROPERTIES = KGRAPH_ELEMENT__PROPERTIES;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABELED_GRAPH_ELEMENT__PERSISTENT_ENTRIES = KGRAPH_ELEMENT__PERSISTENT_ENTRIES;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABELED_GRAPH_ELEMENT__DATA = KGRAPH_ELEMENT__DATA;

	/**
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABELED_GRAPH_ELEMENT__LABELS = KGRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>KLabeled Graph Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABELED_GRAPH_ELEMENT_FEATURE_COUNT = KGRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KGraphDataImpl <em>Data</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphDataImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKGraphData()
     * @generated
     */
	int KGRAPH_DATA = 2;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KGRAPH_DATA__PROPERTIES = EMAP_PROPERTY_HOLDER__PROPERTIES;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KGRAPH_DATA__PERSISTENT_ENTRIES = EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES;

	/**
     * The number of structural features of the '<em>Data</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KGRAPH_DATA_FEATURE_COUNT = EMAP_PROPERTY_HOLDER_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl <em>KNode</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKNode()
     * @generated
     */
	int KNODE = 3;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__PROPERTIES = KLABELED_GRAPH_ELEMENT__PROPERTIES;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__PERSISTENT_ENTRIES = KLABELED_GRAPH_ELEMENT__PERSISTENT_ENTRIES;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__DATA = KLABELED_GRAPH_ELEMENT__DATA;

	/**
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__LABELS = KLABELED_GRAPH_ELEMENT__LABELS;

	/**
     * The feature id for the '<em><b>Xpos</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__XPOS = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Ypos</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__YPOS = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__WIDTH = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__HEIGHT = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Insets</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__INSETS = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__CHILDREN = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__PARENT = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Ports</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__PORTS = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__OUTGOING_EDGES = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE__INCOMING_EDGES = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 9;

	/**
     * The number of structural features of the '<em>KNode</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KNODE_FEATURE_COUNT = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 10;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KEdgeImpl <em>KEdge</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KEdgeImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKEdge()
     * @generated
     */
	int KEDGE = 4;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE__PROPERTIES = KLABELED_GRAPH_ELEMENT__PROPERTIES;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE__PERSISTENT_ENTRIES = KLABELED_GRAPH_ELEMENT__PERSISTENT_ENTRIES;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE__DATA = KLABELED_GRAPH_ELEMENT__DATA;

	/**
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE__LABELS = KLABELED_GRAPH_ELEMENT__LABELS;

	/**
     * The feature id for the '<em><b>Bend Points</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE__BEND_POINTS = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Source Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE__SOURCE_POINT = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Target Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE__TARGET_POINT = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Source</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE__SOURCE = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE__TARGET = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Source Port</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE__SOURCE_PORT = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Target Port</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE__TARGET_PORT = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 6;

	/**
     * The number of structural features of the '<em>KEdge</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE_FEATURE_COUNT = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 7;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KPortImpl <em>KPort</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KPortImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKPort()
     * @generated
     */
	int KPORT = 5;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT__PROPERTIES = KLABELED_GRAPH_ELEMENT__PROPERTIES;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT__PERSISTENT_ENTRIES = KLABELED_GRAPH_ELEMENT__PERSISTENT_ENTRIES;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT__DATA = KLABELED_GRAPH_ELEMENT__DATA;

	/**
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT__LABELS = KLABELED_GRAPH_ELEMENT__LABELS;

	/**
     * The feature id for the '<em><b>Xpos</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT__XPOS = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Ypos</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT__YPOS = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT__WIDTH = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT__HEIGHT = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Insets</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT__INSETS = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Node</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT__NODE = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Edges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT__EDGES = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 6;

	/**
     * The number of structural features of the '<em>KPort</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPORT_FEATURE_COUNT = KLABELED_GRAPH_ELEMENT_FEATURE_COUNT + 7;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabelImpl <em>KLabel</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KLabelImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKLabel()
     * @generated
     */
	int KLABEL = 6;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABEL__PROPERTIES = KGRAPH_ELEMENT__PROPERTIES;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABEL__PERSISTENT_ENTRIES = KGRAPH_ELEMENT__PERSISTENT_ENTRIES;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABEL__DATA = KGRAPH_ELEMENT__DATA;

	/**
     * The feature id for the '<em><b>Xpos</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABEL__XPOS = KGRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Ypos</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABEL__YPOS = KGRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABEL__WIDTH = KGRAPH_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABEL__HEIGHT = KGRAPH_ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Insets</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABEL__INSETS = KGRAPH_ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABEL__TEXT = KGRAPH_ELEMENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABEL__PARENT = KGRAPH_ELEMENT_FEATURE_COUNT + 6;

	/**
     * The number of structural features of the '<em>KLabel</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLABEL_FEATURE_COUNT = KGRAPH_ELEMENT_FEATURE_COUNT + 7;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.IPropertyToObjectMapImpl <em>IProperty To Object Map</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.IPropertyToObjectMapImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getIPropertyToObjectMap()
     * @generated
     */
	int IPROPERTY_TO_OBJECT_MAP = 8;

	/**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IPROPERTY_TO_OBJECT_MAP__KEY = 0;

	/**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IPROPERTY_TO_OBJECT_MAP__VALUE = 1;

	/**
     * The number of structural features of the '<em>IProperty To Object Map</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IPROPERTY_TO_OBJECT_MAP_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.PersistentEntryImpl <em>Persistent Entry</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.PersistentEntryImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getPersistentEntry()
     * @generated
     */
	int PERSISTENT_ENTRY = 10;

	/**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERSISTENT_ENTRY__KEY = 0;

	/**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERSISTENT_ENTRY__VALUE = 1;

	/**
     * The number of structural features of the '<em>Persistent Entry</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERSISTENT_ENTRY_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.KLayoutData <em>KLayout Data</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.KLayoutData
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKLayoutData()
     * @generated
     */
	int KLAYOUT_DATA = 13;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLAYOUT_DATA__PROPERTIES = KGRAPH_DATA__PROPERTIES;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLAYOUT_DATA__PERSISTENT_ENTRIES = KGRAPH_DATA__PERSISTENT_ENTRIES;

	/**
     * The number of structural features of the '<em>KLayout Data</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KLAYOUT_DATA_FEATURE_COUNT = KGRAPH_DATA_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout <em>KShape Layout</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.KShapeLayout
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKShapeLayout()
     * @generated
     */
	int KSHAPE_LAYOUT = 11;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KSHAPE_LAYOUT__PROPERTIES = KLAYOUT_DATA__PROPERTIES;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KSHAPE_LAYOUT__PERSISTENT_ENTRIES = KLAYOUT_DATA__PERSISTENT_ENTRIES;

	/**
     * The feature id for the '<em><b>Xpos</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KSHAPE_LAYOUT__XPOS = KLAYOUT_DATA_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Ypos</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KSHAPE_LAYOUT__YPOS = KLAYOUT_DATA_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KSHAPE_LAYOUT__WIDTH = KLAYOUT_DATA_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KSHAPE_LAYOUT__HEIGHT = KLAYOUT_DATA_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Insets</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KSHAPE_LAYOUT__INSETS = KLAYOUT_DATA_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>KShape Layout</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KSHAPE_LAYOUT_FEATURE_COUNT = KLAYOUT_DATA_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.KEdgeLayout <em>KEdge Layout</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.KEdgeLayout
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKEdgeLayout()
     * @generated
     */
	int KEDGE_LAYOUT = 12;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE_LAYOUT__PROPERTIES = KLAYOUT_DATA__PROPERTIES;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE_LAYOUT__PERSISTENT_ENTRIES = KLAYOUT_DATA__PERSISTENT_ENTRIES;

	/**
     * The feature id for the '<em><b>Bend Points</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE_LAYOUT__BEND_POINTS = KLAYOUT_DATA_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Source Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE_LAYOUT__SOURCE_POINT = KLAYOUT_DATA_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Target Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE_LAYOUT__TARGET_POINT = KLAYOUT_DATA_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>KEdge Layout</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KEDGE_LAYOUT_FEATURE_COUNT = KLAYOUT_DATA_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KPointImpl <em>KPoint</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KPointImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKPoint()
     * @generated
     */
	int KPOINT = 14;

	/**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPOINT__X = 0;

	/**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPOINT__Y = 1;

	/**
     * The number of structural features of the '<em>KPoint</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPOINT_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KInsetsImpl <em>KInsets</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KInsetsImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKInsets()
     * @generated
     */
	int KINSETS = 15;

	/**
     * The feature id for the '<em><b>Top</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KINSETS__TOP = 0;

	/**
     * The feature id for the '<em><b>Bottom</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KINSETS__BOTTOM = 1;

	/**
     * The feature id for the '<em><b>Left</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KINSETS__LEFT = 2;

	/**
     * The feature id for the '<em><b>Right</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KINSETS__RIGHT = 3;

	/**
     * The number of structural features of the '<em>KInsets</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KINSETS_FEATURE_COUNT = 4;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KIdentifierImpl <em>KIdentifier</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KIdentifierImpl
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKIdentifier()
     * @generated
     */
	int KIDENTIFIER = 16;

	/**
     * The feature id for the '<em><b>Properties</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KIDENTIFIER__PROPERTIES = KGRAPH_DATA__PROPERTIES;

	/**
     * The feature id for the '<em><b>Persistent Entries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KIDENTIFIER__PERSISTENT_ENTRIES = KGRAPH_DATA__PERSISTENT_ENTRIES;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KIDENTIFIER__ID = KGRAPH_DATA_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>KIdentifier</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KIDENTIFIER_FEATURE_COUNT = KGRAPH_DATA_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '<em>IProperty</em>' data type.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.elk.graph.properties.IProperty
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getIProperty()
     * @generated
     */
	int IPROPERTY = 17;

	/**
     * The meta object id for the '<em>KVector</em>' data type.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.elk.core.math.KVector
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKVector()
     * @generated
     */
	int KVECTOR = 18;

	/**
     * The meta object id for the '<em>KVector Chain</em>' data type.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.elk.core.math.KVectorChain
     * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKVectorChain()
     * @generated
     */
	int KVECTOR_CHAIN = 19;


	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KGraphElement <em>Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Element</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphElement
     * @generated
     */
	EClass getKGraphElement();

	/**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kgraph.KGraphElement#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphElement#getData()
     * @see #getKGraphElement()
     * @generated
     */
	EReference getKGraphElement_Data();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement <em>KLabeled Graph Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLabeled Graph Element</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement
     * @generated
     */
	EClass getKLabeledGraphElement();

	/**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement#getLabels <em>Labels</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Labels</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement#getLabels()
     * @see #getKLabeledGraphElement()
     * @generated
     */
	EReference getKLabeledGraphElement_Labels();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KGraphData <em>Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphData
     * @generated
     */
	EClass getKGraphData();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KNode <em>KNode</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KNode</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KNode
     * @generated
     */
	EClass getKNode();

	/**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kgraph.KNode#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KNode#getChildren()
     * @see #getKNode()
     * @generated
     */
	EReference getKNode_Children();

	/**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.klighd.kgraph.KNode#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KNode#getParent()
     * @see #getKNode()
     * @generated
     */
	EReference getKNode_Parent();

	/**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kgraph.KNode#getPorts <em>Ports</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Ports</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KNode#getPorts()
     * @see #getKNode()
     * @generated
     */
	EReference getKNode_Ports();

	/**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kgraph.KNode#getOutgoingEdges <em>Outgoing Edges</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Outgoing Edges</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KNode#getOutgoingEdges()
     * @see #getKNode()
     * @generated
     */
	EReference getKNode_OutgoingEdges();

	/**
     * Returns the meta object for the reference list '{@link de.cau.cs.kieler.klighd.kgraph.KNode#getIncomingEdges <em>Incoming Edges</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming Edges</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KNode#getIncomingEdges()
     * @see #getKNode()
     * @generated
     */
	EReference getKNode_IncomingEdges();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KEdge <em>KEdge</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KEdge</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KEdge
     * @generated
     */
	EClass getKEdge();

	/**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Source</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KEdge#getSource()
     * @see #getKEdge()
     * @generated
     */
	EReference getKEdge_Source();

	/**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KEdge#getTarget()
     * @see #getKEdge()
     * @generated
     */
	EReference getKEdge_Target();

	/**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getSourcePort <em>Source Port</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source Port</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KEdge#getSourcePort()
     * @see #getKEdge()
     * @generated
     */
	EReference getKEdge_SourcePort();

	/**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getTargetPort <em>Target Port</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target Port</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KEdge#getTargetPort()
     * @see #getKEdge()
     * @generated
     */
	EReference getKEdge_TargetPort();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KPort <em>KPort</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPort</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KPort
     * @generated
     */
	EClass getKPort();

	/**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.klighd.kgraph.KPort#getNode <em>Node</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Node</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KPort#getNode()
     * @see #getKPort()
     * @generated
     */
	EReference getKPort_Node();

	/**
     * Returns the meta object for the reference list '{@link de.cau.cs.kieler.klighd.kgraph.KPort#getEdges <em>Edges</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Edges</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KPort#getEdges()
     * @see #getKPort()
     * @generated
     */
	EReference getKPort_Edges();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KLabel <em>KLabel</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLabel</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KLabel
     * @generated
     */
	EClass getKLabel();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KLabel#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KLabel#getText()
     * @see #getKLabel()
     * @generated
     */
	EAttribute getKLabel_Text();

	/**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.klighd.kgraph.KLabel#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KLabel#getParent()
     * @see #getKLabel()
     * @generated
     */
	EReference getKLabel_Parent();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder <em>EMap Property Holder</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>EMap Property Holder</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder
     * @generated
     */
	EClass getEMapPropertyHolder();

	/**
     * Returns the meta object for the map '{@link de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder#getProperties <em>Properties</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Properties</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder#getProperties()
     * @see #getEMapPropertyHolder()
     * @generated
     */
	EReference getEMapPropertyHolder_Properties();

	/**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder#getPersistentEntries <em>Persistent Entries</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Persistent Entries</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder#getPersistentEntries()
     * @see #getEMapPropertyHolder()
     * @generated
     */
	EReference getEMapPropertyHolder_PersistentEntries();

	/**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>IProperty To Object Map</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>IProperty To Object Map</em>'.
     * @see java.util.Map.Entry
     * @model keyDataType="de.cau.cs.kieler.klighd.kgraph.IProperty<?>" keyRequired="true" keyTransient="true"
     *        valueDataType="org.eclipse.emf.ecore.EJavaObject" valueTransient="true"
     * @generated
     */
	EClass getIPropertyToObjectMap();

	/**
     * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getIPropertyToObjectMap()
     * @generated
     */
	EAttribute getIPropertyToObjectMap_Key();

	/**
     * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getIPropertyToObjectMap()
     * @generated
     */
	EAttribute getIPropertyToObjectMap_Value();

	/**
     * Returns the meta object for class '{@link org.eclipse.elk.graph.properties.IPropertyHolder <em>IProperty Holder</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>IProperty Holder</em>'.
     * @see org.eclipse.elk.graph.properties.IPropertyHolder
     * @model instanceClass="org.eclipse.elk.graph.properties.IPropertyHolder"
     * @generated
     */
	EClass getIPropertyHolder();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.PersistentEntry <em>Persistent Entry</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Persistent Entry</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.PersistentEntry
     * @generated
     */
	EClass getPersistentEntry();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.PersistentEntry#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.PersistentEntry#getKey()
     * @see #getPersistentEntry()
     * @generated
     */
	EAttribute getPersistentEntry_Key();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.PersistentEntry#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.PersistentEntry#getValue()
     * @see #getPersistentEntry()
     * @generated
     */
	EAttribute getPersistentEntry_Value();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout <em>KShape Layout</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KShape Layout</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KShapeLayout
     * @generated
     */
	EClass getKShapeLayout();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getXpos <em>Xpos</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Xpos</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getXpos()
     * @see #getKShapeLayout()
     * @generated
     */
	EAttribute getKShapeLayout_Xpos();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getYpos <em>Ypos</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ypos</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getYpos()
     * @see #getKShapeLayout()
     * @generated
     */
	EAttribute getKShapeLayout_Ypos();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getWidth()
     * @see #getKShapeLayout()
     * @generated
     */
	EAttribute getKShapeLayout_Width();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getHeight()
     * @see #getKShapeLayout()
     * @generated
     */
	EAttribute getKShapeLayout_Height();

	/**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getInsets <em>Insets</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Insets</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getInsets()
     * @see #getKShapeLayout()
     * @generated
     */
	EReference getKShapeLayout_Insets();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KEdgeLayout <em>KEdge Layout</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KEdge Layout</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KEdgeLayout
     * @generated
     */
	EClass getKEdgeLayout();

	/**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kgraph.KEdgeLayout#getBendPoints <em>Bend Points</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Bend Points</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KEdgeLayout#getBendPoints()
     * @see #getKEdgeLayout()
     * @generated
     */
	EReference getKEdgeLayout_BendPoints();

	/**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kgraph.KEdgeLayout#getSourcePoint <em>Source Point</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Source Point</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KEdgeLayout#getSourcePoint()
     * @see #getKEdgeLayout()
     * @generated
     */
	EReference getKEdgeLayout_SourcePoint();

	/**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kgraph.KEdgeLayout#getTargetPoint <em>Target Point</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Point</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KEdgeLayout#getTargetPoint()
     * @see #getKEdgeLayout()
     * @generated
     */
	EReference getKEdgeLayout_TargetPoint();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KLayoutData <em>KLayout Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLayout Data</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KLayoutData
     * @generated
     */
	EClass getKLayoutData();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KPoint <em>KPoint</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPoint</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KPoint
     * @generated
     */
	EClass getKPoint();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KPoint#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KPoint#getX()
     * @see #getKPoint()
     * @generated
     */
	EAttribute getKPoint_X();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KPoint#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KPoint#getY()
     * @see #getKPoint()
     * @generated
     */
	EAttribute getKPoint_Y();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KInsets <em>KInsets</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KInsets</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KInsets
     * @generated
     */
	EClass getKInsets();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getTop <em>Top</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Top</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KInsets#getTop()
     * @see #getKInsets()
     * @generated
     */
	EAttribute getKInsets_Top();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getBottom <em>Bottom</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bottom</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KInsets#getBottom()
     * @see #getKInsets()
     * @generated
     */
	EAttribute getKInsets_Bottom();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getLeft <em>Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Left</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KInsets#getLeft()
     * @see #getKInsets()
     * @generated
     */
	EAttribute getKInsets_Left();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getRight <em>Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Right</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KInsets#getRight()
     * @see #getKInsets()
     * @generated
     */
	EAttribute getKInsets_Right();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kgraph.KIdentifier <em>KIdentifier</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KIdentifier</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KIdentifier
     * @generated
     */
	EClass getKIdentifier();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kgraph.KIdentifier#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see de.cau.cs.kieler.klighd.kgraph.KIdentifier#getId()
     * @see #getKIdentifier()
     * @generated
     */
	EAttribute getKIdentifier_Id();

	/**
     * Returns the meta object for data type '{@link org.eclipse.elk.graph.properties.IProperty <em>IProperty</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for data type '<em>IProperty</em>'.
     * @see org.eclipse.elk.graph.properties.IProperty
     * @model instanceClass="org.eclipse.elk.graph.properties.IProperty" typeParameters="T"
     * @generated
     */
	EDataType getIProperty();

	/**
     * Returns the meta object for data type '{@link org.eclipse.elk.core.math.KVector <em>KVector</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for data type '<em>KVector</em>'.
     * @see org.eclipse.elk.core.math.KVector
     * @model instanceClass="org.eclipse.elk.core.math.KVector"
     * @generated
     */
	EDataType getKVector();

	/**
     * Returns the meta object for data type '{@link org.eclipse.elk.core.math.KVectorChain <em>KVector Chain</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for data type '<em>KVector Chain</em>'.
     * @see org.eclipse.elk.core.math.KVectorChain
     * @model instanceClass="org.eclipse.elk.core.math.KVectorChain"
     * @generated
     */
	EDataType getKVectorChain();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	KGraphFactory getKGraphFactory();

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
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KGraphElementImpl <em>Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphElementImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKGraphElement()
         * @generated
         */
		EClass KGRAPH_ELEMENT = eINSTANCE.getKGraphElement();

		/**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KGRAPH_ELEMENT__DATA = eINSTANCE.getKGraphElement_Data();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabeledGraphElementImpl <em>KLabeled Graph Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KLabeledGraphElementImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKLabeledGraphElement()
         * @generated
         */
		EClass KLABELED_GRAPH_ELEMENT = eINSTANCE.getKLabeledGraphElement();

		/**
         * The meta object literal for the '<em><b>Labels</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KLABELED_GRAPH_ELEMENT__LABELS = eINSTANCE.getKLabeledGraphElement_Labels();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KGraphDataImpl <em>Data</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphDataImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKGraphData()
         * @generated
         */
		EClass KGRAPH_DATA = eINSTANCE.getKGraphData();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl <em>KNode</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKNode()
         * @generated
         */
		EClass KNODE = eINSTANCE.getKNode();

		/**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KNODE__CHILDREN = eINSTANCE.getKNode_Children();

		/**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KNODE__PARENT = eINSTANCE.getKNode_Parent();

		/**
         * The meta object literal for the '<em><b>Ports</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KNODE__PORTS = eINSTANCE.getKNode_Ports();

		/**
         * The meta object literal for the '<em><b>Outgoing Edges</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KNODE__OUTGOING_EDGES = eINSTANCE.getKNode_OutgoingEdges();

		/**
         * The meta object literal for the '<em><b>Incoming Edges</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KNODE__INCOMING_EDGES = eINSTANCE.getKNode_IncomingEdges();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KEdgeImpl <em>KEdge</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KEdgeImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKEdge()
         * @generated
         */
		EClass KEDGE = eINSTANCE.getKEdge();

		/**
         * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KEDGE__SOURCE = eINSTANCE.getKEdge_Source();

		/**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KEDGE__TARGET = eINSTANCE.getKEdge_Target();

		/**
         * The meta object literal for the '<em><b>Source Port</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KEDGE__SOURCE_PORT = eINSTANCE.getKEdge_SourcePort();

		/**
         * The meta object literal for the '<em><b>Target Port</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KEDGE__TARGET_PORT = eINSTANCE.getKEdge_TargetPort();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KPortImpl <em>KPort</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KPortImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKPort()
         * @generated
         */
		EClass KPORT = eINSTANCE.getKPort();

		/**
         * The meta object literal for the '<em><b>Node</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KPORT__NODE = eINSTANCE.getKPort_Node();

		/**
         * The meta object literal for the '<em><b>Edges</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KPORT__EDGES = eINSTANCE.getKPort_Edges();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabelImpl <em>KLabel</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KLabelImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKLabel()
         * @generated
         */
		EClass KLABEL = eINSTANCE.getKLabel();

		/**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KLABEL__TEXT = eINSTANCE.getKLabel_Text();

		/**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KLABEL__PARENT = eINSTANCE.getKLabel_Parent();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.EMapPropertyHolderImpl <em>EMap Property Holder</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.EMapPropertyHolderImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getEMapPropertyHolder()
         * @generated
         */
		EClass EMAP_PROPERTY_HOLDER = eINSTANCE.getEMapPropertyHolder();

		/**
         * The meta object literal for the '<em><b>Properties</b></em>' map feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference EMAP_PROPERTY_HOLDER__PROPERTIES = eINSTANCE.getEMapPropertyHolder_Properties();

		/**
         * The meta object literal for the '<em><b>Persistent Entries</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES = eINSTANCE.getEMapPropertyHolder_PersistentEntries();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.IPropertyToObjectMapImpl <em>IProperty To Object Map</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.IPropertyToObjectMapImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getIPropertyToObjectMap()
         * @generated
         */
		EClass IPROPERTY_TO_OBJECT_MAP = eINSTANCE.getIPropertyToObjectMap();

		/**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute IPROPERTY_TO_OBJECT_MAP__KEY = eINSTANCE.getIPropertyToObjectMap_Key();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute IPROPERTY_TO_OBJECT_MAP__VALUE = eINSTANCE.getIPropertyToObjectMap_Value();

		/**
         * The meta object literal for the '{@link org.eclipse.elk.graph.properties.IPropertyHolder <em>IProperty Holder</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.elk.graph.properties.IPropertyHolder
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getIPropertyHolder()
         * @generated
         */
		EClass IPROPERTY_HOLDER = eINSTANCE.getIPropertyHolder();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.PersistentEntryImpl <em>Persistent Entry</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.PersistentEntryImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getPersistentEntry()
         * @generated
         */
		EClass PERSISTENT_ENTRY = eINSTANCE.getPersistentEntry();

		/**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PERSISTENT_ENTRY__KEY = eINSTANCE.getPersistentEntry_Key();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PERSISTENT_ENTRY__VALUE = eINSTANCE.getPersistentEntry_Value();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout <em>KShape Layout</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.KShapeLayout
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKShapeLayout()
         * @generated
         */
		EClass KSHAPE_LAYOUT = eINSTANCE.getKShapeLayout();

		/**
         * The meta object literal for the '<em><b>Xpos</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KSHAPE_LAYOUT__XPOS = eINSTANCE.getKShapeLayout_Xpos();

		/**
         * The meta object literal for the '<em><b>Ypos</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KSHAPE_LAYOUT__YPOS = eINSTANCE.getKShapeLayout_Ypos();

		/**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KSHAPE_LAYOUT__WIDTH = eINSTANCE.getKShapeLayout_Width();

		/**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KSHAPE_LAYOUT__HEIGHT = eINSTANCE.getKShapeLayout_Height();

		/**
         * The meta object literal for the '<em><b>Insets</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KSHAPE_LAYOUT__INSETS = eINSTANCE.getKShapeLayout_Insets();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.KEdgeLayout <em>KEdge Layout</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.KEdgeLayout
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKEdgeLayout()
         * @generated
         */
		EClass KEDGE_LAYOUT = eINSTANCE.getKEdgeLayout();

		/**
         * The meta object literal for the '<em><b>Bend Points</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KEDGE_LAYOUT__BEND_POINTS = eINSTANCE.getKEdgeLayout_BendPoints();

		/**
         * The meta object literal for the '<em><b>Source Point</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KEDGE_LAYOUT__SOURCE_POINT = eINSTANCE.getKEdgeLayout_SourcePoint();

		/**
         * The meta object literal for the '<em><b>Target Point</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KEDGE_LAYOUT__TARGET_POINT = eINSTANCE.getKEdgeLayout_TargetPoint();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.KLayoutData <em>KLayout Data</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.KLayoutData
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKLayoutData()
         * @generated
         */
		EClass KLAYOUT_DATA = eINSTANCE.getKLayoutData();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KPointImpl <em>KPoint</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KPointImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKPoint()
         * @generated
         */
		EClass KPOINT = eINSTANCE.getKPoint();

		/**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KPOINT__X = eINSTANCE.getKPoint_X();

		/**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KPOINT__Y = eINSTANCE.getKPoint_Y();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KInsetsImpl <em>KInsets</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KInsetsImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKInsets()
         * @generated
         */
		EClass KINSETS = eINSTANCE.getKInsets();

		/**
         * The meta object literal for the '<em><b>Top</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KINSETS__TOP = eINSTANCE.getKInsets_Top();

		/**
         * The meta object literal for the '<em><b>Bottom</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KINSETS__BOTTOM = eINSTANCE.getKInsets_Bottom();

		/**
         * The meta object literal for the '<em><b>Left</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KINSETS__LEFT = eINSTANCE.getKInsets_Left();

		/**
         * The meta object literal for the '<em><b>Right</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KINSETS__RIGHT = eINSTANCE.getKInsets_Right();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kgraph.impl.KIdentifierImpl <em>KIdentifier</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KIdentifierImpl
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKIdentifier()
         * @generated
         */
		EClass KIDENTIFIER = eINSTANCE.getKIdentifier();

		/**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KIDENTIFIER__ID = eINSTANCE.getKIdentifier_Id();

		/**
         * The meta object literal for the '<em>IProperty</em>' data type.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.elk.graph.properties.IProperty
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getIProperty()
         * @generated
         */
		EDataType IPROPERTY = eINSTANCE.getIProperty();

		/**
         * The meta object literal for the '<em>KVector</em>' data type.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.elk.core.math.KVector
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKVector()
         * @generated
         */
		EDataType KVECTOR = eINSTANCE.getKVector();

		/**
         * The meta object literal for the '<em>KVector Chain</em>' data type.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.elk.core.math.KVectorChain
         * @see de.cau.cs.kieler.klighd.kgraph.impl.KGraphPackageImpl#getKVectorChain()
         * @generated
         */
		EDataType KVECTOR_CHAIN = eINSTANCE.getKVectorChain();

	}

} //KGraphPackage
