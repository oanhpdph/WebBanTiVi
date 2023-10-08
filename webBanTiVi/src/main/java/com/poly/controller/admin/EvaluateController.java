package com.poly.controller.admin;

import com.poly.entity.Evaluate;
import com.poly.repository.EvaluateRepos;
import com.poly.service.EvaluateService;
import com.poly.service.Impl.EvaluateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EvaluateController {
    @Autowired
    EvaluateServiceImpl evaluateService;
    @Autowired
    EvaluateRepos evaluateRepos;

    @GetMapping("/evaluate/index")
    public String index(Model model) {
        model.addAttribute("list", evaluateService.getAll());
        return "admin/page/product/product";
    }

    @PostMapping("/evaluate/add")
    public String add(@Validated Evaluate evaluate, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/page/product/product";
        }
        evaluateService.add(evaluate);
        return "redirect:/evaluate/index";
    }

    @GetMapping("/evaluate/delete/{id}")
    public String delete(@PathVariable Integer id) {
        evaluateService.delete(id);
        return "redirect:/evaluate/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Evaluate evaluate = evaluateRepos.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("evaluate", evaluate);
        return "redirect:/evaluate/index";
    }

    @GetMapping("/evaluate/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Evaluate evaluate = evaluateService.edit(id);
        model.addAttribute("evaluate", evaluate);
        return "redirect:/evaluate/index";
    }

    @PostMapping("/evaluate/update/{id}")
    public String updateAccount(@PathVariable Integer id, @ModelAttribute("evaluate") Evaluate evaluate, Model model) {
        evaluateService.add(evaluate);
        System.out.println("sua thanh cong");
        return "redirect:/evaluate/index";
    }
}
