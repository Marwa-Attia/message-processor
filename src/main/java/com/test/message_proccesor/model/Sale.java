package com.test.message_proccesor.model;

import java.io.Serializable;

public class Sale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -689606020159544052L;
	private String productType;
	private Long price;

	public Sale() {
	}

	public Sale(String productType, Long price) {
		super();
		this.productType = productType;
		this.price = price;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Sale [Product Type=" + productType + ", price=" + price + "]";
	}

}
