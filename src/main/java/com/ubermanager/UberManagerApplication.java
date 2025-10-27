package com.ubermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UberManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UberManagerApplication.class, args);
        System.out.println("\n🚗 UberManager Application Started Successfully!");
        System.out.println("📍 Access Dashboard: http://localhost:8080");
        System.out.println("📍 Access API Docs: http://localhost:8080/api");
    }
}
