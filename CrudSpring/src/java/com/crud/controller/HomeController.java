/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.controller;

import com.crud.model.Conectar;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Juan Villada
 */
@Controller
public class HomeController {

    private final JdbcTemplate jdbcTemplate;

    public HomeController() {

        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());

    }

    @RequestMapping("home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        String sql = "select * from users order by id desc";
        List datos = this.jdbcTemplate.queryForList(sql);
        mv.addObject("datos", datos);
        mv.setViewName("home");
        return mv;
    }

}
