package cn.fwh.store.service;

import java.util.List;

import cn.fwh.store.domain.City;

public interface CityService {
	List<City> getAllCats()throws Exception;
	void addCity(City c)throws Exception;
}
