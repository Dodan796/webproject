/**
 *  login2.js 
 *  로그인 성공 후 다른 페이지로 화면 전환
 */
 
  $(document).ready(function(){
 	$('#frmLogin2').on('submit',function(){
 		event.preventDefault();//ajax통한 비동기 통신 진행하므로 submit은 발생되지 않게 설정
 	
 		let user_id = $('#user_id').val();
 		let user_pw = $('#user_pw').val();
 		
 		$.ajax({
 			type:"post",
 			url:"/mybatis/login",
 			data:{"id":user_id, 
 				  "pw":user_pw},//서버로 보낼 data
			dataType:"text", //서버에서 클라이언트로 반환하는 데이터 타입
			success:function(result){
				if(result=="sucess"){
					alert("로그인성공 \n 상품 조회 화면으로 이동합니다.");
					location.href="/mybatis/product/listAllProduct";
					} //a태그 통한 링크 요청의 스크립트 코드
				else
					alert("로그인실패");
			
			},
			error:function(data, textStatus){
				console.log(data);
				console.log(textStatus);
				alert("전송실패");
			},
			complete:function(data, textStatus){
			
			} 				  
 		});
 	
 	});
 });