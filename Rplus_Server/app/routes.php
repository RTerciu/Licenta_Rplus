<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the Closure to execute when that URI is requested.
|
*/

Route::get('/', function()
{
	return View::make('hello');
});

Route::get('test/{p}/{g}/{A}/{a}',function($p,$g,$A,$a)
{


//return $p.' cu '.$p.' cu '.$A;

//p este modulul primit de la aplicatia mobila
$modulus=gmp_init($p);
//g este baza la care se ridica toate chestiile
$exponent=gmp_init($g);
//Alice este cheia publica primita 
$Alice=gmp_init($A);
//b este secretul random generat aici 
$b=gmp_nextprime(gmp_random());
//Bob este cheia de aici public ce o sa o trimitem
$Bob=gmp_powm($g,$b,$modulus);

//avem toate elementele sa calculam secretul
$secret=gmp_powm($A,$b,$modulus);
return [gmp_strval($p),gmp_strval($g),gmp_strval($Alice),gmp_strval($Bob),gmp_strval($secret),$a] ;






});


Route::get('/signup','UsersController@ShowSignUp');

Route::post('/signup','UsersController@PostSignUp');

Route::get('/signin','UsersController@ShowSignIn');

Route::post('/signin','UsersController@PostSignIn');

Route::get('/signout','UsersController@SignOut');

//de sters ruta asta
Route::get('users',function()
{
$users=User::all();

return $users;

});
//ruta de primire de date odata ce avem un utilizator autentificat pe android
Route::get('getData/{userEmail}/{userToken}/{apiKey}','UsersController@GetData');
//ruta de logare a utilizatorului pe telefon
Route::get('checkUser/{userEmail}/{userToken}','UsersController@CheckUser');
Route::get('checkApp/{appKey}','AppsController@CheckAppKey');



Route::group(array('before' => 'auth'), function()

{

Route::post('update','UsersController@UpdateUserData');
Route::get('profile','UsersController@GetProfilePage');
Route::get('apps','UsersController@GetAppsPage');



Route::get('contact',function()
{
return View::make('contact');
});

//pentru Developerii care doresc sa creeze noi aplicatii

Route::get('new/app','AppsController@NewApp');
Route::post('new/app','AppsController@CreateNewApp');


});