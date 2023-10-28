package com.poly.controller.admin;

import com.poly.entity.Feature;
import com.poly.service.Impl.FeatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FeatureController {
    @Autowired
    FeatureServiceImpl featureService;
    @RequestMapping("/admin/feature")
    public String Feature(Model model) {
        featureService.findAll();
        return "admin/feature";
    }
    @RequestMapping("/admin/feature/save")
    public String saveFeature(Model model, @ModelAttribute("item") Feature feature) {
        featureService.save(feature);
        return "admin/feature";
    }

    @RequestMapping("/admin/feature/edit/{id}")
    public String editFeature(Model model, @PathVariable("id")Integer id) {
        featureService.findById(id);
        return "admin/feature";
    }
    @RequestMapping("/admin/feature/delete/{id}")
    public String deleteFeature(Model model, @PathVariable("id")Integer id) {
        featureService.delete(id);
        return "admin/feature";
    }
}
