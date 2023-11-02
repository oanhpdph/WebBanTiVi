package com.poly.controller.admin;

import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.poly.entity.Type;
import com.poly.repository.TypeRepository;
import com.poly.service.Impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class TypeController {
    @Autowired
    TypeServiceImpl typeService;
    @RequestMapping("/type")
    public String Type(Model model) {
        typeService.findAll();
        return "admin/type";
    }
    @RequestMapping("/type/save")
    public String saveType(Model model, @ModelAttribute("item")Type type) {
        typeService.save(type);
        return "admin/type";
    }
    @RequestMapping("/type/edit/{id}")
    public String editType(Model model, @PathVariable("id")Integer id) {
        typeService.findById(id);
        return "admin/type";
    }
    @RequestMapping("/type/delete/{id}")
    public String deleteType(Model model, @PathVariable("id")Integer id) {
        typeService.delete(id);
        return "admin/type";
    }
}
