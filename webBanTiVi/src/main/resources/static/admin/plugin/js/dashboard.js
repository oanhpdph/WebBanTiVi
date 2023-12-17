function showData() {
    // Hiển thị table chứa dữ liệu
    $("#myDiv table").hide()
    $("#myDiv4 table").hide();
    $("#myDiv3 table").hide();
    $("#myDiv2 table").show();
    $("#myDivProductCount table").hide();
    $("#myDivVoucher table").hide();
    $("#myDivDisCount table").hide();
}
function showData2() {
    // Hiển thị table chứa dữ liệu
    $("#myDiv table").hide()
    $("#myDiv4 table").hide();
    $("#myDiv2 table").hide();
    $("#myDivProductCount table").hide();
    $("#myDivVoucher table").hide();
    $("#myDivDisCount table").hide();
    $("#myDiv3 table").show();

}
function showData3() {
    // Hiển thị table chứa dữ liệu
    $("#myDiv table").hide()
    $("#myDiv3 table").hide();
    $("#myDiv2 table").hide();
    $("#myDivProductCount table").hide();
    $("#myDivVoucher table").hide();
    $("#myDivDisCount table").hide();
    $("#myDiv4 table").show();

}
function showData4() {
    // Hiển thị table chứa dữ liệu
    $("#myDiv3 table").hide();
    $("#myDiv2 table").hide();
    $("#myDiv4 table").hide();
    $("#myDivProductCount table").show();
    $("#myDivVoucher table").hide();
    $("#myDivDisCount table").hide();
}
function showData5() {
    // Hiển thị table chứa dữ liệu
    $("#myDiv3 table").hide();
    $("#myDiv2 table").hide();
    $("#myDiv4 table").hide();
    $("#myDivProductCount table").hide();
    $("#myDivVoucher table").hide();
    $("#myDivDisCount table").show();

}
function showData6() {
    // Hiển thị table chứa dữ liệu
    $("#myDiv3 table").hide();
    $("#myDiv2 table").hide();
    $("#myDiv4 table").hide();
    $("#myDivProductCount table").hide();
    $("#myDivVoucher table").show();
    $("#myDivDisCount table").hide();

}
function oncl(id) {
    window.location.href = "http://localhost:8080/admin/bill/bill_detail/" + id
}
function onclProduct(id) {
    window.location.href = "http://localhost:8080/admin/product/list_detail/" + id
}
function onclDiscount() {
    window.location.href = "http://localhost:8080/admin/coupon/list"
}
function onclVoucher() {
    window.location.href = "http://localhost:8080/admin/voucher/list"
}

$(document).ready(function () {
    var defaultValue = $("#completed").attr("defaultValue");
    $("#date").change(function () {
        var date = $(this).val();
        $.ajax({
            url: "/admin/dashboard/list",
            type: "GET",
            dataType: "json",
            data: {
                date: date
            },
            success: function (myData) {
                if(myData==null){
                    $("#completed").val(defaultValue);
                }else {
                    $("#completed").text(myData.length);
                }
            }
        })
        $.ajax({
            url: "/admin/dashboard/listReturn",
            type: "GET",
            dataType: "json",
            data: {
                date: date
            },
            success: function (DataReturn) {
                $("#return").text(DataReturn.length);
            }
        });
        $.ajax({
            url: "/admin/dashboard/listProcessing",
            type: "GET",
            dataType: "json",
            data: {
                date: date
            },
            success: function (DataProcessing) {
                $("#processing").text(DataProcessing.length);
            }
        });
        $.ajax({
            url: "/admin/dashboard/productCount",
            type: "GET",
            dataType: "json",
            data: {
                date: date
            },
            success: function (DataProcessing) {
                $("#productCount").text(DataProcessing.length);
            }
        });
        $.ajax({
            url: "/admin/dashboard/productCount",
            type: "GET",
            dataType: "json",
            data: {
                date: date
            },
            success: function (DataProcessing) {
                $("#productCount").text(DataProcessing.length);
            }
        });
    });
});