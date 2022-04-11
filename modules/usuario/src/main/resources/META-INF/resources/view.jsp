<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://liferay.com/tld/captcha" prefix="liferay-captcha" %>
<%@ page import="com.liferay.portal.kernel.captcha.CaptchaTextException"%>
<body>
	<div >
	<portlet:actionURL name="addUsuario" var="addUsuarioUrl"/>
	<portlet:resourceURL id="captcha" var="captchaResourceURL"/>
	<liferay-ui:error
	    exception="<%= CaptchaTextException.class %>"
	    message="captcha-verification-failed" />
	    
		<aui:form action="${addUsuarioUrl}">
		    <aui:input name="nombreUsuario" type="textarea" placeholder="nombre"/>
		    <aui:input name="apellidoUsuario" type="textarea" placeholder="apellido"/>
		    <aui:input name="fechaNacimiento" type="date"/>
		    <aui:input name="mailUsuario" type="email" placeholder="name@example.com"/>
		    <liferay-captcha:captcha url="<%= "captchaResourceURL" %>"/>
		    
		    <aui:button name="addUsuarioButton" type="submit" value="Crear usuario"/>
		</aui:form>
	</div>
</body>