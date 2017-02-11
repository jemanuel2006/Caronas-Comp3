<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="entidades.Grupo" %>
<%@ page import ="java.util.ArrayList" %>
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Grupos Cadastrados</title>
</head>
<body>
	<%  
		ArrayList<Grupo> list = (ArrayList<Grupo>) request.getAttribute("list");
	%>
	
	<div class="container">
        <legend>Lista de Grupos</legend>
        <div class="row">
			<table class="table table-striped table-bordered table-hover table-sm">
				<thead class="thead-inverse">
					<tr>
				        <td>Id</td>
				        <td>Nome</td>
				        <td>Descrição</td>
				        <td>Regras</td>
				        <td>Limite</td>
				        <td></td>
				        <td></td>
				    </tr>
			    </thead>
			    <tbody>
				<%
					for(Grupo g : list){
				%>
					<tr>
						<th scope="row"><%= g.get_id() %></td>
				        <td><%= g.get_nome() %></td>
				        <td><%= g.get_descricao() %></td>
				        <td><%= g.get_regras() %></td>
				        <td><%= g.get_limite() %></td>
				        <td><a class="btn btn-success" href="<%= "./EditarGrupo?id=" + g.get_id() %>">Editar</a></td>
				        <td><a class="btn btn-success" href="<%= "./EditarMembrosGrupo?id=" + g.get_id() %>">Alterar Membros</a></td>
			        </tr>
				<%
					}
				%>
				</tbody>
			</table>
		</div>
	
		<a class="btn btn-success" href="./CriarGrupo">Criar Grupo</a>
	</div>
</body>
</html>