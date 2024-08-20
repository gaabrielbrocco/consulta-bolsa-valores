package api.bolsa.investimentosbolsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InvestimentosbolsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestimentosbolsaApplication.class, args);
	}

}
