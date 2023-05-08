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
    ArrayList<RowItem> list;

    public RowItems(RowItem root) throws IOException {
        list = generateList(root);
    }
    private ArrayList<RowItem> generateList( RowItem root) throws IOException {
        ArrayList<RowItem> list = new ArrayList<>();
        list.add(root);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(root.getURL()))) {
            for (Path path : stream) {
                list.add(new RowItem(path.toString(), path.getFileName().toString(), Files.isDirectory(path), Files.getLastModifiedTime(path)));
            }
        }
        return list;
    }

    public ObservableList<RowItem> getList(){
        return FXCollections.observableList(list);
    }
}
