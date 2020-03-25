package com.test.message_proccesor.processors;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.test.message_proccesor.exceptions.InvalidMessageException;
import com.test.message_proccesor.exceptions.InvalidSaleException;
import com.test.message_proccesor.model.Sale;
import com.test.message_proccesor.model.messages.SalesMessage;
import com.test.message_proccesor.services.SaleService;
import com.test.message_proccesor.util.Constants;

public class SalesProcessor implements Processor<SalesMessage> {
	private SaleService saleService;

	public SalesProcessor(SaleService saleService) {
		this.saleService = saleService;
	}

	public Map<String, List<Sale>> process(SalesMessage message) throws InvalidSaleException, InvalidMessageException {
		if (Objects.isNull(message)) {
			throw new InvalidMessageException(Constants.NULL_MSG_ERROR);
		}
		if (Objects.isNull(message.getProductType())) {
			throw new InvalidMessageException(Constants.NULL_PRODUCT_TYPE_ERROR);
		}
		for (int i = 0; i < message.getNumberOfSales(); i++) {
			Sale sale = new Sale(message.getProductType(), message.getPrice());
			saleService.save(sale);
		}
		return SaleService.getSaleMap();
	}

}
