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
			serverRepo.save(new Server(null, "192.168.1.58", "Fedora Linux", "16 GB", "Dell Tower",
					"http://localhost:8080/server/image/server2.png", SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.80", "Windows 11", "32 GB", " Web Server",
					"http://localhost:8080/server/image/server3.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.14", "Red Hat Enterprise Linux", "64 GB", "Mail Server",
					"http://localhost:8080/server/image/server4.png", SERVER_DOWN));
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content_Type", "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Request-With", "" +
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
