//package com.abc.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.client.RestTemplate;
//
//import com.abc.model.Login;
//import com.abc.model.Vattu;
//
//@Controller
//public class VattuController {
//	@RequestMapping("/")
//	public String index(HttpSession session) {
//		return "index";
//	}
//	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}
//	@PostMapping("/login")
//	public String login(String username, String password,HttpServletRequest request) {
//		RestTemplate rt = new RestTemplate();
//		ResponseEntity<Login[]> list = rt.getForEntity("http://localhost:8080/login", Login[].class);
//		Login users[] = list.getBody();
//		for (Login u : users) {
//			if(u.getUsername().equalsIgnoreCase(username)&&u.getPassword().equalsIgnoreCase(password)) {
//				request.getSession().setAttribute("user", u);
//			}
//		}
//		return "redirect:/";
//	}
//	@RequestMapping("/vattu")
//	public String getVattu(Model model) {
//		RestTemplate rt = new RestTemplate();
//		ResponseEntity<Vattu[]> list = rt.getForEntity("http://localhost:8080/vattu", Vattu[].class);
//		Vattu[] listVT = list.getBody();
//		model.addAttribute("listVT",listVT);
//		return "Vattu";
//	}
//	@RequestMapping("/addVattu")
//	public String addVattu(Vattu vt) {
//		RestTemplate rt = new RestTemplate();
//		boolean kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/vattu", vt, String.class));
//		return "redirect:vattu";
//	}
//	@RequestMapping("/deleteVattu/{mavt}")
//	public String deleteVattu(@PathVariable("mavt") String mavt) {
//		RestTemplate rt = new RestTemplate();
//		Map<String, String> params = new HashMap<>();
//		params.put("mavt", mavt);
//		rt.delete("http://localhost:8080/vattu/{mavt}", params);
//		return "redirect:../vattu";
//	}
//	@RequestMapping("/editVT/{mavt}")
//	public String edit(@PathVariable("mavt") String mavt,Model model) {
//		RestTemplate rt = new RestTemplate();
//		Map<String, String> params = new HashMap<>();
//		params.put("mavt", mavt);
//		Vattu vt = rt.getForObject("http://localhost:8080/vattu/{mavt}", Vattu.class, params);
//		model.addAttribute("vt",vt);
//		model.addAttribute("mavt",vt.getMavt());
//		ResponseEntity<Vattu[]> list = rt.getForEntity("http://localhost:8080/vattu", Vattu[].class);
//		Vattu[] listVT = list.getBody();
//		model.addAttribute("listVT",listVT);
//		return "Vattu";
//	}
//	@RequestMapping("/editVattu/{mavt}")
//	public String editVattu(@PathVariable("mavt") String mavt,Vattu vt) {
//		RestTemplate rt = new RestTemplate();
//		Map<String, String> params = new HashMap<>();
//		params.put("mavt", mavt);
//		rt.put("http://localhost:8080/vattu/{mavt}",vt,params);
//		return "redirect:../vattu"; 
//	}
//}
