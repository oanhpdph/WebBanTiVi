package com.poly.controller.user;

import com.poly.dto.ChangeInforDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String loadHome(HttpSession session){
        session.setAttribute("pageView","/user/page/home/home.html");
        return "/user/index";
    }
    @GetMapping("/tivi")
    public String loadProduct(HttpSession session){
        session.setAttribute("pageView","/user/page/product/tivi.html");
        return "/user/index";
    }
    @GetMapping("/accessory")
    public String loadAccessory(HttpSession session){
        session.setAttribute("pageView","/user/page/product/accessory.html");
        return "/user/index";
    }
    @GetMapping("/invoice")
    public String loadInvoice(HttpSession session){
        session.setAttribute("pageView","/user/page/invoice/search_invoice.html");
        return "/user/index";
    }
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String profile(HttpSession session, Model model){
        session.setAttribute("pageView","/user/page/profile/profile.html");
        model.addAttribute("changeInfo", new ChangeInforDto());
        return "/user/index";
    }
    @GetMapping("/invoice/invoice_detail")
    public String loadInvoiceDetail(HttpSession session){
        session.setAttribute("pageView","/user1/page/invoice/detail_invoice.html");
		return "user/layout";
	}




	@RequestMapping("/product")
	public String product(HttpSession session){
		session.setAttribute("pageView", "/user/page/products.html");
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