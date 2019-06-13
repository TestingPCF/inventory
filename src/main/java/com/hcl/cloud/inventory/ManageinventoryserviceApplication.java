package com.hcl.cloud.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hcl.cloud.inventory.config.RabbitmqConfig;
import com.hcl.cloud.inventory.config.SwaggerConfiguration;

@SpringBootApplication
@Import({ SwaggerConfiguration.class, RabbitmqConfig.class })
@EnableDiscoveryClient
@EnableJpaRepositories
public class ManageinventoryserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageinventoryserviceApplication.class, args);
    }

}
