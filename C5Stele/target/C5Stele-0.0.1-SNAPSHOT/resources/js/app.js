$ = jQuery;
    	
  var loginForm = function (){
    			
					var width=document.body.offsetWidth;
								
					if (width < 768) {
									
//						$('#username1').attr('name', 'xx');
//						$('#password1').attr('name', 'yy');
					
									
						if ($('#username2')) {
							$('#username2').attr('name', 'j_username');
						}
									
						if ($('#password2')) {
							$('#password2').attr('name', 'j_password');
						}
									
					} else {
									
//						$('#username2').attr('name', 'aa');
//						$('#password2').attr('id', 'bb');
									
						if ($('#username1')) {
							$('#username1').attr('name', 'j_username');
						}
									
					    if ($('#password1')) {
							$('#password1').attr('name', 'j_password');
						}
					}
								
				};