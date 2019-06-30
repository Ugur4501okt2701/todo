
package com.toDoList.login;

import com.toDoList.db.DB;
import com.toDoList.util.UserInfo;
import com.toDoList.util.Utility;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {
  @FXML
    private Button createAccountButton;

    @FXML
    private Label warningsLabel;

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Button enterButton;

    @FXML
    private Button deleteAccountButton;
DB db=DB.getInstance();
String username,password;
    @FXML
    void enterButtonPressed(ActionEvent event) {
getUserInfo();
if(db.hasRowInTableForThisCondition("users", "where username='"+username+"' and password='"+password+"'")) {
    try {
        UserInfo.username=username;
         msg("İstifadəçi mövcuddur");
        Stage stage=new Stage();
   stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Əsas pəncərə - "+username);
    stage.getIcons().add(new Image("file:"+String.valueOf(Paths.get(System.getProperty("user.dir"),"files","main.png"))));
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/toDoList/main/main.fxml"));

        Parent root=loader.load();
    Scene scene = new Scene(root);
    stage.setScene(scene);
   stage.show();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
 
   
}else{
    msg("İstifadəçi mövcud deyil");
}
    }


    @FXML
    void createAccountButtonPressed(ActionEvent event) {
getUserInfo();
if(username.equals("") || password.equals("")){
    msg("Məlumatlar tam yazılmalıdır");
}else{
    if(db.hasRowInTableForThisCondition("users", "where username='"+username+"'")){
        msg("Bu istifadəçi adı mövcuddur");
    }else{
       if(db.iud("insert into users (username,password,register)"
               + " values ('"+username+"','"+password+"','"+Utility.getNowDateTime()+"')")){
           
           
           msg("İstifadəçi uğurla yaradıldı");
       }else{
           msg("İstifadəçi yaradılmadı");
       } 
        
    }
}
    }


    @FXML
    void deleteAccountButtonPressed(ActionEvent event) {
getUserInfo();
    if(db.hasRowInTableForThisCondition("users", "where username='"+username+"' and password='"+password+"'")){
        db.iud("delete from balance where username='"+username+"'");
        db.iud("delete from expense where username='"+username+"'");
        db.iud("delete from expense_categories where username='"+username+"'");
        db.iud("delete from income where username='"+username+"'");
        db.iud("delete from income_categories where username='"+username+"'");
        db.iud("delete from plan_details where username='"+username+"'");
        db.iud("delete from plans where username='"+username+"'");
        db.iud("delete from users where username='"+username+"'");
        msg("İstifadəçi silindi");
    }else{
        msg("İstifadəçi məlumatları düzgün deyil");
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void msg(String message){
        warningsLabel.setText(message);
    }
     private void getUserInfo() {
         username=usernameTF.getText().trim();
 password=passwordPF.getText().trim();
    }
}
