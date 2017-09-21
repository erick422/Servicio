<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" %>
<div id="static_logout" class="modal fade  bs-modal" tabindex="-1" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-sm">
        <div class="modal-content animated">
            <!-- BEGIN FORM-->
            <form action="logout.html" id="form_logout" class="form-horizontal" method="post">     
                <div class="modal-header bg-green bg-font-green">
                    <button type="button" class="close hide" data-dismiss="modal" aria-hidden="true"></button>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-10" style="font-size:14px;font-size: 18px;font-weight: 400;text-shadow: 1px 1px 2px rgba(150, 150, 150, 1);">
                                <i class="glyphicon glyphicon-question-sign"></i> Confirmación
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-3 col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-2 text-right"><i class='glyphicon glyphicon-log-out'> </i></div>                                      
                        </div>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="portlet-body form">
                        <div class="form-body">
                            ¿Está seguro que desea cerrar la sesión del Sistema SGC?
                        </div> 
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-info btn-outline size_tooltip_480" data-original-title="Cerrar Session"  id="btn-logout-submit" style="width:110px !important;"><i class="glyphicon glyphicon-ok"> </i>Aceptar</button>
                    <button type="button" class="btn dark btn-outline" id="btn-logout-close" data-dismiss="modal"><i class="glyphicon glyphicon-remove"> </i>Cancelar</button>
                </div>                               
            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>

<div class="modal inmodal" id="modalMyPerfil" role="dialog"  data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content animated flipInY fadeIn">
            <div class="modal-header panel-title bg-blue-grey-800 color-white" style="padding: 13px!important;">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title text-left" style="font-size: 18px!important;">Datos de Perfil de Usuario</h4>
            </div>
            <div class="modal-body">
                <form method="post" class="form-horizontal" name="frmMyPerfil" id="frmMyPerfil" onsubmit="return false;">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" style="padding-left:0px!important;padding-right:0px!important;">Contraseña Anterior</label>
                        <div class="col-sm-5"><input type="password" class="form-control input-sm" name="claveanterior" id="claveanterior" maxlength="30" /></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" style="padding-left:0px!important;padding-right:0px!important;">Contraseña Nueva</label>
                        <div class="col-sm-5"><input type="password" class="form-control input-sm"  name="clavenueva" id="clavenueva" maxlength="30"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" style="padding-left:0px!important;padding-right:0px!important;">Vuelve a Escribir</label>
                        <div class="col-sm-5"><input type="password" class="form-control input-sm" name="reclavenueva" id="reclavenueva" maxlength="30" /></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">
                    <i class="fa fa-times"></i> Salir
                </button>
                <button type="button" class="btn btn-success ladda-button"  name="btnGuardarMyPerfil" id="btnGuardarMyPerfil"
                        data-style="zoom-in"  data-spinner-size="30">
                        <span class="ladda-label">
                            <i class="glyphicon glyphicon-floppy-disk"></i> Guardar
                        </span>
                </button>
            </div>
        </div>
    </div>
</div>

