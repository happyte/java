package com.zs.javaweb.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;




@WebServlet("/uploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.得到FileItem集合
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*500);
		//2.临时目录
		File tempDirectory = new File("/Users/zhangshu/Desktop/java_test/FileUpload/TempDirectory");
		factory.setRepository(tempDirectory);
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置总的文件大小
		upload.setSizeMax(1024*1024*5);
		try {
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item:items){
				//1.如果只是一般的表单域
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString();
					System.out.println(name + ":" + value);
				}
				//2.若是文件域
				else{
					String fieldName = item.getFieldName();      //input域中的name属性值
					String fileName = item.getName();            //真实上传的文件名
					String contentType = item.getContentType();
					long sizeInBytes = item.getSize();          //文件大小
					System.out.println("fieldName:"+fieldName);
					System.out.println("fileName:"+fileName);
					System.out.println("contentType:"+contentType);
					System.out.println("sizeInBytes:"+sizeInBytes);
					//输入流
					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					fileName = "/Users/zhangshu/Desktop/java_test/FileUpload/save/"+fileName;
					System.out.println("存储路径:"+fileName);
					OutputStream out = new FileOutputStream(fileName);
					while((len = in.read(buffer)) != -1){
						out.write(buffer, 0, len);
					}
					out.close();
					in.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
