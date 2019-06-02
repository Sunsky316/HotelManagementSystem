package cn.fwh.store.service;

import java.util.List;

import cn.fwh.store.domain.PageModel;
import cn.fwh.store.domain.Product;

public interface ProductService {
	List<Product> findHots()throws Exception;
	List<Product> findNews()throws Exception;
	Product findProductByPid(String pid)throws Exception;
	PageModel findProductsByCidWithPage(String cid, int curNum)throws Exception;
	PageModel findAllProductsWithPage(int curNum)throws Exception;
	void saveProduct(Product product)throws Exception;
}
