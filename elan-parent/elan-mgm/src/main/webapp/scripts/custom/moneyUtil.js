/**
 * 金额工具JS.
 * 
 * @author 潘瑞峥
 * @date 2011-10-18
 */
$( function() {

	/**
	 * 金额两位小数，不足补零.
	 */
	$( '.myMoney' ).live( 'focusout', function() {
		if( '' == $.trim( $( this ).val() ) )
			return false;
		if( isNaN( Number( $( this ).val() ) ) )
			return false;
		var money = String( Number( $( this ).val() ) );
		var index = money.indexOf( '.' );
		/* 没有小数点 */
		if( -1 == index ) {
			money = money.concat( '.00' );
		}
		/* 存在小数点 */
		else {
			var decimals = money.split( '\.' )[ 1 ];
			for( var i = 0; i < 2 - decimals.length; i++ ) {
				money = money.concat( '0' );
			}
		}
		$( this ).val( money );
	} );

} );