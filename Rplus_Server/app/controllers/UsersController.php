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
	$userLogged=Auth::user()->id;
	$userapps=UserApp::where('userID',$userLogged)->get();
	return View::make('apps')->with('userapps',$userapps);
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
	
	
    //$source = 'dhkajsdkashdkashdkah!';

    $iv = "1234567812345678";
    $pass = '1234567812345678';
    $method = 'aes-128-cbc';
	
	
	
	$user=User::where('email',$userEmail)->first();
	//verificam ca userul exista
	if($user==null)
		{$x=json_encode(array('eroare'=>'Userul nu exista!'));
				 return base64_encode(openssl_encrypt($x, $method, $pass, true, $iv));
		}
	//Verificam ca aplicatia sa fie aprobata si existenta
	$app=Application::where('appKey',$apiKey)->where('approved',true)->first();
	
	if($app==null)
		{$x=json_encode(array('eroare'=>'Aplicatia nu exista sau nu este autorizata sa primeasca date!'));
		 return base64_encode(openssl_encrypt($x, $method, $pass, true, $iv));
		}
	$data=Carbon::now();
	
	
	if($data->day<10)
		$ziua='0'.$data->day;
	else 
		$ziua=$data->day;
	
	if($data->month<10)
		$luna='0'.$data->month;
	else 
		$luna=$data->month;
	//construim token-ul dependent de timp baza pe token-ul initial construit la signup
	$token_string=$user->initialToken.$data->year.$luna.$ziua.$data->hour;
	
	//return $token_string.' si primit '.$userToken;
	//Token de testat pentru radurt25@gmail.com 
	//echo md5($token_string);
	//Daca token-ul nu este egal cu cel primit de la aplicatie , atunci returnam eroare
	if($userToken!==md5($token_string))
		{$x=json_encode(array('eroare'=>'Nu esti autorizat sa primesti aceste date sau a intervenit o eroare'));
			 return base64_encode(openssl_encrypt($x, $method, $pass, true, $iv));
		}
	$userAppNr=UserApp::where('userID',$user->_id)->where('appID',$app->_id)->count();
	if(!$userAppNr)
		$userapp=new UserApp;
	else
		$userapp=UserApp::where('userID',$user->_id)->where('appID',$app->_id)->first();
		
	$userapp->userID=$user->_id;
	$userapp->appID=$app->_id;
	$userapp->appName=$app->nume;
	$userapp->save();
	
	return base64_encode(openssl_encrypt($user, $method, $pass, true, $iv));
	
	}
	
	

	
	
	public function CheckUser($userEmail,$userToken)
	{
	
	$user=User::where('email',$userEmail)->first();
	//verificam ca userul exista
	if($user==null)
		return json_encode(array('eroare'=>'Userul nu exista!'));
	
	/*if(!(User::checkUserEmail($userEmail)))
		return json_encode(array('eroare'=>'Userul nu exista!'));*/

	//Daca token-ul este corect primit este corect
	if($userToken==$user->genereazaToken())
			{
			//Preiau data
			$data=Carbon::now();
			//Generez o cheie bazata pe parola
			//sha1 de parola generata cu bcrypt + an + luna +zip
			$crypt_key=sha1($user->password.$data->year.$data->month.$data->day);
			//o pastrez in baza de date
			//SA O FOLOSESC CA CHEIE DE CRIPTARE A COMUNICATIEI
			$user->sessionLoginKey=$crypt_key;
			$user->save();
			
			return $crypt_key;
			}
	else return json_encode(array('eroare'=>'Neautorizat'));
	
	
	}
	
	
	
	
}