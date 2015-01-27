

package com.xt.gt.html.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author albert
 */
public class ClassPool {

    private static ClassPool instance = new ClassPool();

    private Map<Class, ClassDesc> pool = new HashMap();

    private ClassPool() {

    }

    static public ClassPool getInstance() {
        return instance;
    }

    synchronized public void register (Class clazz, ClassDesc classDesc) {
        if (clazz != null && classDesc != null) {
            pool.put(clazz, classDesc);
        }
    }

    synchronized  public ClassDesc getClassDesc (Class clazz) {
        if (clazz == null) {
            return null;
        }
        ClassDesc classDesc = pool.get(clazz);
        if (classDesc == null) {
            classDesc = new ClassDesc();
            pool.put(clazz, classDesc);
            classDesc.load(clazz);
        }
        return classDesc;
    }

}
