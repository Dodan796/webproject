document.addEventListener("DOMContentLoaded", function () {
    const guestMenu = document.getElementById("guestMenu");
    const userMenu = document.getElementById("userMenu");
    const logoutBtn = document.getElementById("logoutBtn");

    // JWT 확인
    const token = localStorage.getItem("accessToken");

    if (token) {
        // 로그인 상태
        guestMenu.style.display = "none";
        userMenu.style.display = "block";

        // 로그아웃 버튼 이벤트
        logoutBtn.addEventListener("click", function () {
            localStorage.removeItem("accessToken"); // JWT 삭제
            alert("로그아웃되었습니다.");
            window.location.href = "/"; // 메인 페이지로 이동
        });
    } else {
        // 비로그인 상태
        guestMenu.style.display = "block";
        userMenu.style.display = "none";
    }
});
