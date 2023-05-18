package pl.jarrobots.totalcommander;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Date;

public class TableClass {
    private TableView<RowItem> table;
    private RowItems list;
    private String rootURL;

    public TableClass() throws IOException{
        list = new RowItems();
        table = new TableView<>();
        rootURL = System.getProperty("user.dir");

        TableColumn<RowItem, String> name = new TableColumn<>("Name:");
        TableColumn<RowItem, Boolean> image = new TableColumn<>("Type:");
        TableColumn<RowItem, Date> time = new TableColumn<>("Time:");

        image.setMinWidth(10);
        name.setMinWidth(200);
        time.setMinWidth(100);
        image.setCellValueFactory(new PropertyValueFactory<>("dir"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        time.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.getColumns().addAll(image,name,time);
        table.setItems(list.getList());

        table.setOnMouseDragged( e -> {RowItem item = table.getSelectionModel().getSelectedItem();});

        table.setRowFactory( tv -> {
            TableRow<RowItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) onClickDir(list, table);
            });
            return row;
        });
    }

    private void onClickDir(RowItems list, TableView<RowItem> table){
        table.setItems(list.getList());
    }

    public TableView<RowItem> getTable() {
        return table;
    }

    public boolean isSelected(){
        return (table.getSelectionModel() != null);
    }

    public boolean remove(){
       boolean b = Functions.rm(table.getSelectionModel().getSelectedItem().getURL());
        table.setItems(list.getList());
        return b;
    }

    public boolean add(String name){
        try {
            Functions.add(rootURL, name);
        }
        catch(IOException ex){
            return false;
        }
        return true;
    }

    public void refresh(){
        table.setItems(list.getList());
    }
}
