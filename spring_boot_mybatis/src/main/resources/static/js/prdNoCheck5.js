/**
 * prdNoCheck5
 * 상품 번호 중복 확인 : axios() 자바 스크립트 내장함수(spring의 의존성 사용), post 방식 사용 예제
 * ajax는 jquery 메소드이고 jquery 코드를 쓴다면 비동기 통신은 ajax 를 선택한다
 * jquery가 원할하지 않으면 js로 되어 있는 코드를 사용해야 함 : 아래 코드는 java script코드로만 구성됨
 */
 
 window.onload = function(){
 
 	//중복확인 버튼 객체 참조 추출
 	let prdNoCheckBtn = document.getElementById("prdNoCheckBtn");
 
 	//버튼에 이벤트 핸들러 함수 연결
 	prdNoCheckBtn.addEventListener("click",()=>{
 		event.preventDefault();

 		//상품번호 입력 값 추출
 		let prdNo = document.getElementById("prdNo").value;
 		
 		if(prdNo==""){
 			alert("상품번호를 입력하세요5");
 		} else {
			//axios 활용 post방식 요청
			const data={"prdNo":prdNo};
			axios.post("/product/prdNoCheck5",data )
				.then(function(response){ //response 객체를 통해서 서버가 전달한 data가 전송됨 : response.data
					console.log(response);
					if(response.data == "available"){
						alert("사용 가능한 번호 입니다.5");
					}else {
						alert("사용할 수 없는 번호입니다.5");
					}
				}
				)//then끝
				.catch((error) => {
					console.log(error.response); //서버가 전송한 에러 메시지 콘솔에 출력
				})//catch 끝

 		} //else 끝  	
 	});//click 끝
 }; //onload 이벤트 핸들러 끝