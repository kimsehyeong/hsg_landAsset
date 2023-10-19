package hsgLandAsset.admin.vo;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("consultVO")
public class ConsultVO extends CommonVO {

	private int consultId;
	private String registerNo;
	private String consultStatus;
	private String consultInfo;
	private Timestamp consultDate;
	private String commentInfo;
	private String commentUser;
	private Timestamp commentDate;
	private String useYn;
	private String answerType;
	private String receiver;
}
