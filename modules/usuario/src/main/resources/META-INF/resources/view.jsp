<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://liferay.com/tld/captcha" prefix="liferay-captcha" %>
<%@ page import="com.liferay.portal.kernel.captcha.CaptchaTextException"%>
<body>
	<div>
	<portlet:actionURL name="addUsuario" var="addUsuarioUrl"/>
	<portlet:resourceURL id="captcha" var="captchaResourceURL"/>
	<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="captcha-verification-failed" />
	<liferay-ui:success key="success" message="Greeting saved successfully!"/>
		<aui:form action="${addUsuarioUrl}">
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
</body>