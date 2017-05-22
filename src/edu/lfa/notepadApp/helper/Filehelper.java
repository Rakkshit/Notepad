
package edu.lfa.notepadApp.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Filehelper {
   
    public static String openFile(String path) throws IOException{
           BufferedReader reader = new BufferedReader(new FileReader(path));
           String line = "";
           StringBuilder content = new StringBuilder();
           while((line = reader.readLine()) != null){
              content.append(line + "\r\n");
           }   
           reader.close();
           return content.toString();
    }
    
    
    public static void saveFile(String path, String content) throws IOException{
     FileWriter writer = new FileWriter("c:/Leapfrog/Notes/"+path+".txt");
                writer.write(content);
                writer.close();
    }
}
