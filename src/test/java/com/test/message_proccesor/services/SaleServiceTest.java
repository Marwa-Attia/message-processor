package com.test.message_proccesor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.test.message_proccesor.exceptions.InvalidSaleException;
import com.test.message_proccesor.model.Sale;

public class SaleServiceTest {
	final static Logger LOGGER = Logger.getLogger(SaleServiceTest.class);

	@Test
	public void findByProductTypeShouldAlwaysReturnASaleList() {
		SaleService saleService = new SaleService();
		List<Sale> sales1 = saleService.findByProductType(null);
		assertNotNull(sales1);
		List<Sale> sales2 = saleService.findByProductType("");
		assertNotNull(sales2);
		List<Sale> sales3 = saleService.findByProductType("milk");
		assertNotNull(sales3);
	}

	@Test
	public void saleShouldBeSavedCorrectly() {
		SaleService saleService = new SaleService();
		try {
			Sale savedSale = saleService.save(new Sale("milk", 20L));
			assertNotNull(savedSale);
			assertEquals("milk", savedSale.getProductType());
			assertFalse(SaleService.getSaleMap().isEmpty());
		} catch (InvalidSaleException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	@Test
	public void nullInputShouldThrowInvalidSaleException() {
		SaleService saleService = new SaleService();
		Exception exception = assertThrows(InvalidSaleException.class, () -> {
			saleService.save(null);
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_SALE_ERROR, exception.getLocalizedMessage());
	}

	@Test
	public void nullProductTypeShouldThrowInvalidSaleException() {
		SaleService saleService = new SaleService();
		Exception exception = assertThrows(InvalidSaleException.class, () -> {
			saleService.save(new Sale(null, 20l));
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_PRODUCT_TYPE_ERROR,
				exception.getLocalizedMessage());
	}

	@Test
	public void emptyProductTypeShouldThrowInvalidSaleException() {
		SaleService saleService = new SaleService();
		Exception exception = assertThrows(InvalidSaleException.class, () -> {
			saleService.save(new Sale("", 20l));
		});
		assertEquals(com.test.message_proccesor.util.Constants.NULL_PRODUCT_TYPE_ERROR,
				exception.getLocalizedMessage());
	}
}
