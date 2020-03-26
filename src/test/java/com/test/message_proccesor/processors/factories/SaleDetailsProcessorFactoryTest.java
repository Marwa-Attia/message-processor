package com.test.message_proccesor.processors.factories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.test.message_proccesor.processors.Processor;
import com.test.message_proccesor.processors.SaleDetailsProcessor;

public class SaleDetailsProcessorFactoryTest {
	final static Logger LOGGER = Logger.getLogger(SaleDetailsProcessorFactoryTest.class);

	@SuppressWarnings("rawtypes")
	@Test
	public void shouldGetSaleDetailsProcessor() {
		SaleDetailsProcessorFactory factory = new SaleDetailsProcessorFactory();
		Processor processor = factory.getProcessor();
		assertNotNull(processor);
		assertTrue(processor instanceof SaleDetailsProcessor);
	}

}
