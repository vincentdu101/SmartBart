package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import services.*;
import models.*;

@SpringBootApplication
@ComponentScan ({"services", "models", "controllers"})
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String args[]) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
        app.run(args);
    }

    private StationController stationController;
    private RoutesController routesController;
    private PlannerController plannerController;
    private StationService stationService;
    private TrainMonitor trainMonitor;
    private TrainService trainService;
    private RouteService routeService;
    private RequestService requestService;
    private PlannerService plannerService;

    @Autowired
    ApplicationContext context;

    @Override
    public void run(String... strings) throws Exception {
        routeService = (RouteService) context.getBean("routeService");
        plannerService = (PlannerService) context.getBean("plannerService");
        requestService = (RequestService) context.getBean("requestService");
        stationService = (StationService) context.getBean("stationService");

        routesController = (RoutesController) context.getBean("routesController");
        plannerController = (PlannerController) context.getBean("plannerController");
        stationController = (StationController) context.getBean("stationController");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurerAdapter() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE")
                        .allowedHeaders("X-Auth-Token", "Content-Type")
                        .exposedHeaders("custom-header1", "custom-header2")
                        .allowCredentials(false)
                        .maxAge(4800);
            }

        };

    }

}