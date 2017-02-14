<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Criar Veículo</title>
</head>
<body>
	<div class="container">
	 <div class="row">
		<form action="./CriarVeiculo" method="post">
			<input name="_id" id="_id" type="hidden" value="${_id}" />
			<div class="col-md-8">
				<legend>Criar Veículo</legend>
				<div class="form-group">
					<label for="_cor">* Cor:</label>
					<input type="text" name="_cor" class="form-control" placeholder="Digite a cor do veículo" required>
				</div>
				<div class="form-group">
					<label for="_modelo">* Modelo:</label>
					<input type="text" name="_modelo" class="form-control" placeholder="Digite o modelo do veículo" required>
				</div>
				<div class="form-group">
					<label for="_placa">* Placa:</label>
					<input type="text" name="_placa" class="form-control" placeholder="Digite o número da placa do veículo" required >
				</div>
				<button class="btn btn-success" type="submit">Salvar</button>
				<a href="./ListarVeiculos">Cancelar</a>
			</div>
		</form>
	</div>
</div>
</body>
</html>