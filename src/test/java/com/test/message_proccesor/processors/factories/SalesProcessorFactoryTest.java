package com.test.message_proccesor.processors.factories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.test.message_proccesor.processors.Processor;
import com.test.message_proccesor.processors.SalesProcessor;

public class SalesProcessorFactoryTest {
	final static Logger LOGGER = Logger.getLogger(SalesProcessorFactoryTest.class);

	@SuppressWarnings("rawtypes")
	@Test
	public void shouldGetSalesProcessor() {
		SalesProcessorFactory factory = new SalesProcessorFactory();
		Processor processor = factory.getProcessor();
		assertNotNull(processor);
		assertTrue(processor instanceof SalesProcessor);
	}
}
