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

import com.test.message_proccesor.exceptions.InvalidAdjustmentException;
import com.test.message_proccesor.exceptions.InvalidMessageException;
import com.test.message_proccesor.exceptions.UnsupportedAdjustmentOperationException;
import com.test.message_proccesor.model.Sale;
import com.test.message_proccesor.model.messages.SaleOperationMessage;
import com.test.message_proccesor.services.SaleAdjustmentsReportService;
import com.test.message_proccesor.services.SaleService;

public class SaleOperationProcessorTest {
	final static Logger LOGGER = Logger.getLogger(SaleOperationProcessorTest.class);

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
		SaleOperationProcessor processor = new SaleOperationProcessor(new SaleService(),
				new SaleAdjustmentsReportService());
		try {
			Map<String, List<Sale>> map = processor.process(new SaleOperationMessage("milk", "+", 5l));
			List<Sale> sales = map.get("milk");
			assertNotNull(sales);
			assertNotNull(SaleAdjustmentsReportService.getAdjustmentMap());
			assertEquals(1, SaleAdjustmentsReportService.getAdjustmentMap().size());

		} catch (InvalidMessageException e) {
			LOGGER.error(e.getLocalizedMessage());
		} catch (InvalidAdjustmentException e) {
			LOGGER.error(e.getLocalizedMessage());
		} catch (UnsupportedAdjustmentOperationException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	@Test
	public void nullMsgShouldThrowInvalidMessageException() {
		SaleOperationProcessor processor = new SaleOperationProcessor(new SaleService(),
				new SaleAdjustmentsReportService());
		Exception exception = assertThrows(InvalidMessageException.class, () -> {
			processor.process(null);
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_MSG_ERROR, exception.getLocalizedMessage());
	}

	@Test
	public void nullProductTypeShouldThrowInvalidMessageException() {
		SaleOperationProcessor processor = new SaleOperationProcessor(new SaleService(),
				new SaleAdjustmentsReportService());
		Exception exception = assertThrows(InvalidMessageException.class, () -> {
			processor.process(new SaleOperationMessage(null, "+", 20l));
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_PRODUCT_TYPE_ERROR,
				exception.getLocalizedMessage());
	}

	@Test
	public void wrongOperatorShouldThrowUnsupportedAdjustmentOperationException() {
		SaleOperationProcessor processor = new SaleOperationProcessor(new SaleService(),
				new SaleAdjustmentsReportService());
		Exception exception = assertThrows(UnsupportedAdjustmentOperationException.class, () -> {
			processor.process(new SaleOperationMessage("milk", "add", 20l));
		});
		assertEquals(com.test.message_proccesor.util.Constants.INVALID_OPERATION_ERROR,
				exception.getLocalizedMessage());
	}
}
