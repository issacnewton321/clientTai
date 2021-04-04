package com.abc.controller;

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
import org.springframework.web.client.RestTemplate;

import com.abc.model.Khachhang;
import com.abc.model.Nhanvien;

@Controller
public class KhachhangController {
	String url = "http://localhost:8080/";
	@GetMapping("khachhang/login")
	public String login(Model model) {
		model.addAttribute("khachhang",new Khachhang());
		return "user/dangnhap.html";
	}
	@PostMapping("khachhang/login")
	public String submitLogin(@ModelAttribute("khachhang") Khachhang kh,Model model,HttpSession session) {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate rt = new RestTemplate();
		// Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<Khachhang> requestBody = new HttpEntity<>(kh, headers);
 
        try {
        	ResponseEntity<Khachhang> result  = rt.postForEntity(url+"khachhang/login", requestBody, Khachhang.class);
        	if(result.getStatusCode() == HttpStatus.OK) {
    			Khachhang khachhang = result.getBody();
    			session.setAttribute("khachhang", khachhang);
    			return "redirect:/";
    		}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", "login failed !!!");
			
		}
        return "user/dangnhap.html";
		
		
	}
}
