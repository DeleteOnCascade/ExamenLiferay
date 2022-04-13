<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@page import="usuario.servicio.service.UsuarioLocalServiceUtil"%>
<portlet:actionURL name="deleteUsuario" var="deleteUsuario"></portlet:actionURL>
<portlet:actionURL name="actualizarUsuario" var="actualizarUsuario"></portlet:actionURL>

<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<meta charset="UTF-8">
	<title>Usuarios</title>
</head>
<body>
	<table class="table table-responsive table-borderless text-center">
		<thead>
			<tr>
				<th scope="col">Name</th>
      			<th scope="col">Surname</th>
      			<th scope="col">Birthday</th>
      			<th scope="col">Email</th>
			</tr>
	    </thead>
			<c:forEach items="${listaUsuarios}" var="usuario" varStatus="estado">
				<tr>
					<td>${usuario.userName}</td>
					<td>${usuario.userSurname}</td>
					<td>${usuario.userBirthdate}</td>
					<td>${usuario.userMail}</td>
					
					<td><button type="button" data-bs-toggle="modal" data-bs-target="#Editar${usuario.userId}"
					 class="btn btn-sm btn-warning">Update</button>
						<div class="modal" id="Editar${usuario.userId}">	
						 	<aui:form action="<%= actualizarUsuario %>" id="formUserUpdate${usuario.userId}" name="<portlet:namespace />fmu" method="post">							
							<div class="modal-dialog">
								<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
											<h4 class="modal-title" id="myModalLabelUpdate${usuario.userId}">${usuario.userName}</h4>
										</div>
										<div class="modal-body">
											<fieldset>
											<div class="mb-3 mb-3">
												<aui:input name="nombreUsuario" type="text" label="Nombre" required="true"
												 style="background: white" value="${usuario.userName}">
												 	<aui:validator name="required"/>
			            						</aui:input>
											</div>
											<div class="mb-3">
												<aui:input name="apellidoUsuario" type="text" label="Apellidos" required="true"
												 style="background: white" value="${usuario.userSurname}">
													<aui:validator name="required"/>
			           	 						</aui:input>
											</div>
											<div class="mb-3">
												<aui:input name="fechaNacimiento" type="text" label="Fecha nacimiento" required="true"
												 style="background: white" value="${usuario.userBirthdate}" placeholder="fecha nacimiento">
			           	 						</aui:input>
											</div>
											<div class="mb-3">
												<aui:input name="mailUsuario" type="email" label="Correo" required="true"
												 style="background: white" value="${usuario.userMail}">
												 	<aui:validator name="required"/>
			           	 						</aui:input>
											</div>
												<aui:input type="hidden" name="userId" value="${usuario.userId}"></aui:input>
											</fieldset>
										</div>
										<div class="modal-footer">
											<button type="submit" class="btn btn-success" name="action">Update</button>
											<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
										</div>
							    </div>
							</div>
							</aui:form>
						</div>
					</td>
					<td>
					<button type="button" data-bs-toggle="modal" data-bs-target="#Eliminar${usuario.userId}"
					 class="btn btn-sm btn-warning">Delete</button>
						<div class="modal" id="Eliminar${usuario.userId}">
							<aui:form action="<%= deleteUsuario %>" id="formUserDelete${usuario.userId}" name="<portlet:namespace />fmd">	
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
										<h4 class="modal-title" id="myModalLabaelDelete${usuario.userId}">${usuario.userName}</h4>
									</div>
									<aui:input type="hidden" name="userId" value="${usuario.userId}"></aui:input>
									<div class="modal-footer">
										<button type="submit" class="btn btn-success" name="action">Delete</button>
										<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
									</div>
							    </div>
							</div>
							</aui:form>
						</div>
					</td>
				</tr>
				
			</c:forEach>
		</table>
</body>
</html>	