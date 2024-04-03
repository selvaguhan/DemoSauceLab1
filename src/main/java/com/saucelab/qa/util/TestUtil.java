package com.saucelab.qa.util;

import java.nio.file.Paths;

public class TestUtil {

	public static String CONFIG_PATH=Paths.get("").toAbsolutePath().toString()+"\\src\\main\\resources\\config.properties";
	public static String SECONDARY_PAGE_HEADER="//div[@class='header_secondary_container']//span[@class='title']";
	
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=10;
	public static long WEBDRIVER_WAIT=15;
	
}
