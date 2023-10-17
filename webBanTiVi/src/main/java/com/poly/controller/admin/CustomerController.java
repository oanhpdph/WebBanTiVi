package com.poly.controller.admin;

import com.poly.common.UploadFile;
import com.poly.entity.Customer;
import com.poly.entity.Staff;
import com.poly.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@RequestMapping("/admin")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/list")
    public String loadCustomer(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/customer/customer.html");
        session.setAttribute("active", "/customer/list");
        model.addAttribute("customer", new Customer());
        model.addAttribute("listCus", this.customerService.findAll());
        return "admin/layout";
    }

    @PostMapping("/customer/save")
    public String addCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result,Model model, @RequestParam("image") MultipartFile file ) {

        System.out.println(result.hasErrors());
        if (result.hasErrors()) {
            return "admin/layout";
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // xóa ký tự đặc biệt
        customer.setAvatar(fileName);
        this.customerService.save(customer);
        String uploadDir = "src/main/resources/static/image"; // đường dẫn upload
        try {
            UploadFile.saveFile(uploadDir, fileName, file);
        } catch (IOException e) {
            //
            e.printStackTrace();
        }
        model.addAttribute("listCus", customerService.findAll());
        return "redirect:/admin/customer/list";
    }

    @GetMapping("/customer/edit/{id}")
    public String showUpdateCustomer(@PathVariable("id") Integer id, Model model) {
        Customer cus = customerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("customer", cus);
        model.addAttribute("listCus", customerService.findAll());
        return "admin/layout";
    }

    @PostMapping("/customer/update/{id}")
    public String updateCustomer(@PathVariable("id") Integer id,
                             @ModelAttribute("customer") Customer customer,
                                 @RequestParam("image") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // xóa ký tự đặc biệt
        Customer findCustomer  = this.customerService.findById(customer.getId()).orElse(null);

        findCustomer.setBirthday(customer.getBirthday());
        findCustomer.setName(customer.getName());
        findCustomer.setEmail(customer.getEmail());
        findCustomer.setAddress(customer.getAddress());
        findCustomer.setPassword(customer.getPassword());
        findCustomer.setPhoneNumber(customer.getPhoneNumber());
        findCustomer.setGender(customer.isGender());
        findCustomer.setIdCard(customer.getIdCard());
        findCustomer.setStatus(customer.getStatus());
        if (!"".equals(fileName)) {
            findCustomer.setAvatar(fileName);
            String uploadDir = "src/main/resources/static/image"; // đường dẫn upload
            try {
                UploadFile.saveFile(uploadDir, fileName, file);
            } catch (IOException e) {
                //
                e.printStackTrace();
            }
        }
        customerService.save(customer);
        return "redirect:/admin/customer/list";
    }

    @GetMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, Model model) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        customerService.deleteById(id);
        return "redirect:/admin/customer/list";
    }
}
