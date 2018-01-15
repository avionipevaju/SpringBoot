package org.nantic.rest.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.nantic.dao.UserRepository;
import org.nantic.entites.User;
import org.nantic.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    protected UserRepository userRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Spring Rest Works!";
    }

    public void setCorsHeaders(HttpServletResponse res) {
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        res.setHeader("Access-Control-Max-Age", "1209600");
    }

    @RequestMapping(value="/register", method = RequestMethod.OPTIONS)
    public void registerOptions(HttpServletResponse res) {
       setCorsHeaders(res);
    }

    @PostMapping("/register")
    public String addUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
        setCorsHeaders(res);
        try {
            JSONObject jsonObject = JSONUtil.requestToJSON(req);
            System.out.println(jsonObject);
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            String email = jsonObject.getString("email");
            User user = new User();
            user.setUsername(username);
            user.setPassword(DigestUtils.sha1Hex(password));
            user.setEmail(email);
            userRepository.save(user);
            return "{\"status\":\"OK\"}";
        } catch (Exception e) {
            return "{\"status\":\"FAIL\"}";
        }

    }

    @RequestMapping(value="/login", method = RequestMethod.OPTIONS)
    public void loginOptions(HttpServletResponse res) {
        setCorsHeaders(res);
    }

    @PostMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse res){
        setCorsHeaders(res);
        try {
            JSONObject jsonObject = JSONUtil.requestToJSON(req);
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            User user = new User();
            user.setUsername(username);
            user.setPassword(DigestUtils.sha1Hex(password));
            List<User> users = userRepository.findAll();
            boolean found = false;
            for (User currentUser:users) {
                if (currentUser.getUsername().equals(username) && currentUser.getPassword().equals(DigestUtils.sha1Hex(password))) {
                    found = true;
                    break;
                }
            }
            if(!found){
                return "{\"status\":\"FAIL\"}";
            }
            return "{\"status\":" + "\"" + user.getUsername() + "\"}";
        } catch (IOException e) {
            return "{\"status\":\"FAIL\"}";
        }
    }

}
