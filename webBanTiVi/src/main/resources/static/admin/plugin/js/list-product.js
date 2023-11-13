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
