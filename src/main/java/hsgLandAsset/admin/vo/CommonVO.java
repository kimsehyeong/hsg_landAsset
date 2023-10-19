package hsgLandAsset.admin.vo;


import lombok.Data;
import lombok.ToString.Exclude;

@Data
public class CommonVO {
	
	@Exclude
	private int page = 1;
	@Exclude
	private int listSize = 10;
	@Exclude
	private boolean isPaging = true;
	
	@Exclude
	private String orderBy; 
	
	@Exclude
	private int s; //검색여부
	@Exclude
	private String arr;
	
	@Exclude
	private PageVO pageVO;
	

}
