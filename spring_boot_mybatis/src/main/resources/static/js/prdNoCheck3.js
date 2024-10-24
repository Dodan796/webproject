/**
 * prdNoCheck3
 * 상품 번호 중복 확인 : fetch() 자바 스크립트 내장함수, post 방식 사용 예제
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
 			alert("상품번호를 입력하세요");
 		} else {
 			//아래 구조 Promises 구조라고 함, fetch(url,data) : data에 post 방식으로 서버 요청시 파라미터 저장
 			//post 방식으로 파라미터 전달할 때 key:value 타입으로 전달{mothod:'post',headers:{key:value},body:서버로전달한파라미터값}
 			fetch("/product/prdNoCheck3/",{
 					method:'post',
 					headers:{
 						'Content-Type':'application/json'
 						},
					body:prdNo
 					}) //요청파라미터 body key에 담아서 보내면 응답객체 반환
 				.then(response => response.text()) //반환되는 데이터를 사용할 수 있는 형식으로 변환(text 형식으로 파싱)
 				.then(result => {
 					if(result == "available")
 						alert("사용가능한 번호 입니다.3");
					else 
						alert("사용할 수 없는 번호입니다.3"); 				
 				}) //변환된 데이터의 처리
 				.catch(err=>console.log(err)); //에러 발생시 대처
 		} //else 끝 	
 	
 	});//click 끝
 }; //onload 이벤트 핸들로 끝