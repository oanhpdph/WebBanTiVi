$.each($(".image-evaluate"), function (index, item) {
    item.addEventListener("click", function () {
        $("#image-evaluate-modal").attr('src', this.getAttribute('src'))
    })
})
$.each($(".active"), function (index, item) {
    $(item).on('click', function () {
        Swal.fire({
            title: "Bạn xác nhận cập nhật?",
            text: "",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Xác nhận!",
            cancelButtonText: "Hủy"
        }).then((result) => {
            if (result.isConfirmed) {
                var data = {
                    id: $(this).val(),
                    active: $(this).is(':checked')
                }
                $.ajax({
                    url: '/admin/evaluate/update',
                    method: 'post',
                    data: JSON.stringify(data),
                    contentType: "application/json",
                    success: function (data) {
                        const Toast = Swal.mixin({
                            toast: true,
                            position: "top-end",
                            showConfirmButton: false,
                            timer: 3000,
                            timerProgressBar: true,
                            didOpen: (toast) => {
                                toast.onmouseenter = Swal.stopTimer;
                                toast.onmouseleave = Swal.resumeTimer;
                            }
                        });
                        Toast.fire({
                            icon: "success",
                            title: "Cập nhật thành công"
                        });
                    }
                })
            } else {
                this.checked = !this.checked
            }
        })
    })
})
// Declare variables
$("#search").keyup(function () {
    var input, filter, table, tr, td, i, txtValue, check;
    input = $("#search");
    filter = input.val().toUpperCase();
    table = $("#table-evaluate");
    tr = table.find("tr");

    for (i = 0; i < tr.length; i++) {
        td = $(tr[i]).find("td");
        if (td.length != 0) {
            for (j = 0; j < td.length; j++) {
                txtValue = td[j].textContent || td[j].innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    check = true;
                    break
                } else {
                    check = false;
                }
            }
        } else {
            check = true;
        }
        if (check) {
            tr[i].style.display = ""
        } else {
            tr[i].style.display = "none"
        }
    }
})