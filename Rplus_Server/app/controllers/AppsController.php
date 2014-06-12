<?php

use Carbon\Carbon;

class AppsController extends BaseController {

	public function NewApp()
	{
	$pending =Application::where('approved',false)->get();
	$approved=Application::where('approved',true)->get();
	return View::make('newApp')->with('pending',$pending)->with('approved',$approved);
	}

	public function CreateNewApp()
	{
	
	$data_minima=Carbon::now();
	$data_minima->addYears(1);
	
	Securitate::globalXssClean();
	$app=new Application;
	$app->nume=Input::get('nume');
	$app->userFrom=Input::get('userFrom');
	$app->descriere=Input::get('descriere');
	$app->approved=false;
	$app->expiresAt=$data_minima;
	$app->appKey=Hash::make($app->nume.rand());
	$app->save();
	
	
	return Redirect::to('/new/app')->with('mesaj','Aplicatie adaugata! Asteptati pentru aprobarea aplicatiei');
	
	
	}

	
	
	public function CheckAppKey($appKey)
	{
	$app=Application::where('appKey',$appKey)->first();
	
	if($app==null)
		return json_encode(array('eroare'=>'Aplicatia ceruta nu exista'));
	else 
		return json_encode(array('nume'=>$app->nume));
	
	}
	

}