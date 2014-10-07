/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
	$(':submit[value=Submit]').click(function(e){ 
		var fp = $('input[type=text][name=fp]').val().length > 0;
		var fn = $('input[type=text][name=fn]').val().length > 0; 

		var ght = $('input[type=radio][name=ght][value=1]').prop('checked')
			|| $('input[type=radio][name=ght][value=2]').prop('checked') 
			|| $('input[type=radio][name=ght][value=3]').prop('checked') 
			|| $('input[type=radio][name=ght][value=4]').prop('checked')
			|| $('input[type=radio][name=ght][value=5]').prop('checked');

		var psdp = $('input[type=radio][name=psdp][value=1]').prop('checked')
			|| $('input[type=radio][name=psdp][value=2]').prop('checked') 
			|| $('input[type=radio][name=psdp][value=3]').prop('checked') 
			|| $('input[type=radio][name=psdp][value=4]').prop('checked')
			|| $('input[type=radio][name=psdp][value=5]').prop('checked')
			|| $('input[type=radio][name=psdp][value=6]').prop('checked');

		var cluster = $('input[type=radio][name=cluster][value=1]').prop('checked')
			|| $('input[type=radio][name=cluster][value=2]').prop('checked'); 

		var glau = $('input[type=radio][name=glau][value=1]').prop('checked')
			|| $('input[type=radio][name=glau][value=2]').prop('checked'); 

		var mdsign = $('input[type=radio][name=mdsign][value=1]').prop('checked')
			|| $('input[type=radio][name=mdsign][value=2]').prop('checked');

		var mddb = $('input[type=text][name=mddb]').val().length > 0; 

		var mdp = $('input[type=radio][name=mdp][value=1]').prop('checked')
			|| $('input[type=radio][name=mdp][value=2]').prop('checked') 
			|| $('input[type=radio][name=mdp][value=3]').prop('checked') 
			|| $('input[type=radio][name=mdp][value=4]').prop('checked')
			|| $('input[type=radio][name=mdp][value=5]').prop('checked')
			|| $('input[type=radio][name=mdp][value=6]').prop('checked');

		var central_15 = $('input[type=text][name=central_15]').val().length > 0;
		var central_0 = $('input[type=text][name=central_0]').val().length > 0; 

		var sup_hem = $('input[type=text][name=sup_hem]').val().length > 0; 
		var inf_hem = $('input[type=text][name=inf_hem]').val().length > 0; 
		var sup_hem2 = $('input[type=text][name=sup_hem2]').val().length > 0; 
		var inf_hem2 = $('input[type=text][name=inf_hem2]').val().length > 0; 
		var pts_five = $('input[type=text][name=pts_five]').val().length > 0; 
		var pts_contig = $('input[type=text][name=pts_contig]').val().length > 0; 
		var pts_one = $('input[type=text][name=pts_one]').val().length > 0; 

		var severe = $('input[type=radio][name=severe][value=0]').prop('checked')
			|| $('input[type=radio][name=severe][value=1]').prop('checked') 
			|| $('input[type=radio][name=severe][value=2]').prop('checked') 
			|| $('input[type=radio][name=severe][value=3]').prop('checked')
			|| $('input[type=radio][name=severe][value=4]').prop('checked')
			|| $('input[type=radio][name=severe][value=5]').prop('checked');

		var reliable_review = $('input[type=radio][name=reliable_review][value=1]').prop('checked')
			|| $('input[type=radio][name=reliable_review][value=2]').prop('checked');

		var vf_loss = $('input[type=radio][name=vf_loss][value=1]').prop('checked')
			|| $('input[type=radio][name=vf_loss][value=2]').prop('checked') 
			|| $('input[type=radio][name=vf_loss][value=3]').prop('checked')
			|| $('input[type=radio][name=vf_loss][value=4]').prop('checked')
			|| $('input[type=radio][name=vf_loss][value=5]').prop('checked');

		var vf_loss_oth_yes = $('input[type=radio][name=vf_loss][value=5]').prop('checked');
		var vf_loss_oth = $('input[type=text][name=vf_loss_oth]').val().length > 0;

		var vf_defect = $('input[type=radio][name=vf_defect][value=1]').prop('checked')
			|| $('input[type=radio][name=vf_defect][value=2]').prop('checked') 
			|| $('input[type=radio][name=vf_defect][value=3]').prop('checked')
			|| $('input[type=radio][name=vf_defect][value=4]').prop('checked')
			|| $('input[type=radio][name=vf_defect][value=5]').prop('checked')
			|| $('input[type=radio][name=vf_defect][value=6]').prop('checked')
			|| $('input[type=radio][name=vf_defect][value=7]').prop('checked')
			|| $('input[type=radio][name=vf_defect][value=8]').prop('checked');
		
		var vf_defect_oth_yes = $('input[type=radio][name=vf_defect][value=8]').prop('checked');
		var vf_defect_oth = $('input[type=text][name=vf_defect_oth]').val().length > 0;

		if(!fn) {
			e.preventDefault(); 
			$('input[type=text][name=fn]').focus();
		} else if(!fp) {
			e.preventDefault(); 
			$('input[type=text][name=fp]').focus();
		} else if(!ght) {
			e.preventDefault(); 
			$('input[type=radio][name=ght][value=1]').focus();
		} else if(!psdp) {
			e.preventDefault(); 
			$('input[type=radio][name=psdp][value=1]').focus();
		} else if(!cluster) {
			e.preventDefault(); 
			$('input[type=radio][name=cluster][value=1]').focus();
		} else if(!glau) {
			e.preventDefault(); 
			$('input[type=radio][name=glau][value=1]').focus();
		} else if(!mdsign) {
			e.preventDefault(); 
			$('input[type=radio][name=mdsign][value=1]').focus();
		} else if(!mddb) {
			e.preventDefault(); 
			$('input[type=text][name=mddb]').focus();
		} else if(!mdp) {
			e.preventDefault(); 
			$('input[type=radio][name=mdp][value=1]').focus();
		} else if(!central_15) {
			e.preventDefault(); 
			$('input[type=text][name=central_15]').focus();
		} else if(!central_0) {
			e.preventDefault(); 
			$('input[type=text][name=central_0]').focus();
		} else if(!sup_hem) {
			e.preventDefault(); 
			$('input[type=text][name=sup_hem]').focus();
		} else if(!inf_hem) {
			e.preventDefault(); 
			$('input[type=text][name=inf_hem]').focus();
		} else if(!sup_hem2) {
			e.preventDefault(); 
			$('input[type=text][name=sup_hem2]').focus();
		} else if(!inf_hem2) {
			e.preventDefault(); 
			$('input[type=text][name=inf_hem2]').focus();
		} else if(!pts_five) {
			e.preventDefault(); 
			$('input[type=text][name=pts_five]').focus();
		} else if(!pts_contig) {
			e.preventDefault(); 
			$('input[type=text][name=pts_contig]').focus();
		} else if(!pts_one) {
			e.preventDefault(); 
			$('input[type=text][name=pts_one]').focus();
		} else if(!severe) {
			e.preventDefault(); 
			$('input[type=radio][name=severe][value=1]').focus();
		} else if(!reliable_review) {
			e.preventDefault(); 
			$('input[type=radio][name=reliable_review][value=1]').focus();
		} else if(!vf_loss) {
			e.preventDefault(); 
			$('input[type=radio][name=vf_loss][value=1]').focus();
		} else if(vf_loss_oth_yes && !vf_loss_oth) {
			e.preventDefault(); 
			$('input[type=text][name=vf_loss_oth]').focus();
		} else if(!vf_defect) {
			e.preventDefault(); 
			$('input[type=radio][name=vf_defect][value=1]').focus();
		} else if(vf_defect_oth_yes && !vf_defect_oth) {
			e.preventDefault(); 
			$('input[type=text][name=vf_defect_oth]').focus();
		}
	});
});
