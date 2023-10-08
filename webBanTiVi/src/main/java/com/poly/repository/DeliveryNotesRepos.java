package com.poly.repository;


import com.poly.entity.DeliveryNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeliveryNotesRepos extends JpaRepository<DeliveryNotes,Integer> {
    @Query(value = "select * from DeliveryNotes dn where dn.id_bill=?1",nativeQuery = true)
    Optional<DeliveryNotes> getDeliveryNotesByIdBill(Integer idBill);
}
