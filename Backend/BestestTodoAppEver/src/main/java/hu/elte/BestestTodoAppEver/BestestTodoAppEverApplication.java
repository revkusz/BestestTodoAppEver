package hu.elte.BestestTodoAppEver;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

@SpringBootApplication
public class BestestTodoAppEverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestestTodoAppEverApplication.class, args);

	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initializeDataToDb() {
		//todo create db entities
		System.out.println("hello world, I have just started up");
	}
}
