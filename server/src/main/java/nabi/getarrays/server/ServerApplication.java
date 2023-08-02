package nabi.getarrays.server;

import nabi.getarrays.server.model.Server;
import nabi.getarrays.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CorsFilter; // Import the correct CorsFilter class
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static nabi.getarrays.server.enumeration.Status.SERVER_DOWN;
import static nabi.getarrays.server.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return orgs -> {
			serverRepo.save(new Server(null, "192.168.1.79", "Ubuntu Linux", "16 GB", "Personal PC",
					"http://localhost:8080/server/image/server1.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.80", "Fedora Linux", "16 GB", "Dell Tower",
					"http://localhost:8080/server/image/server2.png", SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.81", "Windows 11", "32 GB", " Web Server",
					"http://localhost:8080/server/image/server3.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.14", "Red Hat Enterprise Linux", "64 GB", "Mail Server",
					"http://localhost:8080/server/image/server4.png", SERVER_DOWN));
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		// Set allowed origins, methods, and headers
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Accept", "Content-Type", "Authorization", "Jwt-Token"));

		// Allow credentials
		corsConfiguration.setAllowCredentials(true);

		// Set exposed headers (optional)
		corsConfiguration.setExposedHeaders(Arrays.asList("Jwt-Token"));

		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);	}
}
