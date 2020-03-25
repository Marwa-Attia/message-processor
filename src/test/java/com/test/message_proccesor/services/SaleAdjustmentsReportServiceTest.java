package com.test.message_proccesor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.test.message_proccesor.exceptions.InvalidAdjustmentException;
import com.test.message_proccesor.model.reports.SaleAdjustment;

public class SaleAdjustmentsReportServiceTest {
	final static Logger LOGGER = Logger.getLogger(SaleAdjustmentsReportServiceTest.class);

	@Test
	public void saveNonNullAdjustmentShouldWorkCorrectly() {
		SaleAdjustmentsReportService adjustmentsReportService = new SaleAdjustmentsReportService();
		SaleAdjustment adjustment = new SaleAdjustment("+", 20l);
		try {
			adjustmentsReportService.saveAdjustment(adjustment, "milk");
		} catch (InvalidAdjustmentException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
		Map<String, List<SaleAdjustment>> report = SaleAdjustmentsReportService.getAdjustmentMap();
		assertNotNull(report);
		assertEquals(1, report.size());
	}

	@Test
	public void nullAdjustmentShouldThrowInvalidAdjustmentException() {
		SaleAdjustmentsReportService adjustmentsReportService = new SaleAdjustmentsReportService();
		Exception exception = assertThrows(InvalidAdjustmentException.class, () -> {
			adjustmentsReportService.saveAdjustment(null, "milk");
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_ADJUSTMENT_ERROR, exception.getLocalizedMessage());
	}

	@Test
	public void nullProductTypeShouldThrowInvalidAdjustmentException() {
		SaleAdjustmentsReportService adjustmentsReportService = new SaleAdjustmentsReportService();
		Exception exception = assertThrows(InvalidAdjustmentException.class, () -> {
			adjustmentsReportService.saveAdjustment(new SaleAdjustment("+", 20l), null);
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_PRODUCT_TYPE_ERROR,
				exception.getLocalizedMessage());
	}
}
