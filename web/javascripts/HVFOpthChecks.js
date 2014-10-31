/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
	$('input[name=ght]').change(function(){
		validateGlaucoma();
	});
	$('input[name=psdp]').change(function(){
		validateGlaucoma();
	});
	$('input[name=cluster]').change(function(){
		validateGlaucoma();
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
	$('input[type=text][name=pts_five]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=pts_one]').on('input', function() {
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
	$('input[type=text][name=central_15]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	$('input[type=text][name=central_0]').on('input', function() {
		if(this.value != this.value.replace(/[^0-9]/g, '')) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});

	$('input[name=glau]').change(function() {
		determineSeverity();
	});
	$('input[name=mdsign]').change(function(){
		colorMD();
		determineSeverity();
	});
	$('input[name=mddb]').change(function() {
		colorMD();
		determineSeverity();
	});
	$('input[name=central_0]').change(function() {
		colorCentral();
		determineSeverity();
	});
	$('input[name=central_15]').change(function(){
		colorCentral();
		determineSeverity();
	});
	$('input[name=pts_five]').change(function() {
		colorPts();
		determineSeverity();
	});
	$('input[name=pts_one]').change(function() {
		colorPts();
		determineSeverity();
	});
	$('input[name=sup_hem]').change(function() {
		colorHem();
		determineSeverity();
	});
	$('input[name=inf_hem]').change(function() {
		colorHem();
		determineSeverity();
	});

	var glauCount = 0;
	var severeGlau = false;
	var lossGlau = false;
	if($('input[name=glau][value=1]').prop('checked')) {
		glauCount++;
	}
	if(!$('input[name=severe][value=0]').prop('checked')) {
		glauCount++;
		severeGlau = true;
	}
	if($('input[name=vf_loss][value=1]').prop('checked')) {
		glauCount++;
		lossGlau = true;
		$('#vf_defectInvis').removeClass('invis');
	}
	else {
		$('#vf_defectInvis').addClass('invis');
	}

	$('input[name=glau]').change(function() {
		if($('input[name=glau][value=1]').prop('checked')) {
			glauCount++;
		}
		else {
			glauCount--;
		}
	});
	$('input[name=severe]').change(function() {
		if(!$('input[name=severe][value=0]').prop('checked') && !severeGlau) {
			glauCount++;
			severeGlau = true;
		}
		else if($('input[name=severe][value=0]').prop('checked') && severeGlau){
			glauCount--;
			severeGlau = false;
		}
	});
	$('input[name=vf_loss]').change(function() {
		if($('input[name=vf_loss][value=1]').prop('checked') && !lossGlau) {
			glauCount++;
			lossGlau = true;
			$('#vf_defectInvis').removeClass('invis');
		}
		else if(!$('input[name=vf_loss][value=1]').prop('checked') && lossGlau) {
			glauCount--;
			lossGlau = false;
			$('#vf_defectInvis').addClass('invis');
			clearDefect();
		}
	});

	$('input').bind('keypress', function(e){
		e = e || window.event;
		var unicode = e.keycode || e.which;

		if(unicode === 13) {
			e.preventDefault();
			var focus = document.activeElement;
			
			if (focus.name === 'vf_defect_oth'){
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
		var fp = $('input[type=text][name=fp]').val().length > 0;
		var fn = $('input[type=text][name=fn]').val().length > 0; 

		var ght = $('input[type=radio][name=ght][value=1]').prop('checked')
			|| $('input[type=radio][name=ght][value=2]').prop('checked') 
			|| $('input[type=radio][name=ght][value=3]').prop('checked') 
			|| $('input[type=radio][name=ght][value=4]').prop('checked')
			|| $('input[type=radio][name=ght][value=5]').prop('checked')
			|| $('input[type=radio][name=ght][value=999]').prop('checked');

		var psdp = $('input[type=radio][name=psdp][value=1]').prop('checked')
			|| $('input[type=radio][name=psdp][value=2]').prop('checked') 
			|| $('input[type=radio][name=psdp][value=3]').prop('checked') 
			|| $('input[type=radio][name=psdp][value=4]').prop('checked')
			|| $('input[type=radio][name=psdp][value=5]').prop('checked')
			|| $('input[type=radio][name=psdp][value=6]').prop('checked')
			|| $('input[type=radio][name=psdp][value=999]').prop('checked');

		var cluster = $('input[type=radio][name=cluster][value=1]').prop('checked')
			|| $('input[type=radio][name=cluster][value=2]').prop('checked')
			|| $('input[type=radio][name=cluster][value=999]').prop('checked'); 

		var glau = $('input[type=radio][name=glau][value=1]').prop('checked')
			|| $('input[type=radio][name=glau][value=2]').prop('checked'); 

		var mdsign = $('input[type=radio][name=mdsign][value=1]').prop('checked')
			|| $('input[type=radio][name=mdsign][value=2]').prop('checked');

		var mddb = $('input[type=text][name=mddb]').val().length > 0; 

		var central_15 = $('input[type=text][name=central_15]').val().length > 0;
		var central_0 = $('input[type=text][name=central_0]').val().length > 0; 


		var sup_hem = $('input[type=radio][name=sup_hem][value=0]').prop('checked')
			|| $('input[type=radio][name=sup_hem][value=1]').prop('checked')
			|| $('input[type=radio][name=sup_hem][value=2]').prop('checked');
		var inf_hem = $('input[type=radio][name=inf_hem][value=0]').prop('checked')
			|| $('input[type=radio][name=inf_hem][value=1]').prop('checked')
			|| $('input[type=radio][name=inf_hem][value=2]').prop('checked');
		var pts_five = $('input[type=text][name=pts_five]').val().length > 0; 
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

		var vf_defect = $('input[type=checkbox][name=vf_defect1]').prop('checked')
			|| $('input[type=checkbox][name=vf_defect2]').prop('checked') 
			|| $('input[type=checkbox][name=vf_defect3]').prop('checked')
			|| $('input[type=checkbox][name=vf_defect4]').prop('checked')
			|| $('input[type=checkbox][name=vf_defect5]').prop('checked')
			|| $('input[type=checkbox][name=vf_defect6]').prop('checked')
			|| $('input[type=checkbox][name=vf_defect7]').prop('checked')
			|| $('input[type=checkbox][name=vf_defect8]').prop('checked');
		
		var vf_defect_oth_yes = $('input[type=checkbox][name=vf_defect8]').prop('checked');
		var vf_defect_oth = $('input[type=text][name=vf_defect_oth]').val().length > 0;

		var focus = false;

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
		} else if(!pts_five) {
			e.preventDefault(); 
			$('input[type=text][name=pts_five]').focus();
		} else if(!pts_one) {
			e.preventDefault(); 
			$('input[type=text][name=pts_one]').focus();
		} else if(!severe) {
			e.preventDefault(); 
			$('input[type=radio][name=severe][value=1]').focus();
		}
		if(!reliable_review) {
			focus = true;
			e.preventDefault(); 
			$('input[type=radio][name=reliable_review][value=1]').focus();
			$('#reliable_review').addClass('highlight');
		} else {
			$('#reliable_review').removeClass('highlight');
		} if(!vf_loss) {
			if(!focus) {
				e.preventDefault(); 
				focus = true;
				$('input[type=radio][name=vf_loss][value=1]').focus();
			}
			$('#vf_loss').addClass('highlight');
		} else {
			$('#vf_loss').removeClass('highlight');
		} if(vf_loss_oth_yes && !vf_loss_oth) {
			if(!focus){
				focus = true;
				e.preventDefault(); 
				$('input[type=text][name=vf_loss_oth]').focus();
			}
			$('#vf_loss_oth').addClass('highlight');
		} else {
			$('#vf_loss_oth').removeClass('highlight');
		} if(!vf_defect && glauCount == 3) {
			if(!focus) {
				focus = true;
				e.preventDefault(); 
				$('input[type=radio][name=vf_defect][value=1]').focus();
			}
			$('#vf_defect').addClass('highlight');
		} else {
			$('#vf_defect').removeClass('highlight');
		} if(vf_defect_oth_yes && !vf_defect_oth) {
			if(!focus) {
				focus = true;
				e.preventDefault(); 
				$('input[type=text][name=vf_defect_oth]').focus();
			}
			$('#vf_defect_oth').addClass('highlight');
		} else {
			$('#vf_defect_oth').removeClass('highlight');
		} if(glauCount !== 3 && glauCount !== 0) {
			if(!focus) {
				focus = true;
				e.preventDefault();
				$('input[type=radio][name=glau]').focus();
			}
			$('#glauMatch1').removeClass('invis');
			$('#glauMatch2').removeClass('invis');
			$('#glauMatch3').removeClass('invis');
		} else {
			$('#glauMatch1').addClass('invis');
			$('#glauMatch2').addClass('invis');
			$('#glauMatch3').addClass('invis');
		}
	});
});

function clearDefect() {
	for(var i=1; i<9; i++) {
		$('input[name=vf_defect'+i+']').prop('checked',false);
	}
	$('input[name=vf_defect_oth]').val("");
}

function colorMD() {
	var md = $('#MD');
	var sign = ($('input[name=mdsign][value=2]').prop('checked')) ? 2 : 1;
	var db = $('input[name=mddb]').val();

	md.removeClass('noGlau');
	md.removeClass('earlyGlau');
	md.removeClass('modGlau');
	md.removeClass('advGlau');
	md.removeClass('severeGlau');
	md.removeClass('endGlau');

	if(sign === 2 || Number(db) <= 0.001) {
		md.addClass('noGlau');
	} else if(Number(db) <= 6) {
		md.addClass('earlyGlau');
	} else if(Number(db) <= 12) {
		md.addClass('modGlau');
	} else if(Number(db) <= 20) {
		md.addClass('advGlau');
	} else {
		md.addClass('severeGlau');
	}
}

function colorCentral()  {
	var central = $('#central');
	var zero4 = $('input[name=central_0][value=4]').prop('checked');
	var zero3 = $('input[name=central_0][value=3]').prop('checked');
	var zero2 = $('input[name=central_0][value=2]').prop('checked');
	var zero1 = $('input[name=central_0][value=1]').prop('checked');
	var zero0 = $('input[name=central_0][value=0]').prop('checked');
	var fifteen4 = $('input[name=central_15][value=4]').prop('checked');
	var fifteen3 = $('input[name=central_15][value=3]').prop('checked');
	var fifteen2 = $('input[name=central_15][value=2]').prop('checked');
	var fifteen1 = $('input[name=central_15][value=1]').prop('checked');
	var fifteen0 = $('input[name=central_15][value=0]').prop('checked');

	central.removeClass('noGlau');
	central.removeClass('earlyGlau');
	central.removeClass('modGlau');
	central.removeClass('advGlau');
	central.removeClass('severeGlau');
	central.removeClass('endGlau');

	if(zero4 || zero3 || zero2) {
		central.addClass('severeGlau');
	} else if (zero1) {
		central.addClass('advGlau');
	} else if (!fifteen0) {
		central.addClass('modGlau');
	} else  {
		central.addClass('noGlau');
	}
}

function colorPts() {
	var pts = $('#pts');
	var one = Number($('input[name=pts_one]').val());
	var five = Number($('input[name=pts_five]').val());

	pts.removeClass('noGlau');
	pts.removeClass('earlyGlau');
	pts.removeClass('modGlau');
	pts.removeClass('advGlau');
	pts.removeClass('severeGlau');
	pts.removeClass('endGlau');

		if((five >= 19 && five <=36) && (one >= 12 && one <= 18)) {
			pts.addClass('modGlau');
		}
		else if((five >= 37 && five <= 55) && (one >= 19 && one <= 36)) {
			pts.addClass('advGlau');
		}
		else if((five >= 56 && five < 999) || (one >= 37 && one < 999)) {
			pts.addClass('severeGlau');
		}
		else if(((five >= 19 && five <=36) && (one >=19)) || (five >= 37 && (one >= 12 && one <= 18))){
			;//do nothing; it makes no contribution to classifications
		}
		else if (five < 999 && one < 999) {
			pts.addClass('noGlau');
		}
}

function colorHem() {
	var hem = $('#hem');
	var sup2 = $('input[name=sup_hem][value=2]').prop('checked');
	var inf2 = $('input[name=inf_hem][value=2]').prop('checked');
	var sup1 = $('input[name=sup_hem][value=1]').prop('checked');
	var inf1 = $('input[name=inf_hem][value=1]').prop('checked');
	var sup0 = $('input[name=sup_hem][value=0]').prop('checked');
	var inf0 = $('input[name=inf_hem][value=0]').prop('checked');

	hem.removeClass('noGlau');
	hem.removeClass('earlyGlau');
	hem.removeClass('modGlau');
	hem.removeClass('advGlau');
	hem.removeClass('severeGlau');
	hem.removeClass('endGlau');

	if(sup2 && inf2) {
		hem.addClass('severeGlau');
	} else if ((sup2 || sup1) && (inf1 || inf2)) {
		hem.addClass('advGlau');
	} else if (sup2 || sup1 || inf2 || inf1) {
		hem.addClass('modGlau');
	}
	else {
		hem.addClass('noGlau');
	}
}

function validateGlaucoma() {
	var glauPresenceCount = 0;

	if($('input[name=ght][value=3]').prop('checked')) {
		glauPresenceCount++;
	}
	if (!$('input[name=psdp][value=5]').prop('checked') && !$('input[name=psdp][value=999]').prop('checked')) {
		glauPresenceCount++;
	}
	if($('input[name=cluster][value=1]').prop('checked')){
		glauPresenceCount++;
	}

	if(glauPresenceCount > 0) {
		$('input[name=glau][value=1]').prop('checked',true);
	}
	else {
		$('input[name=glau][value=2]').prop('checked',true);
	}

	determineSeverity();
}

function determineSeverity() {
	var severeCount = $('.severeGlau').length - 1;
	var advCount = $('.advGlau').length -1;
	var modCount = $('.modGlau').length -1;
	var earlyCount = $('.earlyGlau').length -1;
	var noCount = $('.noGlau').length -1;
	var md = 0;
	var presence = $('input[name=glau][value=2]').prop('checked');
	
	if($('#MD').hasClass('severeGlau')) {
		severeCount--;
		md = 4;
	}
	if($('#MD').hasClass('advGlau')) {
		advCount--;
		md = 3;
	}
	if($('#MD').hasClass('modGlau')) {
		modCount--;
		md = 2;
	}
	if($('#MD').hasClass('earlyGlau')) {
		earlyCount--;
		md = 1;
	}
	if($('#MD').hasClass('noGlau')) {
		noCount--;
	}

	if(presence){
		$('input[name=severe][value=0]').prop('checked',true);
	}
	else if(md === 0) {
		$('input[name=severe][value=1]').prop('checked',true);
	}
	else if(md === 1) {
		if((modCount + advCount + severeCount) >= 1) {
			$('input[name=severe][value=2]').prop('checked',true);
		}
		else {
			$('input[name=severe][value=1]').prop('checked',true);
		}
	}
	else if(md === 2) {
		if((advCount + modCount + severeCount) === 0) {
			$('input[name=severe][value=1]').prop('checked',true);
		}
		else if((advCount + severeCount) >= 1 && earlyCount === 0)  {
			$('input[name=severe][value=3]').prop('checked',true);
		}
		else {
			$('input[name=severe][value=2]').prop('checked',true);
		}
	}
	else if(md === 3) {
		if((advCount + severeCount) == 0) {
			$('input[name=severe][value=2]').prop('checked',true);
		}
		else if(severeCount >= 1 && (earlyCount + modCount) === 0) {
			$('input[name=severe][value=4]').prop('checked',true);
		}
		else {
			$('input[name=severe][value=3]').prop('checked',true);
		}
	}
	else {
		if(severeCount === 0) {
			$('input[name=severe][value=3]').prop('checked',true);
		}
		else {
			$('input[name=severe][value=4]').prop('checked',true);
		}
	}
}
