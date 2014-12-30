/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
	$('select').on('change', function() {

	});

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
		var scol = Number($('select[name=scol]').val()) > 0;

		var nnum = $('input[type=text][name=nnum]').val().length > 0;
		var ncol = Number($('select[name=ncol]').val()) > 0;

		var inum = $('input[type=text][name=inum]').val().length > 0;
		var icol = Number($('select[name=icol]').val()) > 0;

		var tnum = $('input[type=text][name=tnum]').val().length > 0;
		var tcol = Number($('select[name=tcol]').val()) > 0;

		var sig = $('input[type=text][name=sig]').val().length > 0;

		var isnum = $('input[type=text][name=isnum]').val().length > 0;
		var iscol = Number($('select[name=iscol]').val()) > 0;

		var sinum = $('input[type=text][name=sinum]').val().length > 0;
		var sicol = Number($('select[name=sicol]').val()) > 0;

		var stnum = $('input[type=text][name=stnum]').val().length > 0;
		var stcol = Number($('select[name=stcol]').val()) > 0;

		var itnum = $('input[type=text][name=itnum]').val().length > 0;
		var itcol = Number($('select[name=itcol]').val()) > 0;

		var snnum = $('input[type=text][name=snnum]').val().length > 0;
		var sncol = Number($('select[name=sncol]').val()) > 0;

		var mmnum = $('input[type=text][name=mmnum]').val().length > 0;
		var mmcol = Number($('select[name=mmcol]').val()) > 0;

		var smaxnum = $('input[type=text][name=smaxnum]').val().length > 0;
		var smaxcol = Number($('select[name=smaxcol]').val()) > 0;

		var imaxnum = $('input[type=text][name=imaxnum]').val().length > 0;
		var imaxcol = Number($('select[name=imaxcol]').val()) > 0;

		var savgnum = $('input[type=text][name=savgnum]').val().length > 0;
		var savgcol = Number($('select[name=savgcol]').val()) > 0;

		var iavgnum = $('input[type=text][name=iavgnum]').val().length > 0;
		var iavgcol = Number($('select[name=iavgcol]').val()) > 0;

		var atnum = $('input[type=text][name=atnum]').val().length > 0;
		var atcol = Number($('select[name=atcol]').val()) > 0;

		var s_savg = Number($('input[type=text][name=snum]').val()) ===
			     Number($('input[type=text][name=savgnum]').val());

		var i_iavg = Number($('input[type=text][name=inum]').val()) ===
			     Number($('input[type=text][name=iavgnum]').val());


		var snum_os = $('input[type=text][name=snum_os]').val().length > 0;
		var scol_os = Number($('select[name=scol_os]').val()) > 0;

		var nnum_os = $('input[type=text][name=nnum_os]').val().length > 0;
		var ncol_os = Number($('select[name=ncol_os]').val()) > 0;

		var inum_os = $('input[type=text][name=inum_os]').val().length > 0;
		var icol_os = Number($('select[name=icol_os]').val()) > 0;

		var tnum_os = $('input[type=text][name=tnum_os]').val().length > 0;
		var tcol_os = Number($('select[name=tcol_os]').val()) > 0;

		var sig_os = $('input[type=text][name=sig_os]').val().length > 0;

		var isnum_os = $('input[type=text][name=isnum_os]').val().length > 0;
		var iscol_os = Number($('select[name=iscol_os]').val()) > 0;

		var sinum_os = $('input[type=text][name=sinum_os]').val().length > 0;
		var sicol_os = Number($('select[name=sicol_os]').val()) > 0;

		var stnum_os = $('input[type=text][name=stnum_os]').val().length > 0;
		var stcol_os = Number($('select[name=stcol_os]').val()) > 0;

		var itnum_os = $('input[type=text][name=itnum_os]').val().length > 0;
		var itcol_os = Number($('select[name=itcol_os]').val()) > 0;

		var snnum_os = $('input[type=text][name=snnum_os]').val().length > 0;
		var sncol_os = Number($('select[name=sncol_os]').val()) > 0;

		var mmnum_os = $('input[type=text][name=mmnum_os]').val().length > 0;
		var mmcol_os = Number($('select[name=mmcol_os]').val()) > 0;

		var smaxnum_os = $('input[type=text][name=smaxnum_os]').val().length > 0;
		var smaxcol_os = Number($('select[name=smaxcol_os]').val()) > 0;

		var imaxnum_os = $('input[type=text][name=imaxnum_os]').val().length > 0;
		var imaxcol_os = Number($('select[name=imaxcol_os]').val()) > 0;

		var savgnum_os = $('input[type=text][name=savgnum_os]').val().length > 0;
		var savgcol_os = Number($('select[name=savgcol_os]').val()) > 0;

		var iavgnum_os = $('input[type=text][name=iavgnum_os]').val().length > 0;
		var iavgcol_os = Number($('select[name=iavgcol_os]').val()) > 0;

		var atnum_os = $('input[type=text][name=atnum_os]').val().length > 0;
		var atcol_os = Number($('select[name=atcol_os]').val()) > 0;

		var s_savg_os = Number($('input[type=text][name=snum_os]').val()) ===
			     Number($('input[type=text][name=savgnum_os]').val());

		var i_iavg_os = Number($('input[type=text][name=inum_os]').val()) ===
			     Number($('input[type=text][name=iavgnum_os]').val());

		var focused_os = false;

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
		} if(!snum || !scol || !snum_os || !scol_os) {
			$('#snum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=snum]').focus();
			}
		} else {
			$('#snum').removeClass('highlight');
		} if(!nnum || !ncol || !nnum_os || !ncol_os) {
			$('#nnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=nnum]').focus();
			}
		} else {
			$('#nnum').removeClass('highlight');
		} if(!inum || !icol ||!inum_os || !icol_os) {
			$('#inum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=inum]').focus();
			}
		} else {
			$('#inum').removeClass('highlight');
		} if(!tnum || !tcol ||!tnum_os || !tcol_os) {
			$('#tnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=tnum]').focus();
			}
		} else {
			$('#tnum').removeClass('highlight');
		} if(!sig) {
			$('#sig').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=sig]').focus();
			}
		} else {
			$('#sig').removeClass('highlight');
		} if(!sig_os) {
			$('#sig_os').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=sig_os]').focus();
			}
		} else {
			$('#sig_os').removeClass('highlight');
		} if(!isnum || !iscol ||!isnum_os || !iscol_os) {
			$('#isnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=isnum]').focus();
			}
		} else {
			$('#isnum').removeClass('highlight');
		} if(!sinum || !sinum ||!sinum_os || !sinum_os) {
			$('#sinum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=sinum]').focus();
			}
		} else {
			$('#sinum').removeClass('highlight');
		} if(!stnum || !stcol ||!stnum_os || !stcol_os) {
			$('#stnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=stnum]').focus();
			}
		} else {
			$('#stnum').removeClass('highlight');
		} if(!itnum || !itcol || !itnum_os || !itcol_os) {
			$('#itnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=itnum]').focus();
			}
		} else {
			$('#itnum').removeClass('highlight');
		} if(!snnum || !sncol || !snnum_os || !sncol_os) {
			$('#snnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=snnum]').focus();
			}
		} else {
			$('#snnum').removeClass('highlight');
		} if(!mmnum || !mmcol || !mmnum_os || !mmcol_os) {
			$('#mmnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=mmnum]').focus();
			}
		} else {
			$('#mmnum').removeClass('highlight');
		} if(!smaxnum || !smaxcol || !smaxnum_os || !smaxcol_os) {
			$('#smaxnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=smaxnum]').focus();
			}
		} else {
			$('#smaxnum').removeClass('highlight');
		} if(!imaxnum || !imaxcol || !imaxnum_os || !imaxcol_os) {
			$('#imaxnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=imaxnum]').focus();
			}
		} else {
			$('#imaxnum').removeClass('highlight');
		} if(!savgnum || !savgcol || !savgnum_os || !savgcol_os) {
			$('#savgnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=savgnum]').focus();
			}
		} else {
			$('#savgnum').removeClass('highlight');
		} if(!iavgnum || !iavgcol || !iavgnum_os || !iavgcol_os) {
			$('#iavgnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=iavgnum]').focus();
			}
		} else {
			$('#iavgnum').removeClass('highlight');
		} if(!atnum || !atcol || !atnum_os || !atcol_os) {
			$('#atnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=atnum]').focus();
			}
		} else {
			$('#atnum').removeClass('highlight');
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
		} if(!s_savg_os) {
			$('#savg_snum_os').removeClass('hidden');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=snum_os]').focus();
			}
		} else {
			$('#savg_snum_os').addClass('hidden');
		} if(!i_iavg_os) {
			$('#iavg_inum_os').removeClass('hidden');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=inum_os]').focus();
			}
		} else {
			$('#iavg_inum_os').addClass('hidden');
		}
	});
});