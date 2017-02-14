<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="entidades.Grupo" %>
<%@ page import ="entidades.Carona" %>
<%@ page import ="entidades.Parada" %>
<%@ page import ="java.util.ArrayList" %>
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Participantes de Carona</title>
</head>
<body>
	<%  
		ArrayList<Parada> list = (ArrayList<Parada>) request.getAttribute("paradas");
	%>
	
	<div class="container">
        <legend>Participantes da Carona - ${nome}</legend>
        <input name="caronaId" id="caronaId" type="hidden" value="${caronaId}" />
        <div class="row">
			<table class="table table-striped table-bordered table-hover table-sm">
				<thead class="thead-inverse">
					<tr>
				        <td>Id</td>
				        <td>Nome</td>
				        <td>Email</td>
				        <td>Parada</td>
				        <td></td>
				    </tr>
			    </thead>
			    <tbody>
				<%
					for(Parada p : list){
				%>
					<tr>
						<th scope="row"><%= p.get_usuario().get_id() %></td>
				        <td><%= p.get_usuario().get_nome() %></td>
				        <td><%= p.get_usuario().get_email() %></td>
				        <td><%= p.toString() %></td>
				        <form action="./VisualizarParticipantes?paradaId=<%= p.get_id()%>&caronaId=${caronaId}" method="post">
				        	<td><button type="submit" class="btn btn-danger">Desistir da carona</button></td>
				        </form>
			        </tr>
				<%
					}
				%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>