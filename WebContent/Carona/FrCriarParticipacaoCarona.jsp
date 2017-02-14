<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<%@ page import ="entidades.Veiculo" %>
<%@ page import ="entidades.Logradouro" %>
<%@ page import ="entidades.Usuario" %>
<%@ page import ="entidades.Carona" %>
<%@ page import ="java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1;">
<title>Participar de Carona</title>
</head>
<body>
	<%  
		ArrayList<Carona> caronas = (ArrayList<Carona>) request.getAttribute("caronas");
		ArrayList<Logradouro> logradouros = (ArrayList<Logradouro>) request.getAttribute("logradouros");
	%>
	<div class="container">
	 <div class="row">
		<form action="./CriarParticipacaoCarona" method="post">
			<div class="col-md-8">
				<legend>Participar de Carona</legend>
				
				<div class="form-group">
					<label>* Email do usuário:</label>
			        <input type="email" class="form-control" name="_usuarioEmail" required />
				</div>
				<div class="form-group">
					<label>* Carona:</label>
			            <select class="form-control" name="_caronaId" required>
			                <option value="">Selecione uma carona</option>
			                <%
								for(Carona c : caronas){
							%>
							<option value="<%= c.get_id()%>"><%= c.toString() %></option>
							<%
							}
							%>
			            </select>
				</div>
				<div class="form-group">
					<label>* Parada:</label>
			            <select class="form-control" name="_logradouroId" required>
			                <option value="">Selecione uma parada</option>
			                <%
								for(Logradouro l : logradouros){
							%>
							<option value="<%= l.get_id()%>"><%= l.get_endereco() + "-" + l.get_numero() + "- CEP: " + l.get_cep() %></option>
							<%
							}
							%>
			            </select>
				</div>
				<button class="btn btn-success" type="submit">Adicionar usuário</button>
				<a href="./ListarCaronas">Cancelar</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>