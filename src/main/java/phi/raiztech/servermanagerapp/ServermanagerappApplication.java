package phi.raiztech.servermanagerapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import phi.raiztech.servermanagerapp.server.ServerModel;
import phi.raiztech.servermanagerapp.server.ServerRepository;
import phi.raiztech.servermanagerapp.server.ServerStatus;

import java.util.Arrays;

@SpringBootApplication
@RestController
public class ServermanagerappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServermanagerappApplication.class, args);
    }

    @GetMapping
    public String message(){
        return "Welcome to Manager Server Application";
    }
    @Bean
    CommandLineRunner run(ServerRepository serverRepository) {
        return args -> {
            serverRepository.save(new ServerModel(null, "192.168.1.161", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/api/v1/server/image/server1.png", ServerStatus.SERVER_UP));
            serverRepository.save(new ServerModel(null, "192.168.1.162", "Fedora Linux", "16 GB", "Dell Tower", "http://localhost:8080/api/v1/server/image/server2.png", ServerStatus.SERVER_DOWN));
            serverRepository.save(new ServerModel(null, "192.168.1.163", "MS 2008", "32 GB", "Web Server", "http://localhost:8080/api/v1/server/image/server3.png", ServerStatus.SERVER_UP));
            serverRepository.save(new ServerModel(null, "192.168.1.164", "Red Hat Enterprise Linux", "64 GB", "Mail Server", "http://localhost:8080/api/v1/server/image/server4.png", ServerStatus.SERVER_DOWN));

        };
    }


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        org.springframework.web.cors.UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
