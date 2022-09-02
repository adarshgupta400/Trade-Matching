package com.osttra.trade_matching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class TradeMatchingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeMatchingApplication.class, args);
	}

}

