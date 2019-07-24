package com.hellcow.testBook.api.config;

import com.hellcow.testBook.api.ApiServiceEndpoint;
import com.hellcow.testBook.api.exception.GenericExceptionMapper;
import com.hellcow.testBook.api.filter.CORSFilter;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		packages("com.hellcow.testBook.amore");
		register(GenericExceptionMapper.class);
		register(ApiServiceEndpoint.class);
		register(new LoggingFeature(Logger.getLogger(getClass().getName()), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_TEXT, 8192));
		register(CORSFilter.class);
		// Enable Tracing support.
		property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
	    property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}
}
