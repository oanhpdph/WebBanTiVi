package com.poly.controller.admin;

import com.poly.entity.Feature;
import com.poly.service.Impl.FeatureServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class FeatureController {
    @Autowired
    FeatureServiceImpl featureService;
    @RequestMapping("/feature/save")
    public String saveFeature(Model model, @ModelAttribute("item") Feature feature) {
        featureService.save(feature);
        return "admin/feature";
    }

    @RequestMapping("/feature/edit/{id}")
    public String editFeature(Model model, @PathVariable("id")Integer id) {
        featureService.findById(id);
        return "admin/feature";
    }
    @RequestMapping("/feature/delete/{id}")
    public String deleteFeature(Model model, @PathVariable("id")Integer id) {
        featureService.delete(id);
        return "admin/feature";
    }
    @GetMapping("/feature")
    public String Feature(Model model, HttpSession httpSession) {
        httpSession.setAttribute("pageView", "/admin/page/feature/feature.html");
        httpSession.setAttribute("active","/feature");
        model.addAttribute("listfe",featureService.findAll());
        return "admin/layout";
    }
}
