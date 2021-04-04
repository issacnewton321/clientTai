package com.abc.controller;

import java.awt.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.abc.model.Nhanvien;
import com.abc.model.Sanpham;

@Controller
public class NhanvienController {
	String url = "http://localhost:8080/";
	@GetMapping("nhanvien/login")
	public String login(Model model) {
		model.addAttribute("nhanvien",new Nhanvien());
		return "admin/dangnhap.html";
	}
	@PostMapping("nhanvien/login")
	public String submitLogin(@ModelAttribute("nhanvien") Nhanvien nv,Model model,HttpSession session) {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate rt = new RestTemplate();
		// Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<Nhanvien> requestBody = new HttpEntity<>(nv, headers);
 
        try {
        	ResponseEntity<Nhanvien> result  = rt.postForEntity(url+"nhanvien/login", requestBody, Nhanvien.class);
        	if(result.getStatusCode() == HttpStatus.OK) {
    			Nhanvien nhanvien = result.getBody();
    			session.setAttribute("nhanvien", nhanvien);
    			return "redirect:/nhanvien/sanpham";
    		}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", "login failed !!!");
			
		}
        return "admin/dangnhap.html";
		
		
	}
}
