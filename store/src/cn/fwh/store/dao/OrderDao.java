package cn.fwh.store.dao;

import java.sql.Connection;
import java.util.List;

import cn.fwh.store.domain.Order;
import cn.fwh.store.domain.OrderItem;
import cn.fwh.store.domain.User;

public interface OrderDao {
	void saveOrderItem(Connection conn, OrderItem item)throws Exception;
	void saveOrder(Connection conn, Order order)throws Exception;
	int getTotalRecords(User user)throws Exception;
	List findMyOrdersWithPage(User user, int startIndex, int pageSize)throws Exception;
	Order findOrderByOid(String oid)throws Exception;
	void updateOrder(Order order)throws Exception;
	List<Order> findAllOrders()throws Exception;
	List<Order> findAllOrders(String st)throws Exception;
}
