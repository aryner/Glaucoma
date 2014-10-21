/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




$(document).ready(function (e){
	$('#chart').click(function(e) {
		e.stopPropagation();
		var name = $('.severityChart')[0].name;

		if(name.length > 0) {
			$('body').append("<iframe class='examineImg' src='http://localhost:8080/HVF/pdf?type=severity&name="+name+"' type='application/pdf'><iframe>")
				.fadeIn("fast"); 
		}
	});

	$(document).click(function(e){
		$('.examineImg').remove();
	});
});
