<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../MenuPrincipal.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1;">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
<link type="text/css" href="css/bootstrap.min.css" />
<link type="text/css" href="css/bootstrap-timepicker.min.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-timepicker.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script src="//code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" >

$(document).ready(function() {

    function limpa_formulário_cep() {
        // Limpa valores do formulário de cep.
        $("#rua").val("");
        $("#bairro").val("");
        $("#cidade").val("");
        $("#uf").val("");
        
    }
    
    //Quando o campo cep perde o foco.
    $("#cep").blur(function() {

        //Nova variável "cep" somente com dígitos.
        var cep = $(this).val().replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
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
<title>Criar Carona</title>
</head>
<body>
	<div class="container">
	 <div class="row">
		<form action="./CriarCarona" method="post">
			<div class="col-md-8">
				<legend>Editar Carona</legend>
				<div class="form-group">
					<label for="_veiculoId">* Entre com o Id do Veículo:</label>
					<input type="text" name="_veiculoId" class="form-control" placeholder="Digite o Id do Veículo:" required>
				</div>
				<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
				<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
				
				<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
				
				<style type="text/css">
				
				
				</style>
				<div class="form-group">
				        <label for="_dia">* Selecione a data da carona</label>
				        <div class="col-xs-5 date">
				            <div class="input-group input-append date" id="dateRangePicker">
				                <input type="text" class="form-control" name="_dia" />
				                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
				            </div>
				            <script>
									$(document).ready(function() {
									    $('#dateRangePicker')
									        .datepicker({
									            format: 'mm/dd/yyyy',
									            startDate: '01/01/2010',
									            endDate: '12/30/2020'
									        })
									        .on('changeDate', function(e) {
									            // Revalidate the date field
									            $('#dateRangeForm').formValidation('revalidateField', 'date');
									        });
									
									    $('#dateRangeForm').formValidation({
									        framework: 'bootstrap',
									        icon: {
									            valid: 'glyphicon glyphicon-ok',
									            invalid: 'glyphicon glyphicon-remove',
									            validating: 'glyphicon glyphicon-refresh'
									        },
									        fields: {
									            date: {
									                validators: {
									                    notEmpty: {
									                        message: 'The date is required'
									                    },
									                    date: {
									                        format: 'MM/DD/YYYY',
									                        min: '01/01/2010',
									                        max: '12/30/2020',
									                        message: 'The date is not a valid'
									                   		 }
									                	}
									            	}
									        	}
									    	});
										});
							</script>
				        </div>
				    </div>
				    
				<div class="form-group">
					<label for="_hora_saida">* Horário de saída:</label>
							<div class="input-group bootstrap-timepicker timepicker">
					            <input id="timepicker1" type="text" class="form-control input-small">
					            <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
					        </div>
					 
					        <script type="text/javascript">
					            $('#timepicker1').timepicker();
					        </script>
						
				</div>		
				<div class="form-group">
					<label>* Endereço de origem:</label>
					<input type="text" name="cep" id="cep" class="form-control" placeholder="Digite o CEP do endereço de origem: " required>
					
				</div>
				<div class="form-group">
					<label>Rua:</label>
					<input type="text" name="rua" id="rua" class="form-control">
					
				</div>
				<div class="form-group">
					<label>Número:</label>
					<input type="text" name="numero" class="form-control"required>
				</div>
				<div class="form-group">
					<label>Bairro:</label>
					<input type="text" name="bairro" id="bairro" class="form-control">
				</div>
				
				<div class="form-group">
					<label>Cidade:</label>
					<input type="text" name="cidade" id="cidade" class="form-control">
					
				</div>
				<div class="form-group">
					<label>Estado:</label>
					<input type="text" name="uf" id="uf" class="form-control">
					
				</div>
				
				
				<div class="form-group">
					<label>* Endereço de destino:</label>
					<input type="text" name="cep" id="cep" class="form-control" placeholder="Digite o CEP do endereço de origem: " required>
					
				</div>
				<div class="form-group">
					<label>Rua:</label>
					<input type="text" name="rua" id="rua" class="form-control">
					
				</div>
				<div class="form-group">
					<label>Número:</label>
					<input type="text" name="numero" class="form-control"required>
				</div>
				<div class="form-group">
					<label>Bairro:</label>
					<input type="text" name="bairro" id="bairro" class="form-control">
				</div>
				
				<div class="form-group">
					<label>Cidade:</label>
					<input type="text" name="cidade" id="cidade" class="form-control">
					
				</div>
				<div class="form-group">
					<label>Estado:</label>
					<input type="text" name="uf" id="uf" class="form-control">
					
				</div>
				
				<button class="btn btn-success" type="submit">Salvar</button>
				<a href="./ListarCarona">Cancelar</a>
				
				
				
			</form>
		</div>
		
	</div>
</div>
</body>
</html>