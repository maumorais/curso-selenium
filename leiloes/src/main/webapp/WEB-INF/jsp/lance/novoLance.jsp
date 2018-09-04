<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<tr id="lance">
	<td id="lance.data"><fmt:formatDate value="${lance.data.time}" pattern="dd/MM/yyyy" /></td>
	<td id="lance.usuario.nome">${usuario.nome}</td>
	<td id="lance.valor">${lance.valor}</td>
</tr>
