<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REST</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
<% request.setCharacterEncoding("UTF-8") ;%>
   $(document).ready(function(){
      //ajaxSetup():  실행될  AJAX 요청에 대한 기본 속성을 정의해 재사용 
      //jqXHR : jQuery XMLHttpRequest의 약자(XML뿐만 아니라 모든 유형의 데이터를 검색하는 데 사용
      //Jquery Ajax로 호출하기 전에 serialize를 해주면 form안에 값들을 한번에 전송 가능한  data로 만들 수 있다
      $.ajaxSetup({
         success:function(result){               
            alert(result);
         },
         error: function (jqXHR) {
                alert("jqXHR status code:"+jqXHR.status+" message:"+jqXHR.responseText);
            }
      });//ajaxSetup
      
      $("#testGetListBtn").click(function(){   
         $.ajax({
            type:"get",
            url:"products",            
            success:function(productList){   
               $("#listView").empty(); //.empty():객체내부 비움
               $.each(productList,function(i,product){
                  $("#listView").append(product.id+" "+product.name+" "+product.maker+" "+product.price+"<br>").css("background","pink");
               });               
            }
         });//ajax
      });//click
      
      $("#testGetBtn").click(function(){   
         $.ajax({
            type:"get",
            url:"products/"+$("#pid").val(),            
            success:function(product){               
               alert(product.id+" "+product.name+" "+product.maker+" "+product.price);
            }
         });//ajax
      });//click   
      $("#testCreateBtn").click(function(){         
         $.ajax({
            type:"post",
            url:"products",
            data:$("#createProductForm").serialize()
         }).done(function(){ // done - success 와 동일
            $("#createProductForm")[0].reset();
         });//ajax
      });//click   
      $("#testPutBtn").click(function(){         
         $.ajax({
            type:"put",
            url:"products?"+$("#updateProductForm").serialize()
         }).always(function(){ // always 요청에 대한 처리가 success or fail 상관없이 항상 실행 
            $("#updateProductForm")[0].reset();
         });
      });//click   
      
      $("#testDeleteBtn").click(function(){         
         $.ajax({
            type:"delete",
            url:"products/"+$("#deleteId").val()
         });//ajax
      });//click      
   });//ready
</script>
<style type="text/css">
   .restImg{
      width: 600px;
      height: 400px      
   }
</style>
</head>
<body>
<h3>REST  적용 웹어플리케이션 구현  </h3>
   <ul> 
      <li>
      HTTP Request Method <br><br>
      GET : 리스트 조회 <input type="button" value="testGetListBtn" id="testGetListBtn"><br>
      <div id="listView"></div>    <br> <br>
      GET : 조회 <input type="button" value="testGetBtn" id="testGetBtn"><input type="text" id="pid"><br><br> 
   POST :생성 <input type="button" value="testCreateBtn" id="testCreateBtn"><br>
   <form id="createProductForm">
      상품번호 <input type="text" name="id" size="5">
      상품명 <input type="text" name="name" size="5"> 제조사 <input type="text" name="maker" size="5"> 가격 <input type="number" name="price">
   </form>
   <br><br>
   PUT : 수정 <input type="button" value="testPutBtn" id="testPutBtn"><br>
      <form id="updateProductForm">
      상품번호 <input type="text" name="id" size="3" id="updateId"> 
      상품명 <input type="text" name="name" size="5"> 
      제조사 <input type="text" name="maker" size="5"> 
      가격 <input type="number" name="price">
   </form>
   <br>
   DELETE : 삭제 <input type="button" value="testDeleteBtn" id="testDeleteBtn">
   <input type="text" id="deleteId">
      </li>  
   </ul>
   <img class="restImg" src="image/img.png">
   
</body>
</html>