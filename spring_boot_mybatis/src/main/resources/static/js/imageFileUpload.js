/**
 * 이미지 파일 업로드 후 업로드된 이미지를 화면에 출력해서 결과로 처리
 * ajax post 방식으로 파일을 서버에 전송하고, 현재 페이지에 전송한 이미지를 출력
 */
 
 $(document).ready(function(){
 
 	$("#imageFileForm").on("submit", function(){
 		//폼이 submit 되지 않도록 기본 기능 중단
 		event.preventDefault();
 		
 		//폼데이터 읽어오기(멀티파트폼데이터를 전송해야 하므로 객체 생성)
 		//new FormData(document.imageFileForm);
 		let formData = new FormData($('#imageFileForm')[0]);
 		//업로드된 파일명 알아오기 : imageBox에 이미지 출력하기위해
 		//c:\fakepath\cat.jpg 경로및파일명 : c: | fakepath | cat.jpg =>pop() => cat.jpg
 		let fileName=$('#uploadFile').val().split("\\").pop();
 
 		//서버에 전송하고 결과 받아서 처리
 		//전송된 이미지를 서버측으로 요청해서 출력
 		$.ajax({
 			type:"post",
 			url:"imageFileUpload",
 			enctype:"multipart/form-data",
 			processData:false,
 			contentType:false,
 			data:formData,
 			success:function(result){
 				if(result=="success"){
 					// cat.jpg를 업로드 했다면
 					//<img src="/images/cat.jpg" ...> 가 div 태그내에 삽입
 					//브라우저가 해석하면서 /images/cat.jpg 이미지 파일 서버에게 요청
 					$('#imageBox').html('<img src="/images/' + fileName +'" width="400" height="300">'); 				
 				} 				
 			},
 			error:function(){
 				alert("실패");
 			},
 			complete:function(){
 			
 			}	
 		
 		}); //ajax 종료
 	
 	});//on 끝
 
 });//ready 끝