<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>

	<%--静态包含base标签，css样式、jquery标签--%>
	<%@include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {

			//给验证码的图片绑定单机事件
			$("#code_img").click(function () {
				//在事件响应的function函数中有一个this对象，表示当前正在响应的dom对象
				//src属性表示验证码img标签的图片路径。它可读，可写
				this.src="${basePath}kaptcha.jpg?d="+new Date();
			});

			
		$("#sub_btn").click(function () {
			//验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
			//1.获取用户名输入框里的内容
			var $username = $("#username").val();
			//2.创建正则表达式验证
			var usernamePatt = /^\w{5,12}$/;   //^:开头 $:结尾 \w:数字、下划线
			//3.使用test方法验证
			if (!usernamePatt.test($username)) {
				//4.提示用户结果
				$("span.errorMsg").text("用户名不合法");
				return false;
			}
				$("span.errorMsg").text("");

			//验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
			var $password = $("#password").val();
			//2.创建正则表达式验证
			var passwordPatt = /^\w{5,12}$/;   //^:开头 $:结尾 \w:数字、下划线
			//3.使用test方法验证
			// 验证确认密码：和密码相同
			if (!passwordPatt.test($password)) {
				$password.test()
				//4.提示用户结果
				$("span.errorMsg").text("密码不合法");
				return false;
			}
			$("span.errorMsg").text("");

			var $repwd = $("#repwd").val();
			if (!($repwd==$password)){
				$("span.errorMsg").text("两次输入的密码不一致");
				return false;
			}
			$("span.errorMsg").text("");
			// 邮箱验证：xxxxx@xxx.com
			//1.获取邮箱里的内容
			var $email = $("#email").val();

			//2.创建正则表达式对象
			var emailPatt =/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;

			//3.使用test方法验证是否合法
			if (!emailPatt.test($email)){
				//4.提示用户
				$("span.errorMsg").text("邮箱格式不合法");
				return false;
			}
			$("span.errorMsg").text("");

			// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
			var $code = $("#code").val();
			$code=$.trim($code);
			if ($code ==null || $code==""){
				//去掉验证码前后的空格
				$("span.errorMsg").text("验证码不能为空");
				return false;
			}
			$("span.errorMsg").text("");

		});


		$("#username").blur(function () {
			//获取用户名
			var username=this.value;
			$.getJSON("http://localhost:8080/book/userServlet","action=ajaxExistsUsername&username="+username,function (data) {
				if(data.existsUsername){
					$("span.errorMsg").text("您输入的用户已存在！");
				}
			});
		});


		});
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
		
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
											value="${requestScope.username}"
									/>
									<br/>
									<br/>
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password"
											value="${requestScope.password}"
									/>
									<br/>
									<br/>
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd"
											value="${requestScope.password}"
									/>
									<br/>
									<br/>
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
											value="${requestScope.email}"
									/>
									<br/>
									<br/>
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 80px;" id="code"
											value="${requestScope.code}"
									/>
									<img alt="" id="code_img" src="kaptcha.jpg" style="float: right; margin-right: 40px" width="110px" height="30px">
									<br/>
									<br/>
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>