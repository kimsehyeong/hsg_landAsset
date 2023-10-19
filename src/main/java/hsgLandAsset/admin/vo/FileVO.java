package hsgLandAsset.admin.vo;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("fileVO")
public class FileVO {

	int id;
	String registerNo;
	String fileName;
	String filePk;
	String fileSize;
	String filePath;
	String fileComments;
	String delYn;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Timestamp uploadDate;
	String insertId;
	String deleteId;
}
