<%@page import="com.ojo.vo.UsertblVO"%>
<%@page import="com.ojo.vo.PosttblVO"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Mypage</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/myPage.css">
<script type="text/javascript" src="js/profileimage.js"></script>

<link
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20,300,1,200"
	rel="stylesheet" />

<style type="text/css">
.box {
	width: 50px;
	height: 50px;
	border-radius: 70%;
	overflow: hidden;
	margin: 10px;
}

.profile {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.nav_navbar {
	background-color: #6f7283;
	height: 100px;
	width: 100%;
	display: flex;
	justify-content: space-around;
	align-items: center;
	position: fixed;
	border-top: 2px solid #B88FCC;
	border-bottom: 4px solid #5b5d6a;
	z-index: 1;
}

.nav_navbar>div {
	cursor: pointer;
}

.div_nav_icons {
	display: flex;
}

.div_nav_icons div {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-left: 15px;
	color: white;
}

.div_nav_icons  span {
	color: white;
	margin: 3px;
}

.div_items {
	margin-left: 5%;
	width: 55%;
	background: #ffffff;
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
}

.div_items>.item {
	width: 20%;
	height: 230px;
	margin: 30px;
	background: #ffffff;
	border-radius: 10px;
	border: 1px solid lightgray;
	padding: 10px;
}

.div_items>.item>img {
	width: 90%;
	height: 65%;
	object-fit: cover;
	border-radius: 10px 10px 0px 0px;
}

.div_items>.item>div {
	margin: 0 10px;
}
</style>

</head>
<%
	ArrayList<PosttblVO> postlist = (ArrayList<PosttblVO>) request.getAttribute("postlist");
	ArrayList<String> postimages = (ArrayList<String>) request.getAttribute("postimages");
%>
<body>
	<nav class="nav_navbar">

		<!-- ?????? -->
		<div>
			<img id="logo" alt="no image" src="./images/logo.png">
		</div>

		<!-- ????????? -->
		<%-- <div>
			<form action="main" method="post">
				<input id="input_search_post" type="text" name="search"
					value="<%=search%>" />
			</form>
		</div> --%>

		<!-- ????????? -->
		<div class="div_nav_icons">
			<!-- <b><span>Add Item</span></b> <b><span>Sign Up</span></b> -->

			<!-- <div>
			<span class="material-symbols-outlined">add_box</span>
			</div> -->


			<!-- ????????? ?????? -->
			<%
				if (session.getAttribute("userid") != null) {
					/* String profileimg = request.getParameter("userprofile"); */
			%>
			<div>
				<span id="logout"><b>????????????</b></span> <span
					class="material-symbols-outlined">logout</span>
			</div>
			<div class="box" style="background: #BDBDBD;">
				<img class="profile" src="profileimage/${userprofile}">
			</div>
			<%
				} else {
			%>
			<div>
				<span id="login"><b>?????????/????????????</b></span> <span
					class="material-symbols-outlined">login</span>
			</div>
			<%
				}
			%>
		</div>


	</nav>

	<div id="wrap">
		<section>

			<div id="doc" class="yui-t7">
				<div id="hd">
					<div id="header">head</div>
				</div>
				<br>
				<div id="bd">
					<div id="yui-main">
						<div class="yui-b">
							<div class="yui-gd">
								<div class="yui-u first">

									<div class="image">
										<div class="image_box">
											<img
												style="object-fit: cover; width: 150px; height: 150px; border-radius: 100px;"
												src="profileimage/${userprofile}"><span
												class="material-symbols-outlined"
												style="position: relative; top: -200px; color: gray; border: 1px solid gray; border-radius: 5px; cursor: pointer;"></span>
										</div>
										<div style="color: gray; text-align: center;">
											<b>${nickname}???</b>
										</div>
									</div>
									<br>
									<div class="subbox">
										<!-- ????????? ?????? -->

										<form id="profile_submit" action="uploadAction" method="post"
											enctype="multipart/form-data" style="display: none;">
											??????1: <input id="profile_upload" type="file" name="file1" /><br />
											<input type="submit" value="upload" />
										</form>

										<div class="space"></div>
										<!-- ?????? ?????? -->
										<div class="btn" id="add-btn">
											<b>????????????</b>
										</div>
										<div class="bspace2"></div>
										<!-- ?????? ??????  -->
										<button onclick="test()" class="btn_shop">
											<b>????????????</b>
										</button>
										<!-- ???????????? ?????? -->
										<div class="modal" id="modal">
											<div class="modal_body">
												<div class="m_head">
													<div class="modal_title">???????????? ??????</div>
													<div class="close_btn" id="close_btn">X</div>
												</div>
												<div class="m_body">
													<section class="bg-light">
														<div
															class="row align-items-center justify-content-between">

														</div>
														<!-- ????????? ?????? -->
														<div class="image_model">
															<img
																style="object-fit: cover; width: 200px; height: 200px;"
																src="profileimage/${userprofile}"><span
																id="profile_1" class="material-symbols-outlined"
																style="position: relative; top: -200px; color: gray; border: 1px solid gray; border-radius: 5px; cursor: pointer;">edit</span>
														</div>
														<br>
														<div class="subbox">

															<form id="profile_submit" action="uploadAction"
																method="post" enctype="multipart/form-data"
																style="display: none;">
																??????1: <input id="profile_upload" type="file" name="file1" />
																<input type="hidden" name="userid" id="userid"
																	value="${userid}"> <br /> <input type="submit"
																	value="upload" />
															</form>

															<!-- ?????? ??????  -->
															<div class="card-body">

																<form id="MYpage" method="POST"
																	class="form-signup form-user panel-body"
																	autocomplete="off">
																	<input type="hidden" id="memberName_yn"
																		name="memberName_yn" value="N" />

																	<fieldset>
																		<div class="form-group has-success">
																			<label class="form-label mt-4" for="inputValid"
																				style="color: gray; font-family: sans-serif; font-weight: bold;">ID</label>
																			<input value="${userid}" type="text"
																				class="form-control" id="userid" name="userid"
																				readonly>
																			<div class="valid-feedback"></div>
																		</div>
																		<div class="form-group">
																			<label class="control-label" for="nickname"
																				style="color: gray; font-family: sans-serif; font-weight: bold;">?????????</label>

																			<input type="text" name="nickname"
																				class="form-control input-sm"
																				placeholder="???????????? ???????????? ????????????." id="nickname">

																		</div>

																		<div class="form-group">
																			<label class="control-label" for="userpw"
																				style="color: gray; font-family: sans-serif; font-weight: bold;">PW</label>
																			<input type="password" name="userpw"
																				placeholder="???????????? 8?????? ??????"
																				class="form-control input-sm" id="userpw">
																		</div>

																	</fieldset>


																	<div
																		style="color: gray; font-family: sans-serif; font-weight: bold;">
																		?????? ??????</div>

																	<div style="display: flex; margin: 5px 0px;">
																		<div style="width: 150px">
																			<select id="gu_select" class="form-select"
																				aria-label="Default select example">

																				<%
																					ArrayList<String> gulist = (ArrayList<String>) request.getAttribute("gulist");
																					for (String gu : gulist) {
																				%>
																				<option class="gu"><%=gu%></option>
																				<%
																					}
																				%>
																			</select>
																		</div>
																		<div
																			style="width: 30px; height: auto; display: inline-block;"></div>
																		<div style="width: 150px">
																			<select id="dong_select" class="form-select"
																				aria-label="Default select example">
																				<option selected>???</option>
																			</select>
																		</div>
																	</div>
																	<!-- <div class="form-group" style="width: 400px">
																		<label class="control-label" for="userpw"
																			style="color: gray; font-family: sans-serif; font-weight: bold;">?????????
																			??????</label> <input type="number" name="userpw"
																			placeholder="-?????? ????????? ??????"
																			class="form-control input-sm" id="number">
																			
																	</div>  -->



																</form>

															</div>


														</div>
													</section>
												</div>
												<div class="m_footer">
													<button class="btn btn-primary btn-block" id="userup"
														style="width: 100px; height: 40px; border: 1px solid #73536F; border-radius: 10px; background: #73536F; text-align: center; color: white; cursor: pointer;"
														type="button">??????</button>

												</div>
											</div>
										</div>

										<script>
											function test() {
												if ($('#secondary').css(
														'display') == 'flex') {
													$('#secondary').css(
															'display', 'none');
												} else {
													$('#secondary').css(
															'display', 'flex');
												}
											}
										</script>
										<div class="bspace"></div>
										<div id="writecontent" class="btn_content">
											<b>?????????</b>
										</div>
									</div>
								</div>
								<div class="yui-u">

									<%
										String usercontent = (String) session.getAttribute("usercontent");
									%>
									<div class="content"
										style="border: solid; border-radius: 10px; padding: 10px; border-color: gray;">${usercontent}</div>

									<form action="insert.jsp" method="post"></form>
								</div>
							</div>
						</div>
					</div>

					<!-- ???????????? ?????? -->
					<div id="secondary" class="div_items"
						style="display: none; width: 100%; display: flex; background: #ffffff;">
						<%
						for(int index=0; index<postlist.size(); index++){
							PosttblVO vo = postlist.get(index);
						%>
						<div class="item" postnum="<%=vo.getPostnum()%>">
							<!-- ?????? ????????? -->
							<img alt="no_image"
								src="./uploadImages/<%=postimages.get(index)%>">
							<!-- ?????? -->
							<div><%=vo.getTitle()%></div>
							<div>
								<span><%=vo.getPostgu()%></span> <span><%=vo.getPostdong()%></span>
							</div>
							<div>
								<%=vo.getPrice()%>???
							</div>
						</div>
						<%
						}
						%>

				</div>
				</div>
			</div>
			<br>
		</section>
		<%
			if (session.getAttribute("userid") != null) {
		%>
		<div id="productupload"
			style="cursor: pointer; width: 70px; height: 70px; display: flex; justify-content: center; align-items: center; position: fixed; right: 10%; bottom: 10%; font-size: 30px; font-weight: 1000; color: white; background: orange; border-radius: 50%;">
			<span class="material-symbols-outlined" style="font-size: 50px;">add_box</span>
		</div>
		<%
			} else {
		%>
		<div class="login"
			style="cursor: pointer; width: 70px; height: 70px; display: flex; justify-content: center; align-items: center; position: fixed; right: 10%; bottom: 10%; font-size: 30px; font-weight: 1000; color: white; background: orange; border-radius: 50%;">
			<span class="material-symbols-outlined" style="font-size: 50px;">add_box</span>
		</div>
		<%
			}
		%>

		<footer
			style="display: flex; flex-direction: row; justify-content: flex-start; left: 10%; width: 80%;">
			<nav>
				<a>????????????</a>
			</nav>
			<p>
				<span>?????????</span><br> <span>?????????</span><br> <span>?????????</span><br>
				<span>?????????</span><br>
			</p>
			<nav>
				<a>???????????? ????????????</a>
			</nav>
			<p style="text-align: left;">
				<span>1. ????????? ?????? ??? ??????????????? ????????? ????????? ?????? ????????????</span><br> <span>2.
					???????????? ??????????????? ???????????? ????????? ????????? ???????????? ?????? ?????? ??? ?????? ????????????</span><br> <span>3.
					?????? ???????????? ?????? ?????? ??????????????? ???????????? ????????? ????????? ????????? ????????????</span>
			</p>
		</footer>
	</div>

	<div id="first_modal" class="modal_overlay">
		<div class="modal_window">

			<table class="table"
				style="width: 700px; margin-left: auto; margin-right: auto;">
				<tr class="info">
					<th></th>
					<th colspan="1" style="text-align: center;">????????????</th>
					<th style="text-align: right; cursor: pointer;"><span
						id="closecontent" class="material-symbols-outlined">close</span></th>
				</tr>

				<tr>
					<th class="align-middle"><label for="usercontent">??????</label></th>
					<td colspan="2"><textarea id="usercontent"
							class="form-control" rows="20" name="usercontent"
							style="resize: none;" spellcheck="false"></textarea></td>
				</tr>
				<tr class="table-secondary" style="background: white;">
					<td colspan="1" align="right"></td>
					<td colspan="2" align="right">
						<div id="uploadcontnet"
							style="width: 100px; border: 1px solid #73536F; border-radius: 10px; background: #73536F; text-align: center; color: white; cursor: pointer;">
							<b>??????</b>
						</div>
					</td>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<script>
		/* ???????????? */
		$('#uploadcontnet').click(function() {
			let usercontent = $('#usercontent').val();
			$.ajax({
				url : "update_content",
				data : {
					usercontent : usercontent
				},
				method : "POST",
				success : function(data) {
					console.log("??????");
					location.href = "MYpage"
				},
				error : function(request, status, error) {
					console.log("??????");
				},
				complete : function() {
					console.log("??????");
				}
			});
		})
		$('#closecontent').click(function() {
			console.log("zcc");
			$('#first_modal').css({
				display : "none"
			});
		});
		$('#writecontent').click(function() {
			console.log("zcc");
			$('#first_modal').css({
				display : "block"
			});
		});
		$('#productupload').click(function() {
			location.href = "productUpload";
		});
		$('#logout').click(function() {
			//console.log("??????");
			var form = $('<form></form>');
			form.attr("method", "post");
			form.attr("action", "main");
			form.appendTo('body');
			form.append($('<input type="hidden" value="remove" name=remove>'));
			form.submit();
		});
		// ????????? ????????? 
		$('#profile_1').click(function() {
			$('#profile_upload').click();
		});

		$('#profile_upload').change(function() {
			$('#profile_submit').submit();
		});

		$('#logo').click(function() {
			//console.log("??????");
			var form = $('<form></form>');
			form.attr("method", "post");
			form.attr("action", "main");
			form.appendTo('body');
			form.append($('<input type="hidden" value="clear" name=clear>'));
			form.submit();
		});

		// ???????????? ??????
		$(document).on('click', '#add-btn', function(e) {
			console.log("click event");
			$('#modal').addClass('show');

		});

		// ?????? ??????
		$(document).on('click', '#close_btn', function(e) {
			console.log("click event");
			$('#modal').removeClass('show');

		});

		// ???????????? ??????
		/* $(documenct).ready(function() {

		}); */

/* 		function fnSubmit() {

			if ($("#nickname").val() == null || $("#nickname").val() == "") {
				alert("???????????? ??????????????????.");
				$("#nickname").focus();

				return false;
			}

			if ($("#userpw").val() == null || $("#userpw").val() == "") {
				alert("??????????????? ??????????????????.");
				$("#userpw").focus();

				return false;
			}


			if (confirm("?????????????????????????")) {

				$("#MYpage").submit();

				return false;
			}
		} */
		
		$('#gu_select').change(function() {
			let gu = $(this).val();
			$('#dong_select').empty();
			console.log(gu);
		$.ajax({
				url : "dongselect",
				data : {
					gu : gu
				},
				method : "POST",
				success : function(data) {
					//console.log("??????");
					let donglist = data.split(" ");
					donglist.pop();
					//console.log(donglist);
					for(dong of donglist){
						//console.log(dong);
						$('#dong_select').append(
							        $('<option>').prop({
							            innerHTML: dong
							        })
						    );
					}
				},
				error : function(data, status, error) {

					console.log("??????");
				},
				complete : function(data) {
					console.log("??????");
				}
			});
		});
		
		$('#userup').click(function() {

			let userid = $('#userid').val();
			let nickname = $('#nickname').val();
			let userpw = $('#userpw').val();
			let gu = $('#gu_select').val();
			let dong = $('#dong_select').val();

			$.ajax({
				url : "updateuser",
				data : {
					userid : userid,
					nickname : nickname,
					userpw : userpw,
					gu : gu,
					dong : dong,
				},
				method : "POST",
				success : function(data) {
					console.log(data);
					/* if(data=="???????????????"){
						$('#caution').html("????????? ???????????????.")
						$('#caution').css("display", "block")
					}
					if(data=="???????????????"){
						$('#caution').html("????????? ???????????????.")
						$('#caution').css("display", "block")
					}
					if(data=="??????????????????????????????"){
						$('#caution').html("???????????? ????????? ???????????????.")
						$('#caution').css("display", "block")
					}
					if(data=="???????????? ????????????"){
						alert("???????????? ????????????");
					} */
					
				},
				error : function(data, status, error) {
					/* console.log(data.responseJSON.message);
					$('#caution').html(data.responseJSON.message)
					$('#caution').css("display", "block")
					console.log("??????"); */
				},
				complete : function(data) {
					console.log("??????");
						location.replace("MYpage")
				}
			});
		});
		
		function test() {
			if ($('#secondary').css(
					'display') == 'flex') {
				$('#secondary').css(
						'display', 'none');
			} else {
				$('#secondary').css(
						'display', 'flex');
			}
		}
	</script>
</body>
</html>