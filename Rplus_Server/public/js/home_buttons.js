$(document).ready(function() {

$("#detalii_suplimentare").hide();

$("#form_signUp :input").keyup(function() {

var a=$("#email").val();
var b=$("#username").val();
var c=$("#password").val();
var d=$("#password2").val();


if(a!=''&&b!=''&&c!=''&&d!='')
	$("#detalii_suplimentare").show();
else
	$("#detalii_suplimentare").hide();


});

})