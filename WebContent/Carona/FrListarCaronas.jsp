<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="entidades.Grupo" %>
<%@ page import ="entidades.Carona" %>
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
				        <td></td>
				        <td></td>
				    </tr>
			    </thead>
			    <tbody>
				<%
					for(Carona c : list){
				%>
					<tr>
						<th scope="row"><%= c.get_id() %></td>
				        <td><%= c.getDia() %></td>
				        <td><%= c.getHora_saida() %></td>
				        <td><%= c.get_veiculo().get_motorista().get_nome() %></td>
				        <td><%= c.get_origem().get_endereco() + "-" + c.get_origem().get_numero() + "- CEP: " + c.get_origem().get_cep() %></td>
				        <td><%= c.get_destino().get_endereco() + "-" + c.get_destino().get_numero() + "- CEP: " + c.get_destino().get_cep() %></td>
				        <td><%= c.get_estadoNomeado() %></td>
				        <td><a class="btn btn-success <%= c.get_estadoCarona() != 0 ? "disabled":""%>" href="<%= "./EditarCarona?id=" + c.get_id() %>">Editar</a></td>
				        <td><a class="btn btn-success <%= c.get_estadoCarona() != 0 ? "disabled":""%>" href="<%= "./VisualizarParticipantes?caronaId=" + c.get_id() %>">Ver Participantes</a></td>
				        <td><a class="btn btn-warning <%= c.get_estadoCarona() != 0 ? "disabled":""%>" href="<%= "./FinalizarCarona?caronaId=" + c.get_id() %>">Finalizar</a></td>
			        </tr>
				<%
					}
				%>
				</tbody>
			</table>
		</div>
		<a class="btn btn-primary" href="./CriarParticipacaoCarona">Entrar em uma Carona</a>
	</div>
</body>
</html>