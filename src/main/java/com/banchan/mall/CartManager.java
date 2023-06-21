package com.banchan.mall;

import java.util.HashMap;
import java.util.Map;

public class CartManager {
	private Map<Integer, Integer> carts = null;
	
	public CartManager() {
		this.carts = new HashMap<>();
	}
	
	// 장바구니에 상품 추가
	public void AddCart(int prNum, int prQty) {
		if (this.carts.containsKey(prNum)) { // 품목 존재
			int newQty = this.carts.get(prNum) + prQty;
			this.carts.put(prNum, newQty);
		} else {
			this.carts.put(prNum, prQty);
		}
	}

	// 장바구니에 있는 상품 수정
	public void EditCart(int prNum, int prQty) {
		this.AddCart(prNum, prQty);
	}

	// 장바구니 상품 삭제
	public void DeleteCart(int prNum) {
		this.carts.remove(prNum);
	}

	// 결제 시 장바구니 내역 비우기
	public void RemoveAllCart() {
		this.carts.clear();
	}

	// 장바구니 내역 정보 반환
	public Map<Integer, Integer> GetAllCartList() {
		return this.carts;
	}

	// 장바구니 품목 개수 반환
	public int GetCartSize() {
		return this.carts.size();
	}
}
