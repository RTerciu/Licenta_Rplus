@extends('layout.layout')

@section('content')
	<div class="page-header">
		<h1>Aplicatiile tale <small>Acestea sunt locurile in care ai folosit R+ !</small></h1>
	</div>
	<div class="row">
	
		<div class="col-md-3"></div>
		<div class="col-md-6">
		<h3>Lista de aplicatii unde ati folosit autentificarea prin Rplus</h3><hr>
			<table class="table table-striped">
			<tr><td>Numele Aplicatiei</td><td>Prima folosire</td></tr>
				@foreach($userapps as $app)
					<tr>
					<td>{{$app->appName}}</td><td>{{$app->created_at}}</td>
					</tr>
				@endforeach
			</table>
		</div>
		<div class="col-md-3"></div>
	</div>
	@stop