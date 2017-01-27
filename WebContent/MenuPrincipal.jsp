<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="./css/index.css">
<link rel="stylesheet" type="text/css" href="./scripts/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="./scripts/jquery/jquery.min.js"></script>
<script type="text/javascript" src="./scripts/bootstrap/js/bootstrap.min.js"></script>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Gerenciador de Caronas - COMP3</a>
        </div>
        <div class="collapse navbar-collapse js-navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown mega-dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu<span class="caret"></span></a>
                    <ul class="dropdown-menu mega-dropdown-menu">
                        <li class="col-sm-4">
                            <ul>
                                <li class="dropdown-header">Gerenciar</li>
                                <li><a href="./ListarUsuarios">Usuários</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.nav-collapse -->
    </nav>
</div>
<script type="application/javascript">
    $(document).ready(function(){
        <% if(request.getParameter("fixedMenu") == null || request.getParameter("fixedMenu").toString() == "false") {%>
        $(".dropdown").hover(
                function() {
                    $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideDown("400");
                    $(this).toggleClass('open');
                },
                function() {
                    $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideUp("400");
                    $(this).toggleClass('open');
                }
        );
        <%}%>
    });
</script>