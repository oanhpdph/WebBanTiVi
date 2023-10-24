package com.poly.service;

import com.poly.entity.BillProduct;
import com.poly.entity.idClass.BillProductId;
import com.poly.repository.BillProductRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BillProductService {

    List<BillProduct> getAll();

    void save(BillProduct bp);

    void delete(Integer id);

    BillProduct edit(Integer id);
}
