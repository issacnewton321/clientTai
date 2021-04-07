package com.abc.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.abc.model.Nhanvien;
import com.abc.model.Sanpham;

@Controller
public class SanphamController {
	String url = "http://localhost:8080/";
	@GetMapping("/nhanvien/sanpham")
	public String getListNV(HttpSession session,Model model) {
		if(session.getAttribute("nhanvien") == null){
			return "redirect:/nhanvien/login";
		}
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Sanpham[]> list = rt.getForEntity(url + "sanpham", Sanpham[].class);
		Sanpham[] listSP = list.getBody();
		model.addAttribute("listSP", listSP);
		return "admin/sanpham.html";
	}
	
	@GetMapping("/nhanvien/insertSanpham")
	public String insertSanpham1(Model model) {
		model.addAttribute("sanpham",new Sanpham());
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Sanpham[]> list = rt.getForEntity(url + "sanpham", Sanpham[].class);
		Sanpham[] listSP = list.getBody();
		model.addAttribute("listSP", listSP);
		return "admin/sanpham.html";
	}
	@PostMapping("/nhanvien/sanpham")
	public String insertSanpham(@ModelAttribute("sanpham")Sanpham sp,Model model,@RequestParam("hinhanh") MultipartFile hinhanh) {
		
		Path path = Paths.get("src/main/resources/static/img");
		try {
			InputStream inputStream = hinhanh.getInputStream();
			Files.copy(inputStream, path.resolve(hinhanh.getOriginalFilename().replace(" ", "")),StandardCopyOption.REPLACE_EXISTING);
			sp.setPhoto(hinhanh.getOriginalFilename().replace(" ", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate rt = new RestTemplate();
		// Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<Sanpham> requestBody = new HttpEntity<>(sp, headers);
 
        try {
        	ResponseEntity<Sanpham> result  = rt.postForEntity(url+"sanpham", requestBody, Sanpham.class);
        	if(result.getStatusCode() == HttpStatus.OK) {
        		return "redirect:/nhanvien/sanpham";
    			
    		}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			model.addAttribute("message", "insert failed !!!");
			rt = new RestTemplate();
			ResponseEntity<Sanpham[]> list = rt.getForEntity(url + "sanpham", Sanpham[].class);
			Sanpham[] listSP = list.getBody();
			model.addAttribute("listSP", listSP);
			return "admin/sanpham.html";
		}
        return "redirect:/nhanvien/sanpham";
	}
	@GetMapping("/nhanvien/deleteSP/{masp}")
	public String deleteSP(Model model,@PathVariable("masp")String masp) {
		
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("masp", masp);
		rt.delete(url + "sanpham/{masp}", params);
		rt = new RestTemplate();
		ResponseEntity<Sanpham[]> list = rt.getForEntity(url + "sanpham", Sanpham[].class);
		Sanpham[] listSP = list.getBody();
		model.addAttribute("listSP", listSP);
		return "admin/sanpham.html";
	}
	@GetMapping("/nhanvien/updateSP/{masp}")
	public String updateSP(Model model,@PathVariable("masp")String masp) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("masp", masp);
		Sanpham sp= rt.getForObject(url + "sanpham/{masp}", Sanpham.class, params);
		model.addAttribute("spUpdate",sp);
		rt = new RestTemplate();
		ResponseEntity<Sanpham[]> list = rt.getForEntity(url + "sanpham", Sanpham[].class);
		Sanpham[] listSP = list.getBody();
		model.addAttribute("listSP", listSP);
		return "admin/sanpham.html";
	}
	@PostMapping("/nhanvien/updateSP/{masp}")
	public String updateSP(@ModelAttribute("spUpdate")Sanpham sp,Model model,@RequestParam("hinhanh") MultipartFile hinhanh,@PathVariable("masp")String masp) {
		
		Path path = Paths.get("src/main/resources/static/img");
		try {
			InputStream inputStream = hinhanh.getInputStream();
			Files.copy(inputStream, path.resolve(hinhanh.getOriginalFilename().replace(" ", "")),StandardCopyOption.REPLACE_EXISTING);
			sp.setPhoto(hinhanh.getOriginalFilename().replace(" ", ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate rt = new RestTemplate();
		// Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<Sanpham> requestBody = new HttpEntity<>(sp, headers);
        
 		Map<String, String> params = new HashMap<>();
		params.put("masp", masp);
        try {
        	rt.put(url + "sanpham/{masp}",requestBody,params);
        	return "redirect:/nhanvien/sanpham";
        	//return "admin/sanpham.html";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			model.addAttribute("message", "update failed !!!");
			rt = new RestTemplate();
			ResponseEntity<Sanpham[]> list = rt.getForEntity(url + "sanpham", Sanpham[].class);
			Sanpham[] listSP = list.getBody();
			model.addAttribute("listSP", listSP);
			return "admin/sanpham.html";
		}
	}
}
