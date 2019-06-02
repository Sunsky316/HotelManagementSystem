package cn.fwh.store.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//2个属性3个方法
public class Cart {

	// 总计/积分
	private double total = 0;

	// 购物车上的个数不确定的房间项 商品的pid<=====>CartItem
	Map<String, CartItem> map = new HashMap<String, CartItem>();

	
	
	// 1_向购物车中添加房间项
	// 当用户点击加入购物车，可以将当前要预订的房间id，预订天数发送到服务端,服务端根据id查询到房间信息
	// 有了房间信息Product对象，有了预订天数，当前购物车的房间项也就找到了
	public void addCartItemToCar(CartItem cartItem) {
		// 获取到正在添加的房间的pid
		String pid = cartItem.getProduct().getPid();

		// 将当前的预订项加入购物车前，判断是否加购过该房间  
		// 如果没有加购过 list.add(CartItem);
		// 如果买过：获取原先的数量，获取现在的数量，相加后设置到原先加购项
		if (map.containsKey(pid)) {
			// 获取到原先的房间项
			CartItem oldItem = map.get(pid);
			oldItem.setNum(oldItem.getNum() + cartItem.getNum());

		} else {
			map.put(pid, cartItem);
		}

	}
	
	//返回map中所有的值
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	// 2_从购物车中移除单个房间项
	public void removeCartItem(String pid) {
		map.remove(pid); 
	}

	// 3_清空购物车
	public void clearCart() {
		map.clear();
	}

	// 总计可以经过计算获取到
	public double getTotal() {
		//先让总计清零
		total = 0;
		Collection<CartItem> values = map.values();

		// 遍历所有房间项，将房间项上的小计相加
		for (CartItem cartItem : values) {
			total = cartItem.getSubTotal() + total;
		}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
}
