package com.example.creditstoragebackend;

import com.example.creditstoragebackend.Fileupload.FileControl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication(scanBasePackages = {"com.example.creditstoragebackend"})
public class CreditStorageBackendApplication {

    public static void main(String[] args) {
        new File(FileControl.uploadDirectory).mkdir();
        SpringApplication.run(CreditStorageBackendApplication.class, args);
    }

}
