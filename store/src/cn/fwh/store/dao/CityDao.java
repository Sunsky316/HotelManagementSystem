package cn.fwh.store.dao;

import java.util.List;

import cn.fwh.store.domain.City;

public interface CityDao {
	List<City> getAllcats() throws Exception;
	void addCity(City c)throws Exception;
}
