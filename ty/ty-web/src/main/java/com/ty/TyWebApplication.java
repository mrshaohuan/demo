package com.ty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ty.core.dao")
/*@ComponentScan({"com.ty.core","com.ty.web"})*/
public class TyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TyWebApplication.class, args);
    }

}

