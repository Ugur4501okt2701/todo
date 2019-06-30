
package com.toDoList.model;

import java.time.LocalDate;

public class Task {
    private int id;
    private String task_name;
    private String task_category;
    private int task_given_day;
    private LocalDate task_register;
    private String task_status;

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_category() {
        return task_category;
    }

    public void setTask_category(String task_category) {
        this.task_category = task_category;
    }

    public int getTask_given_day() {
        return task_given_day;
    }

    public void setTask_given_day(int task_given_day) {
        this.task_given_day = task_given_day;
    }

    public LocalDate getTask_register() {
        return task_register;
    }

    public void setTask_register(LocalDate task_register) {
        this.task_register = task_register;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public Task(int id, String task_name, String task_category, int task_given_day, LocalDate task_register, String task_status) {
        this.id = id;
        this.task_name = task_name;
        this.task_category = task_category;
        this.task_given_day = task_given_day;
        this.task_register = task_register;
        this.task_status = task_status;
    }

}
