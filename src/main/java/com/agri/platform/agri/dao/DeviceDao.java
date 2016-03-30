package com.agri.platform.agri.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.agri.platform.agri.bean.Device;

/**
 * 
 * @author zy
 * 
 */
public interface DeviceDao {
	
	public static Logger logger = Logger.getLogger(DeviceDao.class);

	public List<Device> getDevice(Device device);

	public List<Device> getAllParentDevice(String user_id);

	public Device getDeviceById(String device_id);

	public int addDevice(Device device);

	public int updateDevice(Device device);

	public int delDeviceById(Device device);

}
