function changeBtn() {
    const btnEditInfo = document.getElementById("btnEditInfo");
    const btnSave = document.getElementById("btnSave");
    const btnCancel = document.getElementById("btnCancel");
    const nameCustomer = document.getElementById("nameCustomer");
    const phoneCustomer = document.getElementById("phoneCustomer");
    const deliver = document.getElementById("deliver");
    const phoneDeliver = document.getElementById("phoneDeliver");
    const addressDeliver = document.getElementById("addressDeliver");
    const emailCustomer = document.getElementById("emailCustomer");
    const deliveryFee = document.getElementById("deliveryFee");
    const note = document.getElementById("note");

    const arr = [btnSave, btnCancel, nameCustomer, phoneCustomer, deliver,phoneDeliver,addressDeliver,emailCustomer,deliveryFee,note];

    //nút chỉnh sửa đang ẩn
    if (btnEditInfo.style.display === "none") {
        btnEditInfo.style.display = "inline-block";
        for (let i = 0; i < arr.length; i++) {
            arr[i].style.display = "none"
        }
    } else {// nút chỉnh sửa đang hiện
        btnEditInfo.style.display = "none";
        for (let i = 0; i < arr.length; i++) {
            arr[i].style.display = "inline-block"
        }
    }
}

$("#btnEditInfo").click(function () {
        changeBtn()
    }
)
$("#btnCancel").click(function () {
        console.log("oanh")
        changeBtn()
    }
)
$("#btnSave").click(function () {
        changeBtn()
    }
)


$("#billStatus").change(function (){
    $("#formStatus").submit()
})
$("#paymentStatus").change(function (){
    $("#formStatus").submit()
})