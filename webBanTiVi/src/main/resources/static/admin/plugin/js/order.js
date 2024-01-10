var idBill
$(document).ready(checkAll())
$.each(document.getElementsByClassName("return"), function (index, item) {
    item.addEventListener('change', function () {
        idBill = item.value;
        var nameProduct = document.getElementsByClassName("nameProduct" + idBill)
        var quantityReturn = document.getElementsByClassName("quantityReturn" + idBill)
        var image = document.getElementsByClassName("returnImg" + item.value)
        var reason = document.getElementsByClassName("reason" + idBill)
        var completed=document.getElementById("complete");
        if (!item.checked) {
            nameProduct[0].disabled = true;
            quantityReturn[0].disabled = true;
            reason[0].disabled = true;
            $.each(image, function (index, item) {
                item.disabled = true;
            })
            completed.disabled = true;
        } else {
            nameProduct[0].disabled = false;
            quantityReturn[0].disabled = false;
            reason[0].disabled = false;
            $.each(image, function (index, item) {
                item.disabled = false;
            })
            completed.disabled = false;
        }
        checkAll()
    })
})

function checkAll() {
    document.getElementById("checkboxAll").addEventListener("click", function (event) {
        $.each(document.getElementsByClassName("return"), function (index, item) {
            idBill = item.value;
            var nameProduct = document.getElementsByClassName("nameProduct" + idBill)
            var quantityReturn = document.getElementsByClassName("quantityReturn" + idBill)
            var image = document.getElementsByClassName("returnImg" + idBill)
            var reason = document.getElementsByClassName("reason" + idBill)
            var completed = document.getElementById("complete")
            if (document.getElementById("checkboxAll").checked) {
                console.log(item);
                item.checked = true;
                $.each(quantityReturn, function (index, item) {
                    item.disabled = false;
                })
                $.each(reason, function (index, item) {
                    item.disabled = false;
                })
                $.each(image, function (index, item) {
                    item.disabled = false;
                })
                completed.disabled = false;
            } else {
                item.checked = false;
                $.each(quantityReturn, function (index, item) {
                    item.disabled = true;
                })
                $.each(reason, function (index, item) {
                    item.disabled = true;
                })
                $.each(image, function (index, item) {
                    item.disabled = true;
                })
                completed.disabled = true;
            }
        })
    })
}

function clickSave() {
    var data = [];
    $.each(document.getElementsByClassName("return"), function (index, item) {
        idBill = item.value;
        $.each(document.getElementsByClassName("checkbox" + idBill), function (index, item) {
            if (item.checked) {
                console.log(...data);
                var idBillProduct = document.getElementsByClassName("checkbox" + idBill)
                var nameProduct = document.getElementsByClassName("nameProduct" + idBill)
                var quantityReturn = document.getElementsByClassName("quantityReturn" + idBill)
                var reason = document.getElementsByClassName("reason" + idBill)
                var check = true;
                $.each(idBillProduct, function (index, item) {
                    var errorQuantity = document.querySelector(".errorQuantity" + item.value);
                    var errorReason = document.querySelector(".errorReason" + item.value);
                    if (item.checked) {
                        if (quantityReturn[index].value > Number(quantityReturn[index].getAttribute("max"))) {
                            errorQuantity.innerHTML = "Số lượng trả không lớn hơn số lượng mua!"
                            check = false;
                            console.log(errorQuantity);
                        } else if (quantityReturn[index].value.length == 0) {
                            errorQuantity.innerHTML = "Vui lòng điền số lượng!"
                            check = false;
                        } else {
                            errorQuantity.innerHTML = ""
                        }
                        if (reason[index].value.length == 0) {
                            errorReason.innerHTML = "Vui lòng nhập lí do!"
                            check = false;
                        } else {
                            errorReason.innerHTML = ""
                        }
                    }
                })
                $.each(idBillProduct, function (index, item) {
                    if (item.checked) {
                        var image = "returnImg" + item.value
                        var arrImage = []
                        var listImage = document.getElementsByClassName(image)
                        var errorImage = document.querySelector(".errorImage" + item.value)
                        $.each(listImage, function (index, itm) {
                            var imageItem;
                            var fileName = itm.value; // Lấy đường dẫn đầy đủ của tệp
                            var imageName;
                            // Trích xuất tên tệp từ đường dẫn
                            var lastIndex = fileName.lastIndexOf("\\"); // Sử dụng "\\" để tách tên tệp trên Windows
                            if (lastIndex >= 0) {
                                imageName = fileName.substr(lastIndex + 1);
                            }

                            if (fileName.length != 0) {
                                imageItem = {
                                    nameImage: imageName,
                                }
                                arrImage.push(imageItem)
                            }


                        })
                        if (item.checked) {
                            if (arrImage.length == 0) {
                                errorImage.innerHTML = "Vui lòng tải lên ít nhất 1 ảnh!"
                                check = false;
                            } else {
                                errorImage.innerHTML = ""
                            }
                        }

                        var temp = {
                            idBillProduct: item.value,
                            nameProduct: nameProduct[index].value,
                            quantityReturn: quantityReturn[index].value,
                            reason: reason[index].value,
                            image: arrImage,

                        }
                        console.log(temp);
                        data.push(temp)
                    }
                })
                if (check == false) {
                    console.log(111);
                    return
                }
            }
        })

    })
    var billIdRequest = document.getElementById("billId");
    uploadImage();
    var xacNhan = confirm("Bạn có chắc chắn muốn trả hàng không?");
    if (xacNhan) {
        $.ajax({
            url: "/return/" + billIdRequest.value,
            method: "post",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function () {
                console.log("Thành công!")
            }
        })
    } else {
        return;
    }
}

function uploadImage() {
    var fileInput = document.getElementsByClassName('file-input');
    var formData = new FormData();
    $.each(fileInput, function (index, item) {
        console.log(item.value)
        if (item.value.trim().length != 0) {
            formData.append('images', item.files[0], item.files[0].name);
        }
    })
    console.log(formData)
    $.ajax({
        url: '/product/upload',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            console.log("thành công")
        },
        error: function (error) {
            console.error('Lỗi:', error);
        }
    });
}
