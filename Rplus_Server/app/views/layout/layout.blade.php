<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>R+ Autentificare intr-un singur pas oriunde!</title>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" href="{{asset('js/bootstrap.min.js')}}"></script>
	<script type="text/javascript" src="{{asset('js/home_buttons.js')}}"></script>
	<script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
	
	<script>
        tinymce.init({selector:'textarea#descriere_job'});
	</script>
	
	<link rel="stylesheet" href="{{ asset('css/bootstrap.min.css')}}"/>
	<link rel="stylesheet" href="{{ asset('css/style_home.css')}}" type="text/css" media="screen">
	
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a href="{{URL::to('/')}}" class="navbar-brand">R+ Autentificare</a>
				</div>
				<ul class="nav navbar-nav">
					<li>
						<a href="{{URL::to('/profile')}}" class="navbar-brand">Profil</a>
					</li>
					
					<li>
						<a href="{{URL::to('/apps')}}" class="navbar-brand">MyUsedApps</a>
					</li>
					
					<li>
						<a href="{{URL::to('/new/app')}}" class="navbar-brand">MyCreatedApps</a>
					</li>
					
					<li>
					<a href="{{URL::to('contact')}}" class="navbar-brand">Contact</a>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					@if(Auth::check())
						<li class="dropdown">

							<a href="javascript:$('.dropdown-menu').toggle();"  class="dropdown-toggle" data-toggle="dropdown"><img src="{{URL::to(Auth::user()->avatar)}}" class="img-thumbnail" width="50" height="50">{{Auth::user()->email}} <b class="caret"></b></a>


							<ul class="dropdown-menu">
								<li>
									Pe cat mai curand!
								</li>
								<li class="divider"></li>
								<li>
									<a href="{{action('UsersController@SignOut')}}">Sign out</a>
								</li>
							</ul>
						</li>
					@else
						<li>
							<a href="{{action('UsersController@ShowSignUp')}}">Sign up</a>
						</li>
						<li>
							<a href="{{action('UsersController@ShowSignIn')}}">Sign in</a>
						</li>
					@endif
				</ul>
			</div>
		</nav>
	<div class="continut">	
		@yield('content')
		
	<hr>
	<blockquote>
	<p class="text-center"><strong>R+ </strong>, Radu Terciu <small>{{date("d-m-Y")}}</small></p>
	</blockquote>
	</div>
</div>
</body>
</html>
