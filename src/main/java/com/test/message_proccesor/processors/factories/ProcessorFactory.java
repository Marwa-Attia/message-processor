package com.test.message_proccesor.processors.factories;

import com.test.message_proccesor.model.messages.GenericMessage;
import com.test.message_proccesor.processors.Processor;

public interface ProcessorFactory {
public Processor<? extends GenericMessage> getProcessor();
}
