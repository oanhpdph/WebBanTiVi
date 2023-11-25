$(document).ready(
    loadProduct()
)

function loadProduct() {
    var data = {
        point: $("#filterRate").val(),
        sort: $("#sort").val(),
        key: $("#key").val(),
        size: $("#loadMore").val()
    }
    data.listBrand = []

    $.each($(".filter-brand"), function (index, item) {
        if (item.checked) {
            data.listBrand.push(item.value)
        }
    })

    $.ajax({
        url: "/accessory/get",
        method: "get",
        data: data,
        success: function (data) {
            $("#list-product-accessory").empty()
            // data.reverse()
            $.each(data, function (index, item) {
                var colDiv = $("<div>").addClass("col");

// Tạo đối tượng div với class "card h-100 item-product"
                var cardDiv = $("<div>").addClass("card h-100 item-product");

// Tạo đối tượng div với class "card-body mb-4 mt-3"
                var cardBodyDiv = $("<div>").addClass("card-body mb-4 mt-3");

// Tạo đối tượng img
                var img = $("<img>").addClass("img-fluid d-flex mx-auto my-4")
                    .attr("src", "/image/product/" + item.image)
                    .attr("alt", "Hình ảnh bị lỗi");

// Tạo đối tượng a với href
                var aTag = $("<a>").attr("href", "/product/detail/" + item.id)
                    .addClass("stretched-link mb-2 text-black");

// Tạo đối tượng span với class "fs-5 card-title" và text
                var spanTitle = $("<span>").addClass("fs-5 card-title")
                    .text(item.nameProduct);

// Tạo các đối tượng div và span cho giá và đánh giá
                if (item.price != 0) {
                    var strikeThroughDiv = $("<div>").append($("<span>").addClass("text-strike-through fs-6")
                        .html(item.price + "<small class='align-text-top'>đ</small>"));
                }
                var boldTextDiv = $("<div>").append($("<span>").addClass("text-danger fs-6 fw-bold")
                    .html(item.reduceMoney + "<small class='align-text-top'>đ</small>"));

                var starDiv = $("<div>").append($("<span>").html(item.point + "<i class='bx bxs-star' style='color:#fee500'></i>" + " (" + item.quantityEvalute + ")"));

// Gắn các đối tượng con vào các đối tượng cha
                aTag.append(spanTitle);
                cardBodyDiv.append(img, aTag, strikeThroughDiv, boldTextDiv, starDiv);
                cardDiv.append(cardBodyDiv);
                colDiv.append(cardDiv);
                $("#list-product-accessory").append(colDiv)
            })
        }
    })
}

$("#filterRate").on("change", function () {
    loadProduct()
})
$(".filter-brand").on("change", function () {
    loadProduct()
})
$("#btn-search").on("click", function () {
    loadProduct()
})
$("#loadMore").on("click", function () {
    this.value = Number(this.value) + 10
    loadProduct()
})

