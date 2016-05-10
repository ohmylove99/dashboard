package org.octopus.dashboard.web.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.octopus.dashboard.Constants;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings({ "unchecked", "unused" })
public class StartupListener implements ServletContextListener {
	private static final Log log = LogFactory.getLog(StartupListener.class);

	public void contextInitialized(ServletContextEvent event) {
		log.debug("Initializing context...");

		ServletContext context = event.getServletContext();

		// Orion starts Servlets before Listeners, so check if the config
		// object already exists
		Map<String, Object> config = (HashMap<String, Object>) context.getAttribute(Constants.CONFIG);

		if (config == null) {
			config = new HashMap<String, Object>();
		}

		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);

		setupContext(context);

		// Determine version number for CSS and JS Assets
		String appVersion = null;
		try {
			InputStream is = context.getResourceAsStream("/META-INF/MANIFEST.MF");
			if (is == null) {
				log.warn("META-INF/MANIFEST.MF not found.");
			} else {
				Manifest mf = new Manifest();
				mf.read(is);
				Attributes atts = mf.getMainAttributes();
				appVersion = atts.getValue("Implementation-Version");
			}
		} catch (IOException e) {
			log.error("I/O Exception reading manifest: " + e.getMessage());
		}

		// If there was a build number defined in the war, then use it for
		// the cache buster. Otherwise, assume we are in development mode
		// and use a random cache buster so developers don't have to clear
		// their browser cache.
		if (appVersion == null || appVersion.contains("SNAPSHOT")) {
			appVersion = "" + new Random().nextInt(100000);
		}

		log.info("Application version set to: " + appVersion);
		context.setAttribute(Constants.ASSETS_VERSION, appVersion);
	}

	/**
	 * This method uses the LookupManager to lookup available roles from the
	 * data layer.
	 *
	 * @param context
	 *            The servlet context
	 */
	public static void setupContext(ServletContext context) {
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);

	}

	/**
	 * Shutdown servlet context (currently a no-op method).
	 *
	 * @param servletContextEvent
	 *            The servlet context event
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// LogFactory.release(Thread.currentThread().getContextClassLoader());
		// Commented out the above call to avoid warning when SLF4J in
		// classpath.
		// WARN: The method class
		// org.apache.commons.logging.impl.SLF4JLogFactory#release() was
		// invoked.
		// WARN: Please see http://www.slf4j.org/codes.html for an explanation.
	}
}
