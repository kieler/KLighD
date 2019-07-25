package de.cau.cs.kieler.klighd.kgx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLResource.ResourceHandler;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphDataUtil;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;

public class KGXResourceFactoryImpl extends XMIResourceFactoryImpl {

    @Override
    public Resource createResource(URI uri) {
        return new XMIResourceImpl(uri) {
            
            @Override
            public void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
                final Map<Object, Object> theOptions;
                if (options == null) {
                    theOptions = new HashMap<>(1);
                    theOptions.put(
                            OPTION_RESOURCE_HANDLER,
                            new KGXResourceHandler(null));
                } else {
                    theOptions = new HashMap<>(options); // options might be immutable
                    theOptions.put(
                            OPTION_RESOURCE_HANDLER,
                            new KGXResourceHandler((ResourceHandler) options.get(OPTION_RESOURCE_HANDLER)));
                }

                super.doLoad(inputStream, theOptions);
            }
        };
    }

    public static class KGXResourceHandler implements ResourceHandler {
    
        final ResourceHandler delegate;
        
        public KGXResourceHandler(final ResourceHandler delegate) {
            this.delegate = delegate;
        }
        
        @Override
        public void preLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
            if (this.delegate != null)
                this.delegate.preLoad(resource, inputStream, options);
        }

        @Override
        public void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
            if (this.delegate != null)
                this.delegate.postLoad(resource, inputStream, options);

            final Iterator<EObject> it = resource.getContents().stream().filter(e -> e instanceof KNode).iterator();
            if (it.hasNext())
                KGraphDataUtil.loadDataElements((KNode) it.next());
        }

        @Override
        public void preSave(XMLResource resource, OutputStream outputStream, Map<?, ?> options) {
            final Iterator<EObject> it = resource.getContents().stream().filter(e -> e instanceof KNode).iterator();
            if (it.hasNext())
                KGraphUtil.persistDataElements((KNode) it.next());

            if (this.delegate != null)
                this.delegate.preSave(resource, outputStream, options);
        }

        @Override
        public void postSave(XMLResource resource, OutputStream outputStream, Map<?, ?> options) {
            if (this.delegate != null)
                this.delegate.postSave(resource, outputStream, options);
        }
    }
}
