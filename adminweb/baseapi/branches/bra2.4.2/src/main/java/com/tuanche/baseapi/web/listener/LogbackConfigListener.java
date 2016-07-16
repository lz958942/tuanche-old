package com.tuanche.baseapi.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class LogbackConfigListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(LogbackConfigListener.class);

	private final static String LOGBACKCONFIGULOCATION = "logbackConfigLocation";

	public void contextInitialized(final ServletContextEvent event) {
		final String location = event.getServletContext().getInitParameter(LOGBACKCONFIGULOCATION);
		final String realPath = event.getServletContext().getRealPath(location);

		final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.reset();

		final JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(loggerContext);
		try {
			configurator.doConfigure(realPath);
			logger.info("loaded slf4j configure file: [{}] success.", realPath);
		} catch (final JoranException e) {
			logger.error("load logback configurator file from: " + realPath, e);
		}
	}

	public void contextDestroyed(final ServletContextEvent event) {


	}

}
