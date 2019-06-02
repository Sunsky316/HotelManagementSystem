package cn.fwh.store.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fwh.store.domain.Cart;
import cn.fwh.store.domain.CartItem;
import cn.fwh.store.domain.Product;
import cn.fwh.store.service.ProductService;
import cn.fwh.store.service.serviceImp.ProductServiceImp;
import cn.fwh.store.web.base.BaseServlet;


public class CartServlet extends BaseServlet {

	// 添加房间项到购物车
	public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, Exception {

		// 从session获取到购物车
		Cart cart = (Cart) req.getSession().getAttribute("cart");

		if (null == cart) {
			// 如果获取不到，创建购物车对象，放入session中
			cart = new Cart();
			req.getSession().setAttribute("cart", cart);
		}
		// 如果获取到，使用即可
		// 获取房间id,天数
		String pid = req.getParameter("pid");
		int num = Integer.parseInt(req.getParameter("quantity"));
		// 通过商品id查询到房间对象
		ProductService ProductService = new ProductServiceImp();
		Product product = ProductService.findProductByPid(pid);
		// 获取到待预订的房间
		CartItem cartItem = new CartItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);

		// 调用购物车上的方法
		cart.addCartItemToCar(cartItem);
		
		
		// 重新定向到/jsp/cart.jsp
		resp.sendRedirect("/store/jsp/cart.jsp");
		return null;
		//转发到/jsp/cart.jsp(不用，会出现购物车中的数量刷新后变多（数据重复提交）)
		//return "/jsp/cart.jsp";
	}
	
	public String removeCartItem(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, Exception {
		//获取待删除房间的pid
		String pid=req.getParameter("id");
		//获取到购物车
		Cart cart = (Cart)req.getSession().getAttribute("cart");
		//调用购物车删除房间项的方法
		cart.removeCartItem(pid);
		//重定向到/jsp/cart.jsp
		resp.sendRedirect("/store/jsp/cart.jsp");
		return null;
		
	}
	
	//clearCart
	public String clearCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, Exception {
		//获取购物车
		Cart cart = (Cart)req.getSession().getAttribute("cart");
		//调用购物车上的清空购物车方法
		cart.clearCart();
		//重新定向到/jsp/cart.jsp
		resp.sendRedirect("/store/jsp/cart.jsp");
		return null;
	}
}
