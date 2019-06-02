package cn.fwh.store.dao;

import java.sql.SQLException;
import java.util.List;

import cn.fwh.store.domain.Product;

public interface ProductDao {
	List<Product> findNewProducts()throws Exception ;
	List<Product> findHotProducts()throws Exception ;
	Product findProductByPid(String pid)throws Exception;
	int findTotalNum(String cid)throws Exception ;
	List<Product> findProductsWithCidAndPage(String cid, int startIndex, int pageSize)throws Exception ;
	void saveProduct(Product product)throws Exception;
	int findTotalRecords(String cid)throws Exception;
	List<Product> findAllProductsWithPage(int startIndex, int pageSize)throws Exception;
	int findTotalRecords()throws Exception;
}
