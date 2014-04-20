<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Rplus Autentificare</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" href="{{asset('js/bootstrap.min.js')}}"></script>
	<script type="text/javascript" src="{{asset('js/home_buttons.js')}}"></script>
	<script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
	
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
						<a href="{{URL::to('/apps')}}" class="navbar-brand">Apps</a>
					</li>
					
					<li>
						<a href="#" class="navbar-brand">Contact</a>
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
<h1>Bine ati venit la Rplus!
<br><small>Hai sa facem impreuna lucrurile mai usoare!</small></h1><hr>
		
		
<p>		
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis auctor sem nec leo sodales tristique ac a lectus. Donec ultricies diam vel lacus ultricies, eu pellentesque odio posuere. Pellentesque id commodo nisi. Curabitur ac odio eros. Praesent consectetur convallis malesuada. Aliquam mauris sem, convallis dictum bibendum quis, dapibus nec ante. Curabitur eget dolor a sem sollicitudin molestie iaculis sed diam. Nam arcu nulla, egestas ac ullamcorper vitae, porta a mauris. Suspendisse fringilla, augue in accumsan tincidunt, massa felis condimentum nibh, id facilisis turpis velit vitae neque. Quisque pretium fringilla neque, in mollis nisi. Etiam scelerisque leo nibh, in interdum leo ultrices ut. Vestibulum non ante luctus, bibendum odio nec, fermentum nisi.
</p>

<img src="img/monitors.jpg" style="float:right;margin-left:5px;"/>
<p>
Fusce bibendum, odio sed pulvinar sollicitudin, diam leo egestas dui, at auctor ante ipsum sed est. Mauris magna erat, pretium eu condimentum nec, malesuada id dui. Ut bibendum rhoncus porta. Nulla facilisi. Duis laoreet nunc ac neque vulputate feugiat. Cras eros leo, adipiscing adipiscing enim non, egestas tristique arcu. Nullam nunc mi, aliquet sit amet eros ac, porttitor venenatis erat. Mauris hendrerit gravida pellentesque. Nullam sit amet vehicula leo. Duis fringilla dapibus pretium. Quisque at semper dolor. Morbi consequat nulla ac dolor lacinia, quis elementum mi viverra. Vestibulum sollicitudin sodales augue, eget tincidunt felis. Sed ac rutrum justo. Morbi ut felis nec risus pellentesque venenatis vitae a urna.
</p>
<p>
In porta velit at ante suscipit commodo. Cras quis mi et metus adipiscing suscipit a eu elit. In eu nisl dignissim, fringilla tellus id, euismod quam. Vestibulum ac tellus odio. Praesent adipiscing dapibus lectus. Duis in pharetra leo. Sed in ultrices lorem. Donec convallis lectus sed felis pulvinar, quis convallis diam congue. Nam eu est in odio mattis suscipit. Aenean rutrum nibh at condimentum aliquet. Proin at est posuere, blandit risus eu, vulputate mi.
</p><p>
Aenean id leo dui. Curabitur vestibulum posuere eleifend. Ut eu dolor quis purus commodo ornare. Proin non consectetur diam. Nam ornare neque vel metus dignissim, vitae interdum nisi convallis. Nunc dignissim vulputate ante, pretium semper urna volutpat vitae. Praesent odio est, euismod in lobortis ut, facilisis vel arcu. Praesent eu nisi ut est facilisis gravida. Curabitur faucibus molestie hendrerit. Aliquam ullamcorper elit sit amet porta scelerisque. In sit amet lectus in neque lacinia vulputate et nec ligula. Nulla eu condimentum felis. Donec non neque ultricies, pulvinar justo ac, placerat ante. Maecenas rutrum sapien commodo dui pretium feugiat.
</p>	
</div>		
</div>
</body>
</html>
