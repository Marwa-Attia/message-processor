package com.test.message_proccesor.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.test.message_proccesor.exceptions.InvalidAdjustmentException;
import com.test.message_proccesor.model.reports.SaleAdjustment;
import com.test.message_proccesor.util.Constants;

public class SaleAdjustmentsReportService {
	private static Map<String, List<SaleAdjustment>> adjustmentMap = new HashMap<String, List<SaleAdjustment>>();

	public List<SaleAdjustment> saveAdjustment(SaleAdjustment adjustment, String productType)
			throws InvalidAdjustmentException {
		if (Objects.isNull(adjustment)) {
			throw new InvalidAdjustmentException(Constants.NULL_ADJUSTMENT_ERROR);
		}
		if (Objects.isNull(productType)) {
			throw new InvalidAdjustmentException(Constants.NULL_PRODUCT_TYPE_ERROR);
		}
		List<SaleAdjustment> adjustments = Objects.nonNull(adjustmentMap.get(productType))
				? adjustmentMap.get(productType)
				: new ArrayList<SaleAdjustment>();
		adjustments.add(adjustment);
		adjustmentMap.put(productType, adjustments);
		return adjustments;
	}

	public static Map<String, List<SaleAdjustment>> getAdjustmentMap() {
		return adjustmentMap;
	}
}
