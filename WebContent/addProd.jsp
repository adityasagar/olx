<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% String userId= request.getAttribute("userid")==null?"":(String)request.getAttribute("userid");
String name= request.getAttribute("name")==null?"":(String)request.getAttribute("name");
Boolean inserted= request.getAttribute("inserted")==null?false:(Boolean)request.getAttribute("inserted");
%>
 <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add product</title>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<style type="text/css">
.login{
	background-color: #e0e0e0;
	border-radius: 25px;
}
.title{
	background-color: red;
	color:#FFFFFF;
}
.head{
	font-size:50px;
	color:red;
}
</style>
<script>
var userId = '<%=userId %>';
var name = '<%=name %>';
$(document).ready(function(){
	$("#userid").val(userId);
	$("#name").val(name);
	<% if(inserted){ %>
		alert("Thanks! Product added to the Catalog!");
	<%}%>
});
function clearForm(){
	$('form').trigger('reset');
}
</script>
</head>
<body>
<div>
<table width="100%"><tr><th align="left"><img src="img/VerizonLogo.png"/></th><th width="50%" align="left"><label class="head">Vz Classified</label></th><th width="30%" align="right"><%=name.toUpperCase() %><br><a href="javascript:viewUser();">View Profile</a></th></tr></table>
<hr>
</div>
<span><h2 class="title">Just a brief Details for others to know about your Product...</h2></span>
<div class="login" id="login">
<form name='form' action="ProductServlet" method="post">
<input type="hidden" name="type" value="newProd"/>
<input type="hidden" name="userid" id="userid"/>
<input type="hidden" name="name" id="name"/>
<table align="left" width="100%">
<tr>
<td align="center">
<input type="text" name= "title" placeholder="Title of the Product"></td>
</tr>

<tr>
<td align="center">
<input name= "price" placeholder="Price for the product"></td>
</tr>
<tr>
<td align="center">
 <select name= "Category">
  <option value="">Select Category</option>
  <option value="Electronics">Electronics</option>
  <option value="Vehicles">Vehicles</option>
  <option value="Home & Furniture">Home & Furniture</option>
  <option value="Animals">Animals</option>
  <option value="Books,Sports & Hobbies">Books,Sports & Hobbies</option>
  <option value="Fashion & Beauty">Fashion & Beauty</option>
  <option value="Kids & Baby Products">Kids & Baby Products</option>
  <option value="Services">Services</option>
  <option value="Real Estate">Real Estate</option>
  <option value="Others">Others</option>
</select> </td>
</tr>
<tr>
<td align="center">
<input type="radio" name="reason" value="rent">Rent&nbsp;&nbsp;<input type="radio" name="reason" value="sell">Sell
</td>
</tr>
<tr>
<td align="center">
<textarea name= "desc" placeholder="Description"></textarea></td>
</tr>

<tr><td align="center"><input type="submit" value="Submit">&nbsp;&nbsp;
<input type="button" value="Clear" onClick='javascript:clearForm();'></td></tr>
</table>
</form>
</div>
</body>
</html>
 
 

