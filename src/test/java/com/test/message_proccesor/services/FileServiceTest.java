package com.test.message_proccesor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileServiceTest {
	final static Logger LOGGER = Logger.getLogger(FileServiceTest.class);

	@BeforeEach
	public void initSeeMap() {

	}

	@Test
	public void nonEmptyFileShouldBeSavedCorrectly() {
		FileService fileService = new FileService();
		String filename = fileService.saveToFile("test", "abc");
		assertNotNull(filename);
		assertEquals("test", filename);
	}

	@Test
	public void emptyFileShouldBeSavedCorrectly() {
		FileService fileService = new FileService();
		String filename = fileService.saveToFile("test", "");
		assertNotNull(filename);
		assertEquals("test", filename);
	}

}
