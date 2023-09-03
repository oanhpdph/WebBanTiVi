package com.poly.user.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {


	@GetMapping("/")
	public String index(HttpSession session){
		session.setAttribute("pageView", "/user/page/index.html");
		System.out.println("asasas");
		return "user/layout";
	}
	@RequestMapping("/product")
	public String product(HttpSession session){
		session.setAttribute("pageView", "/user/page/products.html");
		return "user/layout";
	}
	@RequestMapping("/promotion")
	public String promotion(HttpSession session){
		session.setAttribute("pageView", "/user/page/promotions.html");
		return "user/layout";
	}

}

