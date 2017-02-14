<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="entidades.Motorista" %>
<%@ page import ="entidades.Veiculo" %>
<%@ page import ="java.util.ArrayList" %>
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Veículos do Usuário</title>
</head>
<body>
	<%  
		ArrayList<Veiculo> list = (ArrayList<Veiculo>) request.getAttribute("list");
	%>
	
	<div class="container">
        <legend>Lista de Veículos - ${_nomeMotorista}</legend>
        <div class="row">
			<table class="table table-striped table-bordered table-hover table-sm">
				<thead class="thead-inverse">
					<tr>
				        <td>Id</td>
				        <td>Cor</td>
				        <td>Modelo</td>
				        <td>Placa</td>
				        <td></td>
				    </tr>
			    </thead>
			    <tbody>
				<%
					for(Veiculo v : list){
				%>
					<tr>
						<th scope="row"><%= v.get_id() %></td>
				        <td><%= v.get_cor() %></td>
				        <td><%= v.get_modelo() %></td>
				        <td><%= v.get_placa() %></td>
				        <td><a class="btn btn-success" href="<%= "./EditarVeiculo?id=" + v.get_id() + "&usuarioId=" %>${_id}">Editar</a></td>
			        </tr>
				<%
					}
				%>
				</tbody>
			</table>
		</div>
	
		<a class="btn btn-success" href="./CriarVeiculo?usuarioId=${_id}">Adicionar Veículo</a>
	</div>
</body>
</html>