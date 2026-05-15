package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.FirebaseClient;

@Service
public class FirebaseService {

    @Autowired
    private FirebaseClient firebaseClient;

    // ✅ 新規登録
    public boolean signup(String email, String password) {

        String endpoint =
                "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=";

        return firebaseClient.request(endpoint, email, password);
    }

    // ✅ ログイン
    public boolean login(String email, String password) {

        String endpoint =
                "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=";

        return firebaseClient.request(endpoint, email, password);
    }
}