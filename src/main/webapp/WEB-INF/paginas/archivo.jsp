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
    <script type="text/javascript" src="${contextPath}/resources/assets/js/plugins/visualization/d3/d3.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/plugins/visualization/d3/d3_tooltip.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/plugins/forms/styling/switchery.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/plugins/forms/styling/uniform.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/plugins/forms/selects/bootstrap_multiselect.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/plugins/ui/moment/moment.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/plugins/pickers/daterangepicker.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assets/js/core/app.js"></script>
    <!-- /theme JS files -->
</head>

<body>

<!-- Main navbar -->
<div class="navbar navbar-inverse">
    <div class="navbar-header">
        <a class="navbar-brand" href="index.html"><img src="${contextPath}/resources/assets/images/logo_light.png" alt=""></a>

        <ul class="nav navbar-nav visible-xs-block">
            <li><a data-toggle="collapse" data-target="#navbar-mobile"><i class="icon-tree5"></i></a></li>
            <li><a class="sidebar-mobile-main-toggle"><i class="icon-paragraph-justify3"></i></a></li>
        </ul>
    </div>

    <div class="navbar-collapse collapse" id="navbar-mobile">
        <ul class="nav navbar-nav">
            <li><a class="sidebar-control sidebar-main-toggle hidden-xs"><i class="icon-paragraph-justify3"></i></a></li>
        </ul>

        <p class="navbar-text"><span class="label bg-success">Online</span></p>

        <ul class="nav navbar-nav navbar-right">

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="icon-bubbles4"></i>
                    <span class="visible-xs-inline-block position-right">Messages</span>
                    <span class="badge bg-warning-400">2</span>
                </a>

                <div class="dropdown-menu dropdown-content width-350">
                    <div class="dropdown-content-heading">
                        Messages
                        <ul class="icons-list">
                            <li><a href="#"><i class="icon-compose"></i></a></li>
                        </ul>
                    </div>

                    <ul class="media-list dropdown-content-body">
                        <li class="media">
                            <div class="media-left">
                                <img src="${contextPath}/resources/assets/images/placeholder.jpg" class="img-circle img-sm" alt="">
                                <span class="badge bg-danger-400 media-badge">5</span>
                            </div>

                            <div class="media-body">
                                <a href="#" class="media-heading">
                                    <span class="text-semibold">James Alexander</span>
                                    <span class="media-annotation pull-right">04:58</span>
                                </a>

                                <span class="text-muted">who knows, maybe that would be the best thing for me...</span>
                            </div>
                        </li>

                        <li class="media">
                            <div class="media-left"><img src="${contextPath}/resources/assets/images/placeholder.jpg" class="img-circle img-sm" alt=""></div>
                            <div class="media-body">
                                <a href="#" class="media-heading">
                                    <span class="text-semibold">Richard Vango</span>
                                    <span class="media-annotation pull-right">Mon</span>
                                </a>

                                <span class="text-muted">Other travelling salesmen live a life of luxury...</span>
                            </div>
                        </li>
                    </ul>

                    <div class="dropdown-content-footer">
                        <a href="#" data-popup="tooltip" title="All messages"><i class="icon-menu display-block"></i></a>
                    </div>
                </div>
            </li>

            <li class="dropdown dropdown-user">
                <a class="dropdown-toggle" data-toggle="dropdown">
                    <img src="${contextPath}/resources/assets/images/placeholder.jpg" alt="">
                    <span>Victoria</span>
                    <i class="caret"></i>
                </a>

                <ul class="dropdown-menu dropdown-menu-right">
                    <li><a href="#"><i class="icon-user-plus"></i> My profile</a></li>
                    <li><a href="#"><i class="icon-coins"></i> My balance</a></li>
                    <li><a href="#"><span class="badge bg-teal-400 pull-right">58</span> <i class="icon-comment-discussion"></i> Messages</a></li>
                    <li class="divider"></li>
                    <li><a href="#"><i class="icon-cog5"></i> Account settings</a></li>
                    <li><a href="javascript:void(0);" id="btnSalir"><i class="icon-switch2"></i> Salir</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- /main navbar -->


<!-- Page container -->
<div class="page-container">

    <!-- Page content -->
    <div class="page-content">

        <!-- Main sidebar -->
        <div class="sidebar sidebar-main">
            <div class="sidebar-content">

                <!-- User menu -->
                <div class="sidebar-user">
                    <div class="category-content">
                        <div class="media">
                            <a href="#" class="media-left"><img src="${contextPath}/resources/assets/images/placeholder.jpg" class="img-circle img-sm" alt=""></a>
                            <div class="media-body">
                                <span class="media-heading text-semibold">Victoria Baker</span>
                                <div class="text-size-mini text-muted">
                                    <i class="icon-pin text-size-small"></i> &nbsp;Santa Ana, CA
                                </div>
                            </div>

                            <div class="media-right media-middle">
                                <ul class="icons-list">
                                    <li>
                                        <a href="#"><i class="icon-cog3"></i></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /user menu -->


                <!-- Main navigation -->
                <div class="sidebar-category sidebar-category-visible">
                    <div class="category-content no-padding">
                        <ul class="navigation navigation-main navigation-accordion" id="menu-html">
                        </ul>
                    </div>
                </div>
                <!-- /main navigation -->

            </div>
        </div>
        <!-- /main sidebar -->


        <!-- Main content -->
        <div class="content-wrapper">

            <!-- Content area -->
            <div class="content">
                <!-- Dashboard content -->
                <div class="row">
                    <div class="col-lg-12">

                        <!-- Marketing campaigns -->
                        <div class="panel panel-flat">
                            <div class="panel-heading">
                                <h6 class="panel-title">Marketing campaigns</h6>
                                <div class="heading-elements">
                                    <span class="label bg-success heading-text">28 active</span>
                                    <ul class="icons-list">
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-menu7"></i> <span class="caret"></span></a>
                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li><a href="#"><i class="icon-sync"></i> Update data</a></li>
                                                <li><a href="#"><i class="icon-list-unordered"></i> Detailed log</a></li>
                                                <li><a href="#"><i class="icon-pie5"></i> Statistics</a></li>
                                                <li class="divider"></li>
                                                <li><a href="#"><i class="icon-cross3"></i> Clear list</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="table-responsive">
                                <table class="table text-nowrap">
                                    <thead>
                                    <tr>
                                        <th>Campaign</th>
                                        <th class="col-md-2">Client</th>
                                        <th class="col-md-2">Changes</th>
                                        <th class="col-md-2">Budget</th>
                                        <th class="col-md-2">Status</th>
                                        <th class="text-center" style="width: 20px;"><i class="icon-arrow-down12"></i></th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- /marketing campaigns -->

                    </div>

                </div>
                <!-- /dashboard content -->

                <jsp:include page="frag_logout.jsp"/>
                <!-- Footer -->
                <div class="footer text-muted">
                    Todos los derechos reservados &copy; 2017.
                </div>
                <!-- /footer -->

            </div>
            <!-- /content area -->

        </div>
        <!-- /main content -->

    </div>
    <!-- /page content -->

</div>
<script src="${contextPath}/resources/scripts/bootbox.min.js"></script>
<script src="${contextPath}/resources/scripts/methods.js"></script>
</body>
</html>
