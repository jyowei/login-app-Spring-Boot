package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.FirebaseService;

@WebMvcTest(SignupController.class)
public class SignupControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FirebaseService firebaseService;

	// ✅ ① サインアップ画面表示
	@Test
	void showSignupPage() throws Exception {
		mockMvc.perform(get("/signup"))
				.andExpect(status().isOk())
				.andExpect(view().name("signup"));
	}

	// ✅ ② 登録成功
	@Test
    void signup_success() throws Exception {

        // Firebaseの登録処理は何もしない（例外なし）
        when(firebaseService.signup("test@example.com", "password123"))
        .thenReturn(true);

        mockMvc.perform(post("/signup")
                .param("email", "test@example.com")
                .param("password", "password123"))
                .andExpect(status().isOk())
                .andExpect(view().name("signupSuccess")); // ここは実装に合わせて！
    }

	// ✅ ③ 登録失敗（例外パターン）
	@Test
	void signup_fail() throws Exception {

		// エラーを発生させる
          when(firebaseService.signup(anyString(), anyString()))
		   .thenThrow(new RuntimeException("登録失敗"));

             mockMvc.perform(post("/signup")
            .param("email", "test@example.com")
            .param("password", "password123"))
            .andExpect(status().isOk())
            .andExpect(view().name("signupResult"));

	}
}
