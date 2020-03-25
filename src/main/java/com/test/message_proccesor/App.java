package com.test.message_proccesor;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.test.message_proccesor.exceptions.InvalidAdjustmentException;
import com.test.message_proccesor.exceptions.InvalidFileNameException;
import com.test.message_proccesor.exceptions.InvalidMessageException;
import com.test.message_proccesor.exceptions.InvalidSaleException;
import com.test.message_proccesor.exceptions.UnSupportedMessageTypeException;
import com.test.message_proccesor.exceptions.UnparsableMessageException;
import com.test.message_proccesor.exceptions.UnsupportedAdjustmentOperationException;
import com.test.message_proccesor.model.messages.GenericMessage;
import com.test.message_proccesor.model.reports.SaleAdjustment;
import com.test.message_proccesor.model.reports.SalesReport;
import com.test.message_proccesor.processors.Processor;
import com.test.message_proccesor.processors.factories.ProcessorCreator;
import com.test.message_proccesor.services.FileService;
import com.test.message_proccesor.services.MessageReaderService;
import com.test.message_proccesor.services.SaleAdjustmentsReportService;
import com.test.message_proccesor.services.TotalSalesReportService;

public class App {
	final static Logger LOGGER = Logger.getLogger(App.class);
	static Integer numberOfMessages = 0;
	static Integer counter = 0;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		FileService fileservice = new FileService();
		MessageReaderService messageReader = new MessageReaderService();
		try (Scanner scanner = new Scanner(System.in)) {
			LOGGER.info("Please enter a file name to process:");
			while (scanner.hasNext()) {
				GenericMessage message = messageReader.read(scanner.nextLine());
				Processor processor = ProcessorCreator.getProcessor(message.getMessageType());
				processor.process(message);
				numberOfMessages++;
				counter++;
				if (counter == 10) {
					TotalSalesReportService service = new TotalSalesReportService();
					List<SalesReport> report = service.getTotalPriceByProductReport();
					LOGGER.info(report);
					Calendar now = Calendar.getInstance();
					String fileName = "TotalSalesReport_" + now.getTimeInMillis() + ".txt";
					fileservice.saveToFile(fileName, report.toString());
					counter = 0;
				}
				if (numberOfMessages == 50) {
					LOGGER.info("The Application is pausing and will stop accepting new messages");
					Map<String, List<SaleAdjustment>> report = SaleAdjustmentsReportService.getAdjustmentMap();
					LOGGER.info(report);
					Calendar now = Calendar.getInstance();
					String fileName = "TotalAdjustmentsReport_" + now.getTimeInMillis() + ".txt";
					fileservice.saveToFile(fileName, report.toString());
					break;
				}
				LOGGER.info("Message proccessed, waiting for next message:");
			}
		} catch (InvalidFileNameException | UnparsableMessageException e) {
			LOGGER.error(e.getLocalizedMessage());
		} catch (UnSupportedMessageTypeException e) {
			LOGGER.error(e.getLocalizedMessage());
		} catch (InvalidSaleException e) {
			LOGGER.error(e.getLocalizedMessage());
		} catch (InvalidAdjustmentException e) {
			LOGGER.error(e.getLocalizedMessage());
		} catch (InvalidMessageException e) {
			LOGGER.error(e.getLocalizedMessage());
		} catch (UnsupportedAdjustmentOperationException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}
}
