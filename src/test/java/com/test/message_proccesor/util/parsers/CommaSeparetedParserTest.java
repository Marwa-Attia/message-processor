package com.test.message_proccesor.util.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.test.message_proccesor.exceptions.UnSupportedMessageTypeException;
import com.test.message_proccesor.exceptions.UnparsableMessageException;
import com.test.message_proccesor.model.messages.GenericMessage;
import com.test.message_proccesor.model.messages.SaleDetailsMessage;
import com.test.message_proccesor.model.messages.SaleOperationMessage;
import com.test.message_proccesor.model.messages.SalesMessage;

public class CommaSeparetedParserTest {
	final static Logger LOGGER = Logger.getLogger(CommaSeparetedParserTest.class);

	@BeforeEach
	public void initSeeMap() {

	}

	@Test
	public void sendingEmptyMsgShouldThrowUnparsableMessageException() {
		Parser parser = new CommaSeparetedParser();
		Exception exception = assertThrows(UnparsableMessageException.class, () -> {
			parser.parse("");
		});
		assertEquals(com.test.message_proccesor.util.Constants.EMPTY_MSG_ERROR, exception.getLocalizedMessage());
	}

	@Test
	public void sendingNullMsgShouldThrowUnparsableMessageException() {
		Parser parser = new CommaSeparetedParser();
		Exception exception = assertThrows(UnparsableMessageException.class, () -> {
			parser.parse(null);
		});
		assertEquals(com.test.message_proccesor.util.Constants.EMPTY_MSG_ERROR, exception.getLocalizedMessage());
	}

	@Test
	public void sendingInvalidMsgFormatShouldThrowUnparsableMessageException() {
		Parser parser = new CommaSeparetedParser();
		Exception exception = assertThrows(UnparsableMessageException.class, () -> {
			parser.parse("dummy nonsense");
		});
		assertEquals(com.test.message_proccesor.util.Constants.INVALID_MSG_ERROR, exception.getLocalizedMessage());
	}

	@Test
	public void sendingInvalidMsgTypeShouldThrowUnSupportedMessageTypeException() {
		Parser parser = new CommaSeparetedParser();
		Exception exception = assertThrows(UnSupportedMessageTypeException.class, () -> {
			parser.parse("5,orange,15");
		});
		assertEquals(com.test.message_proccesor.util.Constants.INVALID_MSG_TYPE_ERROR, exception.getLocalizedMessage());
	}

	@Test
	public void type1MsgShoudBeParsedCorrectly() {
		Parser parser = new CommaSeparetedParser();
		GenericMessage message;
		try {
			message = parser.parse("1,orange,15");
			assertNotNull(message);
			assertTrue(message instanceof SaleDetailsMessage);
			assertEquals(1, message.getMessageType());
		} catch (UnparsableMessageException | UnSupportedMessageTypeException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	@Test
	public void type2MsgShoudBeParsedCorrectly() {
		Parser parser = new CommaSeparetedParser();
		GenericMessage message;
		try {
			message = parser.parse("2,milk,5,4");
			assertNotNull(message);
			assertTrue(message instanceof SalesMessage);
			assertEquals(2, message.getMessageType());
		} catch (UnparsableMessageException | UnSupportedMessageTypeException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	@Test
	public void type3MsgShoudBeParsedCorrectly() {
		Parser parser = new CommaSeparetedParser();
		GenericMessage message;
		try {
			message = parser.parse("3,carrot,+,4");
			assertNotNull(message);
			assertTrue(message instanceof SaleOperationMessage);
			assertEquals(3, message.getMessageType());
		} catch (UnparsableMessageException | UnSupportedMessageTypeException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}
}
