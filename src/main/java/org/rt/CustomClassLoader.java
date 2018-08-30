package org.rt;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ramin Taghizada
 */

public class CustomClassLoader extends ClassLoader {

    private static Logger logger = LogManager.getLogger(CustomClassLoader.class);
    protected final Map<String,Class> classes;
    protected LoadResources loadResources;

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
        classes = new HashMap<String,Class>();
        loadResources = new LoadResources();
    }

    public void load(String path) throws IOException {
        loadResources.loadResource(path);
    }

    @Override
    public Class loadClass(String className) throws ClassNotFoundException{

        return loadClassTree(className);
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

        clazz = defineClass(className, bytes, 0, bytes.length);

        if(clazz==null) return null;

        if(resolveIt) resolveClass(clazz);

        classes.put(className,clazz);

        return clazz;
    }



    public Class loadClassTree(String className) {

        Class clazz = null;
        try {
            clazz = findClass(className);
            if(clazz==null){
                clazz = loadClass(className,true);
            }
        }
        catch (Exception e) {
            logger.warn(e.getMessage(),e);
            try {
                if(clazz==null) {
                    clazz = getParent().loadClass(className);
                }
            }
            catch (Exception e1) {
                logger.warn(e1);
                try {
                    if(clazz==null){
                        clazz = getClass().getClassLoader().loadClass(className);
                    }
                }
                catch (Exception e3) {
                    logger.warn(e3);
                }
            }
        }
        return clazz;
    }


    private byte[] getClassBytes(String className) {
       return loadResources.getJarContents().get(className);
    }
}
