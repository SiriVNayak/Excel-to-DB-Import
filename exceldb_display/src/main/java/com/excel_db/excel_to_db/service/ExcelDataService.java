package com.excel_db.excel_to_db.service;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excel_db.excel_to_db.entity.ExcelData;
import com.excel_db.excel_to_db.repository.ExcelDataRepository;

@Service
public class ExcelDataService {
	@Autowired
	private ExcelDataRepository excelDataRepository;

	public void saveExcelData(MultipartFile file) {
    	try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            
//            for (Row row : sheet) {
//            	ExcelData excelData = new ExcelData();
//            	excelData.setId((int)row.getCell(0).getNumericCellValue());
//            	
//            	excelData.setFirstName(row.getCell(1).getStringCellValue());
//            	excelData.setFirstName(row.getCell(2).getStringCellValue());
//            	excelData.setFirstName(row.getCell(3).getStringCellValue());
//            	excelData.setPhone(row.getCell(4).getStringCellValue());
//            	
//               
            int rowIndex = 0;
            for (Row row : sheet) {
				if (rowIndex == 0) {
					rowIndex++;
					continue;
				}
				Iterator<Cell> cellIterator = row.iterator();
				int cellIndex = 0;
				ExcelData excelData = new ExcelData();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getColumnIndex()) {
					case 0:
						excelData.setId((int) cell.getNumericCellValue());
						break;
					case 1:
						excelData.setFirstName(cell.getStringCellValue());
						break;
					case 2:
						excelData.setLastName(cell.getStringCellValue());
						break;
					case 3:
						excelData.setCountry(cell.getStringCellValue());
						break;
					case 4:
						if (cell.getCellType() == CellType.NUMERIC) {
							excelData.setPhone(String.valueOf((int) cell.getNumericCellValue()));
						} else if (cell.getCellType() == CellType.STRING) {
							excelData.setPhone(cell.getStringCellValue());
						}
						break;
					default:
						// Handle unexpected columns
						break;
					}
                excelDataRepository.save(excelData);
				}}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
