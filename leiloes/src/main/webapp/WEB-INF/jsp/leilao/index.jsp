<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
	<title>Caelum</title>
</head>
<body>
	<h1>Todos leilões</h1>

	<table>
		<tr>
			<th>Nome</th>
			<th>Data de abertura</th>
			<th>Valor inicial</th>
			<th>Usuario</th>
			<th>Usado?</th>
			<th></th>
		</tr>

		<c:forEach items="${leilaoList}" var="leilao">
			<tr id="leilao">
				<td id="leilao.nome">${leilao.nome}</td>
				<td><fmt:formatDate value="${leilao.dataAbertura.time}" pattern="dd/MM/yyyy" /></td>
				<td id="leilao.valorInicial">${leilao.valorInicial}</td>
				<td id="leilao.usuario.nome">${leilao.usuario.nome}</td>
				<td id="leilao.usado">${leilao.usado?'Sim':'Não'}</td>
				<td><a id="exibir" href="${pageContext.request.contextPath}/leiloes/${leilao.id}">exibir</a></td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/leiloes/new">Novo Leilão</a> 
</body>