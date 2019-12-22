package com.pdfUtil;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.seleniumExceptionHandling.CustomExceptionHandler;

public class PDFUtil {
	
	public static synchronized String getText(String absolutePath){
		StringBuffer data=new StringBuffer();
		try {
			PdfReader pdfReader = new PdfReader(absolutePath);	
			int pages = pdfReader.getNumberOfPages(); 
			for(int i=1; i<=pages; i++) { 
				data.append(PdfTextExtractor.getTextFromPage(pdfReader, i));
				//System.out.println("Content on Page "+ i + ": " + data);
			}
			pdfReader.close();
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return data.toString();
	}
	
}
