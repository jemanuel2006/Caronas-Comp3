<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Criar Grupo</title>
</head>
<body>
	<div class="container">
	 <div class="row">
		<form action="./EditarCarona" method="post">
			<div class="col-md-8">
				<legend>Editar Carona</legend>
				<div class="form-group">
					<label for="_nome">* Entre com o Id do Veículo:</label>
					<input type="text" name="_veiculoId" class="form-control" disabled value="${_veiculoId}"placeholder="Digite o Id do Veículo:" required>
				</div>
				<div class="form-group">
					<label for="_descricao">* Data:</label>
					<input type="Date" name="_dia" class="form-control" disabled value="${_dia}"placeholder="Selecione uma data para a carona" required>
				</div>
				<div class="form-group">
					<label for="_regras">* Horário de saída:</label>
					<input type="Time" name="_hora_saida" class="form-control" value="${_hora_saida}"placeholder="Selecione o horário de saída" required >
				</div>
				<div class="form-group">
					<label for="_limite">* Endereço de origem:</label>
					<input type="number" name="_logradouroOrigemId" class="form-control" disabled value="${_logradouroOrigemId}"placeholder="Digite o limite mínimo para o grupo" required>
				</div>
				<div class="form-group">
					<label for="_emailCriador">* Endereço de destino:</label>
					<input type="email" name="_logradouroDestinoId" class="form-control" disabled value="${_logradouroDestinoId}"placeholder="Digite o email do criador" required>
				</div>
				<button class="btn btn-success" type="submit">Salvar</button>
				<a href="./ListarCarona">Cancelar</a>
			</div>
		</form>
	</div>
</div>
</body>
</html>