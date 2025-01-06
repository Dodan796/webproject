$(function () {
    // 이메일 인증 버튼 클릭 시 동작
    $("#verificationEmail").on("click", function (event) {
        event.preventDefault();

        var userEmail = $('#userEmail').val();

        if (validateEmail(userEmail)) { // 이메일 형식 검증
            $.ajax({
                type: "POST",
                url: "/member/emailverify",
                data: { "userEmail": userEmail },
                success: function (data) {
                    console.log("서버에서 받은 인증코드입니다: " + data);
                    alert("해당 이메일 주소로 인증코드가 전송되었습니다.");

                    // 인증번호 입력 필드 및 타이머 추가
                        $('#verificationEmailCode').html(`
                            <label>인증코드 입력</label>
                            <div id="timer" style="color: green; font-weight: bold; margin-top: 5px;"></div>
                            <input type="text" name="verificationEmailCode" id="verificationEmailCodeInput" required />
                            <button type="button" id="verificationEmailCodeBtn">인증하기</button>
                        `);

                    // 타이머 설정
                    var timerDuration = 120; // 2분 (120초)
                    var timerInterval = setInterval(function () {
                        var minutes = Math.floor(timerDuration / 60);
                        var seconds = timerDuration % 60;

                        // 초 단위를 두 자리로 포맷팅
                        var formattedTime = `${minutes}:${seconds.toString().padStart(2, '0')}`;

                        $('#timer').text(`남은 시간: ${formattedTime}`);

                        if (timerDuration <= 0) {
                            clearInterval(timerInterval);
                            $('#timer').text("인증코드가 만료되었습니다.").css({"color": "red"});
                        }

                        timerDuration--;
                    }, 1000);
                },
                error: function () {
                    alert("인증코드 요청 중 오류가 발생했습니다. 다시 시도해주세요.");
                }
            });
        } else {
            alert("올바른 이메일 형식이 아닙니다. 다시 입력해주세요.");
        }
    });


    // 인증번호 확인 버튼 클릭 시 동작
    $(document).on("click", "#verificationEmailCodeBtn", function () {
        var userEmail = $('#userEmail').val(); // 이메일 입력값
        var userInputCode = $('#verificationEmailCodeInput').val(); // 인증코드 입력값

        if (!userInputCode) {
            alert("인증코드를 입력해주세요.");
            return;
        }

        console.log("사용자가 입력한 이메일: ", userEmail);
        console.log("사용자가 입력한 인증코드: ", userInputCode);

        $.ajax({
            type: "POST",
            url: "/member/verifyCode",
            data: { userEmail: userEmail, code: userInputCode },
            success: function (isVerified) {
                if (isVerified) {
                    alert("이메일 인증이 완료되었습니다!");
                    $("#verificationEmailCodeBtn").prop("disabled", true); // 인증 버튼 비활성화
                    $("#verificationEmailCodeInput").prop("disabled", true); // 입력 필드 비활성화
                    $("#timer").text("인증이 완료되었습니다.").css({ "color": "blue", "font-weight": "bold" }); // 타이머 자리에 메시지 출력
                } else {
                    alert("인증번호가 일치하지 않습니다. 다시 입력해주세요.");
                }
            },
            error: function () {
                alert("인증번호 확인 중 오류가 발생했습니다. 다시 시도해주세요.");
            }
        });
    });

    // 이메일 중복체크
    $("#userEmail").on("change", function () {
        var userEmail = $(this).val();

        $.ajax({
            type: "GET",
            url: "/member/checkEmail",
            data: { "userEmail": userEmail },
            dataType: "text",
            success: function (data) {
                console.log(data);
                if (data == "0") {
                    $('#emOk').text("중복된 이메일입니다.").css("color", "red");
                } else {
                    $('#emOk').text("사용 가능한 이메일입니다.").css("color", "green");
                }
            },
            error: function () {
                alert("이메일 중복 체크 중 오류가 발생했습니다. 다시 시도해주세요.");
            }
        });
    });
});

// 이메일 유효성 검증 함수
function validateEmail(email) {
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // 이메일 형식 정규식
    return emailRegex.test(email);
}