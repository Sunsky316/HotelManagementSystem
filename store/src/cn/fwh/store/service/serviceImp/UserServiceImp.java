package cn.fwh.store.service.serviceImp;

import java.sql.SQLException;

import javax.management.RuntimeErrorException;


import cn.fwh.store.dao.UserDao;
import cn.fwh.store.dao.daoImp.UserDaoImp;
import cn.fwh.store.domain.User;
import cn.fwh.store.service.UserService;

public class UserServiceImp implements UserService {

	@Override
	public void userRegist(User user) throws SQLException {
		// 实现注册功能
		UserDao UserDao = new UserDaoImp();
		UserDao.userRegist(user);
	}

	@Override
	public boolean userActive(String code) throws SQLException {

		UserDao UserDao = new UserDaoImp();
		// 对DB发送selecet * from user where code=?
		User user = UserDao.userActive(code);
		// 可以根据激活码查询到一个用户
		if (null != user) {
			// 可以根据激活码查询到一个用户
			// 修改用户的状态。清除激活码
			user.setState(1);
			user.setCode(null);
			// 对数据库执行一次真实的更新操作
			// update user set username=? ,password=? ,name=? ,email=? ,telephone=? ,birthday=? ,sex=? ,state=? ,code=? where uid=?;

			UserDao.updateUser(user);
			return true;

		} else {
			// 不可以根据激活码查询到用户
			return false;

		}
	}

	@Override
	public User userLogin(User user) throws SQLException {
		//此处：可以利用异常再模块之间传递
		
		UserDao UserDao = new UserDaoImp();
		//select * forn user where username=?and password=?
		User uu = UserDao.userLogin(user);
		if(null==uu) {
			throw new RuntimeException("密码有误！");
		}else if(uu.getState()==0) {
			throw new RuntimeException("账户未激活！");
		}else {
			return uu;
		}
	}
	

}
