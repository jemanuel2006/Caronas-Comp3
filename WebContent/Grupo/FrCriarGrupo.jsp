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
		<form action="./CriarGrupo" method="post">
			<div class="col-md-8">
				<legend>Editar Grupo</legend>
				<div class="form-group">
					<label for="_nome">* Nome:</label>
					<input type="text" name="_nome" class="form-control" placeholder="Digite um nome para o grupo" required>
				</div>
				<div class="form-group">
					<label for="_descricao">* Descrição:</label>
					<input type="text" name="_descricao" class="form-control" placeholder="Digite uma descrição pra o grupo" required>
				</div>
				<div class="form-group">
					<label for="_regras">* Regras:</label>
					<input type="text" name="_regras" class="form-control" placeholder="Digite as regras do grupo" required >
				</div>
				<div class="form-group">
					<label for="_limite">* Limite mínimo:</label>
					<input type="number" name="_limite" class="form-control" placeholder="Digite o limite mínimo para o grupo" required>
				</div>
				<div class="form-group">
					<label for="_emailCriador">* Email do criador:</label>
					<input type="email" name="_emailCriador" class="form-control" placeholder="Digite o email do criador" required>
				</div>
				<button class="btn btn-success" type="submit">Salvar</button>
				<a href="./ListarGrupo">Cancelar</a>
			</div>
		</form>
	</div>
</div>
</body>
</html>