package com.susanibar.domains.configuration;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class ApiUser {

    final static Logger miLogger = Logger.getLogger(ApiUser.class);

    static User user;
    static {
        user = new User(1, "David Dali", "Susanibar Arce", 34);
    }

    @RequestMapping("/{id}")
    public User getUserInfo(@PathVariable(name="id") int id) throws Exception {
        miLogger.info("Call -> getUserInfo");
        if(user.getCodigo()==id) {
            return user;
        } else {
            throw new Exception("Error usuario no existe!");
        }
    }

    @RequestMapping("/all")
    //@CrossOrigin(origins = "http://localhost:8080")
    public List getUsers() throws Exception {
        miLogger.info("Call -> getUsers");
        List listadoUser = new ArrayList<String>();
        listadoUser.add(user);
        return listadoUser;
    }
}
