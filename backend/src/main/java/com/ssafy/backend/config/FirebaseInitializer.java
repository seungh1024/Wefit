package com.ssafy.backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseInitializer {
    // FirebaseAuth 초기화

    @Bean
    public FirebaseApp firebaseApp() throws IOException{
        File file = ResourceUtils.getFile("classpath:firebase.json");
        FileInputStream serviceAccount = new FileInputStream(file); // 비밀키 가져오기

        FirebaseOptions options = new FirebaseOptions.Builder() //생성 옵션
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(options); // firebase app 초기화
        return app;
    }

    @Bean
    public FirebaseAuth getFirebaseAuth() throws IOException {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp()); // firebaseAuth 초기화
        return firebaseAuth;
    }
}
