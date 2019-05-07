package com.hcl.cloud.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.hcl.cloud.inventory.config.RabbitmqConfig;
import com.hcl.cloud.inventory.config.SwaggerConfiguration;


@SpringBootApplication
@Import({SwaggerConfiguration.class,RabbitmqConfig.class})
public class ManageinventoryserviceApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(ManageinventoryserviceApplication.class, args);
	}

}
