/**
 * 基于jQuery和jQueryUI的一个信息提示插件.
 * 
 * @author 潘瑞峥
 * @date 2012-12-06
 */
( function( $ ) {

	/**
	 * confirm.
	 */
	$.confirm = function( message, fn ) {
		var options = {
			title: '确认',
			resizable: false,
			width: 300,
			height: 150,
			modal: true,
			zIndex: 9999,
			buttons: {
				'确定': function() {
					$( this ).remove();
					if( fn ) {
						fn( true );
					}
				},
				'取消': function() {
					$( this ).remove();
					if( fn ) {
						fn( false );
					}
				}
			},
			close: function() {
				$( this ).remove();
				if( fn ) {
					fn( false );
				}
			}
		};
		message = null == message ? '' : message;
		var el = '<div class="dialog-confirm"><p>' + message + '</p></div>';
		$( 'body' ).append( el );

		$( 'div.dialog-confirm' ).dialog( options );
	};

	/**
	 * 校验登录密码.
	 */
	$.checkPassword = function( fn ) {
		var options = {
			title: '校验登录密码',
			resizable: false,
			width: 350,
			height: 150,
			modal: true,
			zIndex: 9999,
			buttons: {
				'校验': function() {
					if( $( 'form.dialog_password_form' ).valid() ) {
						$( this ).remove();
						fn();
					}
				},
				'取消': function() {
					$( this ).remove();
				}
			},
			close: function() {
				$( this ).remove();
			}
		};
		var url = path + '/security/account/checkPassword.do';
		var el = '<div class="dialog_password login_form"><form class="dialog_password_form" method="post">'
				+ '<label>登录密码　</label>' + '<input type="password" name="password" class="password" />'
				+ '</form></div>';
		$( 'body' ).append( el );

		/* 利用验证框架进行密码校验等. */
		$( 'form.dialog_password_form' ).validate( {
			onkeyup: false,
//			onfocusout: false,
//			onclick: false,
//			focusInvalid: false,
//			focusCleanup: false,
			rules: {
				password: {
					required: true,
					remote: {
						url: url,
						type: 'post',
						dataType: 'json',
						data: {
							password: function() {
								return $.md5( $( 'input:password[name=password]' ).val() );
							}
						}
					}
				}
			},
			messages: {
				password: {
					remote: "密码错误"
				}
			},
			submitHandler: function() {
				$( 'div.dialog_password' ).dialog( 'close' );
				fn();
			}
		} );

		$( 'div.dialog_password' ).dialog( options );
	};

	/**
	 * 加载中.
	 */
	$.loading = function() {
		var title = '正在处理，请耐心等待...';
		var el = '<div id="loading" style="z-index: 9999; top: 32%; left: 30%; position: absolute; cursor: wait;'
				+ ' width: 470px; height: 80px; color: black; border: 2px solid red; background-color: #c0d2ec; text-align: center;">'
				+ '<div style="padding-top: 30px; font-weight: bold;">' + title + '</div></div>';
		$( 'body' ).append( el );
		$( ':button, :submit' ).attr( 'disabled', 'true' );
	};

} )( jQuery );