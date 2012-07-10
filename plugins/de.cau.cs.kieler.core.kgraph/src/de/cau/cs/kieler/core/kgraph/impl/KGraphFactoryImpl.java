/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * $Id$
 */
package de.cau.cs.kieler.core.kgraph.impl;

import de.cau.cs.kieler.core.kgraph.*;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphFactory;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;

import de.cau.cs.kieler.core.properties.IProperty;
import java.util.Map;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 * @kieler.design 2011-02-01 reviewed by cmot, soh
 */
public class KGraphFactoryImpl extends EFactoryImpl implements KGraphFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static KGraphFactory init() {
        try {
            KGraphFactory theKGraphFactory = (KGraphFactory)EPackage.Registry.INSTANCE.getEFactory("http://kieler.cs.cau.de/KGraph"); 
            if (theKGraphFactory != null) {
                return theKGraphFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new KGraphFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KGraphFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case KGraphPackage.KGRAPH_DATA: return createKGraphData();
            case KGraphPackage.KNODE: return createKNode();
            case KGraphPackage.KEDGE: return createKEdge();
            case KGraphPackage.KPORT: return createKPort();
            case KGraphPackage.KLABEL: return createKLabel();
            case KGraphPackage.IPROPERTY_TO_OBJECT_MAP: return (EObject)createIPropertyToObjectMap();
            case KGraphPackage.PERSISTENT_ENTRY: return createPersistentEntry();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case KGraphPackage.IPROPERTY:
                return createIPropertyFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case KGraphPackage.IPROPERTY:
                return convertIPropertyToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KGraphData createKGraphData() {
        KGraphDataImpl kGraphData = new KGraphDataImpl();
        return kGraphData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KNode createKNode() {
        KNodeImpl kNode = new KNodeImpl();
        return kNode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KEdge createKEdge() {
        KEdgeImpl kEdge = new KEdgeImpl();
        return kEdge;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPort createKPort() {
        KPortImpl kPort = new KPortImpl();
        return kPort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KLabel createKLabel() {
        KLabelImpl kLabel = new KLabelImpl();
        return kLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map.Entry<IProperty<?>, Object> createIPropertyToObjectMap() {
        IPropertyToObjectMapImpl iPropertyToObjectMap = new IPropertyToObjectMapImpl();
        return iPropertyToObjectMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PersistentEntry createPersistentEntry() {
        PersistentEntryImpl persistentEntry = new PersistentEntryImpl();
        return persistentEntry;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IProperty<?> createIPropertyFromString(EDataType eDataType, String initialValue) {
        return (IProperty<?>)super.createFromString(initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertIPropertyToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KGraphPackage getKGraphPackage() {
        return (KGraphPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static KGraphPackage getPackage() {
        return KGraphPackage.eINSTANCE;
    }

} //KGraphFactoryImpl
