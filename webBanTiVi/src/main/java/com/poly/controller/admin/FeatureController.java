package com.poly.controller.admin;

import com.poly.common.UploadFile;
import com.poly.entity.Coupon;
import com.poly.entity.Feature;
import com.poly.service.FeatureService;
import com.poly.service.Impl.FeatureServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.bouncycastle.math.raw.Mod;
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
public class FeatureController {
    @Autowired
    FeatureService featureService;
    @GetMapping("/feature/list")
    public String loadFeature(HttpSession session, Model model) {
        session.setAttribute("pageView","/admin/page/feature/feature.html");
        session.setAttribute("active","/feature/list");
        model.addAttribute("feature",new Feature());
        model.addAttribute("listFea",this.featureService.findAll());
        return "admin/layout";
    }
    @PostMapping("/feature/save")
    public String addFeature(@Valid @ModelAttribute("feature") Feature feature, BindingResult result, Model model) {
        System.out.println(result.hasErrors());
        if (result.hasErrors()) {
            return "admin/layout";
        }
        this.featureService.save(feature);
        model.addAttribute("listFea", featureService.findAll());
        System.out.println(featureService.findAll());
        return "redirect:/admin/feature/list";
    }

    @GetMapping("/feature/edit/{id}")
    public String showUpdateFeature(Model model, @PathVariable("id")Integer id) {
        Feature fea = featureService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("feature",fea);
        model.addAttribute("listFea",featureService.findAll());
        return "admin/layout";
    }
    @PostMapping("/feature/update/{id}")
    public String updateFeature(@PathVariable("id") Integer id,
                                @ModelAttribute("feature") Feature feature)
    {

        Feature findFeature = this.featureService.findById(feature.getId()).orElse(null);

        findFeature.setCode(feature.getCode());
        findFeature.setNamefeature(feature.getNamefeature());
        featureService.save(findFeature);
        return "redirect:/admin/feature/list";
    }
    @RequestMapping("/feature/delete/{id}")
    public String deleteFeature(Model model, @PathVariable("id")Integer id) {
        Feature feature = featureService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        featureService.deleteById(id);
        return "redirect:/admin/feature/list";
    }
}