package com.test.message_proccesor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.test.message_proccesor.exceptions.InvalidFileNameException;
import com.test.message_proccesor.exceptions.UnSupportedMessageTypeException;
import com.test.message_proccesor.exceptions.UnparsableMessageException;
import com.test.message_proccesor.model.messages.GenericMessage;
import com.test.message_proccesor.model.messages.SaleDetailsMessage;

public class MessageReaderServiceTest {
	final static Logger LOGGER = Logger.getLogger(MessageReaderServiceTest.class);

	@Test
	public void nonEmptyFileShouldBeReadCorrectly() {
		MessageReaderService messageReaderService = new MessageReaderService();
		GenericMessage genericMessage;
		try {
			genericMessage = messageReaderService.read("sale1.txt");
			assertNotNull(genericMessage);
			assertEquals(1, genericMessage.getMessageType());
			assertTrue(genericMessage instanceof SaleDetailsMessage);
		} catch (InvalidFileNameException | UnparsableMessageException | UnSupportedMessageTypeException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	@Test
	public void emptyFileShouldThrowInvalidFileNameException() {
		MessageReaderService messageReaderService = new MessageReaderService();
		Exception exception = assertThrows(InvalidFileNameException.class, () -> {
			messageReaderService.read(null);
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_FILENAME_ERROR,
				exception.getLocalizedMessage());
	}

}
