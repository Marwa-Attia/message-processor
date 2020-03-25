package com.test.message_proccesor.processors.factories;

import com.test.message_proccesor.model.messages.SaleDetailsMessage;
import com.test.message_proccesor.processors.Processor;
import com.test.message_proccesor.processors.SaleDetailsProcessor;
import com.test.message_proccesor.services.SaleService;

public class SaleDetailsProcessorFactory implements ProcessorFactory {

	public Processor<SaleDetailsMessage> getProcessor() {
		return new SaleDetailsProcessor(new SaleService());
	}

}
