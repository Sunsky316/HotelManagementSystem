package cn.fwh.store.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.fwh.store.dao.UserDao;
import cn.fwh.store.domain.User;
import cn.fwh.store.utils.JDBCUtils;

public class UserDaoImp implements UserDao {

	@Override
	public void userRegist(User user) throws SQLException {
		String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={
				user.getUid(),
				user.getUsername(),
				user.getPassword(),
				user.getName(),
				user.getEmail(),
				user.getTelephone(),
				user.getBirthday(),
				user.getSex(),
				user.getState(),
				user.getCode()
				};
		qr.update(sql, params);
	}

	@Override
	public User userActive(String code) throws SQLException {
		
		String sql = "select * from user where code=?";
				QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
				User user = qr.query(sql, new BeanHandler<User>(User.class),code);
		return user;
	}

	@Override
	public void updateUser(User user) throws SQLException {
		String sql = "update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={
				user.getUsername(),
				user.getPassword(),
				user.getName(),
				user.getEmail(),
				user.getTelephone(),
				user.getBirthday(),
				user.getSex(),
				user.getState(),
				user.getCode(),
				user.getUid()
				};
		qr.update(sql,params);
	}

	@Override
	public User userLogin(User user) throws SQLException {
		String sql = "select * from user where username=? and password=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}
	
	
	
}
