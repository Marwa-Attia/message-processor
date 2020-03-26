package com.test.message_proccesor.util;

import java.net.URL;

public class Constants {
public static final URL BASE_URL=Constants.class.getProtectionDomain().getCodeSource().getLocation();
public static final String EMPTY_MSG_ERROR="Message can't be empty";
public static final String INVALID_MSG_ERROR="Message must contain a message type and a product type";
public static final String INVALID_MSG_TYPE_ERROR="Allowed message types are : 1, 2 or 3";
public static final String NULL_FILENAME_ERROR="File Name can't be null";
public static final String NULL_SALE_ERROR = "Sale can't be null";
public static final String NULL_PRODUCT_TYPE_ERROR = "Product type can't be null";
public static final String NULL_ADJUSTMENT_ERROR = "Adjustment can't be null";
public static final String NULL_MSG_ERROR="Message can't be null";
public static final String INVALID_OPERATION_ERROR = "Supported operators are + for addition, - for subtraction and * for multiplication";
public static final String NULL_MSG_TYPE_ERROR = "Message type can't be null, supported types are 1, 2, and 3";
public static final Object MSG_PROCESSED = "Message processed successfuly";
public static final Object COUDNT_PROCESS = "Couldn't process message due to invalid input";


}
