package com.abc.model;

public class Giohang {
	Sanpham sp;
	int soluong;
	
	public Giohang(Sanpham sp, int soluong) {
		super();
		this.sp = sp;
		this.soluong = soluong;
	}
	public Sanpham getSp() {
		return sp;
	}
	public void setSp(Sanpham sp) {
		this.sp = sp;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	
}
