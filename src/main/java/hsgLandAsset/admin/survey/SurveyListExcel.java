package hsgLandAsset.admin.survey;

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
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import hsgLandAsset.admin.vo.SurveyVO;
import hsgLandAsset.user.consultReq.service.SurveyService;
import hsgLandAsset.util.ExcelUtil;


@Component("SurveyListExcel")
public class SurveyListExcel extends AbstractXlsView{

	@Autowired
	SurveyService surveyService;
	
	Workbook workbook;
	Sheet sheet;
	CellStyle headerStyle;
	CellStyle cellStyle;
	
	int rownum = 0;
	
	SurveyVO surveyVO;
	
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		this.workbook = workbook;
		
		this.surveyVO = (SurveyVO) model.get("surveyVO");
		
		String userAgent = request.getHeader("User-Agent");
		boolean isIE = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1);
		
		String fileName = "만족도조사.xls";
		if(isIE) fileName = URLEncoder.encode(fileName, "utf-8" ).replaceAll("\\+", "%20");
		else fileName = new String(String.valueOf(fileName).getBytes("utf-8"), "iso-8859-1");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		this.sheet = workbook.createSheet(); 
		this.rownum = 0;
		
		createCellStyle();
		
		//makeHeader();
		
		makeBody();
		
	}
	
	public void createCellStyle() {
		headerStyle = ExcelUtil.getHeaderCellStyle(workbook);
		cellStyle = ExcelUtil.getTableCellStyle(workbook);
	}
	
	
	public void setHeaderColumn(Row row, int i, int width, String title) {
		Cell cell = row.createCell(i);
		cell.setCellValue(title);
		cell.setCellStyle(headerStyle);
		sheet.setColumnWidth(i, width*256);
	}
	public void makeBody() throws Exception {
		
		Map<String, Object> map = surveyService.getSurveyStats(surveyVO);
		
		surveyVO.setPaging(false);
		List<SurveyVO> list = surveyService.selectListSurvey(surveyVO);
		
		
		Row row = sheet.createRow(rownum);
		//sheet.addMergedRegion(CellRangeAddress.valueOf("B1:F1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("B"+(rownum+1)+":F"+(rownum+1)));
		row.createCell(0).setCellValue("기간");
		row.createCell(1).setCellValue((StringUtils.isEmpty(surveyVO.getStartDate())? "":surveyVO.getStartDate()) + " ~ " + (StringUtils.isEmpty(surveyVO.getEndDate())? "":surveyVO.getEndDate()));
		row.getCell(0).setCellStyle(headerStyle);
		row.getCell(1).setCellStyle(cellStyle);
		
		
		rownum++;
		rownum++;
		
		//연령
		row = sheet.createRow(rownum);
		row.createCell(0).setCellValue("연령");
		row.createCell(1).setCellValue("20대");
		row.createCell(2).setCellValue("30대");
		row.createCell(3).setCellValue("40대");
		row.createCell(4).setCellValue("50대");
		row.createCell(5).setCellValue("60대");
		row.createCell(6).setCellValue("합계");
		row.getCell(0).setCellStyle(headerStyle);
		row.getCell(1).setCellStyle(headerStyle);
		row.getCell(2).setCellStyle(headerStyle);
		row.getCell(3).setCellStyle(headerStyle);
		row.getCell(4).setCellStyle(headerStyle);
		row.getCell(5).setCellStyle(headerStyle);
		row.getCell(6).setCellStyle(headerStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("A"+(rownum+1)+":A"+(rownum+2)));
		rownum++;
		
		row = sheet.createRow(rownum);
		row.createCell(0).setCellValue("");
		row.createCell(1).setCellValue(map.get("age_20").toString());
		row.createCell(2).setCellValue(map.get("age_30").toString());
		row.createCell(3).setCellValue(map.get("age_40").toString());
		row.createCell(4).setCellValue(map.get("age_50").toString());
		row.createCell(5).setCellValue(map.get("age_60").toString());
		row.createCell(6).setCellValue(map.get("tot").toString());
		row.getCell(0).setCellStyle(cellStyle);
		row.getCell(1).setCellStyle(cellStyle);
		row.getCell(2).setCellStyle(cellStyle);
		row.getCell(3).setCellStyle(cellStyle);
		row.getCell(4).setCellStyle(cellStyle);
		row.getCell(5).setCellStyle(cellStyle);
		row.getCell(6).setCellStyle(cellStyle);
		rownum++;
		rownum++;
		
		//성별
		row = sheet.createRow(rownum);
		row.createCell(0).setCellValue("성별");
		row.createCell(1).setCellValue("남성");
		row.createCell(2).setCellValue("여성");
		row.createCell(3).setCellValue("합계");
		row.getCell(0).setCellStyle(headerStyle);
		row.getCell(1).setCellStyle(headerStyle);
		row.getCell(2).setCellStyle(headerStyle);
		row.getCell(3).setCellStyle(headerStyle);
		sheet.addMergedRegion(CellRangeAddress.valueOf("A"+(rownum+1)+":A"+(rownum+2)));
		rownum++;
		
		row = sheet.createRow(rownum);
		row.createCell(0).setCellValue("");
		row.createCell(1).setCellValue(map.get("sex_m").toString());
		row.createCell(2).setCellValue(map.get("sex_w").toString());
		row.createCell(3).setCellValue(map.get("tot").toString());
		row.getCell(0).setCellStyle(cellStyle);
		row.getCell(1).setCellStyle(cellStyle);
		row.getCell(2).setCellStyle(cellStyle);
		row.getCell(3).setCellStyle(cellStyle);
		rownum++;
		rownum++;
		
		
		//설문
		row = sheet.createRow(rownum);
		row.createCell(0).setCellValue("설문항목");
		row.createCell(1).setCellValue("매우만족");
		row.createCell(2).setCellValue("만족");
		row.createCell(3).setCellValue("보통");
		row.createCell(4).setCellValue("불만");
		row.createCell(5).setCellValue("매우불만");
		row.createCell(6).setCellValue("합계");
		row.getCell(0).setCellStyle(headerStyle);
		row.getCell(1).setCellStyle(headerStyle);
		row.getCell(2).setCellStyle(headerStyle);
		row.getCell(3).setCellStyle(headerStyle);
		row.getCell(4).setCellStyle(headerStyle);
		row.getCell(5).setCellStyle(headerStyle);
		row.getCell(6).setCellStyle(headerStyle);
		rownum++;
		
		row = sheet.createRow(rownum);
		row.createCell(0).setCellValue("만족도");
		row.createCell(1).setCellValue(map.get("satisfy_A").toString());
		row.createCell(2).setCellValue(map.get("satisfy_B").toString());
		row.createCell(3).setCellValue(map.get("satisfy_C").toString());
		row.createCell(4).setCellValue(map.get("satisfy_D").toString());
		row.createCell(5).setCellValue(map.get("satisfy_E").toString());
		row.createCell(6).setCellValue(map.get("tot").toString());
		row.getCell(0).setCellStyle(headerStyle);
		row.getCell(1).setCellStyle(cellStyle);
		row.getCell(2).setCellStyle(cellStyle);
		row.getCell(3).setCellStyle(cellStyle);
		row.getCell(4).setCellStyle(cellStyle);
		row.getCell(5).setCellStyle(cellStyle);
		row.getCell(6).setCellStyle(cellStyle);
		rownum++;
		
		row = sheet.createRow(rownum);
		row.createCell(0).setCellValue("친절성");
		row.createCell(1).setCellValue(map.get("kindness_A").toString());
		row.createCell(2).setCellValue(map.get("kindness_B").toString());
		row.createCell(3).setCellValue(map.get("kindness_C").toString());
		row.createCell(4).setCellValue(map.get("kindness_D").toString());
		row.createCell(5).setCellValue(map.get("kindness_E").toString());
		row.createCell(6).setCellValue(map.get("tot").toString());
		row.getCell(0).setCellStyle(headerStyle);
		row.getCell(1).setCellStyle(cellStyle);
		row.getCell(2).setCellStyle(cellStyle);
		row.getCell(3).setCellStyle(cellStyle);
		row.getCell(4).setCellStyle(cellStyle);
		row.getCell(5).setCellStyle(cellStyle);
		row.getCell(6).setCellStyle(cellStyle);
		rownum++;
		
		row = sheet.createRow(rownum);
		row.createCell(0).setCellValue("신속성");
		row.createCell(1).setCellValue(map.get("quickness_A").toString());
		row.createCell(2).setCellValue(map.get("quickness_B").toString());
		row.createCell(3).setCellValue(map.get("quickness_C").toString());
		row.createCell(4).setCellValue(map.get("quickness_D").toString());
		row.createCell(5).setCellValue(map.get("quickness_E").toString());
		row.createCell(6).setCellValue(map.get("tot").toString());
		row.getCell(0).setCellStyle(headerStyle);
		row.getCell(1).setCellStyle(cellStyle);
		row.getCell(2).setCellStyle(cellStyle);
		row.getCell(3).setCellStyle(cellStyle);
		row.getCell(4).setCellStyle(cellStyle);
		row.getCell(5).setCellStyle(cellStyle);
		row.getCell(6).setCellStyle(cellStyle);
		rownum++;
		
		row = sheet.createRow(rownum);
		row.createCell(0).setCellValue("전문성");
		row.createCell(1).setCellValue(map.get("professional_A").toString());
		row.createCell(2).setCellValue(map.get("professional_B").toString());
		row.createCell(3).setCellValue(map.get("professional_C").toString());
		row.createCell(4).setCellValue(map.get("professional_D").toString());
		row.createCell(5).setCellValue(map.get("professional_E").toString());
		row.createCell(6).setCellValue(map.get("tot").toString());
		row.getCell(0).setCellStyle(headerStyle);
		row.getCell(1).setCellStyle(cellStyle);
		row.getCell(2).setCellStyle(cellStyle);
		row.getCell(3).setCellStyle(cellStyle);
		row.getCell(4).setCellStyle(cellStyle);
		row.getCell(5).setCellStyle(cellStyle);
		row.getCell(6).setCellStyle(cellStyle);
		rownum++;
		
		row = sheet.createRow(rownum);
		row.createCell(0).setCellValue("안내 서비스 만족도");
		row.createCell(1).setCellValue(map.get("service_A").toString());
		row.createCell(2).setCellValue(map.get("service_B").toString());
		row.createCell(3).setCellValue(map.get("service_C").toString());
		row.createCell(4).setCellValue(map.get("service_D").toString());
		row.createCell(5).setCellValue(map.get("service_E").toString());
		row.createCell(6).setCellValue(map.get("tot").toString());
		row.getCell(0).setCellStyle(headerStyle);
		row.getCell(1).setCellStyle(cellStyle);
		row.getCell(2).setCellStyle(cellStyle);
		row.getCell(3).setCellStyle(cellStyle);
		row.getCell(4).setCellStyle(cellStyle);
		row.getCell(5).setCellStyle(cellStyle);
		row.getCell(6).setCellStyle(cellStyle);
		rownum++;
		rownum++;
		
		//list
		row = sheet.createRow(rownum);
		row.createCell(0).setCellValue("민원명");
		row.createCell(1).setCellValue("연령");
		row.createCell(2).setCellValue("성별");
		row.createCell(3).setCellValue("만족도");
		row.createCell(4).setCellValue("친절성");
		row.createCell(5).setCellValue("신속성");
		row.createCell(6).setCellValue("전문성");
		row.createCell(7).setCellValue("안내서비스 만족도");
		row.createCell(8).setCellValue("개선사항");
		row.getCell(0).setCellStyle(headerStyle);
		row.getCell(1).setCellStyle(headerStyle);
		row.getCell(2).setCellStyle(headerStyle);
		row.getCell(3).setCellStyle(headerStyle);
		row.getCell(4).setCellStyle(headerStyle);
		row.getCell(5).setCellStyle(headerStyle);
		row.getCell(6).setCellStyle(headerStyle);
		row.getCell(7).setCellStyle(headerStyle);
		row.getCell(8).setCellStyle(headerStyle);
		rownum++;
		
		for(SurveyVO item : list) {
			row = sheet.createRow(rownum);
			row.createCell(0).setCellValue(item.getSurveyTitle());
			row.createCell(1).setCellValue(item.getAge()==0? "":item.getAge()+"");
			row.createCell(2).setCellValue(item.getGender()==null? "":item.getGender().equals("m")? "남성":"여성");
			row.createCell(3).setCellValue(getEval(item.getQsatisfy()));
			row.createCell(4).setCellValue(getEval(item.getQkindness()));
			row.createCell(5).setCellValue(getEval(item.getQquickness()));
			row.createCell(6).setCellValue(getEval(item.getQprofessional()));
			row.createCell(7).setCellValue(getEval(item.getQservice()));
			row.createCell(8).setCellValue(item.getSuggestion());
			row.getCell(0).setCellStyle(cellStyle);
			row.getCell(1).setCellStyle(cellStyle);
			row.getCell(2).setCellStyle(cellStyle);
			row.getCell(3).setCellStyle(cellStyle);
			row.getCell(4).setCellStyle(cellStyle);
			row.getCell(5).setCellStyle(cellStyle);
			row.getCell(6).setCellStyle(cellStyle);
			row.getCell(7).setCellStyle(cellStyle);
			row.getCell(8).setCellStyle(cellStyle);
			rownum++;
		}
		
		
		//set width
		sheet.setColumnWidth(0, 256*60);
		sheet.setColumnWidth(1, 256*15);
		sheet.setColumnWidth(2, 256*15);
		sheet.setColumnWidth(3, 256*15);
		sheet.setColumnWidth(4, 256*15);
		sheet.setColumnWidth(5, 256*15);
		sheet.setColumnWidth(6, 256*15);
		sheet.setColumnWidth(7, 256*15);
		sheet.setColumnWidth(8, 256*100);
		
		
	}
	
	public String getEval(String str) {
		String result = "";
		
		if(StringUtils.hasText(str)) {
			if(str.equals("A")) result = "매우만족";
			else if(str.equals("B")) result = "만족";
			else if(str.equals("C")) result = "보통";
			else if(str.equals("D")) result = "불만";
			else if(str.equals("E")) result = "매우불만";
		}
		
		return result;
	}

}
