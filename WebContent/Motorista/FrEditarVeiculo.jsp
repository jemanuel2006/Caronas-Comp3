<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Veículo</title>
</head>
<body>
	<div class="container">
	 <div class="row">
		<form action="./EditarVeiculo" method="post">
			<input name="_id" id="_id" type="hidden" value="${_id}" />
			<input name="_usuarioId" id="_usuarioId" type="hidden" value="${_usuarioId}" />
			<div class="col-md-8">
				<legend>Editar Veículo</legend>
				<div class="form-group">
					<label for="_cor">* Cor:</label>
					<input type="text" name="_cor" class="form-control" placeholder="Digite a cor do veículo" value="${_cor}" required>
				</div>
				<div class="form-group">
					<label for="_modelo">* Modelo:</label>
					<input type="text" name="_modelo" class="form-control" disabled placeholder="Digite o modelo do veículo" value="${_modelo}" required>
				</div>
				<div class="form-group">
					<label for="_placa">* Placa:</label>
					<input type="text" name="_placa" class="form-control" disabled placeholder="Digite o número da placa do veículo" value="${_placa}" required >
				</div>
				<button class="btn btn-success" type="submit">Salvar</button>
				<a href="./ListarVeiculos?usuarioId=${_usuarioId}">Cancelar</a>
			</div>
		</form>
	</div>
</div>
</body>
</html>