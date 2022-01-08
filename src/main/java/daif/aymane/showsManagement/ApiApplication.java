package daif.aymane.showsManagement;

import daif.aymane.showsManagement.models.UserRole;
import daif.aymane.showsManagement.services.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApiApplication implements CommandLineRunner {
	private final AppUserService appUserService;

	public ApiApplication(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserRole basicUser = new UserRole("USER");
		UserRole adminUser = new UserRole("ADMIN");

		appUserService.saveRole(basicUser);
		appUserService.saveRole(adminUser);
	}
}