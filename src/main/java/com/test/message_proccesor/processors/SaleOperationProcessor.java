package com.test.message_proccesor.processors;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.test.message_proccesor.exceptions.InvalidAdjustmentException;
import com.test.message_proccesor.exceptions.InvalidMessageException;
import com.test.message_proccesor.exceptions.UnsupportedAdjustmentOperationException;
import com.test.message_proccesor.model.Sale;
import com.test.message_proccesor.model.messages.SaleOperationMessage;
import com.test.message_proccesor.model.reports.SaleAdjustment;
import com.test.message_proccesor.services.SaleAdjustmentsReportService;
import com.test.message_proccesor.services.SaleService;
import com.test.message_proccesor.util.Constants;

public class SaleOperationProcessor implements Processor<SaleOperationMessage> {
	private SaleService saleService;
	private SaleAdjustmentsReportService adjustmentsReportService;

	public SaleOperationProcessor(SaleService saleService, SaleAdjustmentsReportService adjustmentsReportService) {
		super();
		this.saleService = saleService;
		this.adjustmentsReportService = adjustmentsReportService;
	}

	public Map<String, List<Sale>> process(SaleOperationMessage message) throws InvalidAdjustmentException, InvalidMessageException, UnsupportedAdjustmentOperationException {
		if (Objects.isNull(message)) {
			throw new InvalidMessageException(Constants.NULL_MSG_ERROR);
		}
		if (Objects.isNull(message.getProductType())) {
			throw new InvalidMessageException(Constants.NULL_PRODUCT_TYPE_ERROR);
		}
		List<Sale> productSales = saleService.findByProductType(message.getProductType());
		for (Sale sale : productSales) {
			executeOperation(message.getOperation(), sale, message.getAdjutmentAmount());
		}
		SaleAdjustment adjustment = new SaleAdjustment(message.getOperation(), message.getAdjutmentAmount());
		adjustmentsReportService.saveAdjustment(adjustment, message.getProductType());
		return SaleService.getSaleMap();
	}

	private void executeOperation(String operation, Sale sale, Long amount) throws UnsupportedAdjustmentOperationException {
		switch (operation) {
		case "+": {
			Long price = sale.getPrice() + amount;
			sale.setPrice(price);
		}
			break;
		case "-": {
			Long price = sale.getPrice() - amount;
			sale.setPrice(price);
		}
			break;
		case "*": {
			Long price = sale.getPrice() * amount;
			sale.setPrice(price);
		}
			break;
		default:
			throw new UnsupportedAdjustmentOperationException(Constants.INVALID_OPERATION_ERROR);
		}
	}
}
