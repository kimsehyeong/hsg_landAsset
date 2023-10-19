package hsgLandAsset.admin.vo;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("userVO")
public class UserVO extends CommonVO {
	
	private int userNo;
	private int userNumber;
	
	private String task;
	
	private String id;
	
	private String pwd;
	
	private String username;
	
	private Timestamp insertDate;
	
	private String openConsultYn;
	
	private String userType;
	
	private String insertId;
	
	private String updateId;
	
	private String deleteId;
	
	private String useYn;
	
	private String delYn;
	
	private String tel;
	
	private String mode;
	
	
}
