$(function() {
		
			$("#slideshow > div:gt(0)").hide();
	
			setInterval(function() { 
			  $('#slideshow > div:first')
			    .fadeOut(100)
			    .next()
			    .fadeIn(100)
			    .end()
			    .appendTo('#slideshow');
			},  10000);
			
		});
