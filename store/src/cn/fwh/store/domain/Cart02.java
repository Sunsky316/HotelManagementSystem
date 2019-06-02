package cn.fwh.store.domain;

import java.util.ArrayList;
import java.util.List;

//2个属性3个方法
public class Cart02 {
	// 购物车上的个数不确定的购物项
	private List<CartItem> list = new ArrayList<CartItem>();
	
	// 总计
	private double total;

	// 1_向购物车中添加购物项
	// 当用户点击加入购物车，可以将当前要预定的房间id，预定天数发送到服务端,服务端根据id查询到房间信息
	// 有了房间信息Product对象，有了预定天数，当前购物车的房间项也就找到了
	public void addCartItemToCart(CartItem cartItem) {
		// 将当前的预订项加入购物车前，判断是否加购过该房间
		// 如果没有加购过	list.add(CartItem);
		// 如果买过：获取原先的数量，获取现在的数量，相加后设置到原先加购项

		

		// 设置变量,默认为false，没有加购过
		boolean flag = false;

		// 存储原先的购物项
		CartItem old = null;
		for (CartItem cartItem2 : list) {
			if (cartItem2.getProduct().getPid().equals(cartItem.getProduct().getPid())) {
				flag = true;
				old = cartItem2;
			}
		}
		
		if(flag == false) {
			list.add(cartItem);
		}else {
			// 获取到原先的数量，获取到本次的数量，相加后设置到原先的房间项上
			old.setNum(old.getNum()+cartItem.getNum());
			list.add(cartItem);
		}
	}

	// 2_从购物车中移除单个购物项
	// 用户点击移除连接，可以将当前房型id发送到服务端，服务端根据房型id查询到房型信息
	// 有了房型信息Product对象，有了要预定的天数，当前房型就可以获取到了
	public void removeCartItem(String pid) {
		if (list.size() > 0) {
			// 遍历list看每个CartItem上的product对象id是否和服务端获取到的pid相等，若相等，删除当前房型
			for (CartItem item : list) {
				if (item.getProduct().getPid().equals(pid)) {
					list.remove(item);
				}
			}
		}
	}

	// 3_清空购物车
	public void clearCart() {
		list.clear();
		total = 0;
	}

	// 总计是经过计算获取
	public double getTotal() {
		total = 0;
		if (list.size() > 0) {
			for (CartItem item : list) {
				total = total + item.getSubTotal();
			}
		}
		return total;
	}

	public List<CartItem> getList() {
		return list;
	}

	public void setList(List<CartItem> list) {
		this.list = list;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
