
package com.toDoList.db;

import com.toDoList.main.MainController;
import com.toDoList.model.Task;
import java.sql.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DB {
   private static DB instance = new DB();
    private Connection c;
    private static DB db = new DB();
    private DB() {
        System.out.println("DB - inside constructor");
        connect();
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection
        ("jdbc:mysql://127.0.0.1:3306/todo_project_4501","root","1234");
            System.out.println("Success connection...");
        } catch (Exception exc) { 
            exc.printStackTrace();
        }
       
    }
   public Connection getConnection(){
       return c;
   }
   public static DB getDB() {System.out.println("DB - getDB");return db;} 
    
   //-------------------------------------------------------------------------------------------------

    public boolean iud(String query) {
        boolean result = false;
        try {
            Statement statement = c.createStatement();
            statement.execute(query);
            close(null, statement, null);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
  public ArrayList<String> getArrayListFromDB(String table,String column,String condition) {
        ArrayList<String> list=new ArrayList<>();
        try {
            Statement s=c.createStatement();
            ResultSet r=s.executeQuery("select "+column+" from "+table+"  "+condition);
       while(r.next()){
           list.add(r.getString(column));
       }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
   public boolean hasRowInTableForThisCondition(String table,String condition) {
        
        boolean result=false;
        try {
            Statement s=c.createStatement();
            ResultSet r=s.executeQuery("select * from "+table+" "+condition);
            if(r.next()) {
                result=true;
            }
            r.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;

   }
   public  ObservableList<Task> getTasks() {
        ObservableList<Task> books=FXCollections.observableArrayList();
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection c=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"
                    + "todo_project_4501","root","1234");
           
            Statement emr=c.createStatement();
         
            ResultSet n=emr.executeQuery
        ("select * from tasks "+MainController.filterString);
            
            while (n.next()) {                
                books.add(new Task(n.getInt("id"), n.getString("task_name"), n.getString("task_category"), n.getInt("task_given_day"), n.getDate("task_register").toLocalDate(), n.getString("task_status")));
            }
            n.close();emr.close();c.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return books;
    }
   public static  DB getInstance() {
        return instance;
    }
    public void close(Connection c, Statement s, ResultSet r) {
        try {
            if(c != null) {
                c.close();
            }
            if(s != null) {
                s.close();
            }
            if(r !=null) {
               r.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


                     