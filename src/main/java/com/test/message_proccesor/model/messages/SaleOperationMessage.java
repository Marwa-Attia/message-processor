package com.test.message_proccesor.model.messages;

public class SaleOperationMessage extends GenericMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8242359586986641627L;
	private String operation;
	private Long adjutmentAmount;

	public SaleOperationMessage(String productType, String operation, Long adjutmentAmount) {
		super(3, productType);
		this.operation = operation;
		this.adjutmentAmount = adjutmentAmount;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Long getAdjutmentAmount() {
		return adjutmentAmount;
	}

	public void setAdjutmentAmount(Long adjutmentAmount) {
		this.adjutmentAmount = adjutmentAmount;
	}

}
