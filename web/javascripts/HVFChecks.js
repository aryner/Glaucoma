/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){ 
	$('input[type=text][name=lossnum]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=lossden]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
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
	$('input[type=text][name=fov]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=back]').on('input', function() {
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
	$('input[type=text][name=pup]').on('input', function() {
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
	$('input[type=text][name=vanum]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=vaden]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=sph_num]').on('input', function() {
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
	$('input[type=text][name=cyl_num]').on('input', function() {
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
	$('input[type=text][name=axis]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=vfi]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
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
	$('input[type=text][name=psddb]').on('input', function() {
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
	$('input[type=text][name=sup_hem2]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=inf_hem2]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=pts_five]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=pts_contig]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=pts_one]').on('input', function() {
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

			if (focus.name === 'cluster') {
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
		var mon = $('input[type=radio][name=mon][value=1]').prop('checked')
			|| $('input[type=radio][name=mon][value=2]').prop('checked') 
			|| $('input[type=radio][name=mon][value=3]').prop('checked') 
			|| $('input[type=radio][name=mon][value=4]').prop('checked');
		
		var thing = $('input[type=text][name=mon_oth2_c74]').val(); 
		var mon_oth2_c74 = !$('input[type=radio][name=mon][value=4]').prop('checked')  
			|| thing.length > 0;

		var tar = $('input[type=radio][name=tar][value=1]').prop('checked')
			|| $('input[type=radio][name=tar][value=2]').prop('checked') 
			|| $('input[type=radio][name=tar][value=3]').prop('checked') 
			|| $('input[type=radio][name=tar][value=4]').prop('checked')
			|| $('input[type=radio][name=tar][value=5]').prop('checked');

		thing = $('input[type=text][name=tar_oth]').val(); 
		var tar_oth = !$('input[type=radio][name=tar][value=5]').prop('checked') 
			|| thing.length > 0;

		var lossnum = $('input[type=text][name=lossnum]').val().length > 0;
		var lossden = $('input[type=text][name=lossden]').val().length > 0;
		var fp = $('input[type=text][name=fp]').val().length > 0;
		var fn = $('input[type=text][name=fn]').val().length > 0; 

		var durVal = $('input[type=text][name=dur]').val();
		var durFirst = durVal.substring(0,durVal.indexOf(":"));
		var durSecond = durVal.substring(durVal.indexOf(":")+1);
		var dur = durFirst.length === 2 && durSecond.length === 2;

		var fov = $('input[type=text][name=fov]').val().length > 0; 

		var stimintens = $('input[type=radio][name=stimintens][value=1]').prop('checked')
			|| $('input[type=radio][name=stimintens][value=2]').prop('checked') 
			|| $('input[type=radio][name=stimintens][value=3]').prop('checked') 
			|| $('input[type=radio][name=stimintens][value=4]').prop('checked')
			|| $('input[type=radio][name=stimintens][value=5]').prop('checked')
			|| $('input[type=radio][name=stimintens][value=999]').prop('checked');

		var stimcol = $('input[type=radio][name=stimcol][value=1]').prop('checked')
			|| $('input[type=radio][name=stimcol][value=2]').prop('checked') 
			|| $('input[type=radio][name=stimcol][value=3]').prop('checked') 
			|| $('input[type=radio][name=stimcol][value=4]').prop('checked')
			|| $('input[type=radio][name=stimcol][value=999]').prop('checked');

		thing = $('input[type=text][name=stimcol_oth]').val(); 
		var stimcol_oth = !$('input[type=radio][name=stimcol][value=4]').prop('checked') 
			|| thing.length > 0;

		var back = $('input[type=text][name=back]').val().length > 0; 

		var strategy = $('input[type=radio][name=strategy][value=1]').prop('checked')
			|| $('input[type=radio][name=strategy][value=2]').prop('checked') 
			|| $('input[type=radio][name=strategy][value=3]').prop('checked') 
			|| $('input[type=radio][name=strategy][value=4]').prop('checked')
			|| $('input[type=radio][name=strategy][value=5]').prop('checked');

		thing = $('input[type=text][name=strategy_oth]').val(); 
		var strategy_oth = !$('input[type=radio][name=strategy][value=5]').prop('checked') 
			|| thing.length > 0;

		var pup = $('input[type=text][name=pup]').val().length > 0;
		var vanum = $('input[type=text][name=vanum]').val().length > 0;
		var vaden = $('input[type=text][name=vaden]').val().length > 0; 

		var sph_sign = $('input[type=radio][name=sph_sign][value=1]').prop('checked')
			|| $('input[type=radio][name=sph_sign][value=2]').prop('checked');

		var sph_num = $('input[type=text][name=sph_num]').val().length > 0; 

		var cyl_sign = $('input[type=radio][name=cyl_sign][value=1]').prop('checked')
			|| $('input[type=radio][name=cyl_sign][value=2]').prop('checked');

		var cyl_num = $('input[type=text][name=cyl_num]').val().length > 0; 

		var axis = $('input[type=text][name=axis]').val().length > 0; 

		var ght = $('input[type=radio][name=ght][value=1]').prop('checked')
			|| $('input[type=radio][name=ght][value=2]').prop('checked') 
			|| $('input[type=radio][name=ght][value=3]').prop('checked') 
			|| $('input[type=radio][name=ght][value=4]').prop('checked')
			|| $('input[type=radio][name=ght][value=5]').prop('checked')
			|| $('input[type=radio][name=ght][value=999]').prop('checked');

		var vfi = $('input[type=text][name=vfi]').val().length > 0; 

		var mdsign = $('input[type=radio][name=mdsign][value=1]').prop('checked')
			|| $('input[type=radio][name=mdsign][value=2]').prop('checked');

		var mddb = $('input[type=text][name=mddb]').val().length > 0; 

		var mdp = $('input[type=radio][name=mdp][value=1]').prop('checked')
			|| $('input[type=radio][name=mdp][value=2]').prop('checked') 
			|| $('input[type=radio][name=mdp][value=3]').prop('checked') 
			|| $('input[type=radio][name=mdp][value=4]').prop('checked')
			|| $('input[type=radio][name=mdp][value=5]').prop('checked')
			|| $('input[type=radio][name=mdp][value=6]').prop('checked')
			|| $('input[type=radio][name=mdp][value=999]').prop('checked');

		var psdsign = $('input[type=radio][name=psdsign][value=1]').prop('checked')
			|| $('input[type=radio][name=psdsign][value=2]').prop('checked');

		var psddb = $('input[type=text][name=psddb]').val().length > 0; 

		var psdp = $('input[type=radio][name=psdp][value=1]').prop('checked')
			|| $('input[type=radio][name=psdp][value=2]').prop('checked') 
			|| $('input[type=radio][name=psdp][value=3]').prop('checked') 
			|| $('input[type=radio][name=psdp][value=4]').prop('checked')
			|| $('input[type=radio][name=psdp][value=5]').prop('checked')
			|| $('input[type=radio][name=psdp][value=6]').prop('checked')
			|| $('input[type=radio][name=psdp][value=999]').prop('checked');

		var central_15 = $('input[type=radio][name=central_15][value=0]').prop('checked')
			|| $('input[type=radio][name=central_15][value=1]').prop('checked')
			|| $('input[type=radio][name=central_15][value=2]').prop('checked')
			|| $('input[type=radio][name=central_15][value=3]').prop('checked')
			|| $('input[type=radio][name=central_15][value=4]').prop('checked')
			|| $('input[type=radio][name=central_15][value=999]').prop('checked');

		var central_0 = $('input[type=radio][name=central_0][value=0]').prop('checked')
			|| $('input[type=radio][name=central_0][value=1]').prop('checked')
			|| $('input[type=radio][name=central_0][value=2]').prop('checked')
			|| $('input[type=radio][name=central_0][value=3]').prop('checked')
			|| $('input[type=radio][name=central_0][value=4]').prop('checked')
			|| $('input[type=radio][name=central_0][value=999]').prop('checked');

		var sup_hem = $('input[type=radio][name=sup_hem][value=0]').prop('checked')
			|| $('input[type=radio][name=sup_hem][value=1]').prop('checked')
			|| $('input[type=radio][name=sup_hem][value=2]').prop('checked');
		var inf_hem = $('input[type=radio][name=inf_hem][value=0]').prop('checked')
			|| $('input[type=radio][name=inf_hem][value=1]').prop('checked')
			|| $('input[type=radio][name=inf_hem][value=2]').prop('checked');
		var pts_five = $('input[type=text][name=pts_five]').val().length > 0; 
		var pts_one = $('input[type=text][name=pts_one]').val().length > 0; 

		var cluster = $('input[type=radio][name=cluster][value=1]').prop('checked')
			|| $('input[type=radio][name=cluster][value=2]').prop('checked') 
			|| $('input[type=radio][name=cluster][value=999]').prop('checked'); 

		var focused = false;

		if(!mon) {
			focused = true;
			e.preventDefault(); 
			$('input[type=radio][name=mon][value=1]').focus();
			$('#mon').addClass('highlight');
		} else {
			$('#mon').removeClass('highlight');
		} if(!mon_oth2_c74) {
			$('#mon_oth2_c74').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=mon_oth2_c74]').focus();
			}
		} else {
			$('#mon_oth2_c74').removeClass('highlight');
		} if(!tar){
			$('#tar').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=tar][value=1]').focus();
			}
		} else {
			$('#tar').removeClass('highlight');
		} if(!tar_oth){
			$('#tar_oth').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=tar_oth]').focus();
			}
		} else {
			$('#tar_oth').removeClass('highlight');
		} if(!lossnum){
			$('#lossnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=lossnum]').focus();
			}
		} else if(lossden){
			$('#lossnum').removeClass('highlight');
		} if(!lossden){
			$('#lossnum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=lossden]').focus();
			}
		} if(!fp){
			$('#fp').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=fp]').focus();
			}
		} else {
			$('#fp').removeClass('highlight');
		} if(!fn){
			$('#fn').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=fn]').focus();
			}
		} else {
			$('#fn').removeClass('highlight');
		} if(!dur){
			$('#dur').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=dur]').focus();
			}
		} else {
			$('#dur').removeClass('highlight');
		} if(!fov){
			$('#fov').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=fov]').focus();
			}
		} else {
			$('#fov').removeClass('highlight');
		} if(!stimintens){
			$('#stimintens').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=stimintens][value=1]').focus();
			}
		} else {
			$('#stimintens').removeClass('highlight');
		} if(!stimcol){
			$('#stimcol').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=stimcol][value=1]').focus();
			}
		} else {
			$('#stimcol').removeClass('highlight');
		} if(!stimcol_oth){
			$('#stimcol_oth').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=stimcol_oth]').focus();
			}
		} else {
			$('#stimcol_oth').removeClass('highlight');
		} if(!back){
			$('#back').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=back]').focus();
			}
		} else {
			$('#back').removeClass('highlight');
		} if(!strategy){
			$('#strategy').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=strategy][value=1]').focus();
			}
		} else {
			$('#strategy').removeClass('highlight');
		} if(!strategy_oth){
			$('#strategy_oth').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=strategy_oth]').focus();
			}
		} else {
			$('#strategy_oth').removeClass('highlight');
		} if(!pup){
			$('#pup').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=pup]').focus();
			}
		} else {
			$('#pup').removeClass('highlight');
		} if(!vanum){
			$('#vanum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=vanum]').focus();
			}
		} else if(vaden) {
			$('#vanum').removeClass('highlight');
		} if(!vaden){
			$('#vanum').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=vaden]').focus();
			}
		} if(!sph_sign){
			$('#sph_sign').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=sph_sign][value=1]').focus();
			}
		} else {
			$('#sph_sign').removeClass('highlight');
		} if(!sph_num){
			$('#sph_num').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=sph_num]').focus();
			}
		} else {
			$('#sph_num').removeClass('highlight');
		} if(!cyl_sign){
			$('#cyl_sign').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=cyl_sign][value=1]').focus(); 
			}
		} else {
			$('#cyl_sign').removeClass('highlight');
		} if(!cyl_num){
			$('#cyl_num').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=cyl_num]').focus();
			}
		} else {
			$('#cyl_num').removeClass('highlight');
		} if(!axis){
			$('#axis').addClass('highlight');
			if(!focused) {
				focused = true;
				$('input[type=text][name=axis]').focus();
				e.preventDefault(); 
			}
		} else {
			$('#axis').removeClass('highlight');
		} if(!ght){
			$('#ght').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=ght][value=1]').focus(); 
			}
		} else {
			$('#ght').removeClass('highlight');
		} if(!vfi){
			$('#vfi').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=vfi]').focus();
			}
		} else {
			$('#vfi').removeClass('highlight');
		} if(!mdsign){
			$('#mdsign').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=mdsign][value=1]').focus(); 
			}
		} else {
			$('#mdsign').removeClass('highlight');
		} if(!mddb){
			$('#mddb').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=mddb]').focus();
			}
		} else {
			$('#mddb').removeClass('highlight');
		} if(!mdp){
			$('#mdp').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=mdp][value=1]').focus(); 
			}
		} else {
			$('#mdp').removeClass('highlight');
		} if(!psdsign){
			$('#psdsign').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=psdsign][value=1]').focus(); 
			}
		} else {
			$('#psdsign').removeClass('highlight');
		} if(!psddb){
			$('#psddb').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=psddb]').focus();
			}
		} else {
			$('#psddb').removeClass('highlight');
		} if(!psdp){
			$('#psdp').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=psdp][value=1]').focus(); 
			}
		} else {
			$('#psdp').removeClass('highlight');
		} if(!central_15) {
			$('#central_15').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=central_15][value=0]').focus();
			}
		} else {
			$('#central_15').removeClass('highlight');
		} if(!central_0) {
			$('#central_0').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=central_0][value=0]').focus();
			}
		} else {
			$('#central_0').removeClass('highlight');
		} if(!sup_hem){
			$('#sup_hem').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=sup_hem]').focus();
			}
		} else {
			$('#sup_hem').removeClass('highlight');
		} if(!inf_hem){
			$('#inf_hem').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=inf_hem]').focus();
			}
		} else {
			$('#inf_hem').removeClass('highlight');
		} if(!pts_five){
			$('#pts_five').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=pts_five]').focus();
			}
		} else {
			$('#pts_five').removeClass('highlight');
		} if(!pts_one){
			$('#pts_one').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=text][name=pts_one]').focus();
			}
		} else {
			$('#pts_one').removeClass('highlight');
		} if(!cluster){
			$('#cluster').addClass('highlight');
			if(!focused) {
				focused = true;
				e.preventDefault(); 
				$('input[type=radio][name=cluster][value=1]').focus(); 
			}
		} else {
			$('#cluster').removeClass('highlight');
		}
	});
});