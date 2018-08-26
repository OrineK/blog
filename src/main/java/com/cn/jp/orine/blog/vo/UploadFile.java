package com.cn.jp.orine.blog.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 文件上传
 * 
 */
public class UploadFile {

	private String nid;

	private CommonsMultipartFile[] file;

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public CommonsMultipartFile[] getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile[] file) {
		this.file = file;
	}

}
