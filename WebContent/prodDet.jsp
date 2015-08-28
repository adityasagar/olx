<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ProductVO"%>

<%
String userId= request.getAttribute("userid")==null?"":(String)request.getAttribute("userid");
String name= request.getAttribute("name")==null?"":(String)request.getAttribute("name");
String type= request.getAttribute("type")==null?"":(String)request.getAttribute("type");
String value= request.getAttribute("value")==null?"":(String)request.getAttribute("value");
List<ProductVO> results = request.getAttribute("results")==null?new ArrayList<ProductVO>():(List<ProductVO>)request.getAttribute("results");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<title>Vz Employee Products</title>
<link rel="stylesheet" href="js/styles.css">
<script src="js/script.js"></script>
<style type="text/css">
.title{
	background-color: red;
	color:#FFFFFF;
}
.head{
	font-size:50px;
	color:red;
}
#detDiv{
	width:100%;
	background-color: #e0e0e0;
	border-radius: 25px;
}
.addDet{
	display:none;
}
</style>
<script>
$('.prod').on("click",function(){
	var id=(this).attr("id");
	id="#addDet"+id;
	$('.addDet').hide();
	$(id).show();
});
function viewUser(){
	$('.profile').menu({
		items:".menu"
	})
}
</script>
</head>
<body>
<div>
<table><tr><th><img src="img/VerizonLogo.png"/></th><th>&nbsp;&nbsp;&nbsp;&nbsp;<label class="head">Vz Classified</label></th></tr></table>
<hr>
</div>
<div id="detHdr">
<table width="100%">
<tr><td width="50%"><input width="150" id="searchCrit" placeholder="Enter Product to Search" type="text"/><img width="25" height="25" src="img/search.png" onClick="javascript:prodDetails('search',$('#searchCrit').val());"></td><td align="right"><a href="javascript:addProducts();">Add New Products</a></td><td align="right"><%=name.toUpperCase() %><br>
<!--  <ul class="profile" onClick="javascript:viewUser();"><li>View Profile</li></ul>-->
<div id='cssmenu'>
<ul>
   <li ><a href='#'>View Profile</a>
      <ul>
         <li><a href='#'><%=name %></a>
         </li>
         <li><a href='#'>Contact</a>
         </li>
      </ul>
   </li>
</ul>
</div>
</td></tr>
</table>
<hr>
</div>
<div id="head">
<%if(type.equalsIgnoreCase("mostviewed")){ %>
<span><h2 class="title"> Most Popular Products</h2></span>
<% }else if(type.equalsIgnoreCase("recent")){ %>
<span><h2 class="title"> Recently Added Products</h2></span>
<% }else if(type.equalsIgnoreCase("category")){ %>
<span><h2 class="title"> <%= value %> Category Products</h2></span>
<% }else{ %>
<span><h2 class="title"> Products matching Search Criteria: <%= value %></h2></span>
<%} %>
</div>
<div>
<% for(int i=0; i<results.size(); i++){%>
<div id="detDiv">
<div class="prod" id=<%=i%>>
<table width="90%" align="center"><tr><td rowspan="2" width="15%" align="center"></td><td rowspan="2" align="center" width="50%"><%=results.get(i).getName() %></td><td width="30%" align="center"><%=results.get(i).getCategory() %></td></tr>
<tr><td><%= results.get(i).getReason() %></td></tr></table>
</div>
<div class="addDet" id="addDet"+<%=i%>></div>
<table width="90%" align="center"><tr><td rowspan="2" width="15%"align="center"></td><td rowspan="2" align="center" width="50%"><%=results.get(i).getDescription() %></td><td width="30%" align="center"><%=results.get(i).getPrice() %></td></tr>
<tr><td><input type="button" value="Contact Seller"/></td></tr></table>
</div><br>
<%} %>
</div>
<div class="menu">This is a menu item</div>
</body>
</html>