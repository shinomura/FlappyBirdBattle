package com.qthstudios.game.flappybirdbattle.framework.signature;

import java.util.ArrayList;
import java.util.List;

/**
 * Pool to save resource. Avoid Garbage Collector works frequently
 */
public class Pool<T> {
    /** interface helper for any class use this pool should implement it */
    public interface PoolObjectFactory<T> {
        public T createObject();
    }

    private final List<T> freeObjects;
    private final PoolObjectFactory<T> factory;
    private final int maxSize;

    public Pool(PoolObjectFactory<T> factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObjects = new ArrayList<T>(maxSize);
    }

    public T newObject() {
        T object = null;

        if (freeObjects.size() == 0) {
            /** if currently pool is empty. doesn't have any object.
             *  delegate to concrete class to create new one
             */
            object = factory.createObject();
        }
        else {
            /** get one element in pool and remove it. because it doesn't trash anymore */
            object = freeObjects.remove(freeObjects.size() - 1);

        }

        return object;
    }

    /**
     * Simply free objects by add null object into queue
     */
    public void free(T object) {
        if (freeObjects.size() < maxSize)
            freeObjects.add(object);
    }
}
