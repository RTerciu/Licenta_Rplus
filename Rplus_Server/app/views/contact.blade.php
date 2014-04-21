@extends('layout.layout')

@section('content')
	<div class="page-header">
		<h1>Cine si de ce! <small> Mai multe informatii!</small></h1>
	</div>

<div class="row">
	<div class="col-md-3">
	<h2><strong>Radu Terciu</strong></h2>
	<img src="img/poza.jpg" class="img-rounded"/>
	
	</div>
	<div class="col-md-9">
	
	<dl class="dl-horizontal">
	  <dt>E-mail</dt><dd>radurt25@gmail.com</dd>
	  <dt>Telefon</dt><dd>0751 015528</dd>
	  <dt>Universitate</dt><dd>Academia Tehnica Militara</dd>
	  <dt>Grupa</dt><dd>E214A</dd>
	  <dt>Nr Tema de licenta</dt><dd>27</dd>
	  <dt>Titlu Tema de Licenta</dt><dd>Aplicatie Android de autentificare folosind mijloace Criptografice</dd>
	  <dt>Conducator tema</dt><dd>Dl. Mihai Togan</dd>
	  <dt>Rezumat Tema</dt><dd>Aplicatie integrata care permite autentificarea din cadrul unei aplicatii Android folosing o singura apasare de buton, 
		prin implementarea unei aplicatii intermediare pe telefon care gestioneaza comunicatia cu acest server care ofera servicii de tip RestFul 
		prin intermediul protocolului HTTP in mod securizat.</dd>
	  <dt>Profil Github</dt><dd><a href="https://github.com/RTerciu">RTerciu pe Github</a></dd>
	</dl>
	
	</div>
</div>
@stop