package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.client.FirebaseClient;
import com.example.demo.service.FirebaseService;

@ExtendWith(MockitoExtension.class)
class FirebaseServiceTest {

    @Mock
    private FirebaseClient firebaseClient;

    @InjectMocks
    private FirebaseService firebaseService;

    // ✅ ① signup成功
    @Test
    void signup_success() {

        when(firebaseClient.request(anyString(), anyString(), anyString()))
                .thenReturn(true);

        boolean result = firebaseService.signup("test@example.com", "password123");

        assertTrue(result);
    }

    // ✅ ② signup失敗
    @Test
    void signup_fail() {

        when(firebaseClient.request(anyString(), anyString(), anyString()))
                .thenReturn(false);

        boolean result = firebaseService.signup("test@example.com", "password123");

        assertFalse(result);
    }

    // ✅ ③ login成功
    @Test
    void login_success() {

        when(firebaseClient.request(anyString(), anyString(), anyString()))
                .thenReturn(true);

        boolean result = firebaseService.login("test@example.com", "password123");

        assertTrue(result);
    }

    // ✅ ④ login失敗
    @Test
    void login_fail() {

        when(firebaseClient.request(anyString(), anyString(), anyString()))
                .thenReturn(false);

        boolean result = firebaseService.login("test@example.com", "password123");

        assertFalse(result);
    }

    // ✅ ⑤ 呼び出し確認（重要🔥）
    @Test
    void signup_verify_call() {

        when(firebaseClient.request(anyString(), anyString(), anyString()))
                .thenReturn(true);

        firebaseService.signup("test@example.com", "password123");

        verify(firebaseClient).request(
                contains("signUp"),
                eq("test@example.com"),
                eq("password123")
        );
    }
}