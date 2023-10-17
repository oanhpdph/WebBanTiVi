package com.poly.controller.admin;

import com.poly.common.ExportExcel;
import com.poly.common.SavePdf;
import com.poly.dto.SearchBillDto;
import com.poly.entity.Bill;
import com.poly.entity.BillProduct;
import com.poly.entity.idClass.BillProductId;
import com.poly.service.BillService;
import com.poly.service.Impl.BPServiceImpl;
import com.poly.service.Impl.BillStatusImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BillController {

    @Autowired
    BPServiceImpl billProductService;

    @Autowired
    private BillService billService;

    @Autowired
    private BillStatusImpl billStatus;

    @Autowired
    private ExportExcel exportExcel;

    @Getter
    @Setter
    public class DataExportExcel {
        private int stt;
        private String code;
        private String name;
        private String dateCreate;
        private Long totalPrice;
        private String billStatus;
        private String paymentStatus;
        private String note;
    }

    @GetMapping("/bill_product")
    public String index(Model model) {
        model.addAttribute("bill_product", billProductService.getAll());
        return "";
    }

    @PostMapping("/bil_product/add")
    public String add(BillProduct product) {
        billProductService.save(product);
        return "redirect:/admin/bill_product";
    }

    @GetMapping("/bil_product/delete/{id}")
    public String delete(BillProductId id) {
        billProductService.delete(id);
        return "redirect:/admin/bill_product";
    }

    @GetMapping("/bil_product/edit/{id}")
    public String edit(@PathVariable BillProductId id, Model model) {
        BillProduct product = billProductService.edit(id);
        model.addAttribute("bill_product", product);
        return "redirect:/admin/bill_product";
    }

    @PostMapping("/bil_product/update/{id}")
    public String updateUser(@PathVariable("id") BillProductId id, @ModelAttribute("billproduct") BillProduct billproduct) {
        billProductService.save(billproduct);
        return "redirect:/admin/bill_product";
    }

    @DeleteMapping("/bill/delete/{billCode}")
    public String deleteBill(HttpSession session, @PathVariable(name = "billCode") Integer idBill, Model model) {
        Boolean check = billService.delete(idBill);
        session.setAttribute("pageView", "/admin/page/bill/bill.html");
        session.setAttribute("active", "/bill/list_bill");
        return "/admin/layout";
    }


    @GetMapping(value = {"/bill/list_bill"})
    public String loadBill(HttpSession session, Model model,
                           @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageRequest,
                           @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizeRequest,
                           @ModelAttribute(name = "search") SearchBillDto search) {

        //set trang
        session.setAttribute("pageView", "/admin/page/bill/bill.html");
        session.setAttribute("active", "/bill/list_bill");
        // tìm kiếm
        session.setAttribute("size", sizeRequest);
        session.setAttribute("page", pageRequest);

        session.setAttribute("defaultBillStatus", search.getBillStatus());
        Pageable pageable = PageRequest.of(pageRequest - 1, sizeRequest);
        Page<Bill> bills = billService.loadData(search, pageable);
        session.setAttribute("list", bills);
        model.addAttribute("totalElements", bills.getTotalElements());
        return "/admin/layout";
    }

    @GetMapping("/bill/list_bill/download_excel")
    public void exportIntoExcelFile(HttpServletResponse response, HttpSession session) {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        Page<Bill> bills = (Page<Bill>) session.getAttribute("list");

        List<String> header = listHeader();

        List<DataExportExcel> dataExport = new ArrayList<>();
        listData(bills, (List<DataExportExcel>) dataExport);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Danh_sach_hoa_don_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        exportExcel.exportExel(response, dataExport, header);
    }

    @GetMapping("/bill/list_bill/downloadpdf")
    public void exportPdf(HttpServletResponse response, HttpSession session) throws IOException {
        Page<Bill> bills = (Page<Bill>) session.getAttribute("list");
        List<String> header = listHeader();

        List<DataExportExcel> dataExport = new ArrayList<>();
        listData(bills, (List<DataExportExcel>) dataExport);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Danh_sach_hoa_don_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);
        SavePdf savePdf = new SavePdf();
        savePdf.savePdf(response, dataExport, header);
    }

    private void listData(Page<Bill> bills, List<DataExportExcel> dataExport) {
        int index = 1;
        for (Bill b : bills.getContent()) {
            DataExportExcel dataExportExcel = new DataExportExcel();
            dataExportExcel.setStt(index);
            dataExportExcel.setCode(b.getCode());
            dataExportExcel.setName(b.getCustomer().getName());
            dataExportExcel.setDateCreate(String.valueOf(b.getCreateDate()));
            dataExportExcel.setTotalPrice(b.getTotalPrice().longValue());
            dataExportExcel.setBillStatus(b.getBillStatus().getStatus());
            dataExportExcel.setPaymentStatus(b.getPaymentStatus() == 1 ? "Đã thanh toán" : b.getPaymentStatus() == 2 ? "Chưa thanh toán" : "Đã hoàn tiền");
            dataExportExcel.setNote(b.getNote());
            dataExport.add(dataExportExcel);
            index++;
        }
    }

    private List<String> listHeader() {
        List<String> header = new ArrayList<>();
        header.add("Stt");
        header.add("Mã hóa đơn");
        header.add("Tên khách hàng");
        header.add("Thời gian tạo");
        header.add("Tổng tiền");
        header.add("Trạng thái hóa đơn");
        header.add("Trạng thái thanh toán");
        header.add("Ghi chú");
        return header;
    }
}
