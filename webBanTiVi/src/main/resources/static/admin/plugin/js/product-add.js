var dataProductDetail = {}
$(document).ready(function () {
    loadDataField()
    loadDataGroup()
    // getProduct()
})
$("#select-group").change(function () {
    loadDataType()
})
$("#select-type").change(function () {
    changeTableProductDetail()
})

// document.getElementById("sameProduct").addEventListener("keyup", function (e) {
//     var sameCode = this.value
//     $.ajax({
//         url: "/product/same-product?same-code=" + sameCode,
//         method: "get",
//         success: function (data) {
//             console.log(data)
//         }
//     })
// })
// document.getElementById("sameProduct").addEventListener("paste", function (e) {
//     const clipboardData = e.clipboardData || window.clipboardData;
//     const sameCode = clipboardData.getData('text');
//     $.ajax({
//         url: "/product/same-product?same-code=" + sameCode,
//         method: "get",
//         success: function (data) {
//             console.log(data)
//         }
//     })
// })

function loadDataField() {
    $.ajax({
        url: "/field/all",
        method: "get",
        success: function (data) {
            // var dataTable = $('#tableAttributes tbody');
            $("#tableAttributes tbody").empty()
            $.each(data, function (index, item) {
                var button = document.createElement('button');
                button.innerHTML = 'Thêm'; // Đặt văn bản cho nút
                button.className = "btn btn-outline-secondary addAttributes mx-2"
                button.value = item.name
                button.id = "_" + item.id

                var label = document.createElement('label')
                button.appendChild(label)
                // Tạo một ô trong bảng
                var cell1 = document.createElement('td');
                var cell2 = document.createElement('td');
                var cell3 = document.createElement('td');

                cell1.innerText = index + 1
                cell2.innerText = item.name
                cell3.appendChild(button); // Thêm nút vào ô
                // Lấy bảng theo ID
                var tbody = document.getElementById('tableAttributes').getElementsByTagName('tbody')[0];

                // Thêm ô (cell) vào dòng (row) trong bảng
                var row = tbody.insertRow(index); // Thay đổi chỉ số hàng theo ý muốn
                row.appendChild(cell1);
                row.appendChild(cell2);
                row.appendChild(cell3);
            });
        },
    })

}

function loadDataType() {
    let value = $("#select-group").val()
    $.ajax({
        url: "/type/all?group=" + value,
        method: "get",
        success: function (data) {
            var select = $('#select-type');
            select.empty();
            select.append($('<option>', {
                disabled: true,
                selected: true,
                hidden: true,
                value: -1,
                text: "--Chưa chọn--"
            }))
            $.each(data, function (index, item) {
                select.append($('<option>', {
                    value: item.id,
                    text: item.nameType
                }));
            });
        },
    })

}

function loadDataGroup() {
    $.ajax({
        url: "/group/all",
        method: "get",
        success: function (data) {
            var select = $('#select-group');
            select.empty();
            select.append($('<option>', {
                disabled: true,
                selected: true,
                hidden: true,
                value: -1,
                text: "--Chưa chọn--"
            }))
            $.each(data, function (index, item) {
                select.append($('<option>', {
                    value: item.id,
                    text: item.nameGroup
                }));
            });
        },
    })
}

function clickImage() {
    if (this) {
        // Lấy tham chiếu đến phần tử cha của input (ở đây, phần tử cha là thẻ "li")
        var parentElement = this.closest('li');
        if (parentElement) {
            // Tìm thẻ img bên trong phần tử cha
            var inputFile = parentElement.querySelector('input[type="file"]');
            if (inputFile) {
                // Bạn đã tìm thấy thẻ img, bạn có thể làm việc với nó ở đây.
                inputFile.click()
            }
        }
    }
}

function onchangeImage() {
    if (this) {
        var parentElement = this.closest('li');
        if (parentElement) {
            var imageElement = parentElement.querySelector("img.image-preview")
            const file = this.files[0]; // Lấy tệp đã chọn
            if (file && imageElement) {
                // Kiểm tra xem tệp đã chọn có phải là hình ảnh
                if (file.type.startsWith('image/')) {
                    // Tạo đường dẫn URL cho hình ảnh và hiển thị nó
                    const imageURL = URL.createObjectURL(file);
                    imageElement.src = imageURL;
                } else {
                    alert('Vui lòng chọn một tệp hình ảnh.');
                    this.value = ''; // Đặt lại trường nhập
                }
            } else {
                imageElement.src = ""
            }
        }
    }
}

function saveProduct() {
    document.getElementById("save-product").removeEventListener("click", clickSave)
    document.getElementById("save-product").addEventListener("click", clickSave)
}

function clickSave() {
    var sku = document.getElementsByClassName("sku")
    var priceImport = document.getElementsByClassName("priceImport")
    var priceExport = document.getElementsByClassName("priceExport")
    var quantity = document.getElementsByClassName("quantity")
    var nameProduct = document.getElementsByClassName("name-product")
    var id = document.getElementsByClassName("delete-product-detail")
    var data = []

    // set ảnh
    $.each(id, function (index, item) {
        var value = item.getAttribute("value")
        var image = "imageUpload" + value
        var arrImage = []
        var listImage = document.getElementsByClassName(image)
        $.each(listImage, function (index, item) {
            // Lấy tệp từ trường chọn tệp
            var imageItem;
            var fileName = item.value; // Lấy đường dẫn đầy đủ của tệp

// Trích xuất tên tệp từ đường dẫn
            var lastIndex = fileName.lastIndexOf("\\"); // Sử dụng "\\" để tách tên tệp trên Windows
            if (lastIndex >= 0) {
                fileName = fileName.substr(lastIndex + 1);
            }

            if (item.classList.contains("true")) {
                imageItem = {
                    location: "true",
                    multipartFile: fileName
                }
            } else {
                imageItem = {
                    location: "false",
                    multipartFile: fileName
                }
            }
            arrImage.push(imageItem)
        })

        var temp = {
            sku: sku[index].value,
            type: document.getElementById("select-type").value,
            nameProduct: nameProduct[index].textContent.replace("+ mã sku", sku[index].value),
            priceImport: priceImport[index].value,
            priceExport: priceExport[index].value,
            quantity: quantity[index].value,
            image: arrImage,
            listAttributes: dataProductDetail.listAttributes[index],
            // sameProduct: document.getElementById("sameProduct").value != "" ? document.getElementById("sameProduct").value : null
        }
        data.push(temp)
    })

    uploadImage()
    $.ajax({
        url: "/product/save-product",
        method: "post",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#table-product-detail tbody").empty()
        }
    })
}

function uploadImage() {
    var fileInput = document.getElementsByClassName('file-input');

    var formData = new FormData();
    $.each(fileInput, function (index, item) {
        formData.append('images', item.files[0], item.files[0].name);
    })

    $.ajax({
        url: '/product/upload',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            // Xử lý kết quả từ máy chủ (nếu cần)
            console.log("thành công")
        },
        error: function (error) {
            console.error('Lỗi:', error);
        }
    });

}

// Thêm thuộc tính
$("#submit-add-attribute").click(function () {
    var value = $("#name-attribute").val()
    var dataPost = {name: value}
    $.ajax(
        {
            url: "/field/add",
            method: "post",
            data: JSON.stringify(dataPost), // Chuyển đổi dữ liệu thành chuỗi JSON
            contentType: 'application/json',
            success: function (data) {
                // $("#close-add-attribute").click()
                const Toast = Swal.mixin({
                    toast: true,
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener('mouseenter', Swal.stopTimer)
                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                    }
                })

                Toast.fire({
                    icon: 'success',
                    title: 'Thêm thành công thuộc tính ' + data.name,
                    customClass: {
                        container: 'alert' // Tên lớp tùy chỉnh
                    }
                })
                $("#name-attribute").val("")
                loadDataField()
            },
            error: function (error) {
                console.log('Lỗi trong quá trình POST yêu cầu:', error);
            }
        }
    )
})

function myFunction() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("search-input");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableAttributes");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

document.addEventListener('click', function (event) {
    if (event.target.classList.contains('addAttributes')) {
        var itemContentDiv = document.querySelector('.item-content');
// Tạo phần tử <div class="attributes">
        var attributesGroupDiv = document.createElement('div');
        attributesGroupDiv.className = 'attributes mb-3';

        //tao checkbox
        // <input className="form-check-input mx-2" type="checkbox">
        var checkbox = document.createElement("input")
        checkbox.className = "form-check-input mx-2 input-checkbox"
        checkbox.type = "checkbox"
        checkbox.value = event.target.value
        checkbox.id = "chk" + event.target.id
        attributesGroupDiv.appendChild(checkbox)
// Tạo label
        var label = document.createElement("label")
        label.className = "col-form-label"
        label.innerText = event.target.value
        label.setAttribute("for", "chk" + event.target.id)
        attributesGroupDiv.appendChild(label)
        // div input group
        var inputGroupDiv = document.createElement("div")
        inputGroupDiv.className = "input-group shadow-none"

        var inputDiv = document.createElement("div")
        inputDiv.className = "input-div col-sm-10"
        // tạo textarea
        var input = document.createElement("input")
        input.className = "form-control input-data w-100"
        input.rows = 1
        input.id = "_" + event.target.id
        inputDiv.appendChild(input)
        inputGroupDiv.appendChild(inputDiv)

        var deleteDiv = document.createElement('div');
        deleteDiv.className = "col-sm-2 text-end"
        //tạo button delete
        var buttonDel = document.createElement('button')
        buttonDel.type = 'button';
        buttonDel.className = "btn btn-danger delete-button";
        buttonDel.value = event.target.id
        var spanInBtn = document.createElement('span');
        spanInBtn.className = "tf-icons bx bxs-trash delete-button";
        spanInBtn.value = event.target.id

        buttonDel.appendChild(spanInBtn)
        deleteDiv.appendChild(buttonDel)
        inputGroupDiv.appendChild(deleteDiv)
        attributesGroupDiv.appendChild(inputGroupDiv)
        itemContentDiv.appendChild(attributesGroupDiv);


        $.each($(".input-data"), function (index, item) {
            item.addEventListener("keydown", function (e) {
                if (e.key === "Enter" && e.target.value.trim() !== "") {
                    // Tạo một thẻ span cho tag mới
                    const inputValue = e.target.value.trim();
                    const tagValue = inputValue.slice(0);
                    var listValue = []
                    var allTag = document.querySelectorAll("." + e.target.id)

                    $.each(allTag, function (index, item) {
                        listValue.push(item.textContent)
                    })
                    // thêm tag
                    if (listValue.includes(tagValue)) {
                        // Kiểm tra xem tag đã tồn tại chưa
                        return;
                    }
                    e.target.value = "";

                    const tag = document.createElement("div");
                    tag.textContent = tagValue;
                    tag.classList.add("tag", e.target.id);
                    e.target.closest(".input-div").appendChild(tag);
                    // Xử lý sự kiện khi người dùng nhấp vào tag để xóa
                    tag.addEventListener("click", function () {
                        removeTag(tagValue, tag, e);
                        changeTableProductDetail()
                    });
                    // Ngăn chặn hành vi mặc định của Enter (ngăn xuống dòng)
                    e.preventDefault();

                    saveProduct()
                    changeTableProductDetail()
                }
            })
        });
        $.each($(".input-checkbox"), function (index, item) {
            item.addEventListener("change", function (event) {
                var valueTextArea = document.getElementById("name-display").textContent
                var listValueTextArea = valueTextArea.split("+").map(function (item) {
                    return item.trim()
                })
                var value = event.target.value
                if (event.target.checked === false && listValueTextArea.includes(value.toLowerCase())) {
                    var index = listValueTextArea.indexOf(value.toLocaleLowerCase())
                    listValueTextArea.splice(index, 1)
                }
                if (event.target.checked === true && !listValueTextArea.includes(value.toLowerCase())) {
                    listValueTextArea.splice(listValueTextArea.length - 1, 0, value.toLowerCase())
                }
                document.getElementById("name-display").innerText = "Loại sản phẩm + SKU"
                document.getElementById("name-display").innerText = listValueTextArea.join(" + ")
                changeTableProductDetail()
            })
        })
        event.target.hidden = true
    }
})

document.addEventListener('click', function (event) {
    if (event.target.classList.contains('delete-button')) {
        // Xóa thẻ cha của nút "Xóa"
        var id = event.target.value;
        if (document.getElementById(id) != null) {
            document.getElementById(id).hidden = false
        }
        var checked = document.getElementById("chk" + id)
        if (checked.checked === true) {
            var valueTextArea = document.getElementById("name-display").textContent
            var listValueTextArea = valueTextArea.split("+").map(function (item) {
                return item.trim()
            })
            var value = checked.value

            var index = listValueTextArea.indexOf(value.toLocaleLowerCase())
            listValueTextArea.splice(index, 1)
            document.getElementById("name-display").innerText = listValueTextArea.join(" + ")
        }
        var inputGroupDiv = event.target.closest('.attributes');
        if (inputGroupDiv) {
            inputGroupDiv.remove();
        }
        changeTableProductDetail()
    }
});

function genProduct(listAttri, currentCombination, currentIndex, temp) {
    if (listAttri.length == currentIndex) {
        dataProductDetail.type = $('#select-type option:selected').text();
        var listAttributes = []
        for (let i = 0; i < temp.length; i++) {
            var loop = {
                id: temp[i].id,
                name: temp[i].name,
                index: temp[i].index,
                value: currentCombination[i]
            }
            listAttributes.push(loop)
        }
        dataProductDetail.listAttributes.push(listAttributes)
        return
    }

    var listTemp = []
    $.each(listAttri, function (index, item) {
        var temp = {
            id: item.id,
            name: item.name,
            index: item.index
        }
        listTemp.push(temp)
    })

    var currentList = listAttri[currentIndex].value;

    $.each(currentList, function (index, item) {
        currentCombination.push(item)
        genProduct(listAttri, currentCombination, currentIndex + 1, listTemp)
        currentCombination.pop()
    })
}

function changeTableProductDetail() {
    var group = $("#select-group")
    var type = $("#select-type")
    var listAttributesForm = document.getElementsByClassName("input-data")
    var data = {
        group: group.value,
        type: type.value,
        listAttributes: []
    }

    var valueTextArea = document.getElementById("name-display").textContent
    var listValueTextArea = valueTextArea.split("+").map(function (item) {
        return item.trim()
    })
    var inputCheckbox = document.getElementsByClassName("input-checkbox")
    var listIndex = []
    $.each(inputCheckbox, function (index1, item1) {
        if (listValueTextArea.indexOf(item1.value.toLocaleLowerCase()) >= 0) {
            listIndex.push(listValueTextArea.indexOf(item1.value.toLowerCase()))
        } else {
            listIndex.push("null")
        }
    })
    $.each(listAttributesForm, function (index, item) {
        var arr = []
        var allTag = document.querySelectorAll("." + item.id)
        $.each(allTag, function (index, value) {
            arr.push(value.textContent)
        })
        var idTemp = "chk_" + item.id.substring(2)
        data.listAttributes.push({
            id: item.id.substring(2),
            name: document.getElementById(idTemp).value,
            value: arr,
            index: listIndex[index]
        })
    })
    dataProductDetail.listAttributes = []
    genProduct(data.listAttributes, [], 0, [])
    if (dataProductDetail.listAttributes.length !== 0) {
        $("#table-product-detail tbody").empty()
        $.each(dataProductDetail.listAttributes, function (index, item) {
            // lấy tên san phẩm
            if (item.length === 0) {
                return
            }
            var temp = item
            temp = temp.filter(function (a) {
                return a.index !== "null";
            });
            temp = temp.sort(function (pr1, pr2) {
                return pr1.index - pr2.index
            })
            var nameProduct = []
            document.getElementById("select-type")
            nameProduct.push($('#select-type option:selected').text())
            $.each(temp, function (index, itemTemp) {
                nameProduct.push(itemTemp.value)
            })
            nameProduct.push("+ mã sku")
            //end
// Tạo một thẻ <tr>
            const tableRow = document.createElement("tr");

// Tạo một thẻ <td> cho Tên hiển thị
            const displayNameCell = document.createElement("td");
            var nameSpan = document.createElement("span");
            nameSpan.innerText = nameProduct.join(" ");
            nameSpan.className = "name-product"
            displayNameCell.appendChild(nameSpan)
            tableRow.appendChild(displayNameCell);

// Tạo các ô input
            const inputCells = [];
            const name = ["sku", "quantity", "priceImport", "priceExport"]
            for (let i = 0; i < 4; i++) {
                const inputCell = document.createElement("td");
                const input = document.createElement("input");
                input.className = "form-control " + name[i];
                if (i === 0) {
                    input.type = "text";
                } else {
                    input.type = "number";
                }
                input.name = name[i]
                inputCell.appendChild(input);
                inputCells.push(inputCell);
            }
            tableRow.append(...inputCells);

// Tạo một ô chứa ảnh và input file
            const imageCell = document.createElement("td");
            const imageList = document.createElement("ul");
            imageList.className = "list-unstyled users-list avatar-group m-0 d-flex align-items-center";

            for (let i = 0; i < 5; i++) {
                const listItem = document.createElement("li");
                listItem.setAttribute("data-bs-toggle", "tooltip");
                listItem.setAttribute("data-popup", "tooltip-custom");
                listItem.setAttribute("data-bs-placement", "bottom");
                listItem.className = "avatar avatar-xl pull-up border-dark border";
                listItem.setAttribute("data-bs-offset", "0,4");
                listItem.setAttribute("data-bs-html", "true");

                const image = document.createElement("img");
                image.src = "/image/smart-tivi-toshiba-43-inch-43v31mp-01.jpg";
                image.alt = "Chưa có ảnh";
                image.className = "image-preview";
                image.onclick = clickImage

                const inputFile = document.createElement("input");
                inputFile.type = "file";
                inputFile.hidden = true;
                inputFile.onchange = onchangeImage
                if (i == 0) {
                    listItem.setAttribute("title", "Ảnh chính");
                    inputFile.className = "file-input true" + " imageUpload" + index;
                } else {
                    listItem.setAttribute("title", "Ảnh phụ");
                    inputFile.className = "file-input false" + " imageUpload" + index;
                }
                listItem.appendChild(image);
                listItem.appendChild(inputFile);
                imageList.appendChild(listItem);
            }

            imageCell.appendChild(imageList);
            tableRow.appendChild(imageCell);

// Tạo ô cho nút dropdown
            const dropdownCell = document.createElement("td");
            const dropdown = document.createElement("div");
            dropdown.className = "dropdown";

            const dropdownButton = document.createElement("button");
            dropdownButton.type = "button";
            dropdownButton.className = "btn p-0 dropdown-toggle hide-arrow";
            dropdownButton.setAttribute("data-bs-toggle", "dropdown");
            dropdownButton.innerHTML = '<i class="bx bx-dots-vertical-rounded"></i>';

            const dropdownMenu = document.createElement("div");
            dropdownMenu.className = "dropdown-menu dropdown-menu-end";

            const viewItem = document.createElement("a");
            viewItem.className = "dropdown-item view-product";
            viewItem.href = "javascript:void(0);";
            viewItem.setAttribute("value", index)
            viewItem.innerHTML = '<i class="bx bx-edit-alt me-1"></i> Xem';
            viewItem.setAttribute("data-bs-toggle", "modal")
            viewItem.setAttribute("data-bs-target", "#modalDetail")
            viewItem.onclick = viewProduct

            const deleteItem = document.createElement("a");
            deleteItem.className = "dropdown-item delete-product-detail";
            deleteItem.setAttribute("value", index)
            deleteItem.href = "javascript:void(0);";
            deleteItem.innerHTML = '<i class="bx bx-trash me-1"></i> Xóa';

            dropdownMenu.appendChild(viewItem);
            dropdownMenu.appendChild(deleteItem);

            dropdown.appendChild(dropdownButton);
            dropdown.appendChild(dropdownMenu);
            dropdownCell.appendChild(dropdown);
            tableRow.appendChild(dropdownCell);

// Thêm thẻ <tr> vào bảngtail
            const table = document.querySelector(".table-product tbody"); // Điều chỉnh chọn bảng cụ thể
            table.appendChild(tableRow);
            document.getElementById("detail-pro").hidden = false

            deleteItem.addEventListener("click", function (e) {
                var i = e.target.closest("tr").rowIndex;
                document.querySelector("#table-product-detail").deleteRow(i);
                dataProductDetail.listAttributes.splice(i - 1, 0)
                saveProduct()
            })
        });
        // loadImage()
    } else {
        document.getElementById("detail-pro").hidden = true
    }
}

function viewProduct() {
    var index = this.getAttribute('value')
    document.getElementById("delete-product").value = index

    var product = dataProductDetail.listAttributes[index]
    var temp = dataProductDetail.listAttributes[Number(index)].filter(function (a) {
        return a.index !== "null";
    });
    temp = temp.sort(function (pr1, pr2) {
        return pr1.index - pr2.index
    })
    var nameProduct = []
    nameProduct.push("Thông tin sản phẩm")
    document.getElementById("select-type")
    nameProduct.push($('#select-type option:selected').text())
    $.each(temp, function (index, itemTemp) {
        nameProduct.push(itemTemp.value)
    })
    nameProduct.push("+ mã sku")
    console.log(nameProduct)
    document.getElementById("modalLongTitle").innerText = ""
    document.getElementById("modalLongTitle").innerText = nameProduct.join(" ")

    var type = dataProductDetail.type

    $("#table-detail-attributes tbody").empty()
    var cell1 = document.createElement('td');
    var cell2 = document.createElement('td');

    cell1.innerHTML = "Loại sản phẩm"
    cell2.innerHTML = type

    var tbody = document.getElementById('table-detail-attributes').getElementsByTagName('tbody')[0];
    var row = tbody.insertRow(0);
    row.appendChild(cell1)
    row.appendChild(cell2)
    $.each(product, function (index, item) {
        var cell1 = document.createElement('td');
        var cell2 = document.createElement('td');
        cell1.innerHTML = item.name
        cell2.innerHTML = item.value
        var row = tbody.insertRow(index + 1);
        row.appendChild(cell1)
        row.appendChild(cell2)
    })
}

document.getElementById("delete-product").addEventListener("click", function () {
    var value = Number(this.value)
    var index = value + 1
    document.querySelector("#table-product-detail").deleteRow(index)
    console.log(this.value)
    dataProductDetail.listAttributes.splice(this.value - 1, 0)
    saveProduct()
})

//remove tag input attribute
function removeTag(tagValue, tagElement, e) {
    e.target.closest(".input-div").removeChild(tagElement);
}

// clear form modal add product
function clear() {
    loadDataGroup()
    loadDataType()
    var container = document.querySelector(".item-content")
    while (container.firstChild) {
        container.removeChild(container.firstChild);
    }

    var buttonAdd = document.querySelectorAll(".addAttributes")
    $.each(buttonAdd, function (index, item) {
        item.hidden = false
    })
    document.getElementById("name-display").innerText = "Loại sản phẩm + SKU"
}

