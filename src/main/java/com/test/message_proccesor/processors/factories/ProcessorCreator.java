package com.test.message_proccesor.processors.factories;

import java.util.Objects;

import com.test.message_proccesor.exceptions.InvalidMessageTypeException;
import com.test.message_proccesor.exceptions.UnSupportedMessageTypeException;
import com.test.message_proccesor.util.Constants;

public class ProcessorCreator {

	public static ProcessorFactory getFacory(Integer messageType)
			throws UnSupportedMessageTypeException, InvalidMessageTypeException {
		if (Objects.isNull(messageType)) {
			throw new InvalidMessageTypeException(Constants.NULL_MSG_TYPE_ERROR);
		}
		switch (messageType) {
		case 1:
			return new SaleDetailsProcessorFactory();
		case 2:
			return new SalesProcessorFactory();
		case 3:
			return new SaleOperationsProcessorFactory();
		default:
			throw new UnSupportedMessageTypeException(Constants.INVALID_MSG_TYPE_ERROR);
		}
	}
}
