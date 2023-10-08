package com.poly.service;

import com.poly.entity.BillProduct;
import com.poly.entity.idClass.BillProductId;
import com.poly.repository.BillProductRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BillProductService {

    public List<BillProduct> getAll();

    public void save(BillProduct bp);

    public void delete(BillProductId id);

    public BillProduct edit(BillProductId id);
}
