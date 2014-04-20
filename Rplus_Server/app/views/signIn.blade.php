@extends('layout.layout')

@section('content')
	<div class="page-header">
		<h1>Login pe R+! <small>Daca ai un cont deja!</small></h1>
	</div>
	
	{{ Form::open(array('action' => 'UsersController@PostSignIn', 'role' => 'form' , 'class'=>'form-inline'))}}
		<div class="form-group">
		
			@if(Session::has('login_errors'))
				<p class="alert alert-danger">User name or password incorrect.</p>
			@endif
		
			<label for="email">Email</label>
			<input type="text" class="form-control" name="email" id="email" />
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" name="password" id="password" />
		</div>
		<input type="submit" class="btn btn-primary" name="submit" value="Sign In" />
		<div class="form-group">
			<label for="password"><a href="{{url('signup')}}">Sau inregistreaza-te daca nu ai facut-o deja!</a></label>
		</div>
	{{Form::close()}}
@stop