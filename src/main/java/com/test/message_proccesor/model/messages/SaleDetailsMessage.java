package com.test.message_proccesor.model.messages;

public class SaleDetailsMessage extends GenericMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 172990245428572960L;
	private Long price;

	public SaleDetailsMessage(String productType, Long price) {
		super(1, productType);
		this.price = price;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
