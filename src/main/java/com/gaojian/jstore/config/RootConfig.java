package com.gaojian.jstore.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages={"com.gaojian.jstore"}, 
    includeFilters = {@Filter(type = FilterType.ANNOTATION, value = {Service.class}),
		@Filter(type = FilterType.ANNOTATION, value = {Component.class})
}) 
public class RootConfig {
}
