package com.test.message_proccesor.processors.factories;

import com.test.message_proccesor.exceptions.UnSupportedMessageTypeException;
import com.test.message_proccesor.model.messages.GenericMessage;
import com.test.message_proccesor.processors.Processor;

public class ProcessorCreator {

	public static ProcessorFactory getFacory(Integer messageType) throws UnSupportedMessageTypeException {
		switch (messageType) {
		case 1:
			return new SaleDetailsProcessorFactory();
		case 2:
			return new SalesProcessorFactory();
		case 3:
			return new SaleOperationsProcessorFactory();
		default:
			throw new UnSupportedMessageTypeException("Message type should be 1, 2 or 3");
		}
	}

	public static Processor<? extends GenericMessage> getProcessor(Integer messageType) throws UnSupportedMessageTypeException {
		return getFacory(messageType).getProcessor();
	}

}
