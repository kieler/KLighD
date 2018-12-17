/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.gson_utils;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * A google gson type adapter factory that allows to modify the written and read json objects after/before other type
 * adapters translate the objects.
 * Copied from a stackoverflow.com answer by the user named Jesse Wilson.
 * 
 * @author nir
 * @see <a href="https://stackoverflow.com/a/11272452">Answer to Stackoverflow question </a>
 * <a href="https://stackoverflow.com/users/40013/jesse-wilson">by Jesse Wilson</a>
 * @param <C> The class for which the {@code write} and {@code read} methods should be altered.
 */
public abstract class CustomizedTypeAdapterFactory<C> implements TypeAdapterFactory {
    private final Class<C> customizedClass;
    
    public CustomizedTypeAdapterFactory(Class<C> customizedClass) {
        this.customizedClass = customizedClass;
    }
    
    @SuppressWarnings("unchecked") // we use a runtime check to guarantee that 'C' and 'T' are equal
    public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return customizedClass.isAssignableFrom(type.getRawType()) // put an 'isAssignableFrom' here again
                ? (TypeAdapter<T>) customizeMyClassAdapter(gson, (TypeToken<C>) type)
                : null;
    }
    
    private TypeAdapter<C> customizeMyClassAdapter(Gson gson, TypeToken<C> type) {
        final TypeAdapter<C> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
        return new TypeAdapter<C>() {
            @Override 
            public void write(JsonWriter out, C value) throws IOException {
                JsonElement tree = delegate.toJsonTree(value);
                beforeWrite(value, tree);
                elementAdapter.write(out, tree);
            }
            @Override
            public C read(JsonReader in) throws IOException {
                JsonElement tree = elementAdapter.read(in);
                afterRead(tree);
                return delegate.fromJsonTree(tree);
            }
        };
    }
    
    /**
    * Override this to muck with {@code toSerialize} before it is written to
    * the outgoing JSON stream.
    */
    protected void beforeWrite(C source, JsonElement toSerialize) {
    }
    
    /**
    * Override this to muck with {@code deserialized} before it parsed into
    * the application type.
    */
    protected void afterRead(JsonElement deserialized) {
    }
}