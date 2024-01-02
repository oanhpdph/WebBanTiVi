package com.poly.repository;

import com.poly.entity.HistoryBillProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryBillProductRepository extends JpaRepository<HistoryBillProduct, Integer> {

    @Query(value="Select Max(b.returnTimes) from HistoryBillProduct b where b.bill.id=?1")
    Optional<Integer> findReturnCountBillById(Integer id);

    @Query(value = "select b from HistoryBillProduct b inner join Bill  a on b.bill.id=a.id where  b.status=?1 and a.id=?2")
    List<HistoryBillProduct> findHistoryBillProductReturn(Integer status, Integer id);


    @Query(value="select b.billProduct.id as idBillProduct, sum(b.quantityRequestReturn) as totalReturned  from HistoryBillProduct b where b.bill.id=?1 group by b.billProduct.id")
    List<Object[]> getReturnedDataForBill(Long idBill);

    @Query(value="Select b from HistoryBillProduct b where b.bill.id=?1 and b.returnTimes=?2")
    List<HistoryBillProduct> findAllHistoryBillReturnByIdBill(Integer id, Integer returnTimes);

    @Query(value="select b from HistoryBillProduct b where b.status=?1")
    List<HistoryBillProduct> findHistoryBillProductByStatus(Integer status);

}
