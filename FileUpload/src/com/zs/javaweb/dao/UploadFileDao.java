package com.zs.javaweb.dao;

import java.sql.Connection;
import java.util.List;

import com.zs.javaweb.beans.FileUploadBean;


public class UploadFileDao extends Dao<FileUploadBean> {
	public List<FileUploadBean> getFiles(){
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			String sql = "SELECT id, file_name, file_path, file_desc FROM upload_files";
			return getForList(connection, sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.releaseConnection(connection);
		}
		return null;
	}
	
	public void save(List<FileUploadBean> beans){
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			// 插入新数据
			String sql = "INSERT INTO upload_files(file_name, file_path, file_desc) VALUES (?,?,?)";
			for(FileUploadBean bean:beans){
				update(connection, sql, bean.getFileName(), bean.getFilePath(), bean.getDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.releaseConnection(connection);
		}
	}
}
