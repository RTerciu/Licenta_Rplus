$(document).ready(function(){

  $("div#b1").click(function(){
   $("div#bannere").css("background", "url('img/banner_stg.jpg')");
  });
  
  $("div#b2").click(function(){
   $("div#bannere").css("background-image", "url('img/Banner_principal.jpg')");
  });
  
  $("div#b3").click(function(){
   $("div#bannere").css("background-image", "url('img/banner_dr.jpg')");
  });
  
  
  $("div#post_job").click(function(){
   document.location.href='job/create';
  });
  
  
  
  
  function search() {
		var query_value = $('input#search1').val();
		var link1="search/documents/"+query_value;
		var link2="search/joburi/"+query_value;
		if(query_value !== ''){
			$.ajax({
				type: "GET",
				url: link1,
				cache: false,
				success: function(html){
					$("div#doc_results").html(html);
				}
			});
			
			
			$.ajax({
				type: "GET",
				url: link2,
				cache: false,
				success: function(html){
					$("div#job_results").html(html);
				}
			});
			
			
			
			
		}return false;    
	}

	
		$("input#search1").on("keyup", function(e) {
		// Set Timeout
		
	
		clearTimeout($.data(this, 'timer'));

		// Set Search String
		var search_string = $(this).val();

		// Do Search
		if (search_string == '') {
			//$("ul#results").fadeOut();
			//$('h4#results-text').fadeOut();
		}else{
			//$("ul#results").fadeIn();
			//$('h4#results-text').fadeIn();
			$(this).data('timer', setTimeout(search, 100));
		};
	});
  
  
  
  
  
  
  
});