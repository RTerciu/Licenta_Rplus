<?php

use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;
use Jenssegers\Mongodb\Model as Eloquent;
use Carbon\Carbon;

class User extends Eloquent implements UserInterface, RemindableInterface {

	/**
	 * The database table used by the
	 model.
	 *
	 * @var string
	 */
	protected $collection = 'users';
	/*
	functia care verifica daca utilizatorul cu 
	emailul primit ca si parametru exista in 
	baza de date
	*/
	public static function checkUserEmail($userEmail)
	{
	$user=User::where('email',$userEmail)->first();
	
	if($user==null)
		return false;
		
	return true;
	
	}
	/*
	Functia care genereaza un hash pe modelul:
	md5(md5(parola+email)+an+luna+zi+ora)
	*/
	public function genereazaToken()
	{
	$data=Carbon::now();
		if($data->month<10)
			$luna='0'.$data->month;
		else 
			$luna=$data->month;
			
		if($data->day<10)
			$ziua='0'.$data->day;
		else 
			$ziua=$data->day;

	//construim token-ul dependent de timp baza pe token-ul initial construit la signup
	$token_string=$this->initialToken.$data->year.$luna.$ziua.$data->hour;
	
	return md5($token_string);
	}
	/**
	 * The attributes excluded from the model's JSON form.
	 *
	 * @var array
	 */
	protected $hidden = array('password','initialToken','sessionLoginKey');

	/**
	 * Get the unique identifier for the user.
	 *
	 * @return mixed
	 */
	public function getAuthIdentifier()
	{
		return $this->getKey();
	}

	/**
	 * Get the password for the user. 
	 *
	 * @return string
	 */
	public function getAuthPassword()
	{
		return $this->password;
	}

	/**
	 * Get the e-mail address where password reminders are sent.
	 *
	 * @return string
	 */
	public function getReminderEmail()
	{
		return $this->email;
	}

}