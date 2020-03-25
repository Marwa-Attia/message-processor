package com.test.message_proccesor.processors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.test.message_proccesor.exceptions.InvalidMessageException;
import com.test.message_proccesor.exceptions.InvalidSaleException;
import com.test.message_proccesor.model.Sale;
import com.test.message_proccesor.model.messages.SaleDetailsMessage;
import com.test.message_proccesor.services.SaleService;

public class SaleDetailsProcessorTest {
	final static Logger LOGGER = Logger.getLogger(SaleDetailsProcessorTest.class);

	@BeforeEach
	public void initSeeMap() {
		List<Sale> sales = new ArrayList<Sale>();
		sales.add(new Sale("milk", 20l));
		sales.add(new Sale("milk", 15l));
		sales.add(new Sale("milk", 20l));
		SaleService.getSaleMap().put("milk", sales);
	}

	@Test
	public void nonNullSalesMsgShouldBeProccesedCorrectly() {
		SaleDetailsProcessor processor = new SaleDetailsProcessor(new SaleService());
		try {
			Map<String, List<Sale>> map = processor.process(new SaleDetailsMessage("milk", 20l));
			List<Sale> sales = map.get("milk");
			assertNotNull(sales);
			assertEquals(4, sales.size());
		} catch (InvalidSaleException | InvalidMessageException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	@Test
	public void nullMsgShouldThrowInvalidMessageException() {
		SaleDetailsProcessor processor = new SaleDetailsProcessor(new SaleService());
		Exception exception = assertThrows(InvalidMessageException.class, () -> {
			processor.process(null);
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_MSG_ERROR, exception.getLocalizedMessage());
	}

	@Test
	public void nullProductTypeShouldThrowInvalidMessageException() {
		SaleDetailsProcessor processor = new SaleDetailsProcessor(new SaleService());
		Exception exception = assertThrows(InvalidMessageException.class, () -> {
			processor.process(new SaleDetailsMessage(null, 20l));
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_PRODUCT_TYPE_ERROR,
				exception.getLocalizedMessage());
	}

}
