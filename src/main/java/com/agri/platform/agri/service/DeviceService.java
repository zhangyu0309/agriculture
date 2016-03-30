package com.agri.platform.agri.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.agri.platform.agri.bean.Device;
import com.agri.platform.agri.dao.DeviceDao;
import com.agri.platform.agri.dao.DeviceJDBCDao;

/**
 * 设备管理业务逻辑层的处理
 * @author zy
 *
 */
@Service
public class DeviceService {

	@Resource
	private DeviceDao dao;
	@Resource
	private DeviceJDBCDao jdbcDao;
	/**
	 * 查询设备信息
	 * @return
	 */
	public List<Device> getDevice(Device device) {
		return dao.getDevice(device);
	}
	/**
	 * 获取所有父节点(wifi board)
	 * @return
	 */
	public List<Device> getAllParentDevice(String user_id) {
		if (user_id == null || user_id.equals("")){
			return null;
		}
		return dao.getAllParentDevice(user_id);
	}
	/**
	 * 根据ID查询设备
	 * @param device_id
	 * @return
	 */
	public Device getDeviceById(String device_id) {
		return dao.getDeviceById(device_id);
	}
	/**
	 * 添加
	 * @param device
	 * @return
	 */
	public Map<String, Object> addDevice(Device device) {
		Map<String,Object> map = new HashMap<String,Object>();
		if (jdbcDao.getCountByDeviceId(device.getDevice_id()) > 0){
			map.put("flag", false);
			map.put("msg", "设备ID已存在，不允许重复！");
			return map;
		}
		if (dao.addDevice(device) > 0){
			map.put("flag", true);
			map.put("msg", "添加设备成功！");
		}else{
			map.put("flag", false);
			map.put("msg", "添加设备失败，数据库连接异常");
		}
		return map;
	}
	/**
	 * 编辑
	 * @param device
	 * @return
	 */
	public Map<String, Object> updateDevice(Device device) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(dao.updateDevice(device) > 0){
			map.put("flag", true);
			map.put("msg", "更新设备成功！");
		}else{
			map.put("flag", false);
			map.put("msg", "更新设备失败，数据库连接异常");
		}
		return map;
	}
	/**
	 * 删除
	 * @param device
	 * @return
	 */
	public Map<String, Object> delDeviceById(Device device) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(dao.delDeviceById(device) > 0){
			map.put("flag", true);
			map.put("msg", "删除设备成功！");
		}else{
			map.put("flag", false);
			map.put("msg", "删除设备失败，数据库连接异常！");
		}
		return map;
	}
	
}
