/**
 * login.js
 */
 
 $(document).ready(function(){
 	$('#frmLogin').on('submit',function(){
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
				if(result=="sucess")
					message="로그인성공";
				else
					message="로그인실패";
				alert(message);
			},
			error:function(data, textStatus){
				console.log(data);
				console.log(textStatus);
				alert("전송실패");
			} 				  
 		});
 	
 	});
 });