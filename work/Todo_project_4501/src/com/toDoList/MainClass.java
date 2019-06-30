
package com.toDoList;

import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainClass extends Application{

    @Override
    public void start(Stage s) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("login/login.fxml"));
        s.getIcons().add(new Image("file:"+String.valueOf(Paths.get(System.getProperty("user.dir"),"files","login.png"))));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        s.setScene(scene);
        s.show();
        System.out.println("Main page shown");
        s.setTitle("Giriş");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
