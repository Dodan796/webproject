/**
 * 상품번호 중복 확인 js
 * ajax 활용한 비동기 통신
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
 				type:"post",
 				url:"/product/prdNoCheck", //상품정보는 db에 저장되어 있으므로 컨트롤러는 db까지 전달 사용여부 확인해야 함
 				data:{"prdNo":prdNo}, //컨트롤러에서 파라미터 전달받을 때 : prdNo를 받아줘야 함
 				dataType:'text',
 				success:function(result){
 					if(result=="available"){
 						alert("사용 가능한 번호 입니다.0");
 					}else{
 						alert("사용 불가능한 번호 입니다.0");
 					}
 				},//success 종료
 				error:function(data, textStatus){
 					alert("전송 실패");
 				}//error 종료
 			}); //ajax 종료
 		
 		}//if 끝
 	});//on 종료
 
 });//ready 종료