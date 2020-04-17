package hu.elte.BestestTodoAppEver;

import hu.elte.BestestTodoAppEver.Services.UserService;
import hu.elte.BestestTodoAppEver.entities.Category;
import hu.elte.BestestTodoAppEver.entities.Users;
import hu.elte.BestestTodoAppEver.repositories.*;
import hu.elte.BestestTodoAppEver.staticData.Roles;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
public class BestestTodoAppEverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestestTodoAppEverApplication.class, args);

	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	TodoItemRepository todoItemRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;



	@EventListener(ApplicationReadyEvent.class)
	public void initializeDataToDb() {


		userService.registerUser("GOD","MINEK?");
		userService.registerUser("revkusz","asdvagyol12");
		userService.registerUser("sari","asdvagyol12");
		userService.registerUser("test_user","test_user");
		userService.addRoles(userRepository.findById("revkusz").get(), Arrays.asList(Roles.ADMIN));
		userService.addRoles(userRepository.findById("sari").get(), Arrays.asList(Roles.ADMIN));

		//Categories
		Category categoryImportant = new Category();
		categoryImportant.setColor("FF0000");
		categoryImportant.setName("Fontos!");
		categoryImportant.setOwner(userRepository.findById("GOD").get());
		categoryImportant.setShowAll(Boolean.TRUE);
		categoryRepository.save(categoryImportant);

		Category categoryToday = new Category();
		categoryToday.setColor("FFFF00");
		categoryToday.setName("Mainap");
		categoryToday.setOwner(userRepository.findById("GOD").get());
		categoryToday.setShowAll(Boolean.TRUE);
		categoryRepository.save(categoryToday);

		//Menuroles


		//TestTodoITem
		System.out.println("Start the frontend haxing nigga");
	}

}
