document.addEventListener("DOMContentLoaded", function() {
    var scrollToTopBtn = document.getElementById("scrollToTopBtn");

    // Hiển thị nút khi người dùng cuộn xuống một phần nào đó trên trang
    window.addEventListener("scroll", function() {
        if (window.scrollY > 50) {
            scrollToTopBtn.style.display = "block";
        } else {
            scrollToTopBtn.style.display = "none";
        }
    });

    // Cuộn lên đầu trang khi người dùng nhấp vào nút
    scrollToTopBtn.addEventListener("click", function() {
        window.scrollTo({
            top: 0,
            behavior: "smooth" // Cuộn mượt
        });
    });
});