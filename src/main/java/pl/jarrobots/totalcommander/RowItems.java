package pl.jarrobots.totalcommander;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RowItems {
    private String root;
    private ArrayList<RowItem> list;

    public RowItems() throws IOException {
        String url = System.getProperty("user.dir");
        list = generateList(url);
        this.root = url;
    }
    private ArrayList<RowItem> generateList( String url) throws IOException {
        ArrayList<RowItem> list = new ArrayList<>();
        list.add(new RowItem(url,url,true,null));
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(url))) {
            for (Path path : stream) {
                list.add(new RowItem(path.toString(), path.getFileName().toString(), Files.isDirectory(path), Files.getLastModifiedTime(path)));
            }
        }
        return list;
    }

    public ObservableList<RowItem> getList(){
        return FXCollections.observableList(list);
    }

    public ObservableList<RowItem> getList(String name){
        try {
            root= name;
            list = generateList(root);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return FXCollections.observableList(list);
    }

}
