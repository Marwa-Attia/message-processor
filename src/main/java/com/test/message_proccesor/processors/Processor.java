package com.test.message_proccesor.processors;

import java.util.List;
import java.util.Map;

import com.test.message_proccesor.exceptions.InvalidAdjustmentException;
import com.test.message_proccesor.exceptions.InvalidMessageException;
import com.test.message_proccesor.exceptions.InvalidSaleException;
import com.test.message_proccesor.exceptions.UnsupportedAdjustmentOperationException;
import com.test.message_proccesor.model.Sale;
import com.test.message_proccesor.model.messages.GenericMessage;

public interface Processor<T extends GenericMessage> {

	public Map<String, List<Sale>> process(T message) throws InvalidSaleException, InvalidAdjustmentException, InvalidMessageException, UnsupportedAdjustmentOperationException;
}
