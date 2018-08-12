package org.rt;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ramin Taghizada
 */

public class CustomClassLoader extends ClassLoader {

    private static Logger logger = LogManager.getLogger(CustomClassLoader.class);
    protected final Map<String,Class> classes;
    protected LoadResources loadResources;

    public CustomClassLoader() {
        classes = new HashMap<String,Class>();
        loadResources = new LoadResources();
    }

    public void load(String path) throws IOException {
        loadResources.loadResource(path);
    }

    @Override
    public Class loadClass(String className) throws ClassNotFoundException{

        return loadClass(className,true);
    }

    @Override
    public Class loadClass(String className, boolean resolveIt) throws ClassNotFoundException{

        Class clazz = null;
        byte [] bytes;

        clazz = classes.get(className);

        if(clazz != null) {
            return clazz;
        }

        bytes = getClassBytes(className);

        if(bytes==null) return null;

        clazz = defineClass(className,bytes,0,bytes.length);

        logger.info(clazz);
        if(clazz==null) return null;

        if(resolveIt) resolveClass(clazz);

        classes.put(className,clazz);

        return clazz;
    }

    private byte[] getClassBytes(String className) {
        logger.info(loadResources.getJarContents());
       return loadResources.getJarContents().get(className);
    }
}
