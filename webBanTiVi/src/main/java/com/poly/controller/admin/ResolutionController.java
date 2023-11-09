package com.poly.controller.admin;

import com.poly.entity.Resolution;
import com.poly.service.Impl.ResolutionServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class ResolutionController {
    @Autowired
    ResolutionServiceImpl resolutionService;



    @GetMapping("/technical/resolution")
    public String loadColor(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/technical/resolution.html");
        session.setAttribute("active", "/technical/resolution");
        model.addAttribute("resolution", new Resolution());
        model.addAttribute("listResolution", this.resolutionService.getAll());
        return "admin/layout";
    }

    @PostMapping("/technical/resolution/add")
    public String addColor(Model model, @Valid @ModelAttribute("resolution") Resolution resolution, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return "admin/layout";
        }
        this.resolutionService.add(resolution);
        model.addAttribute("listResolution", resolutionService.getAll());
        return "redirect:/admin/technical/resolution";
    }


    @GetMapping("/technical/resolution/delete/{id}")
    public String deleteColor(@PathVariable("id") Integer id, Model model) {
        Resolution resolution = resolutionService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        resolutionService.delete(id);
        return "redirect:/admin/technical/resolution";
    }

    @PostMapping("/technical/resolution/update/{id}")
    public String updateColor(@PathVariable("id") Integer id,  @ModelAttribute("resolution") Resolution resolution, Model model) {

        Resolution findResolution = resolutionService.findById(resolution.getId()).orElse(null);

        findResolution.setCode(resolution.getCode());
        findResolution.setNameResolution(resolution.getNameResolution());
        findResolution.setScreenLength(resolution.getScreenLength());
        findResolution.setScreenWidth(resolution.getScreenWidth());


        this.resolutionService.add(resolution);
        model.addAttribute("listResolution",resolutionService.getAll());
        return "redirect:/admin/technical/resolution";
    }

}
