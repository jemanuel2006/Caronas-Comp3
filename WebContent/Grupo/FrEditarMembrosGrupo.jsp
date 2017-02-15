<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="entidades.Grupo" %>
<%@ page import ="entidades.Usuario" %>
<%@ page import ="java.util.ArrayList" %>
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Membros do Grupo</title>
</head>
<body>
	<%  
		ArrayList<Usuario> list = (ArrayList<Usuario>) request.getAttribute("list");
		int max = (int) request.getAttribute("max"); 
	%>
	
	<div class="container">
        <legend>Lista de Membros</legend>
        <div class="row">
			<table class="table table-striped table-bordered table-hover table-sm">
				<thead class="thead-inverse">
					<tr>
				        <td>Id</td>
				        <td>Nome</td>
				        <td>Email</td>
				        <td>Telefone</td>
				        <td>Situação</td>
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
				        <td><%= u.get_quantidadeAvaliacoesRuins() > max ? "Inativo" : "Ativo"%></td>
			        </tr>
				<%
					}
				%>
				</tbody>
			</table>
		</div>
	
		<button class="btn btn-success" type="button" data-toggle="modal" data-target="#modalAdicionarMembro">Adicionar Membro</button>
		<a href="./ListarGrupos">Cancelar</a>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="modalAdicionarMembro" tabindex="-1" role="dialog" aria-labelledby="labelAdicionarMembro">
	  <div class="modal-dialog" role="document">
	       <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="labelAdicionarMembro">Adicionar Membro</h4>
		      </div>
		      <form action="./EditarMembrosGrupo" method="post">
		      	  <input name="_id" id="_id" type="hidden" value="${_id}" />
			      <div class="modal-body">
						<label for="_email">* Email do membro:</label>
						<input type="email" name="_email" class="form-control"placeholder="Digite o email do usuário que deseja adicionar" required>
					
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			        <button type="submit" class="btn btn-success">Adicionar Membro</button>
			      </div>
		      </form>
	      </div>
       </div>
	 </div>
</body>
</html>