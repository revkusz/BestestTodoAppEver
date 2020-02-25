package hu.elte.BestestTodoAppEver;

import hu.elte.BestestTodoAppEver.entities.Category;
import hu.elte.BestestTodoAppEver.entities.Role;
import hu.elte.BestestTodoAppEver.entities.User;
import hu.elte.BestestTodoAppEver.repositories.*;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	TodoItemRepository todoItemRepository;
	@Autowired
	MenuRoleRepository menuRoleRepository;


	@EventListener(ApplicationReadyEvent.class)
	public void initializeDataToDb() {
		//Roles
		Role roleAdmin = new Role();
		roleAdmin.setName("admin");
		roleRepository.save(roleAdmin);
		Role roleUser = new Role();
		roleUser.setName("user");
		roleRepository.save(roleUser);
		Role roleGOD = new Role();
		roleGOD.setName("GOD");
		roleRepository.save(roleGOD);

		//users
		User userGOD = new User();
		userGOD.setUsername("GOD");
		userGOD.setPassowrd("MINEK?");
		userGOD.setRole(roleGOD);
		userRepository.save(userGOD);

		User userRicsi = new User();
		userRicsi.setUsername("revkusz");
		userRicsi.setPassowrd("asdvagyol12");
		userRicsi.setRole(roleAdmin);
		userRepository.save(userRicsi);

		User userSari = new User();
		userSari.setUsername("sari");
		userSari.setPassowrd("asdvagyol12");
		userSari.setRole(roleAdmin);
		userRepository.save(userSari);

		User userTest = new User();
		userTest.setUsername("test_user");
		userTest.setPassowrd("test_user");
		userTest.setRole(roleUser);
		userRepository.save(userTest);

		//Categories
		Category categoryImportant = new Category();
		categoryImportant.setColor("FF0000");
		categoryImportant.setName("Fontos!");
		categoryImportant.setOwner(userGOD);
		categoryImportant.setShowAll(Boolean.TRUE);

		Category categoryToday = new Category();
		categoryToday.setColor("FFFF00");
		categoryToday.setName("Mainap");
		categoryToday.setOwner(userGOD);
		categoryToday.setShowAll(Boolean.TRUE);

		//Menuroles


		//TestTodoITem
		System.out.println("Start the frontend haxing nigga");
	}

}
