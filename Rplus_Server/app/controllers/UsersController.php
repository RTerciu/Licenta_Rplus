<?php

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
			$user->save();
		}
		
		return Redirect::to('/');
	}

}