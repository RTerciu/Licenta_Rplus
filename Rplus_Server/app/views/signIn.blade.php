@extends('layout.layout')

@section('content')
	<div class="page-header">
		<h1>Sign in <small>If you signed up!</small></h1>
	</div>
	
	{{ Form::open(array('action' => 'UsersController@PostSignIn', 'role' => 'form' ))}}
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
	{{Form::close()}}
@stop