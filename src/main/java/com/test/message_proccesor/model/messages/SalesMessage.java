package com.test.message_proccesor.model.messages;

public class SalesMessage extends GenericMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5360013198970047082L;
	private Long price;
	private Integer numberOfSales;

	public SalesMessage( String productType, Integer numberOfSales, Long price) {
		super(2, productType);
		this.numberOfSales = numberOfSales;
		this.price = price;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getNumberOfSales() {
		return numberOfSales;
	}

	public void setNumberOfSales(Integer numberOfSales) {
		this.numberOfSales = numberOfSales;
	}

}
