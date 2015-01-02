package com.hariram.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	 private static final PDRectangle PAGE_SIZE = PDPage.PAGE_SIZE_A3;
	    private static final float MARGIN = 20;
	    private static final boolean IS_LANDSCAPE = true;

	    // Font configuration
	    private static final PDFont TEXT_FONT = PDType1Font.HELVETICA_BOLD;
	    private static final float FONT_SIZE = 10;

	    // Table configuration
	    private static final float ROW_HEIGHT = 15;
	    private static final float CELL_MARGIN = 2;
	
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
	
	public static Table createContent(Map<Integer, String> columnMap, String[][] content) {
        // Total size of columns must not be greater than table width.
    	List<Column> columns = new ArrayList<Column>();
    	columnMap.keySet().stream()
    		.forEach(key -> {
    			String value = columnMap.get(key);
    			String[] values = value.split("-");
    			columns.add(new Column(values[0], Integer.parseInt(values[1])));
    		});

    	float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight() - (2 * MARGIN);

        Table table = new TableBuilder()
            .setCellMargin(CELL_MARGIN)
            .setColumns(columns)
            .setContent(content)
            .setHeight(tableHeight)
            .setNumberOfRows(content.length)
            .setRowHeight(ROW_HEIGHT)
            .setMargin(MARGIN)
            .setPageSize(PAGE_SIZE)
            .setLandscape(IS_LANDSCAPE)
            .setTextFont(TEXT_FONT)
            .setFontSize(FONT_SIZE)
            .build();
        return table;
    }   
}
