$(document).ready(function(data)
		{
			$('.button-1').hover(function(data)
			{
				$(this).children('img').fadeOut(100,function()
				{
					$(this).attr('src',"{{asset('img/buton_hover/bulina_banner_1.png')}}");
					$(this).fadeIn(50);
				});
			},
			function(data)
			{
				$(this).children('img').attr('src',"{{asset('img/buton_inactiv/bulina_banner_1.png')}}");
			}
			);
			
			$('.button-1').click(function()
			{
				$('a[data-slidesjs-item=0]').trigger('click');
				$(this).children('img').fadeOut(100,function()
				{
					$(this).attr('src',"{{asset('img/buton_activ/bulina_banner_1.png')}}");
					$(this).fadeIn(50);
				});
			});
			
			$('.button-2').hover(function(data)
			{
				$(this).children('img').fadeOut(100,function()
				{
					$(this).attr('src',"{{asset('img/buton_hover/bulina_banner_2.png')}}");
					$(this).fadeIn(50);
				});
			},
			function(data)
			{
				$(this).children('img').attr('src',"{{asset('img/buton_inactiv/bulina_banner_2.png')}}");
			}
			);
			
			$('.button-2').click(function()
			{
				$('a[data-slidesjs-item=1]').trigger('click');
				$(this).children('img').fadeOut(100,function()
				{
					$(this).attr('src',"{{asset('img/buton_activ/bulina_banner_2.png')}}");
					$(this).fadeIn(50);
				});
			});
			
			$('.button-3').hover(function(data)
			{
				$(this).children('img').fadeOut(100,function()
				{
					$(this).attr('src',"{{asset('img/buton_hover/bulina_banner_3.png')}}");
					$(this).fadeIn(50);
				});
			},
			function(data)
			{
				$(this).children('img').attr('src',"{{asset('img/buton_inactiv/bulina_banner_3.png')}}");
			});
			
			$('.button-3').click(function()
			{
				$('a[data-slidesjs-item=2]').trigger('click');
				$(this).children('img').fadeOut(100,function()
				{
					$(this).attr('src',"{{asset('img/buton_activ/bulina_banner_3.png')}}");
					$(this).fadeIn(50);
				});
			});
		});
		
		$(function(){
		  $("#slides").slidesjs({
			width: 1920,
			height: 620,
			start: 2,
			navigation: 
			{
			  effect: "fade"
			},
			pagination: 
			{
			  effect: "fade"
			},
			effect: 
			{
			  fade: 
			  {
			  	speed: 400
			  }
			}
		  });
		});