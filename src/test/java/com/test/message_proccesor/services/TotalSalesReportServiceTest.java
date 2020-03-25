package com.test.message_proccesor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.test.message_proccesor.model.Sale;
import com.test.message_proccesor.model.reports.SalesReport;

public class TotalSalesReportServiceTest {
	final static Logger LOGGER = Logger.getLogger(TotalSalesReportServiceTest.class);

	@BeforeEach
	public void initSeeMap() {
		List<Sale> sales = new ArrayList<Sale>();
		sales.add(new Sale("milk", 20l));
		sales.add(new Sale("milk", 15l));
		sales.add(new Sale("milk", 20l));
		SaleService.getSaleMap().put("milk", sales);
		List<Sale> sales2 = new ArrayList<Sale>();

		sales2.add(new Sale("apple", 10l));
		SaleService.getSaleMap().put("apple", sales2);
		List<Sale> sales3 = new ArrayList<Sale>();

		sales3.add(new Sale("orange", 9l));
		SaleService.getSaleMap().put("orange", sales3);
	}

	@Test
	public void getTotalPriceByProductReportShouldAlwaysWork() {
		TotalSalesReportService salesReportService = new TotalSalesReportService();
		List<SalesReport> report = salesReportService.getTotalPriceByProductReport();
		assertNotNull(report);
		assertEquals(3, report.size());
	}
}
