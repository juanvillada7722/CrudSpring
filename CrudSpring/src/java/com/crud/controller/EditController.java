package com.crud.controller;

import com.crud.model.Conectar;
import com.crud.model.UserModel;
import com.crud.model.UserValidation;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("edit.htm")
public class EditController {

    UserValidation usuariosValidar;
    private JdbcTemplate jdbcTemplate;

    public EditController() {
        this.usuariosValidar = new UserValidation();
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        UserModel datos = this.selectUsuario(id);
        mav.setViewName("edit");
        mav.addObject("user", new UserModel(id, datos.getUsername(), datos.getEmail(), datos.getPassword()));
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(
            @ModelAttribute("user") UserModel u,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {
        this.usuariosValidar.validate(u, result);
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            int id = Integer.parseInt(request.getParameter("id"));
            UserModel datos = this.selectUsuario(id);
            mav.setViewName("edit");
            mav.addObject("user", new UserModel(id, datos.getUsername(), datos.getEmail(), datos.getPassword()));
            return mav;
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            this.jdbcTemplate.update(
                    "update users "
                    + "set username=?,"
                    + " email=?,"
                    + "password=? "
                    + "where "
                    + "id=? ",
                    u.getUsername(), u.getEmail(), u.getPassword(), id);
            return new ModelAndView("redirect:/home.htm");
        }

    }

    public UserModel selectUsuario(int id) {
        final UserModel user = new UserModel();
        String quer = "SELECT * FROM users WHERE id='" + id + "'";
        return (UserModel) jdbcTemplate.query(
                quer, new ResultSetExtractor<UserModel>() {
            public UserModel extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                }
                return user;
            }

        }
        );
    }
}
