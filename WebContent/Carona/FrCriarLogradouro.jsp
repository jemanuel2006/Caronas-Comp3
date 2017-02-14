<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" >

$(document).ready(function() {

    function limpa_formulário_cep() {
        $("#rua").val("");
        $("#bairro").val("");
        $("#cidade").val("");
        $("#uf").val("");
    }
    
    $("#cep").on('blur', function() {
        var cep = $(this).val().replace(/\D/g, '');

        if (cep && cep != "") {
            var regex = /^[0-9]{8}$/;

            if(regex.test(cep)) {
                $("#rua").val("...");
                $("#bairro").val("...");
                $("#cidade").val("...");
                $("#uf").val("...");
                

                //Consulta o webservice viacep.com.br/
                $.getJSON("//viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                    if (!("erro" in dados)) {
                        //Atualiza os campos com os valores da consulta.
                        $("#rua").val(dados.logradouro);
                        $("#bairro").val(dados.bairro);
                        $("#cidade").val(dados.localidade);
                        $("#uf").val(dados.uf);
                       
                    } //end if.
                    else {
                        //CEP pesquisado não foi encontrado.
                        limpa_formulário_cep();
                        alert("CEP não encontrado.");
                    }
                });
            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    });
});

</script>
<title>Criar Logradouro</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
			<legend>Criar Logradouro</legend>
				<form action="./CriarLogradouro" method="post">
					<div class="form-group">
						<label>* CEP:</label>
						<input type="text" name="cep" id="cep" class="form-control" placeholder="Digite o CEP do endereço de origem" required>
					</div>
					<div class="form-group">
						<label>* Rua:</label>
						<input type="text" name="rua" id="rua" class="form-control" required>
					</div>
					<div class="form-group">
						<label>* Bairro:</label>
						<input type="text" name="bairro" id="bairro" class="form-control" required>
					</div>
					<div class="form-group">
						<label>* Cidade:</label>
						<input type="text" name="cidade" id="cidade" class="form-control" required>
					</div>
					<div class="form-group">
						<label>* Estado:</label>
						<input type="text" name="uf" id="uf" class="form-control" required>
					</div>
					<div class="form-group">
						<label>* Número:</label>
						<input type="number" name="numero" id="numero" class="form-control" required>
					</div>
					<button class="btn btn-success" type="submit">Salvar</button>
					<a href="./ListarLogradouros">Cancelar</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>