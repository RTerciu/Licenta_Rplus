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
		
		<div class="form-group">
			<label for="birthday">Data Nasterii</label>
			{{ Form::input('date','birthday',null,array('class'=>'form-control','id'=>'birthday')) }}
		</div>
		
		<div class="form-group">
			<label for="oras">Orasul Natal</label>
			{{ Form::text('oras',null,array('class'=>'form-control','id'=>'oras')) }}
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
		
		<div class="form-group">
			<label for="resedinta">Resedinta</label>
			{{ Form::text('resedinta',null,array('class'=>'form-control','id'=>'resedinta')) }}
		</div>
		
		<div class="form-group">
			<label for="despre">Despre</label>
			{{ Form::text('despre',null,array('class'=>'form-control','id'=>'despre')) }}
		</div>
		
</div>

</div>
<p class="text-center">
<input type="submit" class="btn btn-primary" name="submit" value="Modifica Datele de autentificare" />
</p>

{{Form::close()}}
@stop