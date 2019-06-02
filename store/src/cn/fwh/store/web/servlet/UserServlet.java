package cn.fwh.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fwh.store.domain.City;
import cn.fwh.store.domain.User;
import cn.fwh.store.service.CityService;
import cn.fwh.store.service.UserService;
import cn.fwh.store.service.serviceImp.CityServiceImp;
import cn.fwh.store.service.serviceImp.UserServiceImp;
import cn.fwh.store.utils.MailUtils;
import cn.fwh.store.utils.MyBeanUtils;
import cn.fwh.store.utils.UUIDUtils;
import cn.fwh.store.web.base.BaseServlet;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {

	public String registUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return "/jsp/register.jsp";// 直接跳转用户注册页面
	}

	public String LoginUI(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "/jsp/login.jsp";// 直接跳转用户登陆页面
	}

	public String userRegist(HttpServletRequest request, HttpServletResponse response)
			throws Exception  {
		// 接受表单参数
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		MyBeanUtils.populate(user, map);
		// 为用户的其他属性赋值
		user.setUid(UUIDUtils.getId());// 生成随机Id
		user.setState(0);// 初始状态置为“0”，激活后置为“1”
		user.setCode(UUIDUtils.getCode());// 生成随机激活码
		// System.out.println(user);
		/*
		 * //遍历map中的数据 Set<String> keySet = map.keySet(); Iterator<String> iterator =
		 * keySet.iterator(); while(iterator.hasNext()){ String str = iterator.next();
		 * String[] strs = map.get(str); for (String string : strs) {
		 * System.out.println(string); } System.out.println(); }
		 */
		// 调用业务注册功能
		UserService UserService = new UserServiceImp();
		try {
			UserService.userRegist(user);
			// 注册成功，向用户邮箱发送信息，跳转到提示页面
			// 发送邮件
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "用户注册成功，请激活！");

		} catch (Exception e) {
			// 注册失败，跳转到提示页面
			request.setAttribute("msg", "用户注册失败，请重新注册！");

		}

		return "/jsp/info.jsp";
	}

	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// 获取激活码
		String code = request.getParameter("code");
		// 调用业务层的激活功能
		UserService UserService = new UserServiceImp();
		boolean flag = UserService.userActive(code); // 是否查询到了用户

		// 激活的信息提示
		if (flag == true) {
			// 用户激活成功，向request放入提示信息，转发到登陆页面
			request.setAttribute("msg", "用户激活成功，请登录！");
			return "/jsp/login.jsp";
		} else {
			// 用户激活失败，向request放入提示信息，转发到提示页面
			request.setAttribute("msg", "用户激活失败，请重新激活！");
			return "/jsp/info.jsp";
		}

	}

	// userLogin
	public String userLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// 获取用户数据（账户/密码）
		User user = new User();
		MyBeanUtils.populate(user, request.getParameterMap()); // user只携带了账户和密码
		// 调用业务层的登陆功能
		UserService UserService = new UserServiceImp();
		User user02 = null;// user02可以携带所有信息
		try {
			// select * from user where username=? and password=?
			user02 = UserService.userLogin(user);
			// 用户登陆成功,将用户信息放入session中
			request.getSession().setAttribute("loginUser", user02);
			response.sendRedirect("/store/index.jsp");
			return null;
		} catch (Exception e) {
			// 用户登陆失败
			String msg = e.getMessage();
			System.out.println(msg);
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}

	}

}
