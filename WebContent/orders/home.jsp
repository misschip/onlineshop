<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    

<%@ include file="../include/header.jsp" %>

<div class="container">


	<div class="row">	
	
	<%-- 바깥쪽 --%>
		<div class="media border p-3">
		  <img src="img_avatar3.png" alt="John Doe" class="mr-3 mt-3 rounded-circle" style="width:60px;">
		  <div class="media-body">
		    <h4>John Doe <small><i>Posted on February 19, 2016</i></small></h4>
		    <p>Lorem ipsum...</p>
		    
		    <%-- 안쪽 --%>
		    <div class="media p-3">
		      <img src="img_avatar2.png" alt="Jane Doe" class="mr-3 mt-3 rounded-circle" style="width:45px;">
		      <div class="media-body">
		        <h4>Jane Doe <small><i>Posted on February 20 2016</i></small></h4>
		        <p>Lorem ipsum...</p>
		      </div>
		    </div> 
		    <%-- 안쪽 끝 --%>
		  </div>
		</div>

	<%-- 바깥쪽 끝 --%>
	
	</div>
	
	

</div>

<%@ include file="../include/footer.jsp" %>