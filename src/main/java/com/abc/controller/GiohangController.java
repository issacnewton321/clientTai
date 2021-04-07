package com.abc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.abc.model.Giohang;
import com.abc.model.Sanpham;

@Controller
public class GiohangController {
	String url = "http://localhost:8080/";
	@RequestMapping("/giohang")
	public String getGiohang() {
		return "giohang.html";
	}
	@RequestMapping("/addCart/{masp}")
	
	public String addGiohang(@PathVariable("masp") int masp,@RequestParam("soluong") int soluong,HttpSession session) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Sanpham> sp = rt.getForEntity(url + "sanpham/"+masp, Sanpham.class);
		Sanpham sanpham = sp.getBody();
		if(session.getAttribute("giohang") == null) {
			List<Giohang> list = new ArrayList<Giohang>();
			list.add(new Giohang(sanpham,soluong));
			session.setAttribute("giohang", list);
		}
		else {
			List<Giohang> list = (List<Giohang>) session.getAttribute("giohang");
			for(Giohang gh :list) {
				if(gh.getSp().getMasp() == masp) {
					System.out.println(masp);
					gh.setSoluong(soluong +gh.getSoluong());
					session.setAttribute("giohang", list);
					return "user/giohang.html";
				}
			}
			list.add(new Giohang(sanpham, soluong));
			session.setAttribute("giohang", list);
			return "user/giohang.html";
			
		}
		
		return "user/giohang.html";
	}
}
