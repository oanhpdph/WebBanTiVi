package com.poly.controller.login;


import com.poly.common.RandomNumber;
import com.poly.common.SendEmail;
import com.poly.config.CustomerUserDetail;
import com.poly.dto.ChangeInforDto;
import com.poly.dto.LoginDto;
import com.poly.entity.Customer;
import com.poly.repository.CustomerRepository;
import com.poly.service.CustomerService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    final AuthenticationManager authenticationManager;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

//    @Autowired
//    StaffService staffService;

//    @Autowired
//    StaffRepository staffRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("")
    public String login(Model model) {
        model.addAttribute("login", new LoginDto());
        return "login/login";
    }
//
//    @PostMapping("/")
//    public String LoginAdmin(@Valid @ModelAttribute("login") LoginDto login, BindingResult result) {
//        if(result.hasErrors()){
//            return "login/login";
//        }
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return "user/index";
//    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registration", new Customer());
        return "login/register";
    }

    @PostMapping("/register/add")
    public String AddCustomer(Model model,
                              @Valid @ModelAttribute("registration") Customer customer,
                              BindingResult bindingResult, HttpSession httpSession)
            throws MessagingException {
        // validate
        if (bindingResult.hasErrors()) {
            return "login/register";
        }
        // suscess
        for (Customer cus : this.customerService.findAll()) {
            if (cus.getUsername().equals(customer.getUsername())) {
                return "redirect:login/register";
            }
        }
        RandomNumber rand = new RandomNumber();
        int value = rand.randomNumber();
        httpSession.setAttribute("accountRegis", customer);
        httpSession.setAttribute("randomNumber", value);
        sendEmail.sendSimpleMessage(customer.getEmail(), "Tạo tài khoản thành viên mới trên Big6Store.vn",
                "Xin chào Bạn!,\n" +
                        "Bạn đã đăng ký tài khoản trên trang Big6Store.vn của chúng tôi.\n" +
                        "Tên đăng nhập của bạn là " + customer.getUsername() + "\n" +
                        "Hãy nhập mã dưới đây để thực hiện hoàn tất viêc đăng ký tài khoản của bạn.Lưu ý không \n" +
                        "chia sẻ mã xác thực cho bên thứ ba .Điều đó có thể dẫn tới thông tin tài khoản của bạn bị lộ.Xin cảm ơn!" + "\n" +
                        "Mã xác thực : " + value
        );
        return "redirect:/login/confirm-register";
    }

    @GetMapping("/confirm-register")
    public String comfirmRegister(HttpSession httpSession) {
        if (httpSession.getAttribute("randomNumber") == null) {
            return "redirect:/login/register";
        }
        return "login/confirm-register";
    }

    @PostMapping("/confirm-register")
    public String accuracy(HttpSession httpSession, @RequestParam("verification-code") String code
    ) {
        Customer account = (Customer) httpSession.getAttribute("accountRegis");
        account.setRoles("USER");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setAvatar("default.jpg");
        int value = (int) httpSession.getAttribute("randomNumber");
        if (code.equals(String.valueOf(value))) {
            customerRepository.save(account);
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
        return "redirect:/";
    }

    @PostMapping("/changeInfo")
    public String changeInfor(@ModelAttribute("changeInfo") ChangeInforDto changeInforDto,
                              @RequestParam("image") MultipartFile file
                              ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();;

        // Lấy UserDetails từ principal
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
         String role=roles.get(0).toString();
//        if (role.equals("USER")) {
            CustomerUserDetail  customerUserDetail = (CustomerUserDetail) userDetails;
            Customer customer  =this.customerService.findById(customerUserDetail.getId()).get();
            customer.setUsername(changeInforDto.getName());
            customer.setPhoneNumber(changeInforDto.getPhone());
            customer.setBirthday(changeInforDto.getBirthday());
            customer.setGender(changeInforDto.isGender());
            if (!changeInforDto.getPassword().equals("")){
                customer.setPassword(passwordEncoder.encode(changeInforDto.getPassword()));
            }else{
                customer.setPassword(customer.getPassword());
            }
            customer.setRoles(customerUserDetail.getRoles());
            customer.setEmail(changeInforDto.getEmail());
            customer.setAddress(changeInforDto.getAddress());
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if(!"".equals(fileName)){
                customer.setAvatar(fileName);
            }
            this.customerService.save(customer);

//        } else {
//            StaffUserDetail  staffUserDetail = (StaffUserDetail) userDetails;
//            Staff staff  =this.staffService.findById(staffUserDetail.getId()).get();
//            staff.setUsername(changeInforDto.getName());
//            staff.setPhone(changeInforDto.getPhone());
//            staff.setBirthday(changeInforDto.getBirthday());
//            staff.setGender(changeInforDto.isGender());
//            if (!staff.getPassword().equals("")){
//                staff.setPassword(passwordEncoder.encode(changeInforDto.getPassword()));
//            }else{
//                staff.setPassword(staff.getPassword());
//            }
//            staff.setRoles(staffUserDetail.getRoles());
//            staff.setEmail(changeInforDto.getEmail());
//            staff.setAddress(changeInforDto.getAddress());
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            if(!"".equals(fileName)){
//                staff.setAvatar(fileName);
//            }
//            this.staffService.save(staff);
//        }
        return "redirect:/login/logout";
    }
}




