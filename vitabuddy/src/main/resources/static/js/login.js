document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("loginForm");

    loginForm.addEventListener("submit", function (event) {
        event.preventDefault(); // 기본 폼 제출 동작 방지

        const username = document.getElementById("id").value;
        const password = document.getElementById("pwd").value;

        // AJAX 요청
        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        })
        .then(response => {
            if (response.ok) {
                // 헤더에서 JWT 토큰 가져오기
                const accessToken = response.headers.get("access");
                if (accessToken) {
                    alert("로그인 성공!");
                    localStorage.setItem("accessToken", accessToken); // 로컬 스토리지에 저장
                    window.location.href = "/"; // 메인 페이지로 이동
                } else {
                    throw new Error("토큰을 찾을 수 없습니다.");
                }
            } else {
                throw new Error("로그인 실패: 아이디 또는 비밀번호를 확인하세요.");
            }
        })
        .catch(error => {
            console.error(error);
            alert(error.message);
        });
    });
});
