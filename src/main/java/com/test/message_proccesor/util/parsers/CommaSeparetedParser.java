package com.test.message_proccesor.util.parsers;

import java.util.Objects;

import com.test.message_proccesor.exceptions.UnSupportedMessageTypeException;
import com.test.message_proccesor.exceptions.UnparsableMessageException;
import com.test.message_proccesor.model.messages.GenericMessage;
import com.test.message_proccesor.model.messages.SaleDetailsMessage;
import com.test.message_proccesor.model.messages.SaleOperationMessage;
import com.test.message_proccesor.model.messages.SalesMessage;
import com.test.message_proccesor.util.Constants;

public class CommaSeparetedParser implements Parser {

	public GenericMessage parse(String msgStr) throws UnparsableMessageException, UnSupportedMessageTypeException {
		GenericMessage message = null;
		if (Objects.isNull(msgStr) || msgStr.isEmpty() || msgStr.isBlank()) {
			throw new UnparsableMessageException(Constants.EMPTY_MSG_ERROR);
		}
		String[] messageParts = msgStr.split(",");
		if (Objects.isNull(messageParts) || messageParts.length < 2) {
			throw new UnparsableMessageException(Constants.INVALID_MSG_ERROR);
		}
		Integer messageType = Integer.valueOf(messageParts[0]);
		String productType = messageParts[1];
		switch (messageType) {
		case 1: {
			Long price = Long.valueOf(messageParts[2]);
			message = new SaleDetailsMessage(productType, price);
			break;
		}
		case 2: {
			Integer numberOfSales = Integer.valueOf(messageParts[2]);
			Long price = Long.valueOf(messageParts[3]);
			message = new SalesMessage(productType, numberOfSales, price);
			break;
		}
		case 3: {
			String operation = messageParts[2];
			Long adjutmentAmount = Long.valueOf(messageParts[3]);
			message = new SaleOperationMessage(productType, operation, adjutmentAmount);
			break;
		}
		default:
			throw new UnSupportedMessageTypeException(Constants.INVALID_MSG_TYPE_ERROR);

		}
		return message;
	}

}
