package org.rt;


import java.net.URLClassLoader;

/**
 * @author Ramin Taghizada
 */

public class CustomClassLoader extends ClassLoader{

    public CustomClassLoader() {

    }


    @Override
    public Class loadClass(String className) throws ClassNotFoundException{

        return loadClass(className,true);
    }

    @Override
    public Class loadClass(String className, boolean resolveIt) throws ClassNotFoundException{

        Class clazz = null;
        // load from loaded resources

        if(clazz == null) {
            throw new ClassNotFoundException(className);
        }

        return clazz;
    }

}
