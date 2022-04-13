<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://liferay.com/tld/captcha" prefix="liferay-captcha" %>
<%@ page import="com.liferay.portal.kernel.captcha.CaptchaTextException"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<body>
<div class="container">
	<div class="row align-items-center"> <br>
        <div class="col-6 mx-auto">
			<div>
				<portlet:actionURL name="addUsuario" var="addUsuarioUrl"/>
				<portlet:resourceURL id="captcha" var="captchaResourceURL"/>
				<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="captcha-verification-failed" />
				<liferay-ui:success key="success" message="Greeting saved successfully!"/>
					<aui:form action="${addUsuarioUrl}">
					<h3>Registro usuario</h3> <br>
						<aui:input name="nombreUsuario" type="text" placeholder="nombre">
							<aui:validator name="required"/>
							<aui:validator name="string"/>
						 </aui:input>
						<aui:input name="apellidoUsuario" type="text" placeholder="apellido">
							<aui:validator name="required"/>
							<aui:validator name="string"/>
						</aui:input>
						<aui:input name="fechaNacimiento" type="date">
							<aui:validator name="required"/>
						</aui:input>
						<aui:input name="mailUsuario" type="email" placeholder="name@example.com">
							<aui:validator name="required"/>
						</aui:input>
						<liferay-captcha:captcha url="<%= "captchaResourceURL" %>"/>
						<aui:button-row>
							<br>
							<aui:button name="addUsuarioButton" type="submit" value="Crear usuario"/>
							<aui:button type="reset" value="clear"/>
						</aui:button-row>
					</aui:form>
			</div>
		</div>
	</div>
</div>
</body>