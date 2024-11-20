package com.example.BatchSizePower;

import com.example.BatchSizePower.service.PostService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class BatchSizePowerApplication {
    @Autowired
    private PostService postService;

    public static void main(String[] args) {
        SpringApplication.run(BatchSizePowerApplication.class, args);
    }

    @PostConstruct
    public void savePosts(){
        postService.saveMainEntities();
    }
}
