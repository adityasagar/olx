<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.ProductVO" %>
    <%@ page import="com.UserVO" %>
<% String userId= request.getAttribute("userid")==null?"0":String.valueOf(request.getAttribute("userid"));
String name= request.getAttribute("name")==null?"":(String)request.getAttribute("name");
List<ProductVO> mostView = request.getAttribute("mostViewList")==null?new ArrayList<ProductVO>():(List<ProductVO>)request.getAttribute("mostViewList");
List<ProductVO> recent = request.getAttribute("recentList")==null?new ArrayList<ProductVO>():(List<ProductVO>)request.getAttribute("recentList");
UserVO user=request.getAttribute("user")==null?new UserVO():(UserVO)request.getAttribute("user");
System.out.println("userId"+userId+" name"+name);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Verizon Classified Portal</title>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="js/styles.css">
<script src="js/script.js"></script>
<style type="text/css">
.login{
	background-color: #e0e0e0;
	border-radius: 25px;
}
.title{
	background-color: red;
	color:#FFFFFF;
}
a.link{
	color:#FFFFFF;
}
.head{
	font-size:50px;
	color:red;
}
.movDiv{
	width:85%;
	overflow:auto;
	display:inline-block;
}
#det{
	display:none;
}
</style>
<script>
var userId = '<%=userId %>';
var name = '<%=name %>';
//alert("userId"+userId+", name"+name);
$(document).ready(function(){
	if(userId!="0" && name!=""){
		document.getElementById("det").style.display="block";
		document.getElementById("login").style.display="none";
	}
	document.getElementById("userid").value=userId;
	document.getElementById("name").value=name;
	var scrollPos=$('#cat').scrollLeft();
	$('#catLeft').on("click", function(){
		$('#cat').animate({scrollLeft:'-=200'},1000);
		scrollLeft=scrollLeft-200;
	});
	$('#catRight').on("click", function(){
		$('#cat').animate({scrollLeft:'+=200'},1000);
		scrollLeft=scrollLeft+200;
	});
	var scrollPos1=$('#recent').scrollLeft();
	$('#recLeft').on("click", function(){
		$('#recent').animate({scrollLeft:'-=200'},1000);
		scrollLeft=scrollLeft-200;
	});
	$('#recRight').on("click", function(){
		$('#recent').animate({scrollLeft:'+=200'},1000);
		scrollLeft=scrollLeft+200;
	});
	var scrollPos2=$('#view').scrollLeft();
	$('#viewLeft').on("click", function(){
		$('#view').animate({scrollLeft:'-=200'},1000);
		scrollLeft=scrollLeft-200;
	});
	$('#viewRight').on("click", function(){
		$('#view').animate({scrollLeft:'+=200'},1000);
		scrollLeft=scrollLeft+200;
	});
});

function addProducts(){
	/*document.getElementById("det").style.display="none";
	document.getElementById("login").style.display="none";
	document.getElementById("addProd").style.display="block";*/
	alert(userId);
	document.getElementById("type").value="addProj";
	var form = document.getElementById("form");
	form.action="ProductServlet";
	form.submit();
}

function prodDetails(type,value){
	alert("type-"+type+", value-"+value);
	document.getElementById("type").value=type;
	document.getElementById("value").value=value;
	var form = document.getElementById("form");
	form.action="ProductServlet";
	form.submit();
	
}
function clearForm(){
	$('form').trigger('reset');
}
function signUp(){
	window.location.replace("addUser.html");
}
function login(){
	window.location.replace("index.jsp");
}
</script>
</head>
<body>
<div>
<table><tr><th><img src="img/VerizonLogo.png"/></th><th>&nbsp;&nbsp;&nbsp;&nbsp;<label class="head">Vz Classified</label></th></tr></table>
<hr>
</div>
<div class="login" id="login">
<span><h2 class="title">Please Login to Proceed</h2></span>
<form id="form" action="RegisterServlet" method="post">
<input type="hidden" name="flag" value="login"/>
<input type="hidden" name="type" id="type"/>
<input type="hidden" name="value" id="value"/>
<input type="hidden" name="userid" id="userid"/>
<input type="hidden" name="name" id="name"/>
<table align="center" width="60%">
<tr><td align="center">
<input type="text" name="email" placeholder="Enter your Email ID"></td></tr>
<tr><td align="center"><input type="password" name="pwd" placeholder="Enter your Password"></td></tr>

<tr><td align="right"><input type="submit" value="Login">
<input type="button" value="Clear" onClick="javascript:clearForm();"></td></tr>
<tr><td align="right"><label>Not a User Yet? <a href="javascript:signUp();">Sign Up Here</a></label></td></tr>
</table>
</form>
</div>
<div class="det" id="det">
<div id="detHdr">
<table width="100%">
<tr><td width="50%"><input width="150" id="searchCrit" placeholder="Enter Product to Search" type="text"/><img width="25" height="25" src="img/search.png" onClick="javascript:prodDetails('search',$('#searchCrit').val());"></td><td align="right"><a href="javascript:addProducts();">Add New Products</a></td><td align="right"><%=name.toUpperCase() %><br>

<div id='cssmenu'>
<ul>
   <li ><a href='#'>View Profile</a>
      <ul>
         <li><a href='#'><%=user.getName() %></a>
         </li>
         <li><a href='#'><%= user.getContact()%></a>
         </li>
         <li><a href='#'><%= user.getEmail()%></a>
         </li>
         <li><a href='javascript:login();'>SignOut </a>
         </li>
         
      </ul>
   </li>
</ul>
</div>
</table>
<hr>
</div>
<span><h2 class="title">Categories</h2></span>
<img id="catLeft" src="img/left.png" width="80" height="100">
<div id="cat" class="movDiv">
<table>
<tr>
<td><img src="img/Electronics.png" width="300" height="150" onClick="javascript:prodDetails('category','Electronics');"></td>
<td><img src="img/vehicles.jpg" width="300" height="150" onClick="javascript:prodDetails('category','Vehicles');"></td>
<td><img src="img/furnishing.jpg" width="300" height="150" onClick="javascript:prodDetails('category','Home & Furniture');"></td>
<td><img src="img/animals.jpg" width="300" height="150" onClick="javascript:prodDetails('category','Animals');"></td>
<td><img src="img/sports.jpg" width="300" height="150" onClick="javascript:prodDetails('category','Books,Sports & Hobbies');"></td>
<td><img src="img/fashion.jpg" width="300" height="150" onClick="javascript:prodDetails('category','Fashion & Beauty');"></td>
<td><img src="img/kids.jpg" width="300" height="150" onClick="javascript:prodDetails('category','Kids & Baby Products');"></td>
<td><img src="img/service.png" width="300" height="150" onClick="javascript:prodDetails('category','Services');"></td>
<td><img src="img/realEstate.gif" width="300" height="150" onClick="javascript:prodDetails('category','Real Estate');"></td>
<td><img src="img/other.png" width="300" height="150" onClick="javascript:prodDetails('category','Others');"></td>
</tr>
<tr>
<td align="center"><a href="javascript:prodDetails('category','Electronics');">Electronics</a></td>
<td align="center"><a href="javascript:prodDetails('category','Vehicles');">Vehicles</a></td>
<td align="center"><a href="javascript:prodDetails('category','Home & Furniture');">Home & Furniture</a></td>
<td align="center"><a href="javascript:prodDetails('category','Animals');">Animals</a></td>
<td align="center"><a href="javascript:prodDetails('category','Books,Sports & Hobbies');">Books,Sports & Hobbies</a></td>
<td align="center"><a href="javascript:prodDetails('category','Fashion & Beauty');">Fashion & Beauty</a></td>
<td align="center"><a href="javascript:prodDetails('category','Kids & Baby Products');">Kids & Baby Products</a></td>
<td align="center"><a href="javascript:prodDetails('category','Services');">Services</a></td>
<td align="center"><a href="javascript:prodDetails('category','Real Estate');">Real Estate</a></td>
<td align="center"><a href="javascript:prodDetails('category','Others');">Others</a></td>
</tr>
</table>
</div>
<img id="catRight" src="img/right.png" width="80" height="100">
<span><h2 class="title"><a class="link" href="javascript:prodDetails('recent','30');">New Arrivals</a></h2></span>
<img id="recLeft" src="img/left.png" width="80" height="100">
<div id="recent" class="movDiv">
<table>
<tr>
<% for(int i=0;i<recent.size();i++){ %>
<td><img src="img/blank2.jpg" width="300" height="150" /></td>
<%} %>
</tr><tr>
<% for(int i=0;i<recent.size();i++){ %>
<td align="center"><%=recent.get(i).getName() %></a></td>
<%} %>
</tr>
</table>
</div>
<img id="recRight" src="img/right.png" width="80" height="100">
<span><h2 class="title"><a class="link" href="javascript:prodDetails('mostviewed','30');">Most Popular</a></h2></span>
<img id="viewLeft" src="img/left.png" width="80" height="100">
<div id="view" class="movDiv">
<table>
<tr>
<% for(int i=0;i<mostView.size();i++){ %>
<td><img src="img/blank2.jpg" width="300" height="150" /></td>
<%} %>
</tr><tr>
<% for(int i=0;i<mostView.size();i++){ %>
<td align="center"><%=mostView.get(i).getName() %></a></td>
<%} %>
</tr>
</table>
</div>
<img id="viewRight" src="img/right.png" width="80" height="100">
</div>
</body>
</html>