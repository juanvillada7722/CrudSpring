/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.model;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Juan Villada
 */
public class Conectar {
    
    public DriverManagerDataSource conectar() {
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1/crud");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
}
