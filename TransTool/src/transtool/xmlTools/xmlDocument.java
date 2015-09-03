/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.xmlTools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//import transtool.xmlTools.xmlWriter;
/**
 *
 * @author gbjohnson
 */
public class xmlDocument {

    private String doc = " ";
    private String filename;
    private String filepath;

    /**
     * Just sets the user directory at the moment....
     *
     */
    public xmlDocument() {
        filepath = System.getProperty("user.dir");
    }

    /**
     * Use a string to set the name of the file.
     *
     * @param file
     */
    public void fileName(String file) {
        filename = file;
    }

    /*public String filePath () {
        filepath = System.getProperty("user.dir");
        
        
        return filepath;
        
    }*/

    /**
     * Writes to a file specified by fileName
     *
     * @throws IOException
     */

    public void writeToFile() throws IOException {
        File file = new File(filepath + filename);
        System.out.println(filepath + filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(doc);
        bw.close();
    }

}
