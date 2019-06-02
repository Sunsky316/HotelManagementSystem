package cn.fwh.store.service.serviceImp;

import java.util.List;

import cn.fwh.store.dao.ProductDao;
import cn.fwh.store.dao.daoImp.ProductDaoImp;
import cn.fwh.store.domain.PageModel;
import cn.fwh.store.domain.Product;
import cn.fwh.store.service.ProductService;

public class ProductServiceImp implements ProductService {

	ProductDao ProductDao = new ProductDaoImp();

	@Override
	public Product findProductByPid(String pid) throws Exception {
		return ProductDao.findProductByPid(pid);

	}

	@Override
	public List<Product> findNews() throws Exception {
		return ProductDao.findNewProducts();

	}

	@Override
	public List<Product> findHots() throws Exception {
		return ProductDao.findHotProducts();
	}

	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception {
		//1_创建PageModle对象 目的：计算分页参数
		
		//统计当前分类下商品个数 select count(*) from product where cid = ?
		int totalRecords = ProductDao.findTotalRecords(cid);
		PageModel pm = new PageModel(curNum,totalRecords,12);
		//2_关联集合select * from product where cid ? limit?,?
		List list = ProductDao.findProductsWithCidAndPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return pm;
	}
	
	@Override
	public void saveProduct(Product product) throws Exception {
		ProductDao.saveProduct(product);
		
	}
	
	@Override
	public PageModel findAllProductsWithPage(int curNum) throws Exception {
		//1_创建对象
		int totalRecords=ProductDao.findTotalRecords();
		PageModel pm=new PageModel(curNum,totalRecords,5);
		//2_关联集合 select * from product limit ? , ?
		List<Product> list=ProductDao.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("AdminProductServlet?method=findAllProductsWithPage");
		return pm;
	}
}
