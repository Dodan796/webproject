/**
 *  상품 검색을 위한 js, ajax post 방식 요청
 *  데이터(form 태그 내 여러개의 값을 쿼리 스트링 방식으로 한번에 추출하고 변환하는 기능 serialize() 사용)
 *  type=prdName&keyword=모니터 와 같은 형식으로 파라미터 구성
 */
 
 $(document).ready(function(){
 
 	//검색 폼에 submit 이벤트가 발생하면 처리 함수
 	$('#prdSearchFrm1').on('submit', function(){
 
 		event.preventDefault();
 		
 		//폼에 입력된 모든 데이터 쿼리스트링변환
 		//이 시점 this는 prdSearchFrm1이고 form태그 내 모든 태그객체의 value를 쿼리스트링으로 변환, 태그내 name 속성 필수
 		//연결된 productSearchForm1.jsp를 예시로
 		//한개의 form태그 내 name 속성이 있는 2개의 태그에 대해서 쿼리 스트링 방식으로 데이터 집합을 생성해 줌
 		//type=prdName&keyword=모니터 과 같이 구성해주는 함수
 		let formData = $(this).serialize(); //서버측으로 전송할 예정
 		console.log(formData);
 		//필수 입력값 입력 확인
 		let keyword = $("#keyword").val();
 		let type = $("#type").val();
 		
 		if(keyword == "" || type == ""){
 			alert("검색조건과 검색어 입력하세요");
 		} else {
 		
 			$.ajax({//서버와 비동기통신
 				type:"post",
 				url:"/product/productSearch1",
 				data:formData, //formData는 key=value 형식으로 2개 data가 저장됨, 컨트롤러는 HashMap으로 받는게 효율적인
 				success:function(result){ //서버에서 컨트롤러 통해 ArrayList 객체가 반환, length 사용 가능한 배열로 반환됨
 					//반환된 객체를 이용해서 id가"searchResultBox"인 div 태그내에 검색되어서 나온 결과를 table 태그 이용해서 삽입
 					//해당 div내 내용 지우기
 					$('#searchResultBox').empty();//기존 태그 삭제
 					$('#searchResultBox').append('<table id="resultTable" border="1" width="500">' +
 												 	'<tr><th>상품번호</th><th>상품명</th><th>상품가격</th>' +
 													'<th>제조사</th><th>재고</th><th>제조일</th><th>사진</th></tr>'); //id가"searchResultBox"인 div태그 내에 새로운 태그를 추가
					if(result==""){//검색 결과가 없는경우
						$('#resultTable').append('<tr align="center"><td colspan="7">찾는 상품이 없습니다</td></tr>');
					} else {//검색 결과가 있는 경우
							console.log(result);
							for(let i=0; i<result.length; i++){
								//제조일 날짜 데이터를 js 날짜 객체로 변경
								let prd_date= new Date(result[i].prdDate); //javascript Date 객체 구성
								let year = prd_date.getFullYear(); //4자리 연도 반환
								let month = (prd_date.getMonth() + 1).toString().padStart(2,'0'); //2자리월
								let date = (prd_date.getDate().toString().padStart(2,'0'));//2자리일
								let prdDate=`${year}-${month}-${date}`;
								$('#resultTable').append('<tr><td>'+result[i].prdNo +'</td><td>' +
																	result[i].prdName + '</td><td>' +
																	result[i].prdPrice + '</td><td>' +
																	result[i].prdCompany + '</td><td>' +
																	result[i].prdStock + '</td><td>' +
																	prdDate + '</td><td>' +
																	'<img src="/mybatis/prd_images/'+result[i].prdNo +
																	'.jpg" width="30" height="20"></td></tr>');		
							
								} //for 끝
												
					}//else끝
					//검색결과 상관없이 table 태그 닫기
					$('#searchResultBox').append('</table>');													
 				},//success 긑
 				error:function(){
 					alert("실패");
 				}
 			});//ajax 끝
 		
 		}//else 끝
 	});//on끝
 	
 });//ready끝