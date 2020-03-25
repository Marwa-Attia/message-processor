package com.test.message_proccesor.processors.factories;

import com.test.message_proccesor.model.messages.SaleOperationMessage;
import com.test.message_proccesor.processors.Processor;
import com.test.message_proccesor.processors.SaleOperationProcessor;
import com.test.message_proccesor.services.SaleAdjustmentsReportService;
import com.test.message_proccesor.services.SaleService;

public class SaleOperationsProcessorFactory implements ProcessorFactory {
	public Processor<SaleOperationMessage> getProcessor() {
		return new SaleOperationProcessor(new SaleService(),new SaleAdjustmentsReportService());
	}
}
