package org.rt;

import org.junit.Test;

import java.io.IOException;


/**
 * @author Ramin Taghizada
 */

public class TestLoadJar {

    @Test
    public void testLoadJar() throws IOException {
        //LoadJar loadJar = new LoadJar();
        //loadJar.loadJar(System.getProperty("user.dir")+"/lib/testJar-1.0.0.jar");

        LoadResources loadResources = new LoadResources();
        loadResources.loadResource(System.getProperty("user.dir")+"/lib");
    }
}
