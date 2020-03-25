package com.test.message_proccesor.processors.factories;

import com.test.message_proccesor.model.messages.SalesMessage;
import com.test.message_proccesor.processors.Processor;
import com.test.message_proccesor.processors.SalesProcessor;
import com.test.message_proccesor.services.SaleService;

public class SalesProcessorFactory implements ProcessorFactory {

	public Processor<SalesMessage> getProcessor() {
		return new SalesProcessor(new SaleService());
	}

}
