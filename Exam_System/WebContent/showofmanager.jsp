<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试题库管理系统</title>
<style>
	.divcss6{color:white; font-size:30px;text-align:center; left: 600px; position: absolute; top: 200px;}
	.button {

            background-color:transparent;
            background-repeat:no-repeat;
            border:none;
            cursor:pointer;
            overflow: hidden;
        }
</style>
</head>
<body background="images/p4.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;" >
<!-- <form action="fdb.html"  method="post">-->
<br/><br/>
<div>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font  size="6" face="方正粗黑宋简体" color=white>欢迎进入管理员试题库管理系统</font>
<font margin-right:50px>1231231313</font>
<br/><br/>
</div>
<table align="center" width="1500" height="600" border="2" bordercolor:rgba(0,0,0,0.9)>
<tr bgcolor:rgba(0,0,0,0.9)>
 <th width="200px" height="50px"><font  size="4" face="方正粗黑宋简体" color=white>管理员功能</th>
 <th width="1000px" height="50px">&nbsp;</th>
 
</tr>
<tr bgcolor:rgba(0,0,0,0.9)>
<th width="200px" height="500px" rowspan="13" colspan="1">
 	 <a href=Finsert.jsp style="color:white; text-decoration: none;">新添试题</a><br/>
 <br />
 <a href=Fselect.html style="color:white; text-decoration: none;">查找试题</a><br/>
 <br />
 <a href=Fdelete.jsp style="color:white; text-decoration: none;">删除试题</a><br/>
 <br/>
 <a href="fdb.html" style="color:white; text-decoration: none;">数据库备份</a><br/>
 <br/>
  <a href="fdbd.html" style="color:white; text-decoration: none;">数据库恢复</a>
 <!--  <input class="button" type="button" value="数据库更新" onclick="document.form1.Submit();"
		                          style="font-size:19px; color: white; left: 90px; position: absolute; top: 520px;
		                          height: 30px;width: 100px;"/>-->
 </th>
 <th width="1000px" height="500px" rowspan="13" colspan="13"></th>
</tr>

</table>
</body>
</html>