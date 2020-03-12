package crackers.traders.janani;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value="crackers.traders.janani.*")
public class JananiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JananiApplication.class, args);
	}

}
