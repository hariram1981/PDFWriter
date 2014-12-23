package com.hariram.pdf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFSample {

    // Page configuration
    private static final PDRectangle PAGE_SIZE = PDPage.PAGE_SIZE_A3;
    private static final float MARGIN = 20;
    private static final boolean IS_LANDSCAPE = true;

    // Font configuration
    private static final PDFont TEXT_FONT = PDType1Font.HELVETICA;
    private static final float FONT_SIZE = 10;

    // Table configuration
    private static final float ROW_HEIGHT = 15;
    private static final float CELL_MARGIN = 2;
    
    public static void main(String[] args) throws IOException, COSVisitorException {
    	
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