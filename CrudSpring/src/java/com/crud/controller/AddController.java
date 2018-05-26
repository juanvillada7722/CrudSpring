package com.crud.controller;

import com.crud.model.Conectar;
import com.crud.model.UserModel;
import com.crud.model.UserValidation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("add.htm")
public class AddController {

    UserValidation usuariosValidar;
    private JdbcTemplate jdbcTemplate;

    public AddController() {
        this.usuariosValidar = new UserValidation();
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        mav.addObject("user", new UserModel());
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(
            @ModelAttribute("user") UserModel u,
            BindingResult result,
            SessionStatus status
    ) {
        this.usuariosValidar.validate(u, result);
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("add");
            mav.addObject("user", new UserModel());
            return mav;
        } else {

            this.jdbcTemplate.update(
                    "insert into users (username,email,password) values (?,?,?)",
                    u.getUsername(), u.getEmail(), u.getPassword()
            );
            return new ModelAndView("redirect:/home.htm");
        }

    }
}
