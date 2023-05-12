package pl.jarrobots.totalcommander;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;

public abstract class Functions {
    public static void cp(String url, String destination, String name){
        Path dest = Paths.get(destination);
        Path _url = Paths.get(url);
        try {
            Files.copy(_url, dest, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void rm(String url){
        try {
            Files.delete(Paths.get(url));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void add(String url, String name) throws IOException{
        url = url+"/"+name;
        new File(url).mkdirs();
    }
}
