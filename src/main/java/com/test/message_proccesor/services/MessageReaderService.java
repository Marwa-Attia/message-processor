package com.test.message_proccesor.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.test.message_proccesor.exceptions.InvalidFileNameException;
import com.test.message_proccesor.exceptions.UnSupportedMessageTypeException;
import com.test.message_proccesor.exceptions.UnparsableMessageException;
import com.test.message_proccesor.model.messages.GenericMessage;
import com.test.message_proccesor.util.Constants;
import com.test.message_proccesor.util.parsers.CommaSeparetedParser;
import com.test.message_proccesor.util.parsers.Parser;


public class MessageReaderService {
	final static Logger LOGGER = Logger.getLogger(MessageReaderService.class);

	public GenericMessage read(String fileName) throws InvalidFileNameException, UnparsableMessageException, UnSupportedMessageTypeException {
		GenericMessage message=null;
		if (Objects.nonNull(fileName)) {
			File file = new File(Constants.BASE_URL.getPath() + fileName);
			try (Scanner sc = new Scanner(file)) {
				StringBuilder msg= new StringBuilder("");
				while (sc.hasNext()) {
					msg.append(sc.nextLine());
				}
				Parser parser= new CommaSeparetedParser(); 
				message=parser.parse(msg.toString());
			} catch (FileNotFoundException e1) {
				LOGGER.error(e1.getLocalizedMessage());
			} 
		} else {
			throw new InvalidFileNameException(Constants.NULL_FILENAME_ERROR);
		}
		return message;
	}
}
