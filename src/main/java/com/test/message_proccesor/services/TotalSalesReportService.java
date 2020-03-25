package com.test.message_proccesor.services;

import java.util.ArrayList;
import java.util.List;

import com.test.message_proccesor.model.Sale;
import com.test.message_proccesor.model.reports.SalesReport;

public class TotalSalesReportService {

	public List<SalesReport> getTotalPriceByProductReport() {
		List<SalesReport> report = new ArrayList<SalesReport>();
		SaleService.getSaleMap().keySet().forEach(productType -> {
			List<Sale> productSales = SaleService.getSaleMap().get(productType);
			SalesReport reportItem = new SalesReport();
			reportItem.setProductType(productType);
			reportItem.setNumberOfSales(productSales.size());
			reportItem.setTotalPrice(calculateTotalPrice(productSales));
			report.add(reportItem);
		});
		return report;
	}

	private Long calculateTotalPrice(List<Sale> productSales) {
		long totalPrice = 0;
		for (Sale sale : productSales) {
			totalPrice = totalPrice + sale.getPrice();
		}
		return totalPrice;
	}
}
