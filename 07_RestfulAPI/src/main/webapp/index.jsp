<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

</head>
<body>

	<h1>휴대전화 목록</h1>
	
	<table border="1">
		<thead>
			<tr>
				<th>모델번호</th>
				<th>모델이름</th>
				<th>가격</th>
				<th>제조사명</th>
			</tr>
		</thead>
		<tbody id="list">
		</tbody>
	</table>
	
	<h1>휴대전화 정보</h1>
	<form id="phoneFrm">
		모델번호 : <input type="text" name="num" id="num"><br>
		모델명 : <input type="text" name="model" id="model"><br>
		가격 : <input type="text" name="price" id="price"><br>
		제조사 : 
		<select id="vcode" name="vcode">
			<option value="10">삼성</option>
			<option value="20">애플</option>
		</select>
		<br>
		<input type="button" value="추가하기" id="insert">
		<input type="button" value="수정하기" id="update">
		<input type="button" value="삭제하기" id="delete">
	</form>

	<script>
		function list() {
			$.ajax({
				url: 'http://localhost:8080/api/phone',
				type: 'get',
				success: function(data) {
					let html = '';
					console.log(data);
					for(let phone of data) {
						// console.log(phone);
						html += '<tr>' + 
									'<td class="num">'+ phone.num + '</td>' +
									'<td>'+ phone.model + '</td>' +
									'<td>'+ phone.price + '</td>' +
									'<td>'+ phone.company.vendor + '</td>' +
								'</tr>';
						
					}					
					$('#list').append(html);	
					//$('#list').html(html);로 넣으면 리스트 초기화 안해도 됨			
				},
				error: function() {	
					console.log('시스템 상 에러 발생!');
				}
			});
		}
		
		list();
		
		// 상세조회
		$('#list').on('click', '.num', function() {
			console.log($(this).text());
			const num = $(this).text();
			
			$.ajax({
				url: 'http://localhost:8080/api/phone/' + num,
				type: 'get',
				success: function(data) {
					$('#num').val(data.num);
					$('#model').val(data.model);
					$('#price').val(data.price);
					$('#vcode').val(data.vcode);
					console.log(data);
					
				
				},
				
				error: function() {
					console.log('시스템 상 에러 발생!');
				}
			});
		});
		
		// 추가
		$('#insert').on('click', function() {
		    let phone = {
		        num: $('#num').val(),
		        model: $('#model').val(),
		        price: $('#price').val(),
		        vcode: $('#vcode').val()
		    };
		
		    $.ajax({
		        url: 'http://localhost:8080/api/phone',
		        type: 'POST',
		        data: JSON.stringify(phone), 
		        contentType: 'application/json', 
		        success: function(data) {
		        	$('#list').html('');
		        	list();
		          
		        },
		        error: function() {
		            console.log('추가 실패!');
		        }
		    });
		});
		
		
		// 수정
        $('#update').on('click', function () {
        	 let phone = {
     		        num: $('#num').val(),
     		        model: $('#model').val(),
     		        price: $('#price').val(),
     		        vcode: $('#vcode').val()
     		    };
     		
     		    $.ajax({
     		        url: 'http://localhost:8080/api/phone',
     		        type: 'put',
     		        data: JSON.stringify(phone), 
     		        contentType: 'application/json', 
     		        success: function(data) {
     		        	$('#list').html('');
     		        	list();
     		          
     		        },
     		        error: function() {
     		            console.log('추가 실패!');
     		        }
     		    });
     		});
        
        // 삭제
        $('#delete').on('click', function () {
            let num = $('#num').val();

            $.ajax({
                url: 'http://localhost:8080/api/phone/' + $('#num').val(),
                type: 'delete',
                success: function (data) {
                   
                	$('#list').html('');
                    list();
                    
                    $('#num').val('');
                    $('#model').val('');
                    $('#price').val('');
                    $('#vcode').val('10');
                    
                },
                error: function () {
                    console.log('삭제 실패!');
                }
            });
        });
        
	</script>
	
</body>
</html>