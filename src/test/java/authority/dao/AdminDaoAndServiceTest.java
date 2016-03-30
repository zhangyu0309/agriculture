package authority.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agri.core.util.JsonUtils;
import com.agri.platform.authority.dao.mysql.AdminDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class AdminDaoAndServiceTest {
	@Resource
	private AdminDao adminDao;
	@Test
	public void getAllAdminInfo(){
		System.out.println(JsonUtils.getJAVABeanJSON(adminDao.getAdminById("535e1191-3a85-4ad7-b9a0-7f61d3910663")));
	}

}
