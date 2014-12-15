/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
	$('input[type=text][name=dur]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9^:]/g, '')) {
			this.value = this.value.replace(/[^0-9^:]/g, '');
		} 
		if(this.value.indexOf(":") >= 0 && this.value.indexOf(":") < this.value.length) { 
			var second = this.value.substring(this.value.indexOf(":")+1, this.value.length); 
			if(second.indexOf(":") >= 0) {
				this.value = this.value.substring(0,this.value.length-1);
			}
		}
	});
	$('input[type=text][name=late]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=fp]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=lu_one]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=ru_one]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=ll_one]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=rl_one]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=ptd]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9^\.]/g, '')) {
			this.value = this.value.replace(/[^0-9^\.]/g, '');
		}
		if(this.value.indexOf(".") >= 0 && this.value.indexOf(".") < this.value.length) { 
			var second = this.value.substring(this.value.indexOf(".")+1, this.value.length); 
			if(second.indexOf(".") >= 0) {
				this.value = this.value.substring(0,this.value.length-1);
			}
		}
	});

	$('input').bind('keypress', function(e){
		e = e || window.event;
		var unicode = e.keycode || e.which;

		if(unicode === 13) {
			e.preventDefault();
			var focus = document.activeElement;

			if (focus.name === 'rl_one') {
				$(':submit[value=Submit]').click();
			}
			else {
				$(focus).nextAll('input:first').focus();
				var next = document.activeElement;

				if(focus.type.toString() === 'radio') {
					while(focus.name.toString() === next.name.toString()) {
						$(next).nextAll('input:first').focus();
						next = document.activeElement;
						console.log("in while");
					}
				}
			}
		}
	});

	$(':submit[value=Submit]').click(function(e){ 
		var late = $('input[type=text][name=late]').val().length > 0;
		var fp = $('input[type=text][name=fp]').val().length > 0;

		var lens = $('input[type=radio][name=lens][value=1]').prop('checked')
			|| $('input[type=radio][name=lens][value=2]').prop('checked');

		var thing = $('input[type=text][name=lens_y]').val(); 
		var lens_y = !$('input[type=radio][name=lens][value=2]').prop('checked') 
			|| thing.length > 0;

		var durVal = $('input[type=text][name=dur]').val();
		var durFirst = durVal.substring(0,durVal.indexOf(":"));
		var durSecond = durVal.substring(durVal.indexOf(":")+1);
		var dur = durFirst.length === 2 && durSecond.length === 2;

		var ptd = $('input[type=text][name=ptd]').val().length > 0;
		var lu_one = $('input[type=text][name=lu_one]').val().length > 0;
		var ru_one = $('input[type=text][name=ru_one]').val().length > 0;
		var ll_one = $('input[type=text][name=ll_one]').val().length > 0;
		var rl_one = $('input[type=text][name=rl_one]').val().length > 0;

		var focused = false;

		if(!late) {
			focused = true;
			e.preventDefault(); 
			$('input[type=text][name=late]').focus();
			$('#late').addClass('highlight');
		} else {
			$('#late').removeClass('highlight');
		} if(!fp) {
			$('#fp').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=fp]').focus();
			}
		} else {
			$('#fp').removeClass('highlight');
		} if(!lens) {
			$('#lens').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=lens][value=1]').focus();
			}
		} else {
			$('#lens').removeClass('highlight');
		} if(!lens_y) {
			$('#lens_y').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=lens_y]').focus();
			}
		} else {
			$('#lens_y').removeClass('highlight');
		} if(!dur) {
			$('#dur').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=dur]').focus();
			}
		} else {
			$('#dur').removeClass('highlight');
		} if(!ptd) {
			$('#ptd').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=ptd]').focus();
			}
		} else {
			$('#ptd').removeClass('highlight');
		} if(!lu_one) {
			$('#lu_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=lu_one]').focus();
			}
		} else {
			$('#lu_one').removeClass('highlight');
		} if(!ru_one) {
			$('#ru_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=ru_one]').focus();
			}
		} else {
			$('#ru_one').removeClass('highlight');
		} if(!ll_one) {
			$('#ll_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=ll_one]').focus();
			}
		} else {
			$('#ll_one').removeClass('highlight');
		} if(!rl_one) {
			$('#rl_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=rl_one]').focus();
			}
		} else {
			$('#rl_one').removeClass('highlight');
		}
	});
});