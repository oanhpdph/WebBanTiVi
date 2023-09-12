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
	@RequestMapping("/shop")
	public String shop(HttpSession session){
		session.setAttribute("pageView", "/user/page/shop.html");
		return "user/layout";
	}
	@RequestMapping("/shopping-cart")
	public String shoppingcart(HttpSession session){
		session.setAttribute("pageView", "/user/page/shoppingcart.html");
		return "user/layout";
	}
	@RequestMapping("/lookuporder")
	public String lookuporder(HttpSession session){
		session.setAttribute("pageView", "/user/page/lookuporder.html");
		return "user/layout";
	}
	@RequestMapping("/registercard")
	public String registercard(HttpSession session){
		session.setAttribute("pageView", "/user/page/registercard.html");
		return "user/layout";
	}
	@RequestMapping("/completed")
	public String completed(HttpSession session){
		session.setAttribute("pageView", "/user/page/_order/_order_complete.html");
		return "user/layout";
	}
//	@RequestMapping("/processing")
//	public String processing(HttpSession session){
//		session.setAttribute("pageViewOrder", "/user/page/_order/_order_processing.html");
//		return "user/layout";
//	}
//	@RequestMapping("/return")
//	public String productreturn(HttpSession session){
//		session.setAttribute("pageView", "/user/page/_order/_order_return.html");
//		return "user/layout";
//	}
	@RequestMapping("/layout_order")
	public String layout(HttpSession session){
		session.setAttribute("pageView", "/user/page/layout_order.html");
//		session.setAttribute("pageViewOrder", "/user/page/_order/_order_detail.html");
		session.setAttribute("pageViewOrder", "/user/page/_order/_order_processing.html");
//		session.setAttribute("pageViewOrder", "/user/page/_order/_order_complete.html");
//			session.setAttribute("pageViewOrder", "/user/page/_order/_order_return.html");
			return "user/layout";
	}

}

