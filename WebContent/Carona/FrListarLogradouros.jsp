<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="entidades.Logradouro" %>
<%@ page import ="java.util.ArrayList" %>
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logradouros Cadastrados</title>
</head>
<body>
	<%  
		ArrayList<Logradouro> list = (ArrayList<Logradouro>) request.getAttribute("list");
	%>
	
	<div class="container">
        <legend>Lista de Logradouros</legend>
        <div class="row">
			<table class="table table-striped table-bordered table-hover table-sm">
				<thead class="thead-inverse">
					<tr>
				        <td>Id</td>
				        <td>CEP</td>
				        <td>Cidade</td>
				        <td>Distrito</td>
				        <td>Endereço</td>
				        <td>Cidade</td>
				        <td>Número</td>
				    </tr>
			    </thead>
			    <tbody>
				<%
					for(Logradouro g : list){
				%>
					<tr>
						<th scope="row"><%= g.get_id() %></td>
				        <td><%= g.get_cep() %></td>
				        <td><%= g.get_cidade() %></td>
				        <td><%= g.get_distrito() %></td>
				        <td><%= g.get_endereco() %></td>
				        <td><%= g.get_cidade() %></td>
				        <td><%= g.get_numero() %></td>
			        </tr>
				<%
					}
				%>
				</tbody>
			</table>
		</div>
	
		<a class="btn btn-success" href="./CriarLogradouro">Criar Logradouro</a>
	</div>
</body>
</html>