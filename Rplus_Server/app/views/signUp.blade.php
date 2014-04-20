@extends('layout.layout')

@section('content')
	<div class="page-header">
		<h1>Inregistreaza-te acum! <small>Sa nu te mai inregistrezi de multe ori de acum incolo!</small></h1>
	</div>
	
	{{ Form::open(array('action' => 'UsersController@ShowSignUp', 'files' => true, 'role' => 'form' ))}}
		<div class="form-group">
			<label for="email">Email</label>
			<input type="text" class="form-control" name="email" id="email" />
		</div>
		<div class="form-group">
			<label for="username">Username</label>
			<input type="text" class="form-control" name="username" id="username" />
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" name="password" id="password" />
		</div>
		<div class="form-group">
			<label for="avatar">Avatar</label>
			<input type="file" class="form-control" name="avatar" id="avatar" />
		</div>
		<input type="submit" class="btn btn-primary" name="submit" value="Register" />
	{{Form::close()}}
@stop