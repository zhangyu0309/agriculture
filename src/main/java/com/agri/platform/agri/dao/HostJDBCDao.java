package com.agri.platform.agri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agri.core.base.dao.jdbc.BaseJDBCDao;
import com.agri.core.util.JsonUtils;

@Repository
public class HostJDBCDao extends BaseJDBCDao{

	private static Logger log = Logger.getLogger(HostJDBCDao.class);
	
	private static DataSource dataSource;

	public static void setDataSource(DataSource dataSource) {
		HostJDBCDao.dataSource = dataSource;
	}

	/**
	 * 保存位置信息
	 * @param gi
	 */
	public static void savePosition(){
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			connection = dataSource.getConnection();
			StringBuilder tempSql = new StringBuilder("call Prc_SavePosition(?,?,?,?,?,?,?,?,?,?,?,?)");
			//call Prc_SavePosition(#{deviceid},#{time},#{latitude},#{ns},#{longitude},#{ew},#{speed},#{direction},#{angle},#{angledirection},#{satellites},#{elevation}
			pstmt = connection.prepareStatement(tempSql.toString());
			rs = pstmt.executeQuery();
//			while(rs.next()){
//				resultRoleStringList.add(rs.getString("role_id"));
//			}
		}catch(Exception e){
			log.error(e.getMessage() , e);
		}finally {
			closeAllConnection(connection, pstmt, rs);
		}
	}
	
	/**
	 * 设备离线
	 * @param id
	 */
	public static void offline (String id){
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			connection = dataSource.getConnection();
			StringBuilder tempSql = new StringBuilder("call Prc_Offline(?)");
			pstmt = connection.prepareStatement(tempSql.toString());
			pstmt.setString(1 , id);
			rs = pstmt.executeQuery();
		}catch(Exception e){
			log.error(e.getMessage() , e);
			log.error("离线设备失败:" + id);
		}finally {
			closeAllConnection(connection, pstmt, rs);
		}
	}
	
	/**
	 * 设备心跳
	 * @param id
	 */
	public static void heartbeat (String id){
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			connection = dataSource.getConnection();
			StringBuilder tempSql = new StringBuilder("call Prc_Heartbead(?)");
			pstmt = connection.prepareStatement(tempSql.toString());
			pstmt.setString(1 , id);
			rs = pstmt.executeQuery();
		}catch(Exception e){
			log.error(e.getMessage() , e);
			log.error("保存设备心跳失败:" + id);
		}finally {
			closeAllConnection(connection, pstmt, rs);
		}
	}
}
