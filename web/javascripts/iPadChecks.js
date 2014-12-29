/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
	$('input[type=text]').on('input', function(){
		if(this.value.length > 20) {
			this.value = this.value.substring(0,20);
		}
	});
	$('input[type=text][name=fp]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=fn]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=sup_hem]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=inf_hem]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});

	$(':submit[value=Submit]').click(function(e){ 
		var fp = $('input[type=text][name=fp]').val().length > 0;
		var fn = $('input[type=text][name=fn]').val().length > 0;
		var sup_hem = $('input[type=text][name=sup_hem]').val().length > 0;
		var inf_hem = $('input[type=text][name=inf_hem]').val().length > 0;

		var focused = false;

		if(!fp) {
			focused = true;
			e.preventDefault(); 
			$('input[type=text][name=fp]').focus();
			$('#fp').addClass('highlight');
		} else {
			$('#fp').removeClass('highlight');
		} if(!fn) {
			$('#fn').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=fn]').focus();
			}
		} else {
			$('#fn').removeClass('highlight');
		} if(!sup_hem) {
			$('#sup_hem').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=sup_hem]').focus();
			}
		} else {
			$('#sup_hem').removeClass('highlight');
		} if (!inf_hem) {
			$('#inf_hem').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=inf_hem]').focus();
			}
		} else {
			$('#inf_hem').removeClass('highlight');
		}
	});
});