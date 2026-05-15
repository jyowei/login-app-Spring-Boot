package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.FirebaseService;

@Controller
public class LoginController {

    @Autowired
    private FirebaseService firebaseService;

    // ログイン画面表示
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // ログイン処理
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        boolean result = firebaseService.login(email, password);

        if (result) {
            return "success";
        } else {
            model.addAttribute("errorMessage", "ログイン失敗");
            return "result";
        }
    }
}