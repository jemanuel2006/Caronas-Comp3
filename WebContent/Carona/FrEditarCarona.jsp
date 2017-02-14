<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<%@ page import ="entidades.Veiculo" %>
<%@ page import ="entidades.Logradouro" %>
<%@ page import ="java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Carona</title>
</head>
<body>
	<%  
		boolean podeAlterarCarona = (boolean) request.getAttribute("podeAlterarCarona");
		int veiculoId = (int) request.getAttribute("veiculoId");
		int origemId = (int) request.getAttribute("origemId");
		int destinoId = (int) request.getAttribute("destinoId");
		ArrayList<Veiculo> veiculos = (ArrayList<Veiculo>) request.getAttribute("veiculos");
		ArrayList<Logradouro> logradouros = (ArrayList<Logradouro>) request.getAttribute("logradouros");
	%>
	<div class="container">
	 <div class="row">
		<form id="caronaForm" action="./EditarCarona" method="post">
			<input name="_id" id="_id" type="hidden" value="${_id}" />
			<input name="usuarioId" id="_id" type="hidden" value="${usuarioId}" />
			<div class="col-md-8">
				<legend>Editar Carona</legend>
				<div class="form-group">
					<label>* Ve�culo:</label>
			            <select class="form-control" name="_veiculoId" required <%= podeAlterarCarona ? "" : "disabled" %>>
			                <option value="">Selecione um ve�culo</option>
			                <%
								for(Veiculo v : veiculos){
							%>
							<option value="<%= v.get_id()%>" <%= v.get_id() == veiculoId ? "selected='selected'" : "" %>><%= v.get_modelo() + " - " + v.get_cor() + " - " + v.get_placa() %></option>
							<%
							}
							%>
			            </select>
				</div>
				<div class="form-group">
			        <label for="_dia">* Data da carona</label>
			        <div class="input-group bootstrap-timepicker timepicker">
	                	<input type="date" class="form-control" name="_dia" value="${dia}" required <%= podeAlterarCarona ? "" : "disabled" %>/>
	                	 <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
	                </div>
			    </div>
				    
				<div class="form-group">
					<label for="horario_saida">* Hor�rio de sa�da:</label>
					<div class="input-group bootstrap-timepicker timepicker">
			            <input name="horario_saida" type="time" class="form-control input-small" value="${hora_saida}" required <%= podeAlterarCarona ? "" : "disabled" %>>
			            <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
			        </div>
				</div>
				<div class="form-group">
					<label>* Logradouro Origem:</label>
		            <select class="form-control" name="_origemId" required <%= podeAlterarCarona ? "" : "disabled" %>>
		                <option value="">Selecione um logradouro de origem</option>
		                <%
							for(Logradouro l : logradouros){
						%>
						<option value="<%= l.get_id()%>" <%= l.get_id() == origemId ? "selected='selected'" : "" %>><%= l.get_endereco() + "-" + l.get_numero() + "- CEP: " + l.get_cep() %></option>
						<%
						}
						%>
		            </select>
				</div>
				<div class="form-group">
					<label>* Logradouro Destino:</label>
		            <select class="form-control" name="_destinoId" required <%= podeAlterarCarona ? "" : "disabled" %>>
		                <option value="">Selecione um logradouro de destino</option>
		               	<%
							for(Logradouro l : logradouros){
						%>
						<option value="<%= l.get_id()%>" <%= l.get_id() == destinoId ? "selected='selected'" : "" %>><%= l.get_endereco() + "-" + l.get_numero() + "- CEP: " + l.get_cep() %></option>
						<%
						}
						%>
		            </select>
				</div>
				<button class="btn btn-success" type="submit" <%= podeAlterarCarona ? "" : "disabled" %>>Salvar</button>
				<a href="./ListarCaronas">Cancelar</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>