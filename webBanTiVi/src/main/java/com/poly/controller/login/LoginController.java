package com.poly.controller.login;


import com.poly.common.RandomNumber;
import com.poly.common.SendEmail;
import com.poly.dto.CustomerDto;
import com.poly.dto.StaffDto;
import com.poly.entity.Customer;
import com.poly.entity.Staff;
import com.poly.repository.CustomerRepository;
import com.poly.repository.StaffRepository;
import com.poly.service.CustomerService;
import com.poly.service.StaffService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    StaffService staffService;

    @Autowired
    StaffRepository staffRepository;


    @GetMapping("/user")
    public String login(Model model) {
        model.addAttribute("customer", new Customer());
        return "login/login";
    }

    @PostMapping("/user")
    private String submitLogin(Model model,
                               @Valid @ModelAttribute("customer") CustomerDto customer, BindingResult result,
                               HttpSession httpSession, RedirectAttributes redirectAttributes) {
        Customer cus = this.customerService.findByEmailAndPass(customer);
        if (result.hasErrors()) {
            return "login/login";
        }
        if (cus == null) {
            model.addAttribute("message", "Nhập sai thông tin");
            return "login/login";
        }
        httpSession.setAttribute("accountLogged", cus);
        httpSession.setAttribute("username", cus.getName());
        httpSession.setAttribute("image", cus.getAvatar());
        redirectAttributes.addFlashAttribute("message", "Đăng nhập thành công!");

        return "redirect:/client";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registration", new Customer());
        return "login/register";
    }

    @PostMapping("/register/add")
    public String AddCustomer(Model model,
                              @ModelAttribute("registration") Customer customer,
                              BindingResult bindingResult, RedirectAttributes attributes, HttpSession httpSession)
            throws MessagingException {
        // validate
        if (bindingResult.hasErrors()) {
            return "login/register";
        }
        // suscess
        if (customer != null) {
            Customer find = this.customerRepository.findByEmail(customer.getEmail());
            if (find != null) {
                attributes.addFlashAttribute("message", "Email đã được đăng ký");
                return "redirect:/register";
            }
            RandomNumber rand = new RandomNumber();
            int value = rand.randomNumber();
            httpSession.setAttribute("accountRegis", customer);
            httpSession.setAttribute("randomNumber", value);
            sendEmail.sendSimpleMessage(customer.getEmail(), "Tạo tài khoản thành viên mới trên Big6Store.vn",
                    "Xin chào Bạn!,\n" +
                            "Bạn đã đăng ký tài khoản trên trang Big6Store.vn của chúng tôi.\n" +
                            "Email đăng nhập của bạn là " + customer.getEmail() + "\n" +
                            "Mật khẩu : " + customer.getPassword() + "\n" +
                            "Hãy nhập mã dưới đây để thực hiện hoàn tất viêc đăng ký tài khoản của bạn.Lưu ý không \n" +
                            "chia sẻ mã xác thực cho bên thứ ba .Điều đó có thể dẫn tới thông tin tài khoản của bạn bị lộ.Xin cảm ơn!" + "\n" +
                            "Mã xác thực : " + value
            );
            return "redirect:/confirm-register";
        }
        return "login/register";
    }

    @GetMapping("/confirm-register")
    public String comfirmRegister(HttpSession httpSession) {
        if (httpSession.getAttribute("randomNumber") == null) {
            return "redirect:/login/register";
        }
        return "login/confirm-register";
    }

    @PostMapping("/confirm-register")
    public String accuracy(HttpSession httpSession, @RequestParam("verification-code") String code,
                           RedirectAttributes attributes) {
        Customer account = (Customer) httpSession.getAttribute("accountRegis");
        int value = (int) httpSession.getAttribute("randomNumber");
        if (code.equals(String.valueOf(value))) {
            customerRepository.save(account);
            attributes.addFlashAttribute("message", "Đăng ký thành công");
            httpSession.removeAttribute("accountRegis");
            httpSession.removeAttribute("randomNumber");
            return "redirect:/login";
        }
        return "login/login";
    }

    @GetMapping("/forgot-password")
    public String forgot() {
        return "login/forgot-password";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/client";
    }

    @PostMapping("/staff")
    public String LoginAdmin(Model model,
                             @Valid @ModelAttribute("staff") StaffDto staff, BindingResult result,
                             HttpSession httpSession, RedirectAttributes redirectAttributes) {


        Staff findStaff = this.staffService.findByUsernameAndPassword(staff);
        if (result.hasErrors()) {
            return "login/login-system";
        }

        httpSession.setAttribute("staff", findStaff);
        redirectAttributes.addFlashAttribute("message", "Đăng nhập thành công!");


        return "redirect:/client";
    }

    @GetMapping("/staff")
    public String viewLoginAdmin(Model model) {
        model.addAttribute("staff", new Staff());
        return "login/login-system";
    }

}




