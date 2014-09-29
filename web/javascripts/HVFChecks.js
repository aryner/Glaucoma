/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
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
		} else if(!mon_oth2_c74) {
			e.preventDefault(); 
		} else if(!tar){
			e.preventDefault(); 
		} else if(!tar_oth){
			e.preventDefault(); 
		} else if(!lossnum){
			e.preventDefault(); 
		} else if(!lossden){
			e.preventDefault(); 
		} else if(!fp){
			e.preventDefault(); 
		} else if(!fn){
			e.preventDefault(); 
		} else if(!dur){
			e.preventDefault(); 
		} else if(!fov){
			e.preventDefault(); 
		} else if(!stimintens){
			e.preventDefault(); 
		} else if(!stimcol){
			e.preventDefault(); 
		} else if(!stimcol_oth){
			e.preventDefault(); 
		} else if(!back){
			e.preventDefault(); 
		} else if(!strategy){
			e.preventDefault(); 
		} else if(!strategy_oth){
			e.preventDefault(); 
		} else if(!pup){
			e.preventDefault(); 
		} else if(!vanum){
			e.preventDefault(); 
		} else if(!vaden){
			e.preventDefault(); 
		} else if(!sph_sign){
			e.preventDefault(); 
		} else if(!sph_num){
			e.preventDefault(); 
		} else if(!cyl_sign){
			e.preventDefault(); 
		} else if(!cyl_num){
			e.preventDefault(); 
		} else if(!axis){
			e.preventDefault(); 
		} else if(!ght){
			e.preventDefault(); 
		} else if(!vfi){
			e.preventDefault(); 
		} else if(!mdsign){
			e.preventDefault(); 
		} else if(!mddb){
			e.preventDefault(); 
		} else if(!mdp){
			e.preventDefault(); 
		} else if(!psdsign){
			e.preventDefault(); 
		} else if(!psddb){
			e.preventDefault(); 
		} else if(!psdp){
			e.preventDefault(); 
		} else if(!pts2){
			e.preventDefault(); 
		} else if(!sup_hem){
			e.preventDefault(); 
		} else if(!inf_hem){
			e.preventDefault(); 
		} else if(!sup_hem2){
			e.preventDefault(); 
		} else if(!inf_hem2){
			e.preventDefault(); 
		} else if(!pts_five){
			e.preventDefault(); 
		} else if(!pts_contig){
			e.preventDefault(); 
		} else if(!pts_one){
			e.preventDefault(); 
		} else if(!cluster){
			e.preventDefault(); 
		} 
	});
});