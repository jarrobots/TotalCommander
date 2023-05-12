package pl.jarrobots.totalcommander;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class HelloApplication extends Application {
    Button submit1;
    Button submit2;
    TextField text1;
    TextField text2;
    TableView<RowItem> table1;
    TableView<RowItem> table2;
    HBox hBox;
    HBox h1;
    HBox h2;
    VBox v1;
    VBox v2;

    @Override
    public void start(Stage window) throws IOException {
        RowItems list1 = new RowItems();
        RowItems list2 = new RowItems();

        submit1 = new Button("Submit");
        submit2 = new Button("Submit");
        v1 = new VBox();
        v2 = new VBox();
        h1 = new HBox();
        h2 = new HBox();
        hBox = new HBox();
        text1 = new TextField();
        table1 = new TableView<>();
        text2 = new TextField();
        table2 = new TableView<>();

        TableColumn<RowItem, String> name1 = new TableColumn<>("Name:");
        TableColumn<RowItem, Boolean> image1 = new TableColumn<>("Type:");
        TableColumn<RowItem, Date> time1 = new TableColumn<>("Time:");

        TableColumn<RowItem, String> name2 = new TableColumn<>("Name:");
        TableColumn<RowItem, Boolean> image2 = new TableColumn<>("Type:");
        TableColumn<RowItem, Date> time2 = new TableColumn<>("Time:");

        image1.setMinWidth(10);
        name1.setMinWidth(200);
        time1.setMinWidth(100);
        image1.setCellValueFactory(new PropertyValueFactory<>("dir"));
        name1.setCellValueFactory(new PropertyValueFactory<>("name"));
        time1.setCellValueFactory(new PropertyValueFactory<>("date"));

        image2.setMinWidth(10);
        name2.setMinWidth(200);
        time2.setMinWidth(100);
        image2.setCellValueFactory(new PropertyValueFactory<>("dir"));
        name2.setCellValueFactory(new PropertyValueFactory<>("name"));
        time2.setCellValueFactory(new PropertyValueFactory<>("date"));


        table1.getColumns().addAll(image1,name1,time1);
        table1.setItems(list1.getList());
        table2.getColumns().addAll(image2,name2,time2);
        table2.setItems(list2.getList());

        submit1.setOnAction(e->onClick(list1,text1.getText(),table1));
        submit2.setOnAction(e->onClick(list2,text2.getText(),table2));


        table1.setOnMouseDragged( e -> {
            RowItem item = table1.getSelectionModel().getSelectedItem();

        });

        table1.setRowFactory( tv -> {
            TableRow<RowItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) onClickDir(list1, table1);
            });
            return row;
        });
        table2.setRowFactory( tv -> {
            TableRow<RowItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) onClickDir(list2, table2);
            });
            return row;
        });

        h1.getChildren().addAll(text1, submit1);
        h2.getChildren().addAll(text2, submit2);
        v1.getChildren().addAll(h1,table1);
        v2.getChildren().addAll(h2,table2);
        hBox.getChildren().addAll(v1,v2);

        Scene scene = new Scene(hBox);
        scene.setOnKeyPressed( e -> {
                switch (e.getCode()) {
                    case F7:
                        Functions.add(table1.getItems().get(0).getURL(), );
                        break;
                    case F8:
                        if(table1.getSelectionModel() != null) {
                            Functions.rm(table1.getSelectionModel().getSelectedItem().getURL());
                        }
                        else if(table2.getSelectionModel() != null){
                            Functions.rm(table2.getSelectionModel().getSelectedItem().getURL());
                        }
                        break;
                }
        });

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }


    private void onClick(RowItems list, String name, TableView<RowItem> table){

        table.setItems(list.getList(name));
        text1.clear();
        text2.clear();
    }

    private void onClickDir(RowItems list, TableView<RowItem> table){
        table.setItems(list.getList(table.getSelectionModel().getSelectedItem().getURL()));
    }
    private String nameBox(){
        TextField field = new TextField();
        Button submit = new Button();
        Stage window = new Stage();
        HBox box = new HBox();


        box.getChildren().addAll(field,submit);
        window.setResizable(false);
        window.setScene(new Scene(box));
    }

}