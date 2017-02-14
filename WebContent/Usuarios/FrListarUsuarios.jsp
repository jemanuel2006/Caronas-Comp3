<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="entidades.Usuario" %>
<%@ page import ="java.util.ArrayList" %>
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuarios Cadastrados</title>
</head>
<body>
	<%  
		ArrayList<Usuario> list = (ArrayList<Usuario>) request.getAttribute("list");
	%>
	
	<div class="container">
        <legend>Lista de Usuários</legend>
        <div class="row">
			<table class="table table-striped table-bordered table-hover table-sm">
				<thead class="thead-inverse">
					<tr>
				        <td>Id</td>
				        <td>Nome</td>
				        <td>Email</td>
				        <td>Telefone</td>
				        <td></td> 
				        <td></td>
				        <td></td>
				    </tr>
			    </thead>
			    <tbody>
				<%
					for(Usuario u : list){
				%>
					<tr>
						<th scope="row"><%= u.get_id() %></td>
				        <td><%= u.get_nome() %></td>
				        <td><%= u.get_email() %></td>
				        <td><%= u.get_telefone() %></td>
				        <td><a class="btn btn-success" href="<%= "./EditarUsuario?id=" + u.get_id() %>">Editar</a></td> 
				        <td><a class="btn btn-primary" href="<%= "./ListarVeiculos?usuarioId=" + u.get_id() %>">Visualizar Veículos</a></td> 
				        <td><a class="btn btn-primary" href="<%= "./CriarCarona?usuarioId=" + u.get_id() %>">Criar Carona</a></td> 
			        </tr>
				<%
					}
				%>
				</tbody>
			</table>
		</div>
	
		<a class="btn btn-success" href="./CriarUsuario">Criar Usuário</a>
	</div>
</body>
<script>
	function confirmDelete(){
		return confirm("Tem certeza que deseja remover o usuario selecionado?") == true;
	}
</script>
</html>