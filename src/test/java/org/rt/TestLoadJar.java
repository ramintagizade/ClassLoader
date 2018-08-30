package org.rt;

import org.junit.Test;



/**
 * @author Ramin Taghizada
 */

public class TestLoadJar {

    @Test
    public void testLoadJar() throws Exception, ClassNotFoundException, IllegalAccessException, InstantiationException {

        CustomClassLoader customClassLoader = new CustomClassLoader(TestLoadJar.class.getClassLoader());
        customClassLoader.load(System.getProperty("user.dir")+"/lib");

        Class clazz = customClassLoader.loadClass("org.rt.Main",true);


        System.out.println("Class : " + clazz.getName());



    }
}
