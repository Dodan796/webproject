document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault(); // 기본 폼 동작 방지

    const username = document.getElementById('id').value;
    const password = document.getElementById('pwd').value;

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
                const token = response.headers.get('Authorization').replace('Bearer ', '');
                alert('로그인 성공!');
                localStorage.setItem('token', token); // JWT 저장
                window.location.href = '/'; // 리다이렉트
            } else {
                throw new Error('로그인 실패: 아이디 또는 비밀번호를 확인하세요.');
            }
        })
        .catch(error => {
            alert(error.message);
        });
});
