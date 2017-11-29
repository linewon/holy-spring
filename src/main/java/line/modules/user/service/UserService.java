package line.modules.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import line.modules.user.dao.UserDao;
import line.modules.user.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User getByUserName(String userName) {
		return userDao.findByName(userName);
	}
}
