package user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import line.modules.user.entity.User;
import line.modules.user.service.UserService;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})  
public class UserDaoTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void userDaoTest() {
		User user = userService.getByUserName("line");
		System.out.println(user.toString());
	}
}
