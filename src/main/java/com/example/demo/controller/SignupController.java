package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.FirebaseService;

@Controller
public class SignupController {

	@Autowired
	private FirebaseService firebaseService;

	@GetMapping("/signup")
	public String showSignup() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(
			@RequestParam String email,
			@RequestParam String password,
			Model model) {

		try {
			boolean success = firebaseService.signup(email, password);

			if (success) {
				return "signupSuccess";
			} else {
				model.addAttribute("errorMessage", "登録に失敗しました");
				return "signupResult";
			}

		} catch (Exception e) {
			// 🔥 ここが重要
			model.addAttribute("errorMessage", "システムエラーが発生しました");
			return "signupResult";
		}
	}

}