
package com.toDoList.main;

import com.toDoList.db.DB;
import com.toDoList.model.Task;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class MainController implements Initializable {

    public MainController() {
    }

    MainController mainController;
     private DB db = DB.getDB();
 @FXML
    private Button deleteButton;

    @FXML
    private Button enterButton;

    @FXML
    private TextField taskTF;

    @FXML
    private TableColumn<Task, String> categoryCol;

    @FXML
    private DatePicker dateDP;

    @FXML
    private Button solveButton;

    @FXML
    private CheckBox allCheckBox;

    @FXML
    private TableColumn<Task, Integer> idCol;

    @FXML
    private ComboBox<String> categoryCB;

    @FXML
    private Button newCategoryButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField dayTF;

    @FXML
    private RadioButton allRadioButton;

    @FXML
    private TableColumn<Task, String> taskCol;

    @FXML
    private Label rowCountLabel;

    @FXML
    private TextField searchTF;

    @FXML
    private TableView<Task> tasksTabelView;

    @FXML
    private Label warningsLabel;

    @FXML
    private RadioButton solveRadioButton;

    @FXML
    private TableColumn<Task, LocalDate> dateCol;

    @FXML
    private TableColumn<Task, String> statusCol;

    @FXML
    private Button deleteCategoryButton;

    @FXML
    private TableColumn<Task, Integer> dayCol;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private RadioButton dontSolveRadioButton;

    @FXML
    void enterButtonPressed(ActionEvent event) {
String taskName=taskTF.getText().trim();
if(taskName.equals("")){
    msg("Tapşırığın adını boş qoymaq olmaz");
}else{
    String taskCategory=categoryCB.getSelectionModel().getSelectedItem();
    if(taskCategory==null){
        msg("Tapşırığın kateqoriyasını boş qoymaq olmaz");
    }else{
        String taskGivenDay=dayTF.getText().trim();
        if(taskGivenDay.equals("")){
            msg("Tapşırığın gününü boş qoymaq olmaz");
        }else{
            LocalDate taskRegister=dateDP.getValue();
            DB db=DB.getDB();
            db.iud("insert into tasks (task_name,task_category,task_given_day,task_register)"
                    + " values ('"+taskName+"','"+taskCategory+"','"+taskGivenDay+"','"+taskRegister+"' )");
            msg("Uğurlu qeydiyyat");
            loadTasks();
        }
            }
}
    }

    
    @FXML
    void saveButtonPressed(ActionEvent event) {
Task selectedBook=tasksTabelView.getSelectionModel().getSelectedItem();
if(selectedBook==null) {
    
    msg("Siyahıdan seçim edilməyib");
}else{
    int id=selectedBook.getId();
     
        String name=taskTF.getText();
        name=name.trim();
        if(name.equals("")){
            msg("Tapşırığın adını boş qoymaq olmaz");
        }else{
            String category=categoryCB.getValue();
            category=category.trim();
            if(category.equals("")) {
                
                msg("Tapşırığın kateqoriyasını boş qoymaq olmaz");
            }else{
                
              LocalDate date=dateDP.getValue();
              
              if(date==null){
                  msg("Tapşırığın tarixini boş qoymaq olmaz");
                  
              }else{
                  String day=dayTF.getText().trim();
                  if(day.equals("")){
                      msg("Tapşırığa verilən günü boş qoymaq olmaz");
                  }else{
                      try{
            
            db.iud("update tasks set task_name='"+name+"',task_category='"+category+"', task_register='"+date+"', task_given_day='"+day+"'"
                    + " where id="+id+" ");
                    loadTasks();
            
            
                       msg("Uğurlu redaktə");
        }catch(Exception ex){
            ex.printStackTrace();
        } 
                  }
                  
              }
            }
        }
        
        
        
        
        
       
    
}
    }



    @FXML
    void deleteButtonPressed(ActionEvent event) {
  Task selectedBook=tasksTabelView.getSelectionModel().getSelectedItem();
        
if(selectedBook==null) {
    msg("Siyahıdan seçim edilməyib");
    
}else{
db.iud("delete from tasks where id = "+selectedBook.getId()+"  ");
loadTasks();
    msg("Silindi");
    if(allCheckBox.isSelected()){
        Alert alert = new Alert(AlertType.CONFIRMATION,
            "Silməyə əminsinizmi ?", ButtonType.YES,
                    ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES) {
                db.iud("truncate table tasks");
            loadTasks();msg("Bütün tapşırıqlar silindi");
            }
    }
}
    }

  

    @FXML
    void searchTFKeyReleased(KeyEvent event) {
String search=searchTF.getText().trim();
        
        filterString=" where id like '%"+search+"%' or task_name like '%"+search+"%' or task_category like '%"+search+"%' or task_given_day like '%"+search+"%' or task_register like '%"+search+"%'"; 
        loadTasks();
    }

    @FXML
    void solveButtonPressed(ActionEvent event) {
        Task selectedTask=tasksTabelView.getSelectionModel().getSelectedItem();
        if(selectedTask==null) {
            msg("Siyahıdan seçim edilməyib");
        }else{
            int id=selectedTask.getId();        
            String status=selectedTask.getTask_status();
if(status.equals("Həll edilməyib")) {
                status="Həll edilib";
                
}else{
    status="Həll edilməyib";
}
 DB db=DB.getDB();
            db.iud("update tasks set task_status='"+status+"' where id="+id+"");
            loadTasks();
                     msg("Kateqoriya qeydiyyat edildi");
                     loadCategories();
                 
             }
           }
           }        
       }
    }

    

    @FXML
    void allRadioButtonSelected(ActionEvent event) {
filterString="";
loadTasks();
    }

   

    @FXML
    void solveRadioButtonSelected(ActionEvent event) {
filterString=" where task_status='Həll edilib'";
loadTasks();
    }

    @FXML
    void tasksTabelViewMousePressed(MouseEvent event) {
Task selectedBook=tasksTabelView.getSelectionModel().getSelectedItem();

if(selectedBook==null) /* bu sert odenerse demek ki, istifadeci hec bir kitab secmeyib;*/ {
    
}else{
    taskTF.setText(selectedBook.getTask_name());
    dayTF.setText(String.valueOf(selectedBook.getTask_given_day()));
    categoryCB.setValue(selectedBook.getTask_category());
    dateDP.setValue(selectedBook.getTask_register());
    
    
}
    }
  

    @FXML
    void dontSolveRadioButtonSelected(ActionEvent event) {
filterString=" where task_status='Həll edilməyib'";
loadTasks();
    }

    

    @FXML
    void deleteCategoryButtonPressed(ActionEvent event) {
String selectedCategory=categoryCB.getValue();
if(selectedCategory==null) {
    msg("Kateqoriya seçilməyib");
}else{
    db.iud("delete from task_categories where category = '"+selectedCategory+"' ");
    msg("Kateqoriya silindi");
    loadCategories();
}
    }

   

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DB db=DB.getDB();
        dateDP.setValue(LocalDate.now());
    loadCategories();
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
taskCol.setCellValueFactory(new PropertyValueFactory<>("task_name"));
dayCol.setCellValueFactory(new PropertyValueFactory<>("task_given_day"));

categoryCol.setCellValueFactory(new PropertyValueFactory<>("task_category"));
statusCol.setCellValueFactory(new PropertyValueFactory<>("task_status"));
dateCol.setCellValueFactory(new PropertyValueFactory<>("task_register"));
    loadTasks();
            
    
    }    
    public void msg(String message){
        warningsLabel.setText(message);
    }

    private void loadCategories() {
         categoryCB.getItems().clear();
    categoryCB.getItems().addAll
        (db.getArrayListFromDB("task_categories", "category", ""));
categoryCB.getSelectionModel().select(0);
    }

    private void loadTasks() {
        tasksTabelView.setItems(db.getTasks());
    rowCountLabel.setText(String.valueOf(tasksTabelView.getItems().size()));
    }
    public static String filterString="";
}
