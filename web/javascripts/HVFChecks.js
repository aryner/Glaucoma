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
	$('input[type=text][name=pts2]').on('input', function() {
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

	$(':submit').click(function(e){ 
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
			|| $('input[type=radio][name=stimintens][value=5]').prop('checked');

		var stimcol = $('input[type=radio][name=stimcol][value=1]').prop('checked')
			|| $('input[type=radio][name=stimcol][value=2]').prop('checked') 
			|| $('input[type=radio][name=stimcol][value=3]').prop('checked') 
			|| $('input[type=radio][name=stimcol][value=4]').prop('checked');

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
			|| $('input[type=radio][name=ght][value=5]').prop('checked');

		var vfi = $('input[type=text][name=vfi]').val().length > 0; 

		var mdsign = $('input[type=radio][name=mdsign][value=1]').prop('checked')
			|| $('input[type=radio][name=mdsign][value=2]').prop('checked');

		var mddb = $('input[type=text][name=mddb]').val().length > 0; 

		var mdp = $('input[type=radio][name=mdp][value=1]').prop('checked')
			|| $('input[type=radio][name=mdp][value=2]').prop('checked') 
			|| $('input[type=radio][name=mdp][value=3]').prop('checked') 
			|| $('input[type=radio][name=mdp][value=4]').prop('checked')
			|| $('input[type=radio][name=mdp][value=5]').prop('checked')
			|| $('input[type=radio][name=mdp][value=6]').prop('checked');

		var psdsign = $('input[type=radio][name=psdsign][value=1]').prop('checked')
			|| $('input[type=radio][name=psdsign][value=2]').prop('checked');

		var psddb = $('input[type=text][name=psddb]').val().length > 0; 

		var psdp = $('input[type=radio][name=psdp][value=1]').prop('checked')
			|| $('input[type=radio][name=psdp][value=2]').prop('checked') 
			|| $('input[type=radio][name=psdp][value=3]').prop('checked') 
			|| $('input[type=radio][name=psdp][value=4]').prop('checked')
			|| $('input[type=radio][name=psdp][value=5]').prop('checked')
			|| $('input[type=radio][name=psdp][value=6]').prop('checked');

		var pts2 = $('input[type=text][name=pts2]').val().length > 0; 
		var sup_hem = $('input[type=text][name=sup_hem]').val().length > 0; 
		var inf_hem = $('input[type=text][name=inf_hem]').val().length > 0; 
		var sup_hem2 = $('input[type=text][name=sup_hem2]').val().length > 0; 
		var inf_hem2 = $('input[type=text][name=inf_hem2]').val().length > 0; 
		var pts_five = $('input[type=text][name=pts_five]').val().length > 0; 
		var pts_contig = $('input[type=text][name=pts_contig]').val().length > 0; 
		var pts_one = $('input[type=text][name=pts_one]').val().length > 0; 

		var cluster = $('input[type=radio][name=cluster][value=1]').prop('checked')
			|| $('input[type=radio][name=cluster][value=2]').prop('checked'); 

		if(!mon) {
			e.preventDefault(); 
			$('input[type=radio][name=mon][value=1]').focus();
		} else if(!mon_oth2_c74) {
			e.preventDefault(); 
			$('input[type=text][name=mon_oth2_c74]').focus();
		} else if(!tar){
			e.preventDefault(); 
			$('input[type=radio][name=tar][value=1]').focus();
		} else if(!tar_oth){
			e.preventDefault(); 
			$('input[type=text][name=tar_oth]').focus();
		} else if(!lossnum){
			e.preventDefault(); 
			$('input[type=text][name=lossnum]').focus();
		} else if(!lossden){
			e.preventDefault(); 
			$('input[type=text][name=lossden]').focus();
		} else if(!fp){
			e.preventDefault(); 
			$('input[type=text][name=fp]').focus();
		} else if(!fn){
			e.preventDefault(); 
			$('input[type=text][name=fn]').focus();
		} else if(!dur){
			e.preventDefault(); 
			$('input[type=text][name=dur]').focus();
		} else if(!fov){
			e.preventDefault(); 
			$('input[type=text][name=fov]').focus();
		} else if(!stimintens){
			e.preventDefault(); 
			$('input[type=radio][name=stimintens][value=1]').focus();
		} else if(!stimcol){
			e.preventDefault(); 
			$('input[type=radio][name=stimcol][value=1]').focus();
		} else if(!stimcol_oth){
			e.preventDefault(); 
			$('input[type=text][name=stimcol_oth]').focus();
		} else if(!back){
			e.preventDefault(); 
			$('input[type=text][name=back]').focus();
		} else if(!strategy){
			e.preventDefault(); 
			$('input[type=radio][name=strategy][value=1]').focus();
		} else if(!strategy_oth){
			e.preventDefault(); 
			$('input[type=text][name=strategy_oth]').focus();
		} else if(!pup){
			e.preventDefault(); 
			$('input[type=text][name=pup]').focus();
		} else if(!vanum){
			e.preventDefault(); 
			$('input[type=text][name=vanum]').focus();
		} else if(!vaden){
			e.preventDefault(); 
			$('input[type=text][name=vaden]').focus();
		} else if(!sph_sign){
			e.preventDefault(); 
			$('input[type=radio][name=sph_sign][value=1]').focus();
		} else if(!sph_num){
			e.preventDefault(); 
			$('input[type=text][name=sph_num]').focus();
		} else if(!cyl_sign){
			e.preventDefault(); 
			$('input[type=radio][name=cyl_sign][value=1]').focus(); 
		} else if(!cyl_num){
			e.preventDefault(); 
			$('input[type=text][name=cyl_num]').focus();
		} else if(!axis){
			$('input[type=text][name=axis]').focus();
			e.preventDefault(); 
		} else if(!ght){
			e.preventDefault(); 
			$('input[type=radio][name=ght][value=1]').focus(); 
		} else if(!vfi){
			e.preventDefault(); 
			$('input[type=text][name=vfi]').focus();
		} else if(!mdsign){
			e.preventDefault(); 
			$('input[type=radio][name=mdsign][value=1]').focus(); 
		} else if(!mddb){
			e.preventDefault(); 
			$('input[type=text][name=mddb]').focus();
		} else if(!mdp){
			e.preventDefault(); 
			$('input[type=radio][name=mdp][value=1]').focus(); 
		} else if(!psdsign){
			e.preventDefault(); 
			$('input[type=radio][name=psdsign][value=1]').focus(); 
		} else if(!psddb){
			e.preventDefault(); 
			$('input[type=text][name=psddb]').focus();
		} else if(!psdp){
			e.preventDefault(); 
			$('input[type=radio][name=psdp][value=1]').focus(); 
		} else if(!pts2){
			e.preventDefault(); 
			$('input[type=text][name=pts2]').focus();
		} else if(!sup_hem){
			e.preventDefault(); 
			$('input[type=text][name=sup_hem]').focus();
		} else if(!inf_hem){
			e.preventDefault(); 
			$('input[type=text][name=inf_hem]').focus();
		} else if(!sup_hem2){
			e.preventDefault(); 
			$('input[type=text][name=sup_hem2]').focus();
		} else if(!inf_hem2){
			e.preventDefault(); 
			$('input[type=text][name=inf_hem2]').focus();
		} else if(!pts_five){
			e.preventDefault(); 
			$('input[type=text][name=pts_five]').focus();
		} else if(!pts_contig){
			e.preventDefault(); 
			$('input[type=text][name=pts_contig]').focus();
		} else if(!pts_one){
			e.preventDefault(); 
			$('input[type=text][name=pts_one]').focus();
		} else if(!cluster){
			e.preventDefault(); 
			$('input[type=radio][name=cluster][value=1]').focus(); 
		} 
	});
});