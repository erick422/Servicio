<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gesti&oacute;n Comercial</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <!-- Global stylesheets -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
    <link href="${contextPath}/resources/assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/resources/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/resources/assets/css/core.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/resources/assets/css/components.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/resources/assets/css/colors.css" rel="stylesheet" type="text/css">
    <!-- /global stylesheets -->
    <!-- Core JS files -->
    <script type="text/javascript" src="${contextPath}/resources/assets/js/plugins/loaders/pace.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/core/libraries/jquery.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/core/libraries/bootstrap.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/plugins/loaders/blockui.min.js"></script>
    <!-- /core JS files -->
    <!-- Theme JS files -->
    <script type="text/javascript" src="${contextPath}/resources/assets/js/plugins/forms/validation/validate.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/plugins/forms/styling/uniform.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/core/app.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/pages/login_validation.js"></script>
    <!-- /theme JS files -->
    <jsp:useBean id="msj_login" scope="request" class="java.lang.String"/>
    <jsp:useBean id="error" scope="request" class="java.lang.String"/>
</head>

<body class="login-container login-cover">

<!-- Page container -->
<div class="page-container">

    <!-- Page content -->
    <div class="page-content">

        <!-- Main content -->
        <div class="content-wrapper">
            <!-- Content area -->
            <div class="content pb-20">
                <!-- Form with validation -->
                <form action="login.html" class="form-validate" method="post">
                    <input type="hidden" name="keykey" id="keykey" />
                    <input type="hidden" name="navi" id="navi" />
                    <input type="hidden" id="error" value="<%=error%>"/>
                    <input type="hidden" id="msj_login" value="<%=msj_login%>" />
                    <div class="panel panel-body login-form">
                        <div class="text-center">
                            <div class="" width="250px;">
                                <img src="/resources/assets/img/zender_logo.png" width="250px;">
                            </div>
                            <!--div class="icon-object border-slate-300 text-slate-300">
                                <i class="icon-reading"></i>
                            </div-->
                            <h5 class="content-group">Ingrese a su cuenta</h5>
                        </div>
                        <div class="form-group has-feedback has-feedback-left">
                            <input type="text" class="form-control" placeholder="Username" name="username" required="required">
                            <div class="form-control-feedback">
                                <i class="icon-user text-muted"></i>
                            </div>
                        </div>
                        <div class="form-group has-feedback has-feedback-left">
                            <input type="password" class="form-control" placeholder="Password" name="password" required="required">
                            <div class="form-control-feedback">
                                <i class="icon-lock2 text-muted"></i>
                            </div>
                        </div>
                        <!--div class="form-group login-options">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" class="styled" checked="checked">
                                        Remember
                                    </label>
                                </div>

                                <div class="col-sm-6 text-right">
                                    <a href="login_password_recover.html">Forgot password?</a>
                                </div>
                            </div>
                        </div-->
                        <div class="form-group">
                            <button type="submit" class="btn bg-blue btn-block">Iniciar sesi&oacute;n <i class="icon-arrow-right14 position-right"></i></button>
                        </div>
                        <span class="help-block text-center no-margin">Todos los derechos reservados &copy; 2017.</span>
                    </div>
                </form>
                <!-- /form with validation -->
            </div>
            <!-- /content area -->
        </div>
        <!-- /main content -->
    </div>
    <!-- /page content -->
</div>
<!-- /page container -->
<script type="text/javascript" src="${contextPath}/resources/assets/js/propios/bootstrap-growl/jquery.bootstrap-growl.js"></script>
<script type="text/javascript" src="${contextPath}/resources/scripts/login.js"></script>
</body>
</html>
