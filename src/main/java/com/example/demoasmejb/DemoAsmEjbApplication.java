package com.example.demoasmejb;

import com.example.demoasmejb.util.TestDetail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoAsmEjbApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(DemoAsmEjbApplication.class, args);
        TestDetail testDetail = ac.getBean(TestDetail.class);
        testDetail.data();
    }

}
