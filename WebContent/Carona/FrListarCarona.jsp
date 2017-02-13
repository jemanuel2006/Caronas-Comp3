<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="entidades.Grupo" %>
<%@ page import ="java.util.ArrayList" %>
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Caronas Criadas</title>
</head>
<body>
	<%  
		ArrayList<Carona> list = (ArrayList<Carona>) request.getAttribute("list");
	%>
	
	<div class="container">
        <legend>Lista de Caronas</legend>
        <div class="row">
			<table class="table table-striped table-bordered table-hover table-sm">
				<thead class="thead-inverse">
					<tr>
				        <td>Id</td>
				        <td>Dia</td>
				        <td>Horario de Saída</td>
				        <td>Motorista</td>
				        <td>Origem</td>
				        <td>Destino</td>
				        <td>Estado da Carona</td>
				        <td></td>
				    </tr>
			    </thead>
			    <tbody>
				<%
					for(Grupo g : list){
				%>
					<tr>
						<th scope="row"><%= g.get_id() %></td>
				        <td><%= .get_dia() %></td>
				        <td><%= g.get_horario_saida() %></td>
				        <td><%= g.get_regras() %></td>
				        <td><%= g.get_limite() %></td>
				        <td><a class="btn btn-success" href="<%= "./EditarCarona?id=" + g.get_id() %>">Editar</a></td>
				        
			        </tr>
				<%
					}
				%>
				</tbody>
			</table>
		</div>
	
		<a class="btn btn-success" href="./CriarCarona">Criar Carona</a>
	</div>
</body>
</html>