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
	$('input[type=text][name=length]').on('input', function() {
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
	$('input[type=text][name=iavgnum]').on('input', function() {
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
	$('input[type=text][name=sig]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9^\.]/g, '')) {
			this.value = this.value.replace(/[^0-9^\.]/g, '');
		}
		if(this.value.indexOf(".") >= 0 && this.value.indexOf(".") < this.value.length) { 
			var second = this.value.substring(this.value.indexOf(".")+1, this.value.length); 
			if(second.indexOf(".") >= 0) {
				this.value = this.value.substring(0,this.value.length-1);
			}
		}
		var num = Number(this.value);
		if(num > 10) {
			this.value = this.value.substring(0,this.value.length-1);
		}
	});
	$('input[type=text][name=isnum]').on('input', function() {
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
	$('input[type=text][name=sinum]').on('input', function() {
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
	$('input[type=text][name=stnum]').on('input', function() {
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
	$('input[type=text][name=itnum]').on('input', function() {
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
	$('input[type=text][name=snnum]').on('input', function() {
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
	$('input[type=text][name=mmnum]').on('input', function() {
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
	$('input[type=text][name=smaxnum]').on('input', function() {
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
	$('input[type=text][name=imaxnum]').on('input', function() {
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
	$('input[type=text][name=savgnum]').on('input', function() {
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
	$('input[type=text][name=imaxnum]').on('input', function() {
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
	$('input[type=text][name=atnum]').on('input', function() {
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

	$('input[type=text][name=snum]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
		var num = Number(this.value);
		if(num > 500) {
			this.value = this.value.substring(0,this.value.length-1);
		}
	});
	$('input[type=text][name=nnum]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=inum]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=tnum]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});

	$('input').bind('keypress', function(e){
		e = e || window.event;
		var unicode = e.keycode || e.which;

		if(unicode === 13) {
			e.preventDefault();
			var focus = document.activeElement;

			if (focus.name === 'atcol') {
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
		var length = $('input[type=text][name=length]').val().length > 0;

		var type = $('input[type=radio][name=type][value=1]').prop('checked')
			|| $('input[type=radio][name=type][value=2]').prop('checked')
			|| $('input[type=radio][name=type][value=3]').prop('checked')
			|| $('input[type=radio][name=type][value=4]').prop('checked')
			|| $('input[type=radio][name=type][value=5]').prop('checked');
		
		var thing = $('input[type=text][name=type_oth]').val(); 
		var type_oth = !$('input[type=radio][name=type][value=5]').prop('checked') 
			|| thing.length > 0;

		var snum = $('input[type=text][name=snum]').val().length > 0;
		var scol = $('input[type=radio][name=scol][value=1]').prop('checked')
			|| $('input[type=radio][name=scol][value=2]').prop('checked')
			|| $('input[type=radio][name=scol][value=3]').prop('checked')
			|| $('input[type=radio][name=scol][value=4]').prop('checked');

		var nnum = $('input[type=text][name=nnum]').val().length > 0;
		var ncol = $('input[type=radio][name=ncol][value=1]').prop('checked')
			|| $('input[type=radio][name=ncol][value=2]').prop('checked')
			|| $('input[type=radio][name=ncol][value=3]').prop('checked')
			|| $('input[type=radio][name=ncol][value=4]').prop('checked');

		var inum = $('input[type=text][name=inum]').val().length > 0;
		var icol = $('input[type=radio][name=icol][value=1]').prop('checked')
			|| $('input[type=radio][name=icol][value=2]').prop('checked')
			|| $('input[type=radio][name=icol][value=3]').prop('checked')
			|| $('input[type=radio][name=icol][value=4]').prop('checked');

		var tnum = $('input[type=text][name=tnum]').val().length > 0;
		var tcol = $('input[type=radio][name=tcol][value=1]').prop('checked')
			|| $('input[type=radio][name=tcol][value=2]').prop('checked')
			|| $('input[type=radio][name=tcol][value=3]').prop('checked')
			|| $('input[type=radio][name=tcol][value=4]').prop('checked');

		var sig = $('input[type=text][name=sig]').val().length > 0;

		var isnum = $('input[type=text][name=isnum]').val().length > 0;
		var iscol = $('input[type=radio][name=iscol][value=1]').prop('checked')
			|| $('input[type=radio][name=iscol][value=2]').prop('checked')
			|| $('input[type=radio][name=iscol][value=3]').prop('checked')
			|| $('input[type=radio][name=iscol][value=4]').prop('checked');

		var sinum = $('input[type=text][name=sinum]').val().length > 0;
		var sicol = $('input[type=radio][name=sicol][value=1]').prop('checked')
			|| $('input[type=radio][name=sicol][value=2]').prop('checked')
			|| $('input[type=radio][name=sicol][value=3]').prop('checked')
			|| $('input[type=radio][name=sicol][value=4]').prop('checked');

		var stnum = $('input[type=text][name=stnum]').val().length > 0;
		var stcol = $('input[type=radio][name=stcol][value=1]').prop('checked')
			|| $('input[type=radio][name=stcol][value=2]').prop('checked')
			|| $('input[type=radio][name=stcol][value=3]').prop('checked')
			|| $('input[type=radio][name=stcol][value=4]').prop('checked');

		var itnum = $('input[type=text][name=itnum]').val().length > 0;
		var itcol = $('input[type=radio][name=itcol][value=1]').prop('checked')
			|| $('input[type=radio][name=itcol][value=2]').prop('checked')
			|| $('input[type=radio][name=itcol][value=3]').prop('checked')
			|| $('input[type=radio][name=itcol][value=4]').prop('checked');

		var snnum = $('input[type=text][name=snnum]').val().length > 0;
		var sncol = $('input[type=radio][name=sncol][value=1]').prop('checked')
			|| $('input[type=radio][name=sncol][value=2]').prop('checked')
			|| $('input[type=radio][name=sncol][value=3]').prop('checked')
			|| $('input[type=radio][name=sncol][value=4]').prop('checked');

		var mmnum = $('input[type=text][name=mmnum]').val().length > 0;
		var mmcol = $('input[type=radio][name=mmcol][value=1]').prop('checked')
			|| $('input[type=radio][name=mmcol][value=2]').prop('checked')
			|| $('input[type=radio][name=mmcol][value=3]').prop('checked')
			|| $('input[type=radio][name=mmcol][value=4]').prop('checked');

		var smaxnum = $('input[type=text][name=smaxnum]').val().length > 0;
		var smaxcol = $('input[type=radio][name=smaxcol][value=1]').prop('checked')
			|| $('input[type=radio][name=smaxcol][value=2]').prop('checked')
			|| $('input[type=radio][name=smaxcol][value=3]').prop('checked')
			|| $('input[type=radio][name=smaxcol][value=4]').prop('checked');

		var imaxnum = $('input[type=text][name=imaxnum]').val().length > 0;
		var imaxcol = $('input[type=radio][name=imaxcol][value=1]').prop('checked')
			|| $('input[type=radio][name=imaxcol][value=2]').prop('checked')
			|| $('input[type=radio][name=imaxcol][value=3]').prop('checked')
			|| $('input[type=radio][name=imaxcol][value=4]').prop('checked');

		var savgnum = $('input[type=text][name=savgnum]').val().length > 0;
		var savgcol = $('input[type=radio][name=savgcol][value=1]').prop('checked')
			|| $('input[type=radio][name=savgcol][value=2]').prop('checked')
			|| $('input[type=radio][name=savgcol][value=3]').prop('checked')
			|| $('input[type=radio][name=savgcol][value=4]').prop('checked');

		var iavgnum = $('input[type=text][name=iavgnum]').val().length > 0;
		var iavgcol = $('input[type=radio][name=iavgcol][value=1]').prop('checked')
			|| $('input[type=radio][name=iavgcol][value=2]').prop('checked')
			|| $('input[type=radio][name=iavgcol][value=3]').prop('checked')
			|| $('input[type=radio][name=iavgcol][value=4]').prop('checked');

		var atnum = $('input[type=text][name=atnum]').val().length > 0;
		var atcol = $('input[type=radio][name=atcol][value=1]').prop('checked')
			|| $('input[type=radio][name=atcol][value=2]').prop('checked')
			|| $('input[type=radio][name=atcol][value=3]').prop('checked')
			|| $('input[type=radio][name=atcol][value=4]').prop('checked');

		var s_savg = Number($('input[type=text][name=snum]').val()) ===
			     Number($('input[type=text][name=savgnum]').val());

		var i_iavg = Number($('input[type=text][name=inum]').val()) ===
			     Number($('input[type=text][name=iavgnum]').val());

		var eye = $('input[type=radio][name=eye][value=1]').prop('checked')
			|| $('input[type=radio][name=eye][value=2]').prop('checked');

		var focused = false;

		if(!length) {
			focused = true;
			e.preventDefault(); 
			$('input[type=text][name=length]').focus();
			$('#length').addClass('highlight');
		} else {
			$('#length').removeClass('highlight');
		} if(!type) {
			$('#type').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=type][value=1]').focus();
			}
		} else {
			$('#type').removeClass('highlight');
		} if(!type_oth) {
			$('#type_oth').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=type_oth]').focus();
			}
		} else {
			$('#type_oth').removeClass('highlight');
		} if(!snum) {
			$('#snum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=snum]').focus();
			}
		} else {
			$('#snum').removeClass('highlight');
		} if(!scol) {
			$('#scol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=scol][value=1]').focus();
			}
		} else {
			$('#scol').removeClass('highlight');
		} if(!nnum) {
			$('#nnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=nnum]').focus();
			}
		} else {
			$('#nnum').removeClass('highlight');
		} if(!ncol) {
			$('#ncol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=ncol][value=1]').focus();
			}
		} else {
			$('#ncol').removeClass('highlight');
		} if(!inum) {
			$('#inum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=inum]').focus();
			}
		} else {
			$('#inum').removeClass('highlight');
		} if(!icol) {
			$('#icol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=icol][value=1]').focus();
			}
		} else {
			$('#icol').removeClass('highlight');
		} if(!tnum) {
			$('#tnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=tnum]').focus();
			}
		} else {
			$('#tnum').removeClass('highlight');
		} if(!tcol) {
			$('#tcol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=tcol][value=1]').focus();
			}
		} else {
			$('#tcol').removeClass('highlight');
		} if(!sig) {
			$('#sig').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=sig]').focus();
			}
		} else {
			$('#sig').removeClass('highlight');
		} if(!isnum) {
			$('#isnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=isnum]').focus();
			}
		} else {
			$('#isnum').removeClass('highlight');
		} if(!iscol) {
			$('#iscol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=iscol][value=1]').focus();
			}
		} else {
			$('#iscol').removeClass('highlight');
		} if(!sinum) {
			$('#sinum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=sinum]').focus();
			}
		} else {
			$('#sinum').removeClass('highlight');
		} if(!sicol) {
			$('#sicol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=sicol][value=1]').focus();
			}
		} else {
			$('#sicol').removeClass('highlight');
		} if(!stnum) {
			$('#stnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=stnum]').focus();
			}
		} else {
			$('#stnum').removeClass('highlight');
		} if(!stcol) {
			$('#stcol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=stcol][value=1]').focus();
			}
		} else {
			$('#stcol').removeClass('highlight');
		} if(!itnum) {
			$('#itnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=itnum]').focus();
			}
		} else {
			$('#itnum').removeClass('highlight');
		} if(!itcol) {
			$('#itcol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=itcol][value=1]').focus();
			}
		} else {
			$('#itcol').removeClass('highlight');
		} if(!snnum) {
			$('#snnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=snnum]').focus();
			}
		} else {
			$('#snnum').removeClass('highlight');
		} if(!sncol) {
			$('#sncol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=sncol][value=1]').focus();
			}
		} else {
			$('#sncol').removeClass('highlight');
		} if(!mmnum) {
			$('#mmnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=mmnum]').focus();
			}
		} else {
			$('#mmnum').removeClass('highlight');
		} if(!mmcol) {
			$('#mmcol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=mmcol][value=1]').focus();
			}
		} else {
			$('#mmcol').removeClass('highlight');
		} if(!smaxnum) {
			$('#smaxnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=smaxnum]').focus();
			}
		} else {
			$('#smaxnum').removeClass('highlight');
		} if(!smaxcol) {
			$('#smaxcol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=smaxcol][value=1]').focus();
			}
		} else {
			$('#smaxcol').removeClass('highlight');
		} if(!imaxnum) {
			$('#imaxnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=imaxnum]').focus();
			}
		} else {
			$('#imaxnum').removeClass('highlight');
		} if(!imaxcol) {
			$('#imaxcol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=imaxcol][value=1]').focus();
			}
		} else {
			$('#imaxcol').removeClass('highlight');
		} if(!savgnum) {
			$('#savgnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=savgnum]').focus();
			}
		} else {
			$('#savgnum').removeClass('highlight');
		} if(!savgcol) {
			$('#savgcol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=savgcol][value=1]').focus();
			}
		} else {
			$('#savgcol').removeClass('highlight');
		} if(!iavgnum) {
			$('#iavgnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=iavgnum]').focus();
			}
		} else {
			$('#iavgnum').removeClass('highlight');
		} if(!iavgcol) {
			$('#iavgcol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=iavgcol][value=1]').focus();
			}
		} else {
			$('#iavgcol').removeClass('highlight');
		} if(!atnum) {
			$('#atnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=atnum]').focus();
			}
		} else {
			$('#atnum').removeClass('highlight');
		} if(!atcol) {
			$('#atcol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=atcol][value=1]').focus();
			}
		} else {
			$('#atcol').removeClass('highlight');
		} if(!s_savg) {
			$('#savg_snum').removeClass('hidden');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=snum]').focus();
			}
		} else {
			$('#savg_snum').addClass('hidden');
		} if(!i_iavg) {
			$('#iavg_inum').removeClass('hidden');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=inum]').focus();
			}
		} else {
			$('#iavg_inum').addClass('hidden');
		} if(!eye) {
			$('#eye').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=eye][value=1]').focus();
			}
		} else {
			$('#eye').removeClass('highlight');
		}
	});
});