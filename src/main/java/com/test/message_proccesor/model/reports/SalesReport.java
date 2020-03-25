package com.test.message_proccesor.model.reports;

import java.io.Serializable;

public class SalesReport implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 6147271000447483614L;
	private String productType;
	private Integer numberOfSales;
	private Long totalPrice;

	public SalesReport(String productType, Integer numberOfSales, Long totalPrice) {
		super();
		this.productType = productType;
		this.numberOfSales = numberOfSales;
		this.totalPrice = totalPrice;
	}

	public SalesReport() {
		super();
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getNumberOfSales() {
		return numberOfSales;
	}

	public void setNumberOfSales(Integer numberOfSales) {
		this.numberOfSales = numberOfSales;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "SalesReport [Product Type=" + productType + ", Number Of Sales=" + numberOfSales + ", Total Price="
				+ totalPrice + "]";
	}

}
