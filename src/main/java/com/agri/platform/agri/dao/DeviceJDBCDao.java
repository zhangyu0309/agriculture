package com.agri.platform.agri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agri.core.base.dao.jdbc.BaseJDBCDao;
import com.agri.platform.authority.bean.Authority;
@Repository
public class DeviceJDBCDao extends BaseJDBCDao {
	
	private static Logger log = Logger.getLogger(DeviceJDBCDao.class);
	@Autowired
	private DataSource dataSource;
	/**
	 * 查询devcice_id是否已存在
	 * @param device_id
	 * @return
	 */
	public int getCountByDeviceId(String device_id){
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			
			connection = dataSource.getConnection();
			StringBuilder tempSql = new StringBuilder("select count(device_id) as count from device_info where device_id=?");
			pstmt = connection.prepareStatement(tempSql.toString());
			pstmt.setString(1, device_id);
			rs = pstmt.executeQuery();
			int count = 0;
			if (rs.next()){
				count = rs.getInt(1);
			}
			return count;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}finally {
			closeAllConnection(connection, pstmt, rs);
		}
		return 0;
	}
}
