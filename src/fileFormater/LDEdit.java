package fileFormater;

import java.io.File;
import java.io.FileNotFoundException;

import ExceptionHandler.*;
import ioExtended.*;

public class LDEdit {
	public static void main(String[] args) throws FileNotFoundException{
		FileInquirer inquirerModel = new FileInquirer(new FileInquirerView());
		FileInquirerController inquirerCtrl = new FileInquirerController(inquirerModel);
		
		File file = inquirerCtrl.requestFile();
		
		LDEModel ldeModel = new LDEModel(file, new LDEView(), new BasicExceptionHandler());
		LDEController ldeController = new LDEController(ldeModel);
		
		ldeController.run();
	}
}
