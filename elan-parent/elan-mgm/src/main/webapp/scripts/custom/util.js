jQuery.lightssh = {
	/**
	 * 弹出菜单
	 */
	popupMenu: function( evt ) {
		if( evt == null )
			evt = window.event;
		var target = ( typeof evt.target != 'undefined' ) ? evt.target : evt.srcElement;
		// var target = evt.target || evt.srcElement;
		var popupMenu = $( target ).children( 'div.popup-menu-layer' )[ 0 ];
		if( popupMenu == null )
			popupMenu = $( $( target ).parent() ).children( '.popup-menu-layer' )[ 0 ];

		$.each( $( '.popup-menu-layer' ), function( index, item ) {
			if( item != popupMenu ) {
				$( item ).hide();
			}
		} );

		if( popupMenu == null )
			return;

		var posx = 0;
		var posy = 0;
		var e = evt;
		if( e.pageX || e.pageY ) {
			posx = e.pageX;
			posy = e.pageY;
		} else if( e.clientX || e.clientY ) {
			posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
		}
		
		var td_element = $( popupMenu ).parent();
		var tr_element = $( td_element ).parent();
		var tr_width = $(tr_element).width();
		var tr_top = tr_element.offset().top;
		var td_width = $( td_element ).outerWidth(); //td.action width
		var td_height = $( td_element ).outerHeight(); //td.action height
		
		var left = tr_width - $( popupMenu ).width() - td_width;
		var top = tr_top + (td_height/2);
		if( (tr_top + $( popupMenu ).height()-(td_height/2)) > ($(document).height()-70) )
			top = tr_top - $( popupMenu ).height() + (td_height/2);
		if($(".ui-layout-center").scrollTop()>0){
			top = top+$(".ui-layout-center").scrollTop();
		}
		$( popupMenu ).css( "left", left + "px" );
		$( popupMenu ).css( "top", (top-70) + "px" );
		$( popupMenu ).show();
		// $("td").removeClass("action");
		// $(event).addClass("action");//事件目标
		
		$( "tr" ).removeClass( "focused" );
		$( tr_element ).addClass( "focused" );// 事件目标父结点
	}

	/**
	 * 显示域错误信息
	 */
	,
	showFieldError: function( ele, val ) {
		$( ele ).focus();
		$( ele ).after( $( "<label name='error' class='error'>" + val + "</label>" ) );
	}

	/**
	 * 删除提示消息
	 */
	,
	removeMessages: function() {
		var eTarget = null;
		if( event != null )
			eTarget = event.currentTarget ? event.currentTarget : event.srcElement;

		if( eTarget == null )
			return;

		$( eTarget ).parent().hide( 'slow' );
	}

	/**
	 * 显示提示信息
	 */
	,showActionError: function( msg ) {
		$.lightssh.showActionMessage(msg,'error');
	}
	
	/**
	 * 显示提示信息
	 */
	,showActionMessage: function( msg,clazz ) {
		if( $( "div.messages" ).length == 0 ) {
			$( 'table' ).before( "<div class='messages'></div>" )
		}
		
		if( clazz == null )
			clazz = 'success';
		
		if( $( ".messages > ."+clazz ).length == 0 ) {
			$( '.messages' ).append( "<div class='"+clazz+"'>" + msg + "</div>" )
		} else {
			$( '.error' ).text( msg );
		}
	}

	/**
	 * 校验用户密码
	 */
	,
	checkPassword: function( opts, callback ) {
		var dialog = 'dialog-check-password';
		var url = '/security/account/validatepassword.do';
		var title = '验证登录密码';
		var password_label = '登录密码';
		if( opts != null ) {
			if( opts.url != null )
				url = opts.url;
			else if( opts.contextPath != null )
				url = opts.contextPath + url;
				
			if( opts.title != null )
				title = opts.title;
		}

		var password = '';
		var options = {
			title: title,
			resizable: false,modal: true,
			width: 320, height: 180,zIndex: 9999,
			buttons: {
				'确定': function() {
					password = $( "#password" ).val();
					// $( this ).remove();
					$.lightssh.callbackCheckPassword( {
						'dialog': dialog,
						'result': true,
						'password': $.md5( password ),
						'url': url
					}, callback );
				},
				'取消': function() {
					$( this ).remove();
					$.lightssh.callbackCheckPassword( {
						'result': false
					} );
				}
			},
			close: function() {
				$( this ).remove();
				$.lightssh.callbackCheckPassword( {
					'result': false
				} );
			}
		};

		var el = "<div id='" + dialog + "' style='display:none;'>" 
			+ "<div id='dialog-check-password-hint' style='height:20px;margin:2px 0 5px 0;'></div>"
			+ "<p><label>" + password_label + "：</label>" 
			+ "<input type='password' id='password' size='30'/>" + "</p></div>";
		$( 'body' ).append( el );

		$( 'div#'+dialog ).dialog( options );
	}

	/**
	 * 显示对话框提示信息
	 */
	,showDialogMessage: function( id,msg,focus,field ) {
		var hint = $('#'+id);
		$(hint).html( msg  );
		$(hint).addClass('error');
		$(hint).animate({
			backgroundColor: "#FCF8E3",
			color: "#F30"
		}, 3000 );
		
		if( focus != null && field != null && focus )
			$('#'+field).focus();
	}
	
	,callbackCheckPassword: function( opts, callback ) {
		var result = false;
		if( opts.result && opts.url != null ) {
			var param = {
				'password': opts.password,
				'rand': Math.random()
			};
			$.post( opts.url, param, function( json ) {
				result = json.passed;
				if( !result ) {
					$.lightssh.showDialogMessage('dialog-check-password-hint','密码不正确！');
				} else {
					$( '#' + opts.dialog ).remove();
				}
				callback( result );
			} );
		}

		return result;
	}
	
	/**
	 * 检查授权
	 */
	,checkAuth: function( opts, callback ) {
		var url = '/security/account/validateauth.do';
		var authUrl = '/security/account/authresource.do';
		var forceSubmit = true,checkPassword=true;
		var noneAuthMsg = '无权限访问该资源！';
		if( opts != null ) {
			if( opts.url != null )
				url = opts.url;
			else if( opts.contextPath != null )
				url = opts.contextPath + url;
			
			if( opts.authUrl != null )
				authUrl = opts.authUrl;
			else if( opts.contextPath != null )
				authUrl = opts.contextPath + authUrl;
			
			if( opts.forceSubmit != null )
				forceSubmit = opts.forceSubmit;
			
			if( opts.checkPassword != null )
				checkPassword = opts.checkPassword;
			
			if( opts.noneAuthMsg != null )
				noneAuthMsg = opts.noneAuthMsg;
		}
		
		var param = {
			'targetUrl': opts.targetUrl
			,'rand': Math.random()
		};
		
		$.post(url, param, function( json ) {
			//alert( json.authType + '\n'+ json.message )
			if( 'hasAuth' == json.authType ){
				if( checkPassword ) //校验密码
					$.lightssh.checkPassword({'contextPath':opts.contextPath},callback );
				else
					callback(true)
			}else if('genAuth' == json.authType ){ //已经授权
				$.lightssh.showActionMessage('授权可继续使用！');
				callback(true,json.ticket); 
			}else if('noneAuth' == json.authType ){//无权限也提交
				if( !forceSubmit )
					$.lightssh.showActionError( noneAuthMsg );
				callback( forceSubmit ); //无权限也提交
			}else if('canAuth' == json.authType){
				$.lightssh.showActionError('需要授权……');
				$.lightssh.showAuthDialog({
					'targetUrl': opts.targetUrl
					,'authUrl': authUrl
				},callback);
			}
		});
	}
	
	/**
	 * 授权资源对话框
	 */
	,showAuthDialog: function( opts, callback ) {
		var dialog = 'dialog-authorize-resource';
		var title = '用户授权',username_label='用户名',password_label='密码';
		
		var options = {
				title: title,
				resizable: false,modal: true,
				width: 360, height: 220,zIndex: 9999,
				buttons: {
					'确定': function() {
						$.lightssh.authResource({
							'password': $( "#password" ).val()
							,'username': $( "#username" ).val()
							,'authUrl': opts.authUrl
							,'targetUrl': opts.targetUrl
							,'dialog': dialog
						},callback);
					},
					'取消': function() {
						$( this ).remove();
					}
				},
				close: function() {
					$( this ).remove();
				}
			};
		
		var el = "<div id='" + dialog + "' style='display:none;'>" 
			+ "<div id='"+dialog+"-hint' style='height:20px;margin:2px 0 5px 0;'></div>"
			+ "<label for='username' style='float:left;width:100px'>" + username_label+ "：</label>" 
			+ "<input type='text' id='username' size='30'/><br/>" 
			+ "<label for='password' style='float:left;width:100px'>" + password_label+ "：</label>" 
			+ "<input type='password' id='password' size='30'/><br/>" 
			+ "</div>";
		$( 'body' ).append( el );
	
		$( 'div#'+dialog ).dialog( options );
	}
	
	/**
	 * 授权资源
	 */
	,authResource: function( opts, callback ) {
		var hint_id = 'dialog-authorize-resource-hint';//提示DIV
		if( opts==null || opts.username == null || opts.username == ''){
			$.lightssh.showDialogMessage(hint_id,'用户名不能为空！',true,'username');
			return;
		}else if( opts==null || opts.password == null || opts.password == ''){
			$.lightssh.showDialogMessage(hint_id,'密码不能为空！',true,'password');
			return;
		}
		
		var param = {
				'targetUrl': opts.targetUrl
				,'username': opts.username
				,'password': $.md5(opts.password)
				,'rand': Math.random()
			};
		
		$.post(opts.authUrl, param, function( json ) {
			//alert( json.passed + '\n' + json.ticket + '\n'+ json.message )
			if( !json.passed ) {
				$.lightssh.showDialogMessage(hint_id,json.message);
			} else {
				$( '#' + opts.dialog ).remove();
			}
			callback( json.passed,json.ticket,json.message );
		});
	}
	
	/**
	 * 滚动到页首
	 */
	,scrollTop: function( ) {
		$(window).scroll(function () {
			if ($(this).scrollTop() > 100) {
				$('#back-top').fadeIn();
			} else {
				$('#back-top').fadeOut();
			}
		});

		// scroll body to 0px on click
		$('#back-top a').click(function () {
			$('body,html').animate({
				scrollTop: 0
			}, 500);
			return false;
		});
	}
};
$( function() {

	/**
	 * 消息提示元素.
	 */
	$( 'span[class^=compare]' ).bind( 'mouseenter mouseleave', function( event ) {
		if( 'mouseenter' == event.type ) {
			if( $( this ).is( '[title]' ) ) {
				$( this ).attr( '_title', $( this ).attr( 'title' ) );
				$( this ).removeAttr( 'title' );
			}
			showTooltip( event.pageX, event.pageY, $( this ).attr( '_title' ) );
		} else if( 'mouseleave' == event.type ) {
			$( 'div.compare-tip' ).remove();
		}
	} );

	/**
	 * 显示提示消息.
	 */
	function showTooltip( x, y, contents ) {
		$( '<div class="compare-tip">' + contents + '</div>' ).css( {
			top: y + 5,
			left: x + 8
		} ).appendTo( 'body' ).fadeIn( 500 );
	}

} );