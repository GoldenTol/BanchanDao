package com.banchan.mall;

import java.util.HashMap;
import java.util.Map;

import com.banchan.model.CartItem;

public class CartMap {
	private Map<Integer, CartItem> itemList = null;
	
	public CartMap() {
		this.itemList = new HashMap<>();
	}
	
	public Map<Integer, CartItem> getItemList() {
		return itemList;
	}
}
