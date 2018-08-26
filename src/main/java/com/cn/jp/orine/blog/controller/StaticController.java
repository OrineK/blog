package com.cn.jp.orine.blog.controller;


import com.cn.jp.orine.blog.Exception.BusinessException;
import com.cn.jp.orine.blog.constant.ResultMsg;
import com.cn.jp.orine.blog.utils.*;
import com.cn.jp.orine.blog.vo.UploadFile;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
public class StaticController {
	private static final Logger logger = LoggerFactory.getLogger(StaticController.class);
	/**
	 * 1M=1024KB ,1KB=1024字节
	 */
	private static final double PICSIZE = 1024.0;
	// 图片路径
//	private String picUrl = "/data/blog/pic/";
	// 图片返回路径
//	private String resPicUrl = "/data/pic/";

	//本地测试
	private String picUrl = "D://workspace//upload";
	private String resPicUrl = "/data/blog/pic/";

	private static final String UPLOAD_PATH = "/upload/img/";

	/**
	 * 上传图片 适应于laiyu的
	 * @param file
	 * @param dirname
	 * @return
	 */
	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	public String uploadImg(@RequestParam(value = "file") MultipartFile file, String dirname,
							HttpServletResponse response) throws IOException {
		logger.debug("上传图片");
		System.out.println(dirname);
		System.out.println(file.getName());
		PrintWriter out = null;
		if (file.isEmpty()) {
			throw new BusinessException("未检测到图片内容");
		}
		String resPath = uploadPic((CommonsMultipartFile) file, dirname);
		out = response.getWriter();
		out.print(JsonUtil.newJson().addData("data", resPath).toJson());
		out.flush();
		out.close();
		return resPath;
	}


	/**
	 * 图片上传
	 *
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/img/uploadifySave", method = RequestMethod.POST)
	public List<String> uploadifySave(UploadFile file, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.debug("图片上传..");
		PrintWriter out = null;
		List<String> resPaths = new ArrayList<>();
		if (null == file.getFile() || file.getFile().length == 0) {
			throw new BusinessException("未检测到图片内容");
		}
		for (CommonsMultipartFile item : file.getFile()) {
			String resPath = uploadPic(item, file.getNid());
			resPaths.add(resPath);
		}
		out = response.getWriter();
		out.print(JsonUtil.newJson().addData("data", resPaths).toJson());
		out.flush();
		out.close();
		return resPaths;
	}

	/**
	 * 处理图片
	 * 
	 * @param multipartFile
	 * @param nid
	 * @return
	 */
	private String uploadPic(CommonsMultipartFile multipartFile, String nid) {
		String picName = multipartFile.getFileItem().getName();
		String prefix = "";
		String fileName = "";
		String resPath = "";
		try {

			DiskFileItem fi = (DiskFileItem) multipartFile.getFileItem();
			File newfile = fi.getStoreLocation();
			// 取文件后缀名
			prefix = getFileExt(picName);
			// 取规定的文件格式对应字符码
			String prefixStr = FileTypeUtil.FILE_TYPE_MAP.get(prefix);
			// 校验上传文件格式
			if (!FileTypeUtil.checkFileType(newfile, "") || StringUtils.isBlank(nid)
					|| StringUtils.isBlank(prefixStr)) {
				throw new BusinessException("文件格式错误！");
			}
			// 校验图片大小
			double fileSize = BigDecimalUtil.div(newfile.length(), PICSIZE);
			fileSize = BigDecimalUtil.div(fileSize, PICSIZE, 2);
			if (BigDecimalUtil.sub(fileSize, 20) > 0) {
				throw new BusinessException(ResultMsg.UPLOAD_PIC_SIZE_ERROR);
			}
			// 保存文件的相对路径
			String filePath = picUrl + nid + "/" + getFolder();
			// 试图创建这个file 如果没有该目录则创建
			fileName = NidGenerator.getSerialNumber();
			File files = new File(filePath);
			if (!files.exists()) {
				files.mkdirs();
			}
			File orignFile = new File(filePath + "/" + fileName + "." + prefix);
			FileUtil.copyFile(newfile, orignFile);

			File fe = new File(filePath + "/" + fileName + "_big" + "." + prefix);
			FileUtil.copyFile(newfile, fe);
			// 把原图生成缩略图
			ImgUtil.scaleImg(orignFile, "", 200, 200);
		} catch (Exception e) {
			logger.error("IO异常", e);
			throw new BusinessException("文件上传失败！");
		} finally {
			resPath = resPicUrl + nid + "/" + getFolder() + "/" + fileName + "." + prefix;
		}
		return resPath;
	}

	/**
	 * 去文件名后缀
	 * 
	 * @param fileName
	 * @return
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 年月日目录
	 * 
	 * @return
	 */
	public String getFolder() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		return formater.format(new Date());
	}

	/**
	 * 
	 * 封装读取路径 返回给平台
	 * 
	 * @param nid
	 * @param fileName
	 * @param prefix
	 * @return
	 * @param
	 */
	public String responseUrl(String nid, String fileName, String prefix) {
		String url = "/" + nid + "/" + fileName + "-" + prefix + "/view.html";
		return url;
	}

}
