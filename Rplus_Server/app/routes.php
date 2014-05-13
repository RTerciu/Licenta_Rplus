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




Route::get('/signup','UsersController@ShowSignUp');

Route::post('/signup','UsersController@PostSignUp');

Route::get('/signin','UsersController@ShowSignIn');

Route::post('/signin','UsersController@PostSignIn');

Route::get('/signout','UsersController@SignOut');
Route::get('contact',function()
{
return View::make('contact');
});

//de sters ruta asta
Route::get('users',function()
{
$users=User::all();

return $users;

});

Route::get('getData/{userEmail}/{userToken}/{apiKey}','UsersController@GetData');





Route::group(array('before' => 'auth'), function()

{

Route::post('update','UsersController@UpdateUserData');
Route::get('profile','UsersController@GetProfilePage');
Route::get('apps','UsersController@GetAppsPage');

//pentru Developerii care doresc sa creeze noi aplicatii

Route::get('new/app','AppsController@NewApp');
Route::post('new/app','AppsController@CreateNewApp');


});