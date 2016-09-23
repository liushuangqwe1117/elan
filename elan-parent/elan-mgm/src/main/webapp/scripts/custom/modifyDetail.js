$( function() {
	
	if(!$( '#frm' )){
		return ;
	}
	
	var url = $( '#frm' ).attr( 'action' );

	var workTypeName = '_type';

	var idName = '_id';

	/** 需要编辑的当前年. */
	var currentYear = $( '#currentYear' ).val();

	var date = new Date( currentYear, '0' );

	$( '#yearDatepicker' ).datepicker( {
		numberOfMonths: [ 3, 4 ],
		hideIfNoPrevNext: true,
		showButtonPanel: false,
		changeYear: false,
		changeMonth: false,
		defaultDate: date,
		minDate: date,
		maxDate: new Date( currentYear, '11', '31' )
	} );

	/** 删除当天、选中等的CSS. */
	$( 'a.ui-state-highlight' ).removeClass( 'ui-state-highlight' );
	$( 'a.ui-state-hover' ).removeClass( 'ui-state-hover' );
	$( 'a.ui-state-active' ).removeClass( 'ui-state-active' );
	
	//居中对齐
	$("#yearDatepicker .ui-widget-content").css("margin","0 auto");

	/**
	 * 将后台数据放入Array.
	 */
	$( '#paramArray div' ).each( function() {
		$( '#paramArray' ).data( $( this ).attr( 'key' ), $( this ).attr( 'value' ) );
	} );

	/**
	 * 将非工作日变红.
	 */
	$( 'a.ui-state-default' ).each( function( index ) {
		var c = $( this ).parent().attr( 'onclick' ).toString();
		var arguments = c.substring( c.indexOf( '(' ) + 1, c.indexOf( ')' ) );
		var ar = arguments.split( ',' );

		var year = ar[ 2 ];
		var month = ( parseInt( ar[ 1 ] ) + 1 ).toString();
		var day = $( this ).text();

		var cDate = format( year, month, day );

		/* 非工作日. */
		if( 'NO' == $( '#paramArray' ).data( cDate ) ) {
			$( this ).css( {
				'background': 'none #FF870F'
			} ).attr( workTypeName, '0' );
		} else {
			$( this ).attr( workTypeName, '1' );
		}
		$( this ).parent().removeAttr( 'onclick' );
		$( this ).attr( idName, cDate ).removeAttr( 'href' ).css( 'cursor', 'pointer' );
	} );

	/**
	 * 格式化日期.
	 */
	function format( year, month, day ) {
		var time = year;
		if( 1 == month.length ) {
			time += '0' + month;
		} else {
			time += month;
		}
		if( 1 == day.length ) {
			time += '0' + day;
		} else {
			time += day;
		}
		return time;
	}

	/**
	 * 修改.
	 */
	$( 'a.ui-state-default' ).click( function() {
		var $dom = $( this );
		var $id = $dom.attr( idName );
		var $type = $dom.attr( workTypeName );
		//不能修改之前的工作日
		var dt = new Date();
		var y = dt.getFullYear();
		var m = dt.getMonth();
		var d = dt.getDate();
		dt.setFullYear(y,m,d)
		//操作日期
		if($id != '' && $id.length == 8){
			var year = $id.substring(0,4);
			var month = $id.substring(4,6);
			//IE8下 parseInt("08")有问题;parseInt("08")等于0
			if(month.substring(0,1) === "0"){
				month = month.substring(1)
			}
			var day = $id.substring(6);
			if(day.substring(0,1) === "0"){
				day = day.substring(1)
			}
			var clickDt = new Date();
			clickDt.setFullYear(parseInt(year),(parseInt(month)-1),parseInt(day));
			if(clickDt.getTime() <= dt.getTime()){
				alert("不能修改今天及之前的工作日历");
				return ;
			}
		} else {
			alert("格式错误:"+$id);
		}
		/* 非工作日. */
		if( '0' == $type ) {
			if( confirm( '您确定要将该日期改为[工作日]？' ) ) {
				$.getJSON( url, 'dayId=' + $id + '&workDayType=1', function() {
					$dom.attr( workTypeName, '1' ).removeAttr( 'style' ).add( 'ui-state-default' );
				} ).error( function() {
					alert( '修改失败！' );
				} );
			}
		}
		/* 工作日. */
		else {
			if( confirm( '您确定要将该日期改为[非工作日]？' ) ) {
				$.getJSON( url, 'dayId=' + $id + '&workDayType=0', function(rtn) {
					if(rtn){
						$dom.attr( workTypeName, '0' ).css( {
							'background': 'none #FF870F'
						} );
					}else{
						alert( '修改失败！' );
					}
				} ).error( function() {
					alert( '修改失败！' );
				} );
			}
		}
	} );

} );