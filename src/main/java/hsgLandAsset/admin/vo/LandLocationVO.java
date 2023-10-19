package hsgLandAsset.admin.vo;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("landLocationVO")
public class LandLocationVO {

	int landId;
	String registerNo;
	String location;
	Timestamp insertDate;
	String insertId;
	String updateId;
}
