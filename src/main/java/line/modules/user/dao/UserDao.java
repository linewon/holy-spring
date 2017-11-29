package line.modules.user.dao;

import line.modules.user.entity.User;

public interface UserDao {
	User findByName(String userName);
}