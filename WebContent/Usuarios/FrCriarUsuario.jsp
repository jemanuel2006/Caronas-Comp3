<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Criar Usuário</title>
</head>
<body>
	<div class="container">
	 <div class="row">
		<form action="./CriarUsuario" method="post">
			<div class="col-md-8">
				<legend>Editar Usuário</legend>
				<div class="form-group">
					<label for="_nome">* Nome:</label>
					<input type="text" name="_nome" class="form-control" placeholder="Digite um nome para o usuário" required>
				</div>
				<div class="form-group">
					<label for="_email">* Email:</label>
					<input type="email" name="_email" class="form-control" placeholder="Digite um email válido" required>
				</div>
				<div class="form-group">
					<label for="_telefone">* Telefone:</label>
					<input type="text" name="_telefone" class="form-control" placeholder="Digite o telefone do usuário" required>
				</div>
				<button class="btn btn-success" type="submit">Salvar</button>
				<a href="./ListarUsuarios">Cancelar</a>
			</div>
		</form>
	</div>
</div>
</body>
</html>