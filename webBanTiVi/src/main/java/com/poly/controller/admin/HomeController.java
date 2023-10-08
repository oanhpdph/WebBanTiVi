package com.poly.controller.admin;

import com.poly.entity.Staff;
import com.poly.service.Impl.BillImpl;
import com.poly.service.Impl.StaffServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    StaffServiceImpl staffService;

    @Autowired
    private BillImpl billImpl;

    @GetMapping("")
    public String loadHome(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/statistic.html");
        session.setAttribute("active", "/dashboard");
        return "admin/layout";
    }

    @GetMapping("/dashboard")
    public String loadDashboard(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/dashboard/dashboard.html");
        session.setAttribute("active", "/dashboard");
        return "admin/layout";
    }

    @GetMapping("/bill/list_bill")
    public String loadBill(HttpSession session) {
        session.setAttribute("listBill", billImpl.getALl());
        session.setAttribute("pageView", "/admin/page/bill/bill.html");
        session.setAttribute("active", "/bill/list_bill");
        return "admin/layout";
    }
    @GetMapping("/bill/payment_method")
    public String loadPaymentMethod(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/bill/payment_method.html");
        session.setAttribute("active", "/bill/payment_method");
        return "admin/layout";
    }
    @GetMapping("/customer/list")
    public String loadCustomer(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/customer/customer.html");
        session.setAttribute("active", "/customer/list");
        return "admin/layout";
    }

    // staff
    @GetMapping("/staff")
    public String loadStaff(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/staff.html");
        session.setAttribute("active", "/staff");
        model.addAttribute("listStaff", this.staffService.findAll());
        model.addAttribute("staff", new Staff());
        return "admin/layout";
    }

    @PostMapping("/staff/add")
    public String addStaff(@Valid Staff staff, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Vui lòng điền đầy đủ thông tin!");
            return "redirect:/admin/staff";
        }
        this.staffService.save(staff);
        return "redirect:/admin/staff";

    }

    @GetMapping("/staff/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        this.staffService.delete(id);
        return "redirect:/admin/staff";
    }

    @GetMapping("/staff/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Staff staff = staffService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("listStaff", this.staffService.findAll());
        model.addAttribute("staff", staff);
        return "admin/layout";
    }

    @PostMapping("/staff/update/{id}")
    public String update(@PathVariable("id") Integer id, @Valid Staff staff, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("message", "Vui lòng điền đầy đủ thông tin!");
            model.addAttribute("listStaff", this.staffService.findAll());
            return "admin/layout";
        }
        if (staff.getId().equals(id)) {
            this.staffService.save(staff);
            model.addAttribute("listStaff", this.staffService.findAll());
        }
        return "admin/layout";
    }

    @GetMapping("/position")
    public String loadPosition(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/position.html");
        session.setAttribute("active", "/position");

        return "admin/layout";
    }

    @GetMapping("/product")
    public String loadProduct(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/product/product.html");
        session.setAttribute("active", "/product");
        return "admin/layout";
    }

    @GetMapping("/statistic")
    public String loadStatistic(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/statistic.html");
        session.setAttribute("active", "/statistic");
        return "admin/layout";
    }

    @GetMapping("/blog")
    public String loadBlog(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/blog/blog.html");
        session.setAttribute("active", "/blog");
        return "admin/layout";
    }

}
