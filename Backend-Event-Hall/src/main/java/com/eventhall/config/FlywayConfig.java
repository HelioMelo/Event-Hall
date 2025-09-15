package com.eventhall.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class FlywayConfig {

	   @Bean
	    public Flyway flyway(DataSource dataSource) {
	        return Flyway.configure()
	                     .dataSource(dataSource)
	                     .baselineOnMigrate(true)
	                     .load();
	    }

	    @Bean
	    public FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
	        return new FlywayMigrationInitializer(flyway, (FlywayMigrationStrategy) -> {} );
	    }

	    @Bean
	    @DependsOn("entityManagerFactory")
	    public ApplicationRunner delayedFlywayInitializer(Flyway flyway) {
	        return args -> flyway.migrate();
	    }
}