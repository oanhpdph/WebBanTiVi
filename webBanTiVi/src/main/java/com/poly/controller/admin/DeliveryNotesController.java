package com.poly.controller.admin;

import com.poly.entity.DeliveryNotes;
import com.poly.service.DeliveryNotesSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/admin")
public class DeliveryNotesController {

    @Autowired
    private DeliveryNotesSevice deliveryNotesSevice;

    @PostMapping("/bill/bill_detail/update_delivery_notes/{id}/{idBill}")
    public String updateDelivery(@PathVariable("id") Integer idDelivery,
                                 @PathVariable("idBill") Integer idBill,
                                 @RequestParam(name = "deliver") String deliver,
                                 @RequestParam(name = "deliveryPhone") String deliveryPhone,
                                 @RequestParam(name = "receivingAddress") String receivingAddress,
                                 @RequestParam(name = "deliveryFee") BigDecimal deliveryFee,
                                 @RequestParam(name = "note") String note) {
        DeliveryNotes deliveryNotes = new DeliveryNotes();
        deliveryNotes.setDeliver(deliver);
        deliveryNotes.setDeliveryPhone(deliveryPhone);
        deliveryNotes.setReceivingAddress(receivingAddress);
        deliveryNotes.setDeliveryFee(deliveryFee);
        deliveryNotes.setNote(note);
        deliveryNotesSevice.update(deliveryNotes, idDelivery);
        return "redirect:/admin/bill/bill_detail/" + idBill;
    }
}
