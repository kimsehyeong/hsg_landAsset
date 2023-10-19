package hsgLandAsset.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelUtil {

	public static Workbook getWorkbook(MultipartFile file) {

		Workbook wb = null;

		/*
		 * 파일의 확장자를 체크해서 .XLS 라면 HSSFWorkbook에 .XLSX라면 XSSFWorkbook에 각각 초기화 한다.
		 */
		if (file.getOriginalFilename().toLowerCase().endsWith(".xls")) {
			try {
				wb = new HSSFWorkbook(file.getInputStream());
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		} else if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
			try {
				wb = new XSSFWorkbook(file.getInputStream());
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

		return wb;
	}

	public static String getValue(Cell cell) {
		String value = "";

		if (cell == null) {
			value = "";
		} else {
			if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				value = cell.getCellFormula();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					value = new SimpleDateFormat("yyyy-MM-dd").format(date);
				} else {
					value = cell.getNumericCellValue() + "";
				}
			} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				value = cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				value = cell.getBooleanCellValue() + "";
			} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
				value = cell.getErrorCellValue() + "";
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				value = "";
			} else {
				value = cell.getStringCellValue();
			}
		}

		return value;
	}
	
	public static String getString(Cell cell, String defaultValue) {
		String value = getValue(cell);
		if(BaseUtil.isEmpty(value)) value = defaultValue;
		return value;
	}

	public static CellStyle getHeaderCellStyle(Workbook workbook) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 정렬
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // 배경
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND); // 배경

		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

		return cellStyle;
	}

	public static CellStyle getTableCellStyle(Workbook workbook) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 정렬

		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

		return cellStyle;
	}

}
