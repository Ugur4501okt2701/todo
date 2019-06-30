/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toDoList.util;

import java.time.LocalDateTime;

/**
 *
 * @author UGUR
 */
public class Utility {
     public static String getNowDateTime() {
        String now="";
        now=LocalDateTime.now().toString();
        
        return now;
    }
}
