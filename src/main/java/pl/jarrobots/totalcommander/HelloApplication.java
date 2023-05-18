package pl.jarrobots.totalcommander;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static String text;
    Button submit1;
    Button submit2;
    TextField text1;
    TextField text2;
    HBox hBox;
    HBox h1;
    HBox h2;
    VBox v1;
    VBox v2;

    @Override
    public void start(Stage window) throws IOException {
        RowItems list1 = new RowItems();
        RowItems list2 = new RowItems();

        TableClass table1 = new TableClass();
        TableClass table2 = new TableClass();

        submit1 = new Button("Submit");
        submit2 = new Button("Submit");
        v1 = new VBox();
        v2 = new VBox();
        h1 = new HBox();
        h2 = new HBox();
        hBox = new HBox();
        text1 = new TextField();
        text2 = new TextField();

        submit1.setOnAction(e->onClick(list1,text1.getText(),table1));
        submit2.setOnAction(e->onClick(list2,text2.getText(),table2));

        h1.getChildren().addAll(text1, submit1);
        h2.getChildren().addAll(text2, submit2);
        v1.getChildren().addAll(h1,table1.getTable());
        v2.getChildren().addAll(h2,table2.getTable());
        hBox.getChildren().addAll(v1,v2);

        Scene scene = new Scene(hBox);
        scene.setOnKeyPressed( e -> {
                switch (e.getCode()) {
                    case F:
                    case F7:
                        if(table1.isSelected()) {
                            if(!table1.add(answerBox())) alertBox("Adding failed!");
                        }
                        else if(table2.isSelected()) {
                            if(!table2.add(answerBox())) alertBox("Adding failed!");
                        }
                        break;
                    case G:
                    case F8:
                        if(table1.isSelected()) {
                            if(!table1.remove()) alertBox("Removing failed!");
                        }
                        else if(table2.isSelected()) {
                            if(!table2.remove()) alertBox("Removing failed!");
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

    private void onClick(RowItems list, String name, TableClass table){
        table.refresh();
        text1.clear();
        text2.clear();
    }

    private String answerBox(){
        Label label = new Label("Name:");
        Stage window = new Stage();
        TextField field = new TextField();
        Button submit = new Button();
        submit.setOnAction(e->{
            text = field.getText();
            window.close();
        });
        HBox box = new HBox();
        box.getChildren().addAll(label,field,submit);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(new Scene(box));
        window.showAndWait();
        return text;
    }

    private void alertBox( String s){
        Label label = new Label(s);
        Stage window = new Stage();
        HBox box = new HBox();
        box.getChildren().add(label);
        window.setScene(new Scene(box));
        window.show();
    }

}