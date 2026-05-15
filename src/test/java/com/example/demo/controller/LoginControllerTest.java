package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.FirebaseService;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirebaseService firebaseService;

    // ✅ ① ログイン画面表示のテスト
    @Test
    void showLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    // ✅ ② ログイン成功
    @Test
    void login_success() throws Exception {

        // モック設定（true返す）
        when(firebaseService.login("test@example.com", "password123"))
                .thenReturn(true);

        mockMvc.perform(post("/login")
                .param("email", "test@example.com")
                .param("password", "password123"))
                .andExpect(status().isOk())
                .andExpect(view().name("success"));
    }

    // ✅ ③ ログイン失敗
    @Test
    void login_fail() throws Exception {

        // モック設定（false返す）
        when(firebaseService.login("test@example.com", "wrongpass"))
                .thenReturn(false);

        mockMvc.perform(post("/login")
                .param("email", "test@example.com")
                .param("password", "wrongpass"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "ログイン失敗"));
    }
}