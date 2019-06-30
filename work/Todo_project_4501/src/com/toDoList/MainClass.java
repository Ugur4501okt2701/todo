
package com.toDoList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClass extends Application{

    @Override
    public void start(Stage s) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("main/main.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        s.setScene(scene);
        s.show();
        System.out.println("Main page shown");
        s.setTitle("Todo_project_4501");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
