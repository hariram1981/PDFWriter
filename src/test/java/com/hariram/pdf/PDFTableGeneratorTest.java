package com.hariram.pdf;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.junit.Test;

/**
 * @author hariram
 * date 22-Dec-2014
 */
public class PDFTableGeneratorTest {

	//@Test
	public void testTable() {
		try {
			Map<Integer, String> columnMap = new TreeMap<Integer, String>();
			columnMap.put(1, "FirstName-90");
			columnMap.put(2, "LastName-90");
			columnMap.put(3, "Email-230");
			columnMap.put(4, "ZipCode-43");
			columnMap.put(5, "MailOptIn-50");
			columnMap.put(6, "Code-80");
			columnMap.put(7, "Branch-39");
			columnMap.put(8, "Product-300");
			columnMap.put(9, "Date-120");
			columnMap.put(10, "Channel-50");
			
			String[][] content = { 
			        { "FirstName", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am", "WEB" },
			        { "FirstName", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am", "WEB" },
			        { "FirstName", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am", "WEB" }
			};
			
			Table table = PDFUtil.createContent(columnMap, content);
			//System.out.println(table.getNumberOfColumns());
			//System.out.println(table.getColumns().get(9).getName());
			new PDFGenerator().generatePDF(table,"sampletable.pdf");
			assertTrue(true);
		} catch (COSVisitorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	//@Test
	public void testContent() {
		try {
			String[] content = { 
					"FirstName", "LastName", "fakemail@mock.com", "12345", "yes", "XH4234FSD", "4334", "yFone 5 XS", "31/05/2013 07:15 am", "WEB" 
			};
			
			new PDFGenerator().generatePDF(content,"samplecontent.pdf");
			assertTrue(true);
		} catch (COSVisitorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testConvertData() {
		List<String> list = new ArrayList<String>();
		Map<String, List<String>> dataMap = new HashMap<String, List<String>>();
		
		list.add("1.0");
		list.add("1.5");
		list.add("2.0");
		list.add("2.5");
		dataMap.put(null, list);
		
		list = new ArrayList<String>();
		list.add("2.0");
		list.add("2.5");
		list.add("3 .0");
		list.add("3.5");
		//list.add("3.0");
		dataMap.put("B", list);

		Map<Integer, String> columnMap = new TreeMap<Integer, String>();
		columnMap.put(1, "col1");
		columnMap.put(2, "col2");
		columnMap.put(3, "col3");
		columnMap.put(4, "col4");
		
		
		try {
			String[][] content = PDFUtil.convertData(dataMap);
			System.out.println(content);
			
			Table table = PDFUtil.createContent(columnMap, content);
			new PDFGenerator().generatePDF(table,"table.pdf");
		} catch (COSVisitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
