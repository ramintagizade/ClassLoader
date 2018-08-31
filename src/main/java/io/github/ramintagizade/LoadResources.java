package io.github.ramintagizade;

import java.io.File;
import java.io.IOException;

/**
 * @author Ramin Taghizada
 */

public class LoadResources extends LoadJar {

    /*
     * @param resourcePath
     * @throws IOException
     */
    public void loadResource(String resourcePath) throws IOException{
        loadResource(new File(resourcePath));
    }

    /*
     * @param file
     * @throws IOException
     */
    private void loadResource(File file) throws IOException {
        if(file.isFile()) {
            if(file.getName().toLowerCase().endsWith(".jar")) {
                loadJar(file.getAbsolutePath());
            }
        }

        if(file.list()!=null) {
            for(String fileName : file.list()) {

                File ff = new File(file.getAbsolutePath()+"/"+fileName);

                loadResource(ff);
            }
        }
    }
}
