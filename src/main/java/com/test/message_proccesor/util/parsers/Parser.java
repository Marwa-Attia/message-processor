package com.test.message_proccesor.util.parsers;

import com.test.message_proccesor.exceptions.UnSupportedMessageTypeException;
import com.test.message_proccesor.exceptions.UnparsableMessageException;
import com.test.message_proccesor.model.messages.GenericMessage;

public interface Parser {
public GenericMessage parse(String msgStr) throws UnparsableMessageException, UnSupportedMessageTypeException;
}
