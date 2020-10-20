package br.com.transacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TransacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransacoesApplication.class, args);
	}

}
