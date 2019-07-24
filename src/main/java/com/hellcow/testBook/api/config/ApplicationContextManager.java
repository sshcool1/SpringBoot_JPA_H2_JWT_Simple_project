package com.hellcow.testBook.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class ApplicationContextManager implements ApplicationContextAware {

	private Logger logger = LoggerFactory.getLogger(ApplicationContextManager.class);

	private static ApplicationContext context;

	@Autowired
	private ApplicationContext ctx;

	@PostConstruct
	public void init() {
		this.context = ctx;
	}

	public ApplicationContextManager(ApplicationContext applicationContext) {
		this.context = applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info("INIT Application Context Aware");
		this.context = applicationContext;
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static Resource getResource(String location) {
		return context.getResource(location);
	}

	public static Resource[] getResources(String location) throws IOException {
		return context.getResources(location);
	}

	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}

}
