package com.lin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lin.mapper")
public class SimulationLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulationLogApplication.class, args);
	}

}
