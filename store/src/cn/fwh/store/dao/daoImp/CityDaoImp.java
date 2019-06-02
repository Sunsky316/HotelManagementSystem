package cn.fwh.store.dao.daoImp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.fwh.store.dao.CityDao;
import cn.fwh.store.domain.City;
import cn.fwh.store.utils.JDBCUtils;

public class CityDaoImp implements CityDao {

	@Override
	public List<City> getAllcats() throws Exception {
		String sql = "select * from city";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<City>(City.class));
	}
	
	
	@Override
	public void addCity(City c) throws Exception {
		String sql="insert into city values (? ,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,c.getCid(),c.getCname());
	}

}
