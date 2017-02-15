<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="entidades.Usuario" %>
<%@ page import ="java.util.ArrayList" %>
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finalizar Carona</title>
</head>
<body>
	<div class="container">
		<%  
			ArrayList<Usuario> list = (ArrayList<Usuario>) request.getAttribute("usuarios");
		%>
        
        <div class="row">
			<form action="./FinalizarCarona" method="post">
				<input name="_id" id="_id" type="hidden" value="${_id}" />
				<div class="col-md-8">
				<legend>Finalizar Carona</legend>
					<%
						for(Usuario u : list){
					%>
						<div class="form-group">
					        <label for="_dia">* Avaliar usuário '<%= u.get_nome() %>':</label>
					        <div class="input-group bootstrap-timepicker timepicker">
			                	<input type="number" class="form-control" name="avaliacao_<%=u.get_id() %>" min="1" max="5" required/>
			                	<span class="input-group-addon"><i class="glyphicon glyphicon-star"></i></span>
			                </div>
					    </div>
				    <%
						}
				    %>
				    <button class="btn btn-success" type="submit">Salvar</button>
					<a href="./ListarCaronas">Cancelar</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>