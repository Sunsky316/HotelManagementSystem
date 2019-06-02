package cn.fwh.store.service.serviceImp;

import java.util.List;

import cn.fwh.store.dao.CityDao;
import cn.fwh.store.dao.daoImp.CityDaoImp;
import cn.fwh.store.domain.City;
import cn.fwh.store.service.CityService;
import cn.fwh.store.utils.JedisUtils;
import redis.clients.jedis.Jedis;

public class CityServiceImp implements CityService {
	CityDao CityDao = new CityDaoImp();
	@Override
	public List<City> getAllCats() throws Exception {
		return CityDao.getAllcats();
	}
	
	@Override
	public void addCity(City c) throws Exception {
		//本质是向MYSQL插入一条数据
		CityDao.addCity(c);
		//更新redis缓存
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
	}
}
