@extends('layout.layout')

@section('content')

	<div class="page-header">
		<h1>Datele tale <small>Cu ele te autentifici in lume!</small></h1>
	</div>


{{Form::model($user, array('url' =>'update' , 'method'=>'POST' , 'role' => 'form'))	}}	
	
<div class="row">


<div class="col-md-6">
		<div class="form-group">
			<label for="email">Email</label>
			{{ Form::email('email',null,array('class'=>'form-control','id'=>'email')) }}
		</div>
		<div class="form-group">
			<label for="username">Username</label>
			{{ Form::text('username',null,array('class'=>'form-control','id'=>'username')) }}
		</div>
</div>
<div class="col-md-6">
		<div class="form-group">
			<label for="password">Password</label>
			{{ Form::password('password',array('class'=>'form-control','id'=>'password')) }}
		</div>
		<div class="form-group">
			<label for="avatar">Avatar</label>
			{{ Form::file('avatar',array('class'=>'form-control','id'=>'avatar')) }}
		</div>
		
</div>

</div>
<p class="text-center">
<input type="submit" class="btn btn-primary" name="submit" value="Modifica Datele de autentificare" />
</p>

{{Form::close()}}
@stop