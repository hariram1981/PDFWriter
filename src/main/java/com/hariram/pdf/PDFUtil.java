package com.hariram.pdf;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * 
 * @author hariram
 * date 08-Dec-2014
 */
public class PDFUtil {
	public static void main(String[] args) {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage(PDPage.PAGE_SIZE_A4);
		document.addPage(page);
		
		PDFont font = PDType1Font.TIMES_ROMAN;
		
		try {
			PDPageContentStream contentStream = new PDPageContentStream(document, page);

			PDRectangle rect = page.getMediaBox();
			int line = 0;
			
			/*contentStream.beginText();
			contentStream.setFont(font, 12);
			contentStream.moveTextPositionByAmount( 100, rect.getHeight() - 50*(++line) );
			contentStream.drawString( "Hello World" );
			contentStream.endText();
			
			*/
			writeText(contentStream, "Hello World1", font, 12, rect, ++line);
			
			writeText(contentStream, "Hello World2", font, 12, rect, ++line);
			
			contentStream.close();
			
			document.save("first.pdf");
			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (COSVisitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public static void writeText(PDPageContentStream contentStream, String text, PDFont font, int fontSize, PDRectangle rect, int line) {
		try {
			contentStream.beginText();
			contentStream.setFont(font, fontSize);
			contentStream.moveTextPositionByAmount( 100, rect.getHeight() - 20*(line) );
			contentStream.drawString(text);
			contentStream.endText();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
