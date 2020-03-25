package com.test.message_proccesor.model.messages;

import java.io.Serializable;

public class GenericMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4946015259480714481L;
	private Integer messageType;
	private String productType;

	public GenericMessage(Integer messageType, String productType) {
		super();
		this.messageType = messageType;
		this.productType = productType;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

}
