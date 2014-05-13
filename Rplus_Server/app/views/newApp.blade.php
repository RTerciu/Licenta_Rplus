@extends('layout.layout')

@section('content')
	<div class="page-header">
		<h1>Sectiunea pentru dezvoltatori! <small>Administreaza aplicatiile tale!</small></h1>
	</div>
	
	
	<?php
		$m=Session::get('mesaj');
	?>
	@if(isset($m))
		<div class="alert alert-info">  
			<a href="javascript:$('.alert-info').toggle();" class="close" data-dismiss="alert">×</a>  
			<strong>Info! </strong>{{Session::get('mesaj')}}  
		</div> 
	@endif
	
	<div class="row">
		

	<div class="col-md-8 text-center">
		
		<h3>Aplicatii Aprobate</h3>
	
		@if(isset($approved[0]))
		
		<table class="table table-striped">
			<tr>
				<td>Nume</td>
				<td>Expira la:</td>
				<td>AppKey</td>
			</tr>
			@foreach($approved as $a)
				<tr>
					<td>{{$a->nume}} </td> 
					<td>{{$a->expiresAt['date']}}</td> 
					<td>{{$a->appKey}}</td>
				</tr>
			@endforeach  
		
		
		</table>
		@else
			<p>Nu ai nici o aplicatie aprobata inca!</p>
		@endif
		
		
		<hr>
		
		
		<h3>Aplicatii Pending</h3>
		
		@if(isset($pending[0]))
		<table class="table table-striped">
				<tr>
				<td>Nume</td> <td>Expira la:</td> <td>AppKey</td>
				</tr>
		
			@foreach($pending as $p)
				<tr>
					<td>{{$p->nume}} </td> 
					<td>{{$p->expiresAt['date']}}</td> 
					<td><small>{{$p->appKey}}</small></td>
				</tr>
			@endforeach  
		</table>
		@else
			<p>Nu ai nici o aplicatie in curs de aprobare!</p>
		@endif	
	</div>
	
	<div class="col-md-4">
		<h3>Aplicatie noua in sistem! </h3><hr>
		
			{{Form::open(array('action' => 'AppsController@CreateNewApp', 'role' => 'form' ))}}
				
			<input type="hidden" id="userFrom" name="userFrom" value="{{Auth::user()->id}}" >
			
			
			
			<div class="form-group">
				
				<input type="text" class="form-control" name="nume" id="nume" placeholder="Numele Aplicatiei">
			</div>
			
			<div class="form-group">
			
				<textarea name="descriere" class="form-control" rows="10" id="mesaj" placeholder="Despre aplicatia dumneavoastra"></textarea>
			</div>
			<div class="text-center">
				<button class="btn btn-primary" type="submit" >Trimite Cerere</button>
			</div>
			{{Form::close()}}
	</div>	
		
	</div>
@stop