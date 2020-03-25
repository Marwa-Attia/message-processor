package com.test.message_proccesor.model.reports;

import java.io.Serializable;

public class SaleAdjustment implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = -8496447242800848903L;
	private String operation;
	private Long amount;

	public SaleAdjustment(String operation, Long amount) {
		super();
		this.operation = operation;
		this.amount = amount;
	}

	public String getOperation() {
		switch (operation) {
		case "+":
			return "Add";
		case "-":
			return "Subtract";
		case "*":
			return "Multiply by";
		default:
			return operation;
		}
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "SaleAdjustment [" + getOperation() + " " + amount + "P]";
	}

}
