document.getElementById("prev").addEventListener("click", function () {
    if (document.getElementById("pageInput").value > 1) {
        document.getElementById("pageInput").value = Number(document.getElementById("pageInput").value) - 1
        $("#form-product").submit()

    }
})
document.getElementById("next").addEventListener("click", function () {
    if (document.getElementById("pageInput").value < Number(document.getElementById("total").getAttribute("value"))) {
        document.getElementById("pageInput").value = Number(document.getElementById("pageInput").value) + 1
        $("#form-product").submit()
    }
})
document.getElementById("last").addEventListener("click", function () {
    if (document.getElementById("pageInput").value < Number(document.getElementById("total").getAttribute("value"))) {
        document.getElementById("pageInput").value = Number(document.getElementById("total").getAttribute("value"))
        $("#form-product").submit()
    }
})
document.getElementById("first").addEventListener("click", function () {
    if (document.getElementById("pageInput").value > 1) {
        document.getElementById("pageInput").value = 1
        $("#form-product").submit()
    }
})
document.getElementById("select-size").addEventListener("change", function () {
    $("#form-product").submit()
})
document.getElementById("sort").addEventListener("change", function () {
    $("#form-product").submit()
})
document.getElementById("pageInput").addEventListener("blur", function () {
    if (document.getElementById("pageInput").value != Number(document.getElementById("pageInput").getAttribute("temp"))) {
        if (document.getElementById("pageInput").value <= Number(document.getElementById("total").getAttribute("value")) && document.getElementById("pageInput").value > 0) {
            $("#form-product").submit()
        } else {
            document.getElementById("pageInput").value = Number(document.getElementById("pageInput").getAttribute("temp"))
        }
    }
})
document.getElementById("pageInput").addEventListener("keypress", function (event) {
    // If the user presses the "Enter" key on the keyboard
    if (event.key === "Enter") {
        // Cancel the default action, if needed
        if (document.getElementById("pageInput").value != Number(document.getElementById("pageInput").getAttribute("temp"))) {

            if (document.getElementById("pageInput").value <= Number(document.getElementById("total").getAttribute("value")) && document.getElementById("pageInput").value > 0) {
                $("#form-product").submit()
            } else {
                document.getElementById("pageInput").value = Number(document.getElementById("pageInput").getAttribute("temp"))
            }
        }
        event.preventDefault();
    }
});
if (document.getElementById("edit-attri")) {
    document.getElementById("edit-attri").addEventListener("click", function () {
        $.each($(".span-value-attri"), function (index, item) {
            var input = $('<input>', {
                class: 'form-control attributes-update',
                value: item.textContent,
                id: '__' + item.getAttribute('value')
            });
            console.log(input)
            $(item).closest('td').html(input);
        })
    })
}
$.each(document.getElementsByClassName("update-active-product"), function (index, item) {
    item.addEventListener("click", function () {
        function change() {
            item.checked = false
        }

        var text;
        if (item.checked) {
            document.getElementById("cancelActive").removeEventListener("click", change)
            document.getElementById("cancelActive").addEventListener("click", change)
            document.getElementById("closeActive").removeEventListener("click", change)
            document.getElementById("closeActive").addEventListener("click", change)

            var data = {
                id: item.value
            }
            $.ajax({
                url: "/product/get-product-by-id",
                method: "get",
                data: data,
                // contentType: "application/json",
                success: function (data) {
                    console.log(data)

                    var table = document.querySelector("#table-active tbody")
                    $("#table-active tbody").empty()
                    $.each(data.productDetails, function (index, item2) {
                        var nameProduct = []
                        nameProduct.push(data.nameProduct)
                        nameProduct.push("[")
                        $.each(item2.fieldList, function (index, item3) {
                            nameProduct.push(item3.value)
                            nameProduct.push("-")
                        })

                        nameProduct.splice(nameProduct.length - 1, 1, "]")
                        var row = document.createElement("tr")

                        var cell1 = document.createElement("td")
                        var spanName = document.createElement("span")
                        spanName.innerText = nameProduct.join(" ")
                        spanName.className = "fw-semibold"
                        cell1.appendChild(spanName)

                        var cell2 = document.createElement("td")
                        var spanSku = document.createElement("span")
                        spanSku.innerText = item2.sku
                        cell2.appendChild(spanSku)

                        var cell3 = document.createElement("td")
                        var spanPrice = document.createElement("span")
                        spanPrice.innerText = item2.priceExport
                        cell3.appendChild(spanPrice)

                        var cell4 = document.createElement("td")
                        var spanQuantity = document.createElement("span")
                        spanQuantity.innerText = item2.quantity
                        cell4.appendChild(spanQuantity)


                        var cell5 = document.createElement("td")
                        var inputActive = document.createElement("input")
                        inputActive.className = "form-check-input larger-checkbox input-active"
                        inputActive.type = "checkbox"
                        inputActive.value = item2.id
                        inputActive.checked = true
                        cell5.appendChild(inputActive)

                        row.appendChild(cell1)
                        row.appendChild(cell2)
                        row.appendChild(cell3)
                        row.appendChild(cell4)
                        row.appendChild(cell5)
                        table.appendChild(row)
                    })
                }
            })
            $("#saveActive").on("click", function () {
                var checkbox = document.getElementsByClassName("input-active")
                var temp = false
                $.each(checkbox, function (index, item) {
                    if (item.checked) {
                        temp = true
                    }
                })
                if (temp) {
                    Swal.fire({
                        title: "Bạn xác nhận cập nhật?",
                        text: "",
                        icon: "question",
                        showCancelButton: true,
                        confirmButtonColor: "#3085d6",
                        cancelButtonColor: "#d33",
                        confirmButtonText: "Xác nhận!",
                    }).then((result) => {
                        if (result.isConfirmed) {
                            var productDetailList = []
                            $.each(checkbox, function (index, item) {
                                if (item.checked) {
                                    var detail = {};
                                    detail.id = item.value
                                    detail.active = "true"
                                    productDetailList.push(detail)
                                }
                            })
                            $.ajax({
                                url: "/product/update-active-detail",
                                method: "post",
                                data: JSON.stringify(productDetailList),
                                contentType: "application/json",
                                success: function (data) {
                                }
                            })

                            var data = {
                                id: item.value,
                                active: item.checked
                            }

                            $.ajax({
                                url: "/product/update-product",
                                method: "post",
                                data: JSON.stringify(data),
                                contentType: "application/json",
                                success: function (data) {
                                    Swal.fire({
                                        title: "Thành công!",
                                        text: "Trạng thái sản phẩm đã được cập nhật",
                                        icon: "success",
                                    });
                                }
                            })
                            document.getElementById("closeActive").removeEventListener("click", change)
                            document.getElementById("closeActive").click()
                        } else {
                            item.checked = !item.checked
                        }
                    });
                } else {
                    Swal.fire({
                        title: "Lưu ý!",
                        text: "Cần chọn ít nhất 1 sản phẩm bày bán mới có thể bán sản phẩm này",
                        icon: "warning"
                    })
                }
            })
            $("#active").click()
            return
        } else {
            text = "Nếu thay đổi thì một số sản phẩm chi tiết có thể sẽ ngừng bày bán!"
        }
        Swal.fire({
            title: "Bạn xác nhận cập nhật?",
            text: text,
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Xác nhận!",
        }).then((result) => {
            if (result.isConfirmed) {
                var data = {
                    id: item.value,
                    active: item.checked
                }
                $.ajax({
                    url: "/product/update-product",
                    method: "post",
                    data: JSON.stringify(data),
                    contentType: "application/json",
                    success: function (data) {
                        console.log(data)
                        Swal.fire({
                            title: "Thành công!",
                            text: "Trạng thái sản phẩm đã được cập nhật",
                            icon: "success",
                        });
                    }
                })
            } else {
                item.checked = !item.checked
            }
        });
    })
})
$.each(document.getElementsByClassName("active-product-detail"), function (index, item) {
    item.addEventListener("click", function () {
            // if (item.checked) {
            Swal.fire({
                title: "Bạn xác nhận cập nhật?",
                text: "",
                icon: "question",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Xác nhận!",
            }).then((result) => {
                if (result.isConfirmed) {
                    var data = []
                    var product = {
                        id: item.value,
                        active: item.checked
                    }
                    data.push(product)
                    $.ajax({
                        url: "/product/update-active-detail",
                        method: "post",
                        data: JSON.stringify(data),
                        contentType: "application/json",
                        success: function (data) {
                        }
                    })
                } else {
                    item.checked = !item.checked
                }
            })
            // } else {
            //
            // }
        }
    )
})
$.each(document.getElementsByClassName("edit-product"), function (index, item) {
    item.addEventListener("click", function () {
        var data = {
            id: item.getAttribute('value')
        }
        $.ajax({
            url: "/product/get-product-by-id",
            method: "get",
            data: data,
            // contentType: "application/json",
            success: function (data) {
                var localData = data
                document.getElementById("save").value = data.id
                var table = document.querySelector("#table-detail-modal tbody")
                $("#table-detail-modal tbody").empty()
                $.each(data.productFieldValues, function (index, item) {
                    var row = document.createElement("tr")
                    var cell1 = document.createElement("td")
                    var nameAttri = document.createElement("span")
                    nameAttri.innerText = item.field.name
                    cell1.appendChild(nameAttri)

                    var cell2 = document.createElement("td")
                    var value = document.createElement("span")
                    value.className = "span-value-attri"
                    // value.disabled = true
                    value.innerText = item.value
                    value.setAttribute('value', item.field.id)
                    cell2.appendChild(value)

                    row.appendChild(cell1)
                    row.appendChild(cell2)
                    table.appendChild(row)

                })
                $("#save").on("click", function () {
                    var data = {}
                    data.product = []
                    data.id = $("#save").val()
                    data.active = localData.active
                    $.each($(".attributes-update"), function (index, item) {
                        var attri = {
                            id: item.id.substring(2, item.id.length),
                            value: item.value,

                        }
                        data.product.push(attri)
                    })
                    console.log(data)
                    console.log(data)
                    $.ajax({
                        url: "/product/update-product",
                        method: "post",
                        data: JSON.stringify(data),
                        contentType: "application/json",
                        success: function (data) {
                        }
                    })
                })
            }
        })
    })
})