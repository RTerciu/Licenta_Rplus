<?php

use Carbon\Carbon;

class UsersController extends BaseController {

	public function ShowSignUp()
	{
		return View::make('signUp');
	}
	
	public function ShowSignIn()
	{
		return View::make('signIn');
	}
	
	public function ShowProfile($username)
	{
		return View::make('profile.profile');
	}
	
	public function SignOut()
	{
		Auth::logout();
		return Redirect::to('/')->with('signout_notice','Sadly, you have successfully signed out.');
	}
	
	public function PostSignIn()
	{  
		//echo var_dump(Input::all());
		
		if(Auth::attempt(array('email' => Input::get('email'), 'password' =>Input::get('password'))))
		{
			return Redirect::to('/');
		}
		else
		{
			return Redirect::to('signup');
		}
	}
	
	
	public function GetProfilePage()
	{
	$user=User::find(Auth::user()->id);
	
	return View::make('profile')->with('user',$user);
	}
	
	public function GetAppsPage()
	{
	//$user=User::find(Auth::user()->id);
	
	return View::make('apps');
	}
	
	
	public function UpdateUserData()
	{
	
	return 'bla';
	}
	
	
	public function PostSignUp()
	{
		$destinationPath = 'uploads/avatars/';
		$file = Input::file('avatar');
		
		$filename = Input::file('avatar')->getClientOriginalName();
		$uploadSuccess = Input::file('avatar')->move($destinationPath, $filename);
		
		if($uploadSuccess)
		{
			$user = new User;
			$user->email = Input::get('email');
			$user->password = Hash::make(Input::get('password'));
			$user->avatar = $destinationPath.$filename;
			$user->username = Input::get('username');
			$user->birthday = Input::get('data_nasterii');
			$user->oras=Input::get('oras');
			$user->resedinta=Input::get('resedinta');
			$user->scoala=Input::get('scoala');
			$user->despre=Input::get('despre');
			$user->initialToken=md5(Input::get('password').Input::get('email'));
			$user->save();
		}
		
		return Redirect::to('/');
	}

	
	public function GetData($userEmail,$userToken,$apiKey)
	{
	
	$user=User::where('email',$userEmail)->first();
	//verificam ca userul exista
	if($user==null)
		return json_encode(array('eroare'=>'Userul nu exista!'));
	//Verificam ca aplicatia sa fie aprobata si existenta
	$app=Application::where('appKey',$apiKey)->where('approved',true)->first();
	
	if($app==null)
		return json_encode(array('eroare'=>'Aplicatia nu exista sau nu este autorizata sa primeasca date!'));
	
	$data=Carbon::now();
	
	
	if($data->month<10)
		$luna='0'.$data->month;
	else 
		$luna=$data->month;
	//construim token-ul dependent de timp baza pe token-ul initial construit la signup
	$token_string=$user->initialToken.$data->year.$luna.$data->day.$data->hour;
	
	//Token de testat pentru radurt25@gmail.com a5ccbe561062aa83611ea109c2935dc1
	
	//Daca token-ul nu este egal cu cel primit de la aplicatie , atunci returnam eroare
	if($userToken!==md5($token_string))
		return json_encode(array('eroare'=>'Nu esti autorizat sa primesti aceste date sau a intervenit o eroare'));
	
	
	
	return $user;
	}
	
	
}