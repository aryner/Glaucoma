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
	$('input[type=text][name=fixxer_num]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=fixxer_den]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=fp_num]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=fp_den]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=va_num]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=va_den]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=psdp]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=lu_one]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=lu_five]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=ru_one]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=ru_five]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=ll_one]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=ll_five]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=rl_one]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=rl_five]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=fixerr_num]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=fixerr_den]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=fp_num]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=fp_den]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=fn_num]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=fn_den]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=va_num]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=va_den]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=pupil]').on('input', function() {
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
	$('input[type=text]').on('input', function(){
		if(this.value.length > 20) {
			this.value = this.value.substring(0,20);
		}
	});
	$('input[type=text][name=rx]').on('input', function() {
		if(this.value.length > 14) {
			this.value = this.value.substring(0,14);
		}
	});
	$('input[type=text][name=psdb]').on('input', function() {
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
	$('input[type=text][name=mddb]').on('input', function() {
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
	$('input[type=text][name=mdp]').on('input', function() {
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

			if (focus.name === 'rl_five') {
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
		var durVal = $('input[type=text][name=dur]').val();
		var durFirst = durVal.substring(0,durVal.indexOf(":"));
		var durSecond = durVal.substring(durVal.indexOf(":")+1);
		var dur = durFirst.length === 2 && durSecond.length === 2;

		var targ = $('input[type=radio][name=targ][value=1]').prop('checked')
			|| $('input[type=radio][name=targ][value=2]').prop('checked');
		
		var thing = $('input[type=text][name=targ_oth]').val(); 
		var targ_oth = !$('input[type=radio][name=targ][value=2]').prop('checked') 
			|| thing.length > 0;

		var fixerr_num = $('input[type=text][name=fixerr_num]').val().length > 0;
		var fixer_den = $('input[type=text][name=fixerr_den]').val().length > 0;
		var fp_num = $('input[type=text][name=fp_num]').val().length > 0;
		var fp_den = $('input[type=text][name=fp_den]').val().length > 0;
		var fn_num = $('input[type=text][name=fn_num]').val().length > 0;
		var fn_den = $('input[type=text][name=fn_den]').val().length > 0;

		var test = $('input[type=radio][name=test][value=1]').prop('checked')
			|| $('input[type=radio][name=test][value=2]').prop('checked');
		
		thing = $('input[type=text][name=test_oth]').val(); 
		var test_oth = !$('input[type=radio][name=test][value=2]').prop('checked') 
			|| thing.length > 0;

		var speed = $('input[type=radio][name=speed][value=1]').prop('checked')
			|| $('input[type=radio][name=speed][value=2]').prop('checked')
			|| $('input[type=radio][name=speed][value=3]').prop('checked');
		
		thing = $('input[type=text][name=speed_oth]').val(); 
		var speed_oth = !$('input[type=radio][name=speed][value=3]').prop('checked') 
			|| thing.length > 0;

		var pupil = $('input[type=text][name=pupil]').val().length > 0;
		var va_num = $('input[type=text][name=va_num]').val().length > 0;
		var va_den = $('input[type=text][name=va_den]').val().length > 0;
		var rx = $('input[type=text][name=rx]').val().length > 0;

		var mdsign = $('input[type=radio][name=mdsign][value=1]').prop('checked')
			|| $('input[type=radio][name=mdsign][value=2]').prop('checked');

		var mddb = $('input[type=text][name=mddb]').val().length > 0;
		var mdp = $('input[type=text][name=mdp]').val().length > 0;
		
		var psdsign = $('input[type=radio][name=psdsign][value=1]').prop('checked')
			|| $('input[type=radio][name=psdsign][value=2]').prop('checked');

		var psdb = $('input[type=text][name=psdb]').val().length > 0;
		var psdp = $('input[type=text][name=psdp]').val().length > 0;
		var lu_one = $('input[type=text][name=lu_one]').val().length > 0;
		var lu_five = $('input[type=text][name=lu_five]').val().length > 0;
		var ru_one = $('input[type=text][name=ru_one]').val().length > 0;
		var ru_five = $('input[type=text][name=ru_five]').val().length > 0;
		var ll_one = $('input[type=text][name=ll_one]').val().length > 0;
		var ll_five = $('input[type=text][name=ll_five]').val().length > 0;
		var rl_one = $('input[type=text][name=rl_one]').val().length > 0;
		var rl_five = $('input[type=text][name=rl_five]').val().length > 0;

		var focused = false;

		if(!dur) {
			focused = true;
			e.preventDefault(); 
			$('input[type=text][name=dur]').focus();
			$('#dur').addClass('highlight');
		} else {
			$('#dur').removeClass('highlight');
		} if(!targ) {
			$('#targ').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=targ][value=1]').focus();
			}
		} else {
			$('#targ').removeClass('highlight');
		} if(!targ_oth) {
			$('#targ_oth').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=targ_oth]').focus();
			}
		} else {
			$('#targ_oth').removeClass('highlight');
		} if(!fixerr_num) {
			$('#fixerr').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=fixerr_num]').focus();
			}
		} else {
			$('#fixerr').removeClass('highlight');
		} if(!fixer_den) {
			$('#fixerr').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=fixerr_den]').focus();
			}
		} else {
			$('#fixerr').removeClass('highlight');
		} if(!fp_num) {
			$('#fp').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=fp_num]').focus();
			}
		} else {
			$('#fp').removeClass('highlight');
		} if(!fp_den) {
			$('#fp').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=fp_den]').focus();
			}
		} else {
			$('#fp').removeClass('highlight');
		} if(!fn_num) {
			$('#fn').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=fn_num]').focus();
			}
		} else {
			$('#fn').removeClass('highlight');
		} if(!fn_den) {
			$('#fn').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=fn_den]').focus();
			}
		} else {
			$('#fn').removeClass('highlight');
		} if(!test) {
			$('#test').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=test][value=1]').focus();
			}
		} else {
			$('#test').removeClass('highlight');
		} if(!test_oth) {
			$('#test_oth').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=test_oth]').focus();
			}
		} else {
			$('#test_oth').removeClass('highlight');
		} if(!speed) {
			$('#speed').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=speed][value=1]').focus();
			}
		} else {
			$('#speed').removeClass('highlight');
		} if(!speed_oth) {
			$('#speed_oth').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=speed_oth]').focus();
			}
		} else {
			$('#speed_oth').removeClass('highlight');
		} if(!pupil) {
			$('#pupil').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=pupil]').focus();
			}
		} else {
			$('#pupil').removeClass('highlight');
		} if(!va_num) {
			$('#va').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=va_num]').focus();
			}
		} else {
			$('#va').removeClass('highlight');
		} if(!va_den) {
			$('#va').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=va_den]').focus();
			}
		} else {
			$('#va').removeClass('highlight');
		} if(!rx) {
			$('#rx').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=rx]').focus();
			}
		} else {
			$('#rx').removeClass('highlight');
		} if(!mdsign) {
			$('#mdsign').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=mdsign][value=1]').focus();
			}
		} else {
			$('#mdsign').removeClass('highlight');
		} if(!mddb) {
			$('#mddb').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=mddb]').focus();
			}
		} else {
			$('#mddb').removeClass('highlight');
		} if(!mdp) {
			$('#mdp').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=mdp]').focus();
			}
		} else {
			$('#mdp').removeClass('highlight');
		} if(!psdsign) {
			$('#psdsign').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=psdsign][value=1]').focus();
			}
		} else {
			$('#psdsign').removeClass('highlight');
		} if(!psdb) {
			$('#psdb').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=psdb]').focus();
			}
		} else {
			$('#psdb').removeClass('highlight');
		} if(!psdp) {
			$('#psdp').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=psdp]').focus();
			}
		} else {
			$('#psdp').removeClass('highlight');
		} if(!lu_one) {
			$('#lu_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=lu_one]').focus();
			}
		} else {
			$('#lu_one').removeClass('highlight');
		} if(!lu_five) {
			$('#lu_five').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=lu_five]').focus();
			}
		} else {
			$('#lu_five').removeClass('highlight');
		} if(!ru_one) {
			$('#ru_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=ru_one]').focus();
			}
		} else {
			$('#ru_one').removeClass('highlight');
		} if(!ru_five) {
			$('#ru_five').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=ru_five]').focus();
			}
		} else {
			$('#ru_five').removeClass('highlight');
		} if(!ll_one) {
			$('#ll_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=ll_one]').focus();
			}
		} else {
			$('#ll_one').removeClass('highlight');
		} if(!ll_five) {
			$('#ll_five').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=ll_five]').focus();
			}
		} else {
			$('#ll_five').removeClass('highlight');
		} if(!rl_one) {
			$('#rl_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=rl_one]').focus();
			}
		} else {
			$('#rl_one').removeClass('highlight');
		} if(!rl_five) {
			$('#rl_five').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=rl_five]').focus();
			}
		} else {
			$('#rl_five').removeClass('highlight');
		}
	});
});
