package com.agri.platform.authority.dao.mysql;

import com.agri.platform.authority.bean.Authority;

public interface AuthorityDao {
	/**
	 * 
	 * @param record
	 * @return
	 */
    int insert(Authority record);
    /**
     * 添加
     * @param record
     * @return
     */
    int addAuthority(Authority record);
    /**
     * 更新
     * @param record
     * @return
     */
    int updateAuthorityById(Authority record);
    
    
}