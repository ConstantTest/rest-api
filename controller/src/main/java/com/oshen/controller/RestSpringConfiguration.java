package com.oshen.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:dao-context.xml")
@ComponentScan(basePackageClasses = RestSpringConfiguration.class)
public class RestSpringConfiguration {

}
