package com.test.message_proccesor.services;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.test.message_proccesor.util.Constants;

public class FileService {
	final static Logger LOGGER = Logger.getLogger(FileService.class);

	public String saveToFile(String fileName,String content){
			try (FileWriter writer = new FileWriter(Constants.BASE_URL.getPath() + fileName, true)) {
				writer.append(content);
			} catch (IOException e) {
				LOGGER.error(e.getLocalizedMessage());
			}	
		return fileName;
	}
}
