<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
function change(id) {
	// 특정 id값의 태그에서 value 속성의 값을 읽어옴
	var v = $("#"+id).attr("value");
	
	// 출력해 봄
	console.log(v);
	
	
	// 1이면 0으로, 0이면 1로 바꿈
	if (v==1) {
		v=0;
	} else {
		v=1;
	}
	
	// 해당 태그의 value 속성에 바뀐 값으로 적용함
	$("#"+id).attr("value", v);
	
}

</script>
</head>

<body>
<i value='0' id='itag-1' onclick="change('itag-1')">111</i>

</body>
</html>