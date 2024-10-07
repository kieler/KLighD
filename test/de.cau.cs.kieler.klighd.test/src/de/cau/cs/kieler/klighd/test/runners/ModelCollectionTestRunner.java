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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.test.runners;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.elk.core.service.ElkServicePlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Test;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This class defines a JUnit4TestRunner dedicated to run tests on model data bases. It provides
 * various Java annotations:
 * <ul>
 * <li><samp>@Models,</samp></li>
 * <li><samp>@BundleId,</samp></li>
 * <li><samp>@ModelPath,</samp></li>
 * <li><samp>@ModelFilter,</samp></li>
 * <li><samp>@ResourceSet</samp></li>
 * <li><samp>@StopOnFailure</samp></li>
 * </ul>
 * 
 * In a test implementation the model base can be provided in two ways:
 * 
 * <ol>
 * <li>Implementing a method <code>public static Iterable&lt;Object&gt; methodName() {...}</code>
 * annotated with <samp>@Models</samp>,</li>
 * 
 * <li>Implementing <code>public static String ...</code> methods providing the bundle id, annotated
 * with <samp>@BundleId</samp>, and the path to the models, annotated with <samp>@ModelPath</samp>.<br>
 * In addition, a model file filter may be provided in form of a regular expression string by means
 * of a method annotated with <samp>@ModelFilter</samp> and a special
 * {@link org.eclipse.emf.ecore.resource.ResourceSet ResourceSet} may registered by means a method
 * <code>public static ResourceSet ...</code> annotated with <samp>@ResourceSet</samp>.</li>
 * 
 * <li>Attaching the parameterized annotations <samp>@BundleId("...")</samp>,
 * <samp>@ModelPath("...")</samp>, and optionally <samp>@ModelFilter("...")</samp> containing the
 * related String values to the test class besides the {@link org.junit.runner.RunWith @RunWith(...)}
 * annotation.<br>
 * Due to the limitation of Java annotations w.r.t. to the parameter type a custom ResourceSet may
 * only be provided by means of an annotated method as mentioned in item 2.</li>
 * </ol>

 * <p><i>Note, that <samp>@ModelPath(...)</samp> may also take an array of String, like
 * <samp>@ModelPath( { "a/", "b/c/", ... } )</samp> The same applies to a <samp>public static ...</samp>
 * method annotated with <samp>@ModelPath</samp> that may return a <samp>String[]</samp>, as well.</i>
 * </p>
 * 
 * <p><i>Note furthermore, that the values of the provided model paths may end with <samp>/**</samp> or
 * <samp>/*&#042;/...</samp> in order to instruct the model gathering to descent into sub folders.</i></p>
 * 
 * <p>The test classes may have a constructor with zero or one argument(s) of type {@link Object} or
 * {@link EObject} in order to inject the model into the test. The same holds for the test methods
 * (annotated with {@link Test @Test}). Hence, if a test method does not require any parameter the
 * test class must provide a one argument constructor in order to get the models to be tested
 * injected into the test class object.</p>
 * 
 * <p>If the test incorporates <i>before</i> or <i>after</i> methods annotated with
 * {@link org.junit.Before @Before} or {@link org.junit.After @After}, the constructor based "model
 * injection" is required. (Evaluation and invocation of before and after methods could be customized as
 * well; the corresponding methods in {@link Suite}, however, are marked deprecated, and I considered
 * overriding them not a good idea.) 
 * 
 * <p>In case various tests in a test class are functionally dependent, i.e. executing test2(),
 * test3(), ... makes no sense if test1() failed, test1() may be annotated with
 * <samp>@StopOnFailure</samp>. This instructs the particular test runner being in charge of testing
 * with a certain model to skip the remaining tests and pass the baton the next runner testing with
 * the next model. Note that the test order depends on the specification order of the test methods
 * in the test class (from top to bottom).</p>
 * 
 * Examples of valid test specifications:
 * 
 * <pre>
 * <samp>@RunWith(ModelCollectionTestRunner.class)</samp>
 * public class Test {
 * 
 *     <samp>@Models</samp>
 *     public static Iterable&lt;?&gt; getModels() {
 *         List&lt;Object&gt; models = new ArrayList<>();
 *         return models;
 *     }
 * 
 *     <samp>@Test</samp>
 *     <samp>@StopOnFailure</samp>
 *     public void requirementsTest(EObject model) {
 *         Assert.assertTrue(model instanceof KNode);
 *     }
 * 
 *     <samp>@Test</samp>
 *     public void test(EObject model) {
 *         System.out.println(((KNode) model).getData().get(0));
 *     }
 * }
 * </pre>
 * 
 * <pre>
 * <samp>@RunWith(ModelCollectionTestRunner.class)</samp>
 * public class Test {
 * 
 *     <samp>@BundleId</samp>
 *     public static String getBundleId() {
 *         return "de.cau.cs.kieler.klighd.test";
 *     }
 * 
 *     <samp>@ModelPath</samp>
 *     public static String getModelPath() {
 *         return "sizeEstimationTests/";
 *     }
 * 
 *     <samp>@ModelFilter</samp>
 *     public static String getModelFilter() {
 *         return "*.kgt";
 *     }
 * 
 *     <samp>@ModelCollectionTestRunner.ResourceSet</samp>
 *     public static ResourceSet getResourceSet() {
 *         return KGraphStandaloneSetup.doSetup().getInstance(XtextResourceSet.class);
 *     }
 * 
 *     <samp>@Test</samp>
 *     public void test(EObject model) {
 *         System.out.println(((KNode) model).getData().get(0));
 *     }
 * }
 * </pre>
 * 
 * In case the String-typed parameters are constant (not computed somehow, similar to the example
 * above) they may be provided by means annotations attached to the test class, see below:
 * 
 * <pre>
 * <samp>@RunWith(ModelCollectionTestRunner.class)</samp>
 * <samp>@BundleId("de.cau.cs.kieler.klighd.test")</samp>
 * <samp>@ModelPath("sizeEstimationTests/")</samp>
 * <samp>@ModelFilter("*.kgt")</samp>
 * public class Test {
 * 
 *     <samp>@ModelCollectionTestRunner.ResourceSet</samp>
 *     public static ResourceSet getResourceSet() {
 *         return KGraphStandaloneSetup.doSetup().getInstance(XtextResourceSet.class);
 *     }
 *   
 *     <samp>@Test</samp>
 *     public void test(EObject model) {
 *         System.out.println(((KNode) model).getData().get(0));
 *     }
 * }
 * </pre>
 * 
 * The following example finally illustrates the incorporation of <i>before</i> and <i>after</i> methods.
 * 
 * <pre>
 * <samp>@RunWith(ModelCollectionTestRunner.class)</samp>
 * <samp>@BundleId("de.cau.cs.kieler.klighd.test")</samp>
 * <samp>@ModelPath("sizeEstimationTests/")</samp>
 * <samp>@ModelFilter("*.kgt")</samp>
 * public class Test {
 * 
 *     <samp>@ModelCollectionTestRunner.ResourceSet</samp>
 *     public static ResourceSet getResourceSet() {
 *         return KGraphStandaloneSetup.doSetup().getInstance(XtextResourceSet.class);
 *     }
 *
 *     private final KNode model;
 *
 *     public Test(EObject model) {
 *         Assert.assertTrue(model instanceof KNode);
 *         this.model = (KNode) model;
 *     }
 *
 *     <samp>@Before</samp>
 *     public void before() {
 *         KimlUtil.validate(this.model);
 *     }
 *
 *     <samp>@Test</samp>
 *     public void test() {
 *         System.out.println(this.model.getData().get(0));
 *     }
 *
 *     <samp>@After</samp>
 *     public void after() {
 *         // do what you ever wanted to do with 'model'
 *     }
 * }
 * </pre>
 * 
 * @author chsch
 */
public class ModelCollectionTestRunner extends Suite {

    /**
     * The declaration of the Java annotation 'Models', see documentation of
     * {@link ModelCollectionTestRunner} for details.
     * 
     * @author chsch
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Models {
    }

    /**
     * The declaration of the Java annotation 'BundleId', see documentation of
     * {@link ModelCollectionTestRunner} for details.
     * 
     * @author chsch
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE })
    public @interface BundleId {
        
        /** An optional annotation parameter in case the annotation is attached to the test class. */
        String value() default "";
    }

    /**
     * The declaration of the Java annotation 'ModelPath', see documentation of
     * {@link ModelCollectionTestRunner} for details.
     * 
     * @author chsch
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE })
    public @interface ModelPath {
        
        /** An optional annotation parameter in case the annotation is attached to the test class. */
        String[] value() default { };
    }

    /**
     * The declaration of the Java annotation 'ModelFilter', see documentation of
     * {@link ModelCollectionTestRunner} for details.
     * 
     * @author chsch
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE })
    public @interface ModelFilter {
        
        /** An optional annotation parameter in case the annotation is attached to the test class. */
        String value() default "";
    }

    /**
     * The declaration of the Java annotation 'ResourceSet', see documentation of
     * {@link ModelCollectionTestRunner} for details.<br>
     * <br>
     * Unfortunately, annotation parameters may only be of primitive, Annotation, Class, Enumeration,
     * or String type, so the parameterized type annotation variant doesn't work here :-(.
     * 
     * @author chsch
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ResourceSet {
    }

    
    // --------------------------------------------------------------------


    /**
     * Instructs the {@link ModelCollectionTestRunner} to stop testing with the current model and
     * proceed with the next one in the provided model base if the annotated test fails.<br>
     * This way, "pre-condition" tests may be formulated.<br>
     * <br>
     * In more detail, a failing test method annotated with <samp>@StopOnFailure</samp> makes the
     * {@link SingleModelTestRunner} to ignore the remaining tests. Thereafter the current
     * {@link ModelCollectionTestRunner} instance proceeds with its next {@link SingleModelTestRunner}
     * child that restarts the test procedure with its assigned model. 
     * 
     * @author chsch
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface StopOnFailure {
    }

    
    // --------------------------------------------------------------------

    private final List<Runner> childRunners;

    /**
     * Constructor.
     * 
     * @param clazz the tests class
     * @throws Throwable if something unexpected happens
     */
    public ModelCollectionTestRunner(final Class<?> clazz) throws Throwable {
        super(clazz, Collections.emptyList());

        // make sure elk.core.service is loaded
        ElkServicePlugin.getInstance();

        // try to obtain the test models by means of a method annotated with 'Models'
        List<?> models = Lists.newLinkedList(getModelsByModelsMethod());

        // if no models are found they are most probably provided by the path-based method hooks 
        //  so try to obtain them the 2nd way
        if (models.isEmpty()) {
            models = Lists.newLinkedList(getModelsByPathMethods());
        }

        // if no models are found either then there is most probably something wrong in the
        // registration - throw a verbose exception pointing the test author precisely to the issue
        if (models.isEmpty()) {
            throw new NoSuchMethodException(
                    "Expected a public static method annotated width @Models returning a "
                    + "non-empty Iterable<Object> in "
                    + getTestClass().getName()
                    + ", or methods annotated with @BundleId, @ModelPath, and @ResourceSet as "
                    + "documented in the ModelCollectionTestRunner class enabling to obtain a "
                    + "non-empty collection of models. If you chose the 2nd way, make sure the "
                    + "provided paths are correct and contain model files.");
        }

        // for each of the revealed model objects determine a name (the fragmentURI in case of
        //  EObjects) and create a related child test runner
        
        final List<Runner> childRunners = new ArrayList<>();
        for (Object o : models) {

            final String modelName;
            if (o instanceof EObject && ((EObject) o).eResource() != null) {
                final URI uri = EcoreUtil.getURI((EObject) o);
                modelName = uri.path() + uri.fragment();
            } else {
                modelName = o.toString();
            }

            childRunners.add(
                    new SingleModelTestRunner(getTestClass().getJavaClass(), o, modelName));
        }
        
        this.childRunners = Collections.unmodifiableList(childRunners);
    }

    protected List<Runner> getChildren() {
        return childRunners;
    }

    // --------------------------------------------------------------------

    /**
     * Method tries to reveal test models by means of a method annotated with @Models. 
     * 
     * @return An {@link Iterable} of models or an empty list if the attempt fails.
     */
    private Iterable<Object> getModelsByModelsMethod() {
        try {

            // try to obtain the provider method:
            FrameworkMethod modelsMethod = getAnnotatedMethod(getTestClass(), Models.class);
            if (modelsMethod == null
                    || !Iterable.class.isAssignableFrom(modelsMethod.getMethod().getReturnType())) {
                return Collections.emptyList();
            }
            
            // now reveal the provided information ...
            @SuppressWarnings("unchecked")
            Iterable<Object> models = (Iterable<Object>) modelsMethod.invokeExplosively(null);
            if (models != null) {
                // if models are available return them,
                return models;
            } else {
                // ... otherwise:
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            return Collections.emptyList();
        }
    }

    // --------------------------------------------------------------------

    /**
     * Method tries to reveal test models by means of path denoting methods.
     * 
     * @return An {@link Iterable} of models or an empty list if the attempt fails.
     */
    private Iterable<Object> getModelsByPathMethods() {
        String bundleId = null;
        try {
            // first examine the test class annotations:
            //  (doesn't work for the ResourceSet as java annotations don't support
            //   custom typed parameters)
            BundleId bundleIdAn = getClassAnnotation(BundleId.class);
            bundleId = bundleIdAn != null ? bundleIdAn.value() : null;
            
            ModelPath modelPathAn = getClassAnnotation(ModelPath.class);
            String[] modelPaths = modelPathAn != null ? modelPathAn.value() : null;
            
            ModelFilter modelFilterAn = getClassAnnotation(ModelFilter.class);
            String modelFilter = modelFilterAn != null ? modelFilterAn.value() : null;
            
            // try to obtain the mandatory and optional provider methods:
            FrameworkMethod bundleIdMethod = bundleId == null
                    ? getAnnotatedMethod(getTestClass(), BundleId.class) : null;
            FrameworkMethod modelPathMethod = modelPaths == null || modelPaths.length == 0
                    ? getAnnotatedMethod(getTestClass(), ModelPath.class) : null;

            // optional:
            FrameworkMethod modelFilterMethod = modelFilter == null
                    ? getAnnotatedMethod(getTestClass(), ModelFilter.class) : null;
            FrameworkMethod resourceSetMethod = getAnnotatedMethod(getTestClass(), ResourceSet.class);

            boolean valid = true;
            boolean pathArray = false;

            // check whether the mandatory annotations are given
            if (Strings.isNullOrEmpty(bundleId)
                    && (bundleIdMethod == null || !bundleIdMethod.getMethod().getReturnType()
                            .equals(String.class))) {
                // in this case no bundle id is provided
                valid = false;
                
            } else if (modelPaths != null && modelPaths.length != 0) { // SUPPRESS CHECKSTYLE Empty
                // in this case we're fine
                
            } else if ((modelPaths == null || modelPaths.length == 0) && modelPathMethod != null) {
                // in this case no test class @ModelPath annotation is given but a related method
                
                if (modelPathMethod.getMethod().getReturnType().equals(String.class)) {
                    pathArray = false;
                } else if (modelPathMethod.getMethod().getReturnType().equals(String[].class)) {
                    pathArray = true;
                } else {
                    // in this case no proper modelPath(s) providing method is available
                    valid = false;
                }
                
            } else {
                // in this case no modelPath(s) is/are provided
                valid = false;
            }
          
            // check whether the optional one is given
            boolean filtered = true;
            if (modelFilterMethod == null
                    || !modelFilterMethod.getMethod().getReturnType().equals(String.class)) {
                filtered = false;
            }
            
            // check whether a custom ResourceSet is provided
            boolean customResourceSet = true;
            if (resourceSetMethod == null
                || !resourceSetMethod.getMethod().getReturnType()
                        .isAssignableFrom(org.eclipse.emf.ecore.resource.ResourceSet.class)) {
                customResourceSet = false;
            }
            
            if (valid) {
                // now reveal the provided information ...
                bundleId = bundleId == null
                        ? (String) bundleIdMethod.invokeExplosively(null) : bundleId;
                        
                if (modelPaths == null || modelPaths.length == 0) {
                    
                    Object modelPathObj = modelPathMethod.invokeExplosively(null);
                    modelPaths = pathArray ? (String[]) modelPathObj
                            : new String[] { (String) modelPathObj };
                }
                
                modelFilter = filtered
                        ? (String) modelFilterMethod.invokeExplosively(null) : modelFilter;
                final org.eclipse.emf.ecore.resource.ResourceSet set = customResourceSet
                        ? (org.eclipse.emf.ecore.resource.ResourceSet) resourceSetMethod
                        .invokeExplosively(null) : new ResourceSetImpl();
                
                List<URL> urls = new ArrayList<>();
                
                // for all provided model paths ...
                for (String modelPath : modelPaths) {
                    
                    // since Bundle#findEntries() does not support path pattern expressions
                    //  the path is first checked for the recurse pattern and accordingly modified
                    boolean recurse = true;
                    if (modelPath != null) {
                        if (modelPath.endsWith("/**")) {
                            // SUPPRESS CHECKSTYLE NEXT 5 MagicNumber
                            modelPath = modelPath.substring(0, modelPath.length() - 3);
                        } else {
                            if (modelPath.endsWith("/**/")) {
                                modelPath = modelPath.substring(0, modelPath.length() - 4);
                            } else {
                                recurse = false;
                            }
                        }
                    }
                    
                    // ... try to access the specified path, transform the Enumeration of URLs
                    //  into a list, and add them to the whole url list
                    urls.addAll(Collections.list(Platform.getBundle(bundleId).findEntries(
                            modelPath, modelFilter, recurse)));
                    
                }

                if (urls.isEmpty()) {
                    // if nothing is found (without a failing) return an empty list
                    return Collections.emptyList();
                }

                // if some model files are available
                //  try to load each Resource by means of the provided ResourceSet
                //  and finally return the revealed models
                return this.loadURLs(bundleId, set, urls);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            if (!Strings.isNullOrEmpty(bundleId)) {
                String message = "ModelCollectionTestRunner:Loading model resources of " + bundleId
                        + "failed with the following exception:"
                        + System.getProperty("line.separator");
                Platform.getLog(Platform.getBundle(bundleId)).log(
                        new Status(IStatus.ERROR, bundleId, message, t));
            }
            return Collections.emptyList();
        }
    }
    
    private Iterable<Object> loadURLs(final String bundleId,
            final org.eclipse.emf.ecore.resource.ResourceSet set, final Iterable<URL> urls) {
        return Iterables.concat(Iterables.transform(urls, new Function<URL, Iterable<?>>() {
            public Iterable<?> apply(final URL url) {
                try {
                    final Resource r = set.getResource(URI.createURI(url.toString()), true);
                    return r.getContents();
                } catch (WrappedException w) {
                    // if the resource load fails (e.g. as no valid ResourceFactory has been
                    //  registered) return the empty list
                    String message = "ModelCollectionTestRunner: Loading model resource "
                            + url.toString() + " of " + bundleId
                            + " failed with the following exception:"
                            + System.getProperty("line.separator");
                    Platform.getLog(Platform.getBundle(bundleId)).log(
                            new Status(IStatus.ERROR, bundleId, message, w));
                    return Collections.emptyList();
                }
            }
        }));
    }

    // --------------------------------------------------------------------

    /**
     * Gets the method annotated with an instance of 'annotationClass'.
     * 
     * @author chsch
     * 
     * @param testClass
     *            the test class
     * @param annotationClass
     *            the annotation type to look for
     * @return the annotated method
     */
    protected FrameworkMethod getAnnotatedMethod(final TestClass testClass,
            final Class<? extends Annotation> annotationClass) {
        List<FrameworkMethod> methods = testClass.getAnnotatedMethods(annotationClass);
        for (FrameworkMethod each : methods) {
            int modifiers = each.getMethod().getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                return each;
            }
        }
        return null;
    }

    // --------------------------------------------------------------------

    /**
     * Reveals the first annotation instance of type 'annotationClass' from the test class's
     * annotations.
     * 
     * @param <T>
     *            the requested annotation type
     * @param annotationType
     *            the requested annotation type
     * @return the first instance of #annotationType being found
     */
    protected <T extends Annotation> T getClassAnnotation(final Class<T> annotationType) {
        final List<Annotation> annotations = Arrays.asList(getTestClass().getAnnotations());
        return Iterables.getFirst(Iterables.filter(annotations, annotationType), null);
    }


    // --------------------------------------------------------------------

    
    /**
     * A specialized {@link BlockJUnit4ClassRunner} running tests on a given model element. 
     *
     * @author chsch
     */
    protected class SingleModelTestRunner extends BlockJUnit4ClassRunner {

        private Object model = null;
        private String modelName = null;

        /**
         * Constructor.
         * 
         * @param <T>
         *            the type of the current model
         * @param clazz
         *            the test class
         * @param theModel
         *            the model to perform the tests on
         * @param theModelName
         *            the name of model, used to provide a useful output in the JUnit view
         * @throws InitializationError
         *             if the super implementation throws such an error
         */
        public <T extends Object> SingleModelTestRunner(final Class<?> clazz,
                final T theModel, final String theModelName) throws InitializationError {
            super(clazz);
            this.model = theModel;
            this.modelName = theModelName;
        }

        // --------------------------------------------------------------------

        /**
         * {@inheritDoc}
         */
        @Override
        protected void validateConstructor(final List<Throwable> errors) {
            validateOnlyOneConstructor(errors);
            Class<?>[] constructorParams = getTestClass().getOnlyConstructor().getParameterTypes();
            
            if (constructorParams.length > 1
                    || constructorParams.length == 1
                    && !(constructorParams[0].equals(Object.class) || EObject.class
                            .isAssignableFrom(constructorParams[0]))) {
                errors.add(new Exception("Constructor of test class " + getTestClass().getName()
                        + " should have at most one parameter of type Object or (a sub type of)"
                        + " EObject."));
            }
        }

        // --------------------------------------------------------------------

        /**
         * Adds to {@code errors} for each method annotated with {@code @Test}that
         * is not a public, void instance method with zero arguments or one
         * Object/(a sub type of) EObject argument.
         * 
         * @param errors the error collecting list
         */
        protected void validateTestMethods(final List<Throwable> errors) {
            List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(Test.class);

            for (FrameworkMethod testMethod : methods) {
                    testMethod.validatePublicVoid(false, errors);
                    Method method = testMethod.getMethod();
                    final Class<?>[] methodParams = method.getParameterTypes();
                boolean methodInvalid = (methodParams.length > 1 || (methodParams.length == 1
                        && !(methodParams[0].equals(Object.class)
                                || EObject.class.isAssignableFrom(methodParams[0]))));
                if (methodInvalid) {
                    errors.add(new Exception("Method " + testMethod.getMethod().getName()
                            + " should have at most one parameter of type Object or (a sub type of)"
                            + " EObject."));
                }
                    
            }
        }

        // --------------------------------------------------------------------

        /**
         * Depending on the number of constructor parameters and, if a parameter is present the
         * parameter type, this method creates a new instance of the test class by calling the
         * (only) constructor and optionally injecting the current model.
         * 
         * @throws Exception
         *             if no fitting constructor is declared or the constructor call fails
         * @return the created test class instance
         */
        @Override
        public Object createTest() throws Exception {
            Class<?>[] constructorParams = getTestClass().getOnlyConstructor().getParameterTypes();
            if (constructorParams.length == 0) {
                return getTestClass().getOnlyConstructor().newInstance();
            } else if (constructorParams.length == 1
                    && constructorParams[0].isAssignableFrom(this.model.getClass())) {
                return getTestClass().getOnlyConstructor().newInstance(this.model);
            } else {
                throw new NoSuchMethodError("Type of the parameter of " + getTestClass()
                        + "'s constructor is not compatible with the current model's type "
                        + this.model.getClass().getSimpleName());
            }
        }
        
        // --------------------------------------------------------------------
        
        private boolean methodStopsExecution = false;
        private boolean ignoreRemainingTests = false;
        
        /**
         * This listener is registered in {@link #childrenInvoker}. In case it notices a test
         * failure it configures its parent {@link SingleModelTestRunner} to ignore its remaining
         * tests, see {@link #runChild(FrameworkMethod, RunNotifier)}.
         */
        private RunListener listener = new RunListener() {

            /**
             * Called when an atomic test fails.
             * 
             * @param failure
             *            describes the test that failed and the exception that was thrown
             */
            public void testFailure(final Failure failure) throws Exception {
                SingleModelTestRunner.this.ignoreRemainingTests =
                        SingleModelTestRunner.this.methodStopsExecution;
            }
        };


        /**
         * {@inheritDoc}.<br>
         * <br>
         * The returned result iterates on this' children and calls
         * {@link #runChild(FrameworkMethod, RunNotifier)} for each.
         */
        protected Statement childrenInvoker(final RunNotifier notifier) {
            notifier.addListener(listener);
            return super.childrenInvoker(notifier);
        }
        
        
        /**
         * {@inheritDoc}
         */
        protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
            if (this.ignoreRemainingTests) {
                notifier.fireTestIgnored(this.describeChild(method));
            } else {
                this.methodStopsExecution = method.getAnnotation(StopOnFailure.class) != null;
                super.runChild(method, notifier);
            }
        }

        // --------------------------------------------------------------------

        /**
         * Returns a {@link Statement} that invokes {@code method} on {@code test} with parameter
         * {@code model} if required.
         * 
         * @param method
         *            the test method to be invoked
         * @param testClassInstance
         *            the testClassInstance to invoke the test method on
         * @return the {@link Statement} wrapping the test method call
         */
        protected Statement methodInvoker(final FrameworkMethod method,
                final Object testClassInstance) {
            return new InvokeMethodOnModel(method, testClassInstance, this.model);
        }
        
        // --------------------------------------------------------------------

        /**
         * The name of the runner consists of the name of the model being tested and the class name
         * in parentheses.
         * 
         * @return the name
         */
        @Override
        protected String getName() {
            return this.modelName + " - " + ModelCollectionTestRunner.this.getClass().getSimpleName()
                    + "." + SingleModelTestRunner.this.getClass().getSimpleName();
        }

        // --------------------------------------------------------------------

        /**
         * The name of the test of a concrete test run.
         * Due to some weird reason the JUnit view cuts the test name right before
         * the first occurrence of '(', so I used '[]' instead.
         * 
         * @param method
         *            the method
         * @return the string
         */
        @Override
        protected String testName(final FrameworkMethod method) {
            return "test method '" + method.getName() + "[]' testing model '" + this.modelName + "'";
        }
    }
    
    
    // --------------------------------------------------------------------


    /**
     * A custom {@link Statement} encapsulating the invocation of a test method executed by the
     * JUnit {@link Runner}. It enables the invocation of test methods expecting the model element
     * as a (exactly one) parameter.
     * 
     * @author chsch
     */
    private static class InvokeMethodOnModel extends Statement {

        private FrameworkMethod method = null;
        private Object testClassInstance = null;
        private Object model = null;
        
        public InvokeMethodOnModel(final FrameworkMethod theMethod, final Object theTestClassInstance,
                final Object theModel) {
            this.method = theMethod;
            this.testClassInstance = theTestClassInstance;
            
            final Class<?>[] methodParams = theMethod.getMethod().getParameterTypes();
            if (methodParams.length == 1
                    && (methodParams[0].equals(Object.class) || (methodParams[0]
                            .isAssignableFrom(theModel.getClass())))) {
                this.model = theModel;
            } else {
                this.model = null;
            }
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void evaluate() throws Throwable {
            if (model == null) {
                method.invokeExplosively(testClassInstance);
            } else {
                method.invokeExplosively(testClassInstance, this.model);
            }
        }
    }
}
