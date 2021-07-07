package dev.riddle.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppLogger {

	// create a public static final logger object - single logger
	// object for use in the entire project
	public static final Logger logger = LogManager.getLogger(AppLogger.class);

}