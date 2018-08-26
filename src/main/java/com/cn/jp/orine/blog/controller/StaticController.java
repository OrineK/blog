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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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

	@RequestMapping("/uploadImg")
	public void uplodaImg(@RequestParam("upload") MultipartFile file, HttpServletRequest request,
						  HttpServletResponse response, @RequestParam("CKEditorFuncNum") String CKEditorFuncNum)
			throws IllegalStateException, IOException {
		PrintWriter out = response.getWriter();
		String fileName = file.getOriginalFilename();
		String uploadContentType = file.getContentType();
		String expandedName = "";
		if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) {
			// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
			expandedName = ".jpg";
		} else if (uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")) {
			// IE6上传的png图片的headimageContentType是"image/x-png"
			expandedName = ".png";
		} else if (uploadContentType.equals("image/gif")) {
			expandedName = ".gif";
		} else if (uploadContentType.equals("image/bmp")) {
			expandedName = ".bmp";
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'',"
					+ "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
			out.println("</script>");
			return;
		}
		if (file.getSize() > 600 * 1024) {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",''," + "'文件大小不得大于600k');");
			out.println("</script>");
			return;
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		fileName = df.format(new Date()) + expandedName;
		/** 构建上传图片的保存目录* */
		String saveDir = UPLOAD_PATH + fileName; // 相当于("/upload/img/"+fileName)
		/** 得到文件保存目录的真实路径* */
		/**
		 * 一定要注意这里，这里的路径就是上传图片所在的项目根路径，在编译后的文件夹里面，前端可以根据wepapp下的路径直接取即可。
		 * 不确定的话，就打印出这句话，然后看看下面的配置的前端是怎么取到的
		 */
		String imgRealPathDir = request.getSession().getServletContext().getRealPath(saveDir);

		File targetFile = new File(imgRealPathDir);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		file.transferTo(targetFile);// 注意，项目经常会在这里出现错误，错误的原因一般都是配的上传路径或者前端获取的路径不对，一定要注意这里

		// 返回"图像"选项卡并显示图片 request.getContextPath()为web项目名,只适合jsp页面使用，不适用于后台Java代码
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + request.getContextPath()
				+ "/upload/img/" + fileName + "','')");
		out.println("</script>");

		return;
	}

}
