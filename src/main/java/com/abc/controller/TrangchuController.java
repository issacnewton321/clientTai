package com.abc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.abc.model.Sanpham;

@Controller
public class TrangchuController {
	String url = "http://localhost:8080/";
	@RequestMapping("/")
	public String home(ModelMap model) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Sanpham[]> list = rt.getForEntity(url + "sanpham", Sanpham[].class);
		Sanpham[] listSP = list.getBody();
		model.addAttribute("listSP", listSP);
		return "user/trangchu.html";
	}
	@RequestMapping("/{masp}")
	public String getSanphamDetail(ModelMap model,@PathVariable("masp")String masp) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Sanpham> list = rt.getForEntity(url + "sanpham/"+masp, Sanpham.class);
		Sanpham sanpham = list.getBody();
		model.addAttribute("sanpham",sanpham);
		return "user/chitietsp.html";
	}
	@RequestMapping("/admin")
	public String admin() {
		return "admin/sanpham.html";
	}
}
