package com.test.message_proccesor.processors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.test.message_proccesor.exceptions.InvalidMessageException;
import com.test.message_proccesor.exceptions.InvalidSaleException;
import com.test.message_proccesor.model.Sale;
import com.test.message_proccesor.model.messages.SalesMessage;
import com.test.message_proccesor.services.SaleService;

public class SalesProcessorTest {
	final static Logger LOGGER = Logger.getLogger(SalesProcessorTest.class);

	@BeforeEach
	public void initSeeMap() {

	}

	@Test
	public void nonNullSalesMsgShouldBeProccesedCorrectly() {
		SalesProcessor processor = new SalesProcessor(new SaleService());
		try {
			Map<String, List<Sale>> map = processor.process(new SalesMessage("milk", 20, 20l));
			List<Sale> sales = map.get("milk");
			assertNotNull(sales);
			assertEquals(20, sales.size());
			assertFalse(map.isEmpty());

		} catch (InvalidSaleException | InvalidMessageException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	@Test
	public void nullSalesMsgShouldThrowInvalidMessageException() {
		SalesProcessor processor = new SalesProcessor(new SaleService());
		Exception exception = assertThrows(InvalidMessageException.class, () -> {
			processor.process(null);
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_MSG_ERROR, exception.getLocalizedMessage());
	}

	@Test
	public void nullProductTypeShouldThrowInvalidMessageException() {
		SalesProcessor processor = new SalesProcessor(new SaleService());
		Exception exception = assertThrows(InvalidMessageException.class, () -> {
			processor.process(new SalesMessage(null, 20, 20l));
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_PRODUCT_TYPE_ERROR,
				exception.getLocalizedMessage());
	}
}
