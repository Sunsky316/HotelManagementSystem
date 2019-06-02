package cn.fwh.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fwh.store.domain.City;
import cn.fwh.store.domain.Product;
import cn.fwh.store.service.CityService;
import cn.fwh.store.service.ProductService;
import cn.fwh.store.service.serviceImp.CityServiceImp;
import cn.fwh.store.service.serviceImp.ProductServiceImp;
import cn.fwh.store.web.base.BaseServlet;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends BaseServlet {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 调用业务层功能：获取全部信息，返回集合
		CityService CityService = new CityServiceImp();
		List<City> list = CityService.getAllCats();
		// 将返回的集合放入request
		request.setAttribute("allCats",list);
		
		ProductService ProductService=new ProductServiceImp();
		//获取最新9件商品
		List<Product> list01=ProductService.findNews();
		//获取最热9件商品
		List<Product> list02=ProductService.findHots();
		//将最新商品放入request
		request.setAttribute("news", list01);
		//将最热商品放入request
		request.setAttribute("hots", list02);
		
		
		
		
		
		
		
		// 转发到真实的首页
		return "/jsp/index.jsp";
	}
}
