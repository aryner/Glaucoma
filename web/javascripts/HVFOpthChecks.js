/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
	$('input[name=mdsign]').change(function(){
		colorMD();
	});
	$('input[name=mddb]').change(function() {
		colorMD();
	});
	$('input[name=central_0]').change(function() {
		colorCentral();
	});
	$('input[name=central_15]').change(function(){
		colorCentral();
	});
	$('input[name=pts_five]').change(function() {
		colorPts();
	});
	$('input[name=pts_one]').change(function() {
		colorPts();
	});
	$('input[name=sup_hem]').change(function() {
		colorHem();
	});
	$('input[name=inf_hem]').change(function() {
		colorHem();
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

		var sup_hem = $('input[type=text][name=sup_hem]').val().length > 0; 
		var inf_hem = $('input[type=text][name=inf_hem]').val().length > 0; 
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
	} else if(Number(db) < 6) {
		md.addClass('earlyGlau');
	} else if(Number(db) < 12) {
		md.addClass('modGlau');
	} else if(Number(db) < 20) {
		md.addClass('advGlau');
	} else {
		md.addClass('severeGlau');
	}
}

function colorCentral()  {
	var central = $('#central');
	var zero = Number($('input[name=central_0').val());
	var fifteen = Number($('input[name=central_15').val());

	central.removeClass('noGlau');
	central.removeClass('earlyGlau');
	central.removeClass('modGlau');
	central.removeClass('advGlau');
	central.removeClass('severeGlau');
	central.removeClass('endGlau');

	if(zero >= 2) {
		central.addClass('severeGlau');
	} else if (zero === 1) {
		central.addClass('advGlau');
	} else if (fifteen > 0) {
		central.addClass('modGlau');
	} else  {
		central.addClass('earlyGlau');
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

	if (five >= 56 || one >= 37) {
		pts.addClass('severeGlau');
	} else if (five >= 37 && one >= 19) {
		pts.addClass('advGlau');
	} else if (five >= 19 && one >= 12) {
		pts.addClass('modGlau');
	} else if(((five >= 19 && five <=36) && (one >=19)) || (five >= 37 && (one >= 12 && one <= 36))){
		;//do nothing; it makes no contribution to classifications
	} else {
		pts.addClass('earlyGlau');
	}
}

function colorHem() {
	var hem = $('#hem');
	var sup = Number($('input[name=sup_hem]').val());
	var inf = Number($('input[name=inf_hem]').val());

	hem.removeClass('noGlau');
	hem.removeClass('earlyGlau');
	hem.removeClass('modGlau');
	hem.removeClass('advGlau');
	hem.removeClass('severeGlau');
	hem.removeClass('endGlau');
console.log('sup = '+sup+' : inf = '+inf);
	if(sup >= 2 && inf >= 2) {
		hem.addClass('severeGlau');
	} else if (sup >= 1 && inf >= 1) {
		hem.addClass('advGlau');
	} else if ((sup + inf) >= 1) {
		hem.addClass('modGlau');
	} else {
		hem.addClass('earlyGlau');
	}
}