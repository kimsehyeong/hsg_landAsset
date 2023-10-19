package hsgLandAsset.admin.status.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import hsgLandAsset.admin.status.service.AdminStatusService;
import hsgLandAsset.admin.vo.StatusVO;
import hsgLandAsset.util.ExcelUtil;


@Component("StatsListExcel")
public class StatsListExcel extends AbstractXlsView{

	@Autowired
	AdminStatusService statsService;
	
	Workbook workbook;
	Sheet sheet;
	CellStyle headerStyle;
	CellStyle cellStyle;
	
	int rownum = 0;
	
	StatusVO statsVO;
	
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		this.workbook = workbook;
		
		this.statsVO = (StatusVO) model.get("statusVO");
		
		String userAgent = request.getHeader("User-Agent");
		boolean isIE = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1);
		
		String fileName = "통계.xls";
		if(isIE) fileName = URLEncoder.encode(fileName, "utf-8" ).replaceAll("\\+", "%20");
		else fileName = new String(String.valueOf(fileName).getBytes("utf-8"), "iso-8859-1");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		this.sheet = workbook.createSheet(); 
		this.rownum = 0;
		
		createCellStyle();
		
		makeHeader();
		
		makeBody();
		
	}
	
	public void createCellStyle() {
		headerStyle = ExcelUtil.getHeaderCellStyle(workbook);
		cellStyle = ExcelUtil.getTableCellStyle(workbook);
	}
	
	public void makeHeader() throws Exception {
		
		Row header = sheet.createRow(rownum++);
		
		setHeaderColumn(header, 0, 10, "번호");
		setHeaderColumn(header, 1, 30, "접수번호");
		setHeaderColumn(header, 2, 50, "민원명");
		setHeaderColumn(header, 3, 20, "민원인");
		setHeaderColumn(header, 4, 20, "전화번호");
		setHeaderColumn(header, 5, 20, "담당자");
		setHeaderColumn(header, 6, 20, "접수일");
		setHeaderColumn(header, 7, 20, "처리기한");
		setHeaderColumn(header, 8, 10, "접수확인");
		setHeaderColumn(header, 9, 10, "협의내용");
		
	}
	
	public void setHeaderColumn(Row row, int i, int width, String title) {
		Cell cell = row.createCell(i);
		cell.setCellValue(title);
		cell.setCellStyle(headerStyle);
		sheet.setColumnWidth(i, width*256);
	}
	public void makeBody() throws Exception {
		statsVO.setPaging(false);
		List<StatusVO> list = statsService.selectStatsList(statsVO);
		
		Row row = sheet.createRow(rownum);
		
		for(int i=0; i<list.size(); i++) {
			StatusVO item = list.get(i);
			row = sheet.createRow(rownum);
			row.createCell(0).setCellValue((i+1)+"");
			row.createCell(1).setCellValue(item.getRegisterNo());
			row.createCell(2).setCellValue(item.getMwAfrShtnm());
			row.createCell(3).setCellValue(item.getMwAplctNm());
			row.createCell(4).setCellValue(item.getMwAplctHpno());
			row.createCell(5).setCellValue(item.getDppUsrNm());
			row.createCell(6).setCellValue(item.getTakeYmd());
			row.createCell(7).setCellValue(item.getDealPlanYmd());
			row.createCell(8).setCellValue(item.getProgressCnt()+"");
			row.createCell(9).setCellValue(item.getCompromiseCnt()+"");
			row.getCell(0).setCellStyle(cellStyle);
			row.getCell(1).setCellStyle(cellStyle);
			row.getCell(2).setCellStyle(cellStyle);
			row.getCell(3).setCellStyle(cellStyle);
			row.getCell(4).setCellStyle(cellStyle);
			row.getCell(5).setCellStyle(cellStyle);
			row.getCell(6).setCellStyle(cellStyle);
			row.getCell(7).setCellStyle(cellStyle);
			row.getCell(8).setCellStyle(cellStyle);
			row.getCell(9).setCellStyle(cellStyle);
			rownum++;
		}
		
	}
	

}
