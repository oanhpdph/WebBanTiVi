package com.poly.dto;


import com.poly.entity.*;

import java.math.BigDecimal;

public interface ProductDto {
    String getNametv();
    Size getSize();
    Resolution getResolution();
    BigDecimal getPrice_export();
    Origin getOrigin();
    Manufacture getManufacture();
    Coupon getValue();
    ImageProduct getImageProduct();


}
