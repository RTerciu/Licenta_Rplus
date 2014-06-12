@extends('layout.layout')

@section('content')
	<div class="page-header">
		<h1 id="formular_inregistrare">Inregistreaza-te acum! <small>Sa nu te mai inregistrezi de multe ori de acum incolo!</small></h1>
	</div>
	
	{{ Form::open(array('action' => 'UsersController@ShowSignUp', 'files' => true, 'role' => 'form' ,'id'=>'form_signUp' ))}}
	
<div class="row">
	<div class="col-md-6">
	<h3>Intai sa stabilim lucrurile elementare</h3>
		<div class="form-group">
			<label for="email">Mai intai Email-ul tau!</label>
			<input type="text" class="form-control" name="email" id="email" />
		</div>
		<div class="form-group">
			<label for="username">Unele site-uri folosesc adresarea pe baza de Porecla(Username)</label>
			<input type="text" class="form-control" name="username" id="username" />
		</div>
		<div class="form-group">
			<label for="password">Ai nevoie totusi si de o parola pentru a-ti proteja celelalte conturi</label>
			<input type="password" class="form-control" name="password" id="password" />
		</div>
		<div class="form-group">
			<label for="password2">Mai scrie parola odata sa ne asiguram ca nu ai gresit-o prima data</label>
			<input type="password" class="form-control" name="password2" id="password2" />
		</div>
		<div class="form-group">
			<label for="data_nasterii">Poti sa ne spui data ta de nastere? Unele site-uri iti dau cadouri de ziua ta!</label>
			<input type="date" class="form-control" name="data_nasterii" id="data_nasterii" />
		</div>

	</div>
	<div class="col-md-6" id="detalii_suplimentare">
	<h3>Acum sa vedem si detalii, cine stie cine le cere!</h3>
		<div class="form-group">
			<label for="oras">Din ce oras esti ?</label>
			<input type="text" class="form-control" name="oras" id="oras" />
		</div>
		<div class="form-group">
			<label for="resedinta">Si spune-ne, acum in ce oras locuiesti?</label>
			<input type="text" class="form-control" name="resedinta" id="resedinta" />
		</div>
		<div class="form-group">
			<label for="scoala">Care e scoala pe care vrei sa o afisam la profilul tau ?</label>
			<input type="text" class="form-control" name="scoala" id="scoala" />
		</div>

		<div class="form-group">
			<label for="despre">Spune-ne si o fraza care sa te caracterize! Scurt si la obiect!</label>
			<input type="text" class="form-control" name="despre" id="despre" />
		</div>
		<div class="form-group">
			<label for="avatar">Pune si o poza aici, sa ne recunoasca lumea!</label>
			<input type="file" class="form-control" name="avatar" id="avatar" />
		</div>
	
	</div>
		
</div>		
		<input type="submit" class="btn btn-primary" name="submit" value="Register" />
	{{Form::close()}}
@stop