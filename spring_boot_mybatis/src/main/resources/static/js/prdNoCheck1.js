/**
 * 상품번호 중복 확인 js
 * ajax 활용한 비동기 통신 :get 방식
 */
 
 $(document).ready(function(){
 	
 	$('#prdNoCheckBtn').on('click',function(){
 		event.preventDefault(); //버튼 기본 이벤트 중단
 		
 		let prdNo = $('#prdNo').val(); //상품번호 input 태그 내 값을 추출
 	
 		if(prdNo == ""){ //사용자가 상품번호 입력 없이 중복확인 버튼을 클릭했을 때
 			alert("상품번호를 입력하세요");
 			return false; //현재 화면으로 반환
 		} else {//상품번호를 서버로 전송해서 사용여부 반환(상품번호만 보내고 나머지 form data는 전송하지 않으므로 비동기 통신)
 		
 			$.ajax({
 				type:"get",
 				url:"/product/prdNoCheck1/"+prdNo, //get 방식으로 요청할때 url에 data 포함시켜서 요청
 				//data:{"prdNo":prdNo}, //get 방식인 경우 data key 사용하지 않음
 				dataType:'text',
 				success:function(result){
 					if(result=="available"){
 						alert("사용 가능한 번호 입니다.1");
 					}else{
 						alert("사용 불가능한 번호 입니다.1");
 					}
 				},//success 종료
 				error:function(data, textStatus){
 					alert("전송 실패");
 				}//error 종료
 			}); //ajax 종료
 		
 		}//if 끝
 	});//on 종료
 
 });//ready 종료