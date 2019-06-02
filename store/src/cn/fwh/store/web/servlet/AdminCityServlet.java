package cn.fwh.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fwh.store.domain.City;
import cn.fwh.store.service.CityService;
import cn.fwh.store.service.serviceImp.CityServiceImp;
import cn.fwh.store.utils.UUIDUtils;
import cn.fwh.store.web.base.BaseServlet;

public class AdminCityServlet extends BaseServlet {
	//findAllCats
	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取全部分类信息
		CityService CategoryService=new CityServiceImp();
		List<City> list=CategoryService.getAllCats();
		//全部分类信息放入request
		req.setAttribute("allCats", list);
		//转发到/admin/category/list.jsp
		return "/admin/category/list.jsp";
	}
	
	//addCategoryUI
	public String addCityUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "/admin/category/add.jsp";
	}
	//addCategory
	public String addCity(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取分类名称
		String cname=req.getParameter("cname");
		//创建分类ID
		String id=UUIDUtils.getId();
		City c=new City();
		c.setCid(id);
		c.setCname(cname);
		//调用业务层添加分类功能
		CityService CategoryService=new CityServiceImp();
		CategoryService.addCity(c);
		//重定向到查询全部分类信息
		resp.sendRedirect("/store/AdminCityServlet?method=findAllCats");
		return null;
	}
}
