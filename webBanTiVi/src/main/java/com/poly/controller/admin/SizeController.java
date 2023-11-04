package com.poly.controller.admin;


import com.poly.entity.Size;
import com.poly.service.Impl.SizeServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/size")
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class SizeController {
    @Autowired
    SizeServiceImpl sizeService;

    @GetMapping("/technical/size")
    public String loadSize(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/technical/size.html");
        session.setAttribute("active", "/technical/size");
        model.addAttribute("size", new Size());
        model.addAttribute("listSize", this.sizeService.getAll());
        return "admin/layout";
    }

    @PostMapping("/technical/size/add")
    public String addColor(Model model, @Valid @ModelAttribute("size") Size size, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return "admin/layout";
        }
        this.sizeService.add(size);
        model.addAttribute("listSize", sizeService.getAll());
        return "redirect:/admin/technical/size";
    }


    @GetMapping("/technical/size/delete/{id}")
    public String deleteColor(@PathVariable("id") Integer id, Model model) {
        Size size = sizeService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        sizeService.delete(id);
        return "redirect:/admin/technical/size";
    }

    @PostMapping("/technical/size/update/{id}")
    public String updateColor(@PathVariable("id") Integer id,  @ModelAttribute("Size") Size size, Model model) {

        Size findSize = sizeService.findById(size.getId()).orElse(null);

        findSize.setCode(size.getCode());
        findSize.setNameSizeInch(size.getNameSizeInch());
        findSize.setLength(size.getLength());
        findSize.setWidth(size.getWidth());
        findSize.setThickness(size.getThickness());


        this.sizeService.add(size);
        model.addAttribute("listColor", sizeService.getAll());
        return "redirect:/admin/technical/size";
    }

}
