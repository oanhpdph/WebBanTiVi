package com.poly.controller.user;


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
	@RequestMapping("/about")
	public String about(HttpSession session){
		session.setAttribute("pageView", "/user/page/about.html");
		return "user/layout";
	}
	@RequestMapping("/logincard")
	public String logincard(HttpSession session){
		session.setAttribute("pageView", "/user/page/logincard.html");
		return "user/layout";
	}
	@RequestMapping("/products")
	public String products(HttpSession session){
		session.setAttribute("pageView", "/user/page/products.html");
		return "user/layout";
	}
	@RequestMapping("/shopping-detail")
	public String shoppingdetail(HttpSession session){
		session.setAttribute("pageView", "/user/page/shopdetail.html");
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

	@RequestMapping("/layout_order")
	public String layout(HttpSession session){
		session.setAttribute("pageView", "/user/page/layout_order.html");
		session.setAttribute("pageViewOrder", "/user/page/_order/_order_complete.html");
			return "user/layout";
	}
	@RequestMapping("/membership-card")
	public String membershipcard(HttpSession session){
		session.setAttribute("pageView", "/user/page/membershipcard.html");
		return "user/layout";
	}
}

