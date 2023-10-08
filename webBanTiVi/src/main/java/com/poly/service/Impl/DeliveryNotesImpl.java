package com.poly.service.Impl;

import com.poly.entity.DeliveryNotes;
import com.poly.repository.DeliveryNotesRepos;
import com.poly.service.DeliveryNotesSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryNotesImpl implements DeliveryNotesSevice {
    @Autowired
    private DeliveryNotesRepos deliveryNotesRepos;

    @Override
    public DeliveryNotes add(DeliveryNotes deliveryNotes) {
        return deliveryNotesRepos.save(deliveryNotes);
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<DeliveryNotes> optional = deliveryNotesRepos.findById(id);
        if (optional.isPresent()) {
            deliveryNotesRepos.delete(optional.get());
            return true;
        }
        return false;
    }

    @Override
    public DeliveryNotes update(DeliveryNotes deliveryNotes, Integer id) {
        Optional<DeliveryNotes> optional = deliveryNotesRepos.findById(id);
        if (optional.isPresent()) {
            DeliveryNotes deliveryNotesUpdate = optional.get();
            deliveryNotesUpdate.setNote(deliveryNotes.getNote());
            deliveryNotesUpdate.setDeliveryPhone(deliveryNotes.getDeliveryPhone());
            deliveryNotesUpdate.setReceived(deliveryNotesUpdate.getReceived());
            deliveryNotesUpdate.setReceiverPhone(deliveryNotes.getReceiverPhone());
            deliveryNotesUpdate.setDeliver(deliveryNotes.getDeliver());
            deliveryNotesUpdate.setStatus(deliveryNotesUpdate.getStatus());
            deliveryNotesUpdate.setDeliveryFee(deliveryNotes.getDeliveryFee());
            return deliveryNotesRepos.save(deliveryNotesUpdate);
        }
        return null;
    }

    @Override
    public DeliveryNotes getByIdBill(Integer idBill) {
        Optional<DeliveryNotes> optional = deliveryNotesRepos.getDeliveryNotesByIdBill(idBill);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
