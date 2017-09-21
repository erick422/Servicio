var Redondear2 = function(numero){
    var num = parseFloat(numero);
    return num.toFixed(2);
};

var FormatoFecha = function(inFecha,format){
    $(inFecha).datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        todayHighlight: true,
        autoclose: true,
        format:format,
        endDate:new Date()
    });
};

var NumeroDosDecimales = function(inNumero){
    $(inNumero).inputmask("decimal",{
        min:0.00,
        integerDigits: 7,
        allowMinus: false,
        digits: 2
    });
    $(inNumero).on("blur",function(){
        if($(this).val()!==""){$(this).val(Redondear2($(this).val()));}
    });
};

var NumeroDosDecimalesxDefectoUno = function(inNumero){
    $(inNumero).inputmask("decimal",{
        min:0.00,
        integerDigits: 7,
        allowMinus: false,
        digits: 2
    });
    $(inNumero).on("blur",function(){
        if($(this).val()===""){$(this).val("1.00");}else{$(this).val(Redondear2($(this).val()));}
    });
};

var NumeroEntero = function(inNumero){
    $(inNumero).inputmask("integer",{
        min:0,
        integerDigits: 7,
        allowMinus: false
    });
};

var NumeroEntero = function(inNumero,totDigitos){
    $(inNumero).inputmask("integer",{
        min:0,
        integerDigits: totDigitos,
        allowMinus: false
    });
};

var uploadAviso = function(mensaje){
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "3000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    toastr.warning(mensaje, 'Alerta del Sistema');
    ///toastr["warning"](mensaje);
};

var uploadMsn = function(mensaje,tipo){
    toastr.options = {
        closeButton: true,
        progressBar: true,
        positionClass: "toast-top-full-width",
        showMethod: 'slideDown',
        hideMethod: "fadeOut",
        timeOut: 8000
    };
    if(tipo === "OK"){
        toastr.success(mensaje, 'Exito');
    }else if(tipo === "ERROR"){
        toastr.error(mensaje, 'ERROR');
    }
};

var uploadMsnSmall = function(mensaje,tipo){
   /* toastr.options = {
        closeButton: true,
        showMethod: 'slideDown',
        hideMethod: "fadeOut",
        timeOut: 6000
    };
    if(tipo === "OK"){
        toastr.success(mensaje, 'Exito');
    }else if(tipo === "ERROR"){
        toastr.error(mensaje, 'ERROR');
    }else if(tipo === "ALERTA"){
        toastr.warning(mensaje, 'Alerta del Sistema');
    }*/
};

var progressHandlingFunction = function (e,elemento){
    if(e.lengthComputable){
        var percentComplete = Math.ceil((e.loaded/e.total)*100);
        $(elemento).width(((e.loaded/e.total)*100)+'%'); //dynamicaly change the progress bar width
        $(elemento).attr("aria-valuenow", percentComplete);
        $(elemento).html( ((e.loaded/e.total)*100).toFixed()+'%'); // show the percentage number
    }
};

var reiniciar = function(id){
    $("#"+id).width('0%');
    $("#"+id).attr("aria-valuenow", '0');
    $("#"+id).html('');
};

var CerrarSession = function(){
    $("#form_logout").submit();
};

$("#btnSalir").on("click",function(){
    bootbox.confirm({
        message: "<strong>&iquest;Esta Seguro que desea Salir del Sistema?</strong>",
        buttons: {
            confirm: {
                label: "<i class='fa fa-check'></i> Si",className: "btn-primary btn-sm sbold"
            },
            cancel: {
                label: "<i class='fa fa-times'></i> No",className: "btn-sm sbold"
            }
        },
        callback: function(result) {
            if(result){CerrarSession();}
        }
    });
});

var keyup_input_general_3 = function (elemento, form, modal) {
    $.each($(elemento), function () {
        $(this).on("keyup", function () {
            estilo_error(false, this);
        });
    });
};

var estilo_error = function (estilo, elemento){
    if (estilo === true) {
        $(elemento).closest('.form-group').addClass('has-error');
        $(elemento).addClass('error-input');
    } else {
        $(elemento).closest('.form-group').removeClass('has-error');
        $(elemento).removeClass('error-input');
    }
};

var style_error_cbo_final = function (id_cbo, condition) {
    if (condition === true) {
        $(id_cbo).closest('.form-group').addClass('has-error');
    } else {
        $(id_cbo).closest('.form-group').removeClass('has-error');
    }
    var style_remove = condition ? "style_cbo" : "red error-input";
    var style_add = condition ? "red error-input" : "style_cbo";
    $(id_cbo).selectpicker('setStyle', style_remove, 'remove');
    $(id_cbo).selectpicker({style: style_add, size: 5});
    $(id_cbo).selectpicker('refresh');
};

function acceptLet(a){
    tecla=document.all?a.keyCode:a.which;
    if(8==tecla)return!0;
    patron=/[A-Za-z\u00f1\u00d1\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\s]/;
    te=String.fromCharCode(tecla);
    return patron.test(te)
}

var validarPerfil = function(){
    var sevalido = "1";
    if( $("#claveanterior").val() === "" ){
        estilo_error(true,'#claveanterior');
        sevalido = "0";
    }
    if( $("#clavenueva").val() === ""){
        estilo_error(true,'#clavenueva');
        sevalido = "0";
    }
    if( $("#reclavenueva").val() === "" ){
        estilo_error(true,'#reclavenueva');
        sevalido = "0";
    }
    if( $("#clavenueva").val() !== "" && $("#reclavenueva").val() !== ""  ){
        if($("#clavenueva").val() !== $("#reclavenueva").val()){
            estilo_error(true,'#reclavenueva');
            uploadMsnSmall('Las Claves deben de ser Iguales.', 'ERROR');
            sevalido = "0";
        }
    }
    return sevalido === "1";
};


$("#btnGuardarMyPerfil").on("click",function(){
    if(validarPerfil()) {
        var cargando = Ladda.create(document.querySelector('#btnGuardarMyPerfil'));
        var anterior = $("#claveanterior").val();
        var nueva = $("#clavenueva").val();
        var renueva = $("#reclavenueva").val();
        cargando.start();
        $.ajax({
            type: 'post',
            url: 'save_myperfil',
            data: {anterior: anterior, nueva: nueva, renueva: renueva},
            dataType: 'json',
            success: function (data) {
                if (data !== null) {
                    if(data.dato==="OK"){
                        $("#modalMyPerfil").modal("hide");
                        cargando.stop();
                        uploadMsnSmall(data.msj,'OK');
                    }else if(data.dato==="ERROR"){
                        if(data.listado.length>0){
                            for (var i = 0; i < data.listado.length; i++) {
                                if(data.listado[i] === "E1"){estilo_error(true,"#claveanterior");}
                                if(data.listado[i] === "E2"){estilo_error(true,"#clavenueva");}
                                if(data.listado[i] === "E3"){estilo_error(true,"#reclavenueva");}
                                if(data.listado[i] === "E4"){estilo_error(true,"#reclavenueva");uploadMsnSmall("Las Claves deben de ser iguales.",'OK');}
                            }
                        }else{
                            uploadMsnSmall(data.msj,'ERROR');
                        }
                        cargando.stop();
                    }
                } else {
                    cargando.stop();
                    uploadMsnSmall('Problemas con el sistema', 'ERROR');
                }
            },
            error: function (jqXHR, status, error) {
                cargando.stop();
                uploadMsnSmall(jqXHR.responseText, 'ERROR');
            }
        });
    }
});

$("#myperfil").on("click",function(){
    $("#frmMyPerfil")[0].reset();
    estilo_error(false,'#claveanterior');
    estilo_error(false,'#clavenueva');
    estilo_error(false,'#reclavenueva');
    $("#modalMyPerfil").modal("show");
});

$("#btnCollapse").on("click",function(){
    if( $("#demo").hasClass("in")){
        $(this).children().removeClass("fa fa-chevron-up").addClass("fa fa-chevron-down");
    }else{
        $(this).children().removeClass("fa fa-chevron-down").addClass("fa fa-chevron-up");
    }
});

//keyup_input_general_3("#frmMyPerfil input", "#frmMyPerfil", "#modalMyPerfil");
/*
var reloadSession = function () {
    $.ajax({
        type: 'post',
        url: 'reload_session',
        dataType: 'json',
        success: function (data) {
            if (data !== null) {
                if(data.dato==="OK"){
                    console.log("Session activa.");
                }else if(data.dato==="ERROR"){
                    uploadMsnSmall(data.msj,'ERROR');
                }
            } else {
                uploadMsnSmall('Problemas con el sistema', 'ERROR');
            }
        },
        error: function (jqXHR, status, error) {
            uploadMsnSmall(jqXHR.responseText, 'ERROR');
        }
    });
};

setInterval(reloadSession,600000);*/