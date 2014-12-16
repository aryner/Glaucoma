/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
	$('input[type=text][name=notch_hrs_one]').on('input', function() {
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
	$('input[type=text][name=notch_hrs_two]').on('input', function() {
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
	$('input[type=text][name=eros_hrs_one]').on('input', function() {
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
	$('input[type=text][name=eros_hrs_two]').on('input', function() {
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
	$('input[type=text][name=disc_hrs_one]').on('input', function() {
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
	$('input[type=text][name=disc_hrs_two]').on('input', function() {
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
	$('input[type=text][name=rnfl_hrs_one]').on('input', function() {
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
	$('input[type=text][name=rnfl_hrs_two]').on('input', function() {
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

	$('input').bind('keypress', function(e){
		e = e || window.event;
		var unicode = e.keycode || e.which;

		if(unicode === 13) {
			e.preventDefault();
			var focus = document.activeElement;

			if (focus.name === 'rnfl_hrs_two') {
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
		var qual = $('input[type=radio][name=qual][value=1]').prop('checked')
			|| $('input[type=radio][name=qual][value=2]').prop('checked')
			|| $('input[type=radio][name=qual][value=3]').prop('checked');

		var cdr = $('input[type=text][name=cdr]').val().length > 0;

		var notch = $('input[type=radio][name=notch][value=1]').prop('checked')
			|| $('input[type=radio][name=notch][value=2]').prop('checked');
		var thing = $('input[type=text][name=notch_hrs_one]').val(); 
		var notch_hrs_one = !$('input[type=radio][name=notch][value=1]').prop('checked') 
			|| thing.length > 0;
		thing = $('input[type=text][name=notch_hrs_two]').val(); 
		var notch_hrs_two = !$('input[type=radio][name=notch][value=1]').prop('checked') 
			|| thing.length > 0;

		var erosion= $('input[type=radio][name=erosion][value=1]').prop('checked')
			|| $('input[type=radio][name=erosion][value=2]').prop('checked');
		var thing = $('input[type=text][name=eros_hrs_one]').val(); 
		var eros_hrs_one = !$('input[type=radio][name=erosion][value=1]').prop('checked') 
			|| thing.length > 0;
		thing = $('input[type=text][name=eros_hrs_two]').val(); 
		var eros_hrs_two = !$('input[type=radio][name=erosion][value=1]').prop('checked') 
			|| thing.length > 0;

		var disc = $('input[type=radio][name=disc][value=1]').prop('checked')
			|| $('input[type=radio][name=disc][value=2]').prop('checked');
		var thing = $('input[type=text][name=disc_hrs_one]').val(); 
		var disc_hrs_one = !$('input[type=radio][name=disc][value=1]').prop('checked') 
			|| thing.length > 0;
		thing = $('input[type=text][name=disc_hrs_two]').val(); 
		var disc_hrs_two = !$('input[type=radio][name=disc][value=1]').prop('checked') 
			|| thing.length > 0;

		var rnfl = $('input[type=radio][name=rnfl][value=1]').prop('checked')
			|| $('input[type=radio][name=rnfl][value=2]').prop('checked');
		var thing = $('input[type=text][name=rnfl_hrs_one]').val(); 
		var rnfl_hrs_one = !$('input[type=radio][name=rnfl][value=1]').prop('checked') 
			|| thing.length > 0;
		thing = $('input[type=text][name=rnfl_hrs_two]').val(); 
		var rnfl_hrs_two = !$('input[type=radio][name=rnfl][value=1]').prop('checked') 
			|| thing.length > 0;

		var focused = false;

		if(!qual) {
			focused = true;
			e.preventDefault(); 
			$('input[type=text][name=qual]').focus();
			$('#qual').addClass('highlight');
		} else {
			$('#qual').removeClass('highlight');
		} if(!cdr) {
			$('#cdr').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=cdr]').focus();
			}
		} else {
			$('#cdr').removeClass('highlight');
		} if(!notch) {
			$('#notch').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=notch][value=1]').focus();
			}
		} else {
			$('#notch').removeClass('highlight');
		} if(!notch_hrs_one) {
			$('#notch_hrs_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=notch_hrs_one]').focus();
			}
		} else {
			$('#notch_hrs_one').removeClass('highlight');
		} if(!notch_hrs_two) {
			$('#notch_hrs_two').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=notch_hrs_two]').focus();
			}
		} else {
			$('#notch_hrs_two').removeClass('highlight');
		} if(!erosion) {
			$('#erosion').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=erosion][value=1]').focus();
			}
		} else {
			$('#erosion').removeClass('highlight');
		} if(!eros_hrs_one) {
			$('#eros_hrs_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=eros_hrs_one]').focus();
			}
		} else {
			$('#eros_hrs_one').removeClass('highlight');
		} if(!eros_hrs_two) {
			$('#eros_hrs_two').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=eros_hrs_two]').focus();
			}
		} else {
			$('#eros_hrs_two').removeClass('highlight');
		} if(!disc) {
			$('#disc').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=disc][value=1]').focus();
			}
		} else {
			$('#disc').removeClass('highlight');
		} if(!disc_hrs_one) {
			$('#disc_hrs_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=disc_hrs_one]').focus();
			}
		} else {
			$('#disc_hrs_one').removeClass('highlight');
		} if(!disc_hrs_two) {
			$('#disc_hrs_two').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=disc_hrs_two]').focus();
			}
		} else {
			$('#disc_hrs_two').removeClass('highlight');
		} if(!rnfl) {
			$('#rnfl').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=rnfl][value=1]').focus();
			}
		} else {
			$('#rnfl').removeClass('highlight');
		} if(!rnfl_hrs_one) {
			$('#rnfl_hrs_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=rnfl_hrs_one]').focus();
			}
		} else {
			$('#rnfl_hrs_one').removeClass('highlight');
		} if(!rnfl_hrs_two) {
			$('#rnfl_hrs_two').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=rnfl_hrs_two]').focus();
			}
		} else {
			$('#rnfl_hrs_two').removeClass('highlight');
		}
	});
});