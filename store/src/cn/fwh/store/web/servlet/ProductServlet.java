package cn.fwh.store.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fwh.store.domain.PageModel;
import cn.fwh.store.domain.Product;
import cn.fwh.store.service.ProductService;
import cn.fwh.store.service.serviceImp.ProductServiceImp;
import cn.fwh.store.web.base.BaseServlet;
 

public class ProductServlet extends BaseServlet {
	
	
	
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取房间pid
		String pid = request.getParameter("pid");
		//根据房间pid查询房间信息
		ProductService ProductService = new ProductServiceImp();
		Product product = ProductService.findProductByPid(pid);
		//将获取到的房间放入request
		request.setAttribute("product", product);
		
		//转到到/jsp/product_info.jsp
		return "/jsp/product_info.jsp";
	}
	
	
	//findProductsByCidWithPage
	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//获取cid，num
		String cid = request.getParameter("cid");
		int curNum = Integer.parseInt(request.getParameter("num"));
		//调用业务层功能：以分页形式查询当前类别下的房间信息
		//返回PageModle对象（1-当前商品信息2-分页3-url）
		ProductService Productservice = new ProductServiceImp();
		PageModel pm = Productservice.findProductsByCidWithPage(cid,curNum);
		
		//将PageModle对象放入request
		request.setAttribute("page",pm);
		//转发到/jsp/product_list.jsp
		
	return "/jsp/product_list.jsp";
	}
	
}
