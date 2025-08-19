package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class MyFirstCrudOperationApplication {

    public static void main(String[] args) {


        SpringApplication.run(MyFirstCrudOperationApplication.class, args);
        System.out.println("This project is created by datta todays Date is : " + LocalDateTime.now());
    }

}
