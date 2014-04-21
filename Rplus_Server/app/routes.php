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

Route::get('users',function()
{
$users=User::all();

return $users;

});


Route::group(array('before' => 'auth'), function()

{

Route::post('update','UsersController@UpdateUserData');
Route::get('profile','UsersController@GetProfilePage');
Route::get('apps','UsersController@GetAppsPage');


});