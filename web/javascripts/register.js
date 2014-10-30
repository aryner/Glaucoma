/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
	$('select[name=graderType]').change(function() {
		var chosen = $('select[name=graderType').val();
		if(Number(chosen) === 2) {
			$('#opthChosen').removeClass('invis');
		}
		else {
			$('#opthChosen').addClass('invis');
		}
	});
});