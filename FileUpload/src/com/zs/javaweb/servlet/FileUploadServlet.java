package com.zs.javaweb.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zs.javaweb.beans.FileUploadBean;
import com.zs.javaweb.properities.FileUploadProperities;


@WebServlet("/fileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String TEMP_DIR = "/Users/zhangshu/Desktop/java_test/FileUpload/TempDirectory";
	
	private static final String FILE_PATH = "/WEB-INF/files/";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取ServletFileUpload对象
		ServletFileUpload servletFileUpload = getServletFileUpload();
		try {
			//把需要上传的FileItem对象都放到这个Map中
			//键:文件待存放的路径 值:对应的FileItem对象
			Map<String, FileItem> uploadFiles = new HashMap<>();
			//解析请求，得到FileItem集合
			List<FileItem> items = servletFileUpload.parseRequest(request);
			//1.构建FileUploadBean集合，同时填充uploadFiles
			List<FileUploadBean> beans = buildFileUploadBeans(items, uploadFiles);
			//2.校验文件的扩展名
			validateExtNames(beans);
			//3.校验文件的大小，在解析时，已经校验了
			//4.进行文件的上传操作,只是本地操作，数据库的信息还没有更新
			upload(uploadFiles);
			//5.把信息保存到数据库中
			saveBeans(beans);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveBeans(List<FileUploadBean> beans) {
		
		
	}
	
	/**
	 * 文件上传前的准备操作，得到filePath和InputStream
	 * @param uploadFiles
	 * @throws IOException 
	 */
	private void upload(Map<String, FileItem> uploadFiles) throws IOException {
		for(Map.Entry<String, FileItem> uploadFile: uploadFiles.entrySet()){
			String filePath = uploadFile.getKey();
			FileItem fileItem = uploadFile.getValue();
			upload(filePath, fileItem);
		}
	}

	/**
	 * 真实的上传操作，根据文件路径构建outputstream对象，获得fileItem的inputstream
	 * @param filePath
	 * @param fileItem
	 * @throws IOException 
	 */
	private void upload(String filePath, FileItem fileItem) throws IOException {
		System.out.println("文件路径:"+filePath);
		InputStream in = fileItem.getInputStream();
		OutputStream out = new FileOutputStream(filePath);
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = in.read(buffer)) != -1){
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

	private void validateExtNames(List<FileUploadBean> beans) {
		
		
	}
	
	/**
	 * 利用传入的FileItem集合，构建FileUploadBean的集合，同时填充uploadFiles map集合
	 * @param items
	 * @param uploadFiles
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private List<FileUploadBean> buildFileUploadBeans(List<FileItem> items, Map<String, FileItem> uploadFiles) 
			throws UnsupportedEncodingException {
		Map<String, String> descs = new HashMap<>();
		List<FileUploadBean> beans = new ArrayList<>();
		for(FileItem item:items){
			//如果是普通的表单域
			if(item.isFormField()){
				String fieldName = item.getFieldName();//desc1,desc2
				String description = item.getString("UTF-8");
				//利用desc1,desc2 的1、2
				descs.put(fieldName, description);
			}
		}
		for(FileItem item:items){
			//如果是文件域
			if(!item.isFormField()){
				String fieldName = item.getFieldName(); //file1,file2,文件域name的属性值
				String descName = "desc"+fieldName.substring(fieldName.length()-1);
				String description = descs.get(descName); //利用file1中的数字对应到desc1中的数字，从而查找上面存储的map中对应键的值
				String fileName = item.getName(); //文件名
				String filePath = getPath(fileName);
				FileUploadBean bean = new FileUploadBean(fileName, filePath, description);
				uploadFiles.put(bean.getFilePath(), item);
				beans.add(bean);
			}
		}
		return beans;
	}

	private String getPath(String fileName) {
		//根据.获取扩展名
		String extName = fileName.substring(fileName.indexOf("."));
		Random random = new Random();
		String filePath = getServletContext().getRealPath(FILE_PATH) + "/" + System.currentTimeMillis() + random.nextInt(10000) +extName;
		return filePath;
	}

	private ServletFileUpload getServletFileUpload() {
		//获取properties中的读取参数
		String fileSize = FileUploadProperities.getInstance().getProperty("file.max.size");
		String totalSize = FileUploadProperities.getInstance().getProperty("total.file.max.size");
		System.out.println("fileSize:"+fileSize);
		System.out.println("totalSize:"+totalSize);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*500);
		//2.临时目录
		File tempDirectory = new File(TEMP_DIR);
		factory.setRepository(tempDirectory);
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置总的文件大小
		upload.setSizeMax(Integer.parseInt(totalSize));
		//设置单个文件大小
		upload.setFileSizeMax(Integer.parseInt(fileSize));
		return upload;
	}

}
