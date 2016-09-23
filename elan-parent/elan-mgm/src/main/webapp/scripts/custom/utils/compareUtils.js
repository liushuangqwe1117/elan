/**
 * 原始值、新值对比工具.
 * 
 * @author 潘瑞峥
 * @date 2013-02-23
 */
$( function() {

	/** checkbox组序号. */
	var checkboxGroupIndex = 0;

	/** checkbox组名. */
	var checkboxGroupName = 'checkboxGroup';

	/** checkbox key. */
	var checkboxGroupKey = '_group';

	/** 注意图标的class. */
	var comparePanelClass = 'compare-panel';

	/** 消息提示的class */
	var compareTipClass = 'compare-tip';

	/** 消息文字. */
	var compareMsgPrefix = '原始值为：';

	/** 类型JAVA Map对象. */
	var $map = $( 'body' );

	/** 淡出淡入的时间. */
	var time = 500;

	/**
	 * 可能会修改值的元素.
	 */
	$(
			':text:visible:enabled, textarea:visible:enabled, select:visible:enabled, :radio:visible:enabled, :checkbox:visible:enabled' )
	/**
	 * 第一次聚焦时, 先将原始值保存在map里, 并删除该事件.
	 */
	.focusin( function() {

		// 当前$元素.
		var $el = $( this );
		// 当前对象id.
		var elId = $el.attr( 'id' );
		// 当前对象的值(可能为text()也可能为val()).
		var elValue = undefined;
		// map的key.
		var key = elId;

		/*
		 * select.
		 */
		if( $el.is( 'select' ) ) {
			elValue = $el.find( 'option:selected' ).text();
		}
		/*
		 * textarea || :text.
		 */
		else if( $el.is( 'textarea' ) || $el.is( ':text' ) ) {
			elValue = $el.val();
		}
		/*
		 * :radio.
		 * 
		 * 将name作为key.一般都是按name分组.
		 */
		else if( $el.is( ':radio' ) ) {
			var elName = $el.attr( 'name' );
			key = elName;
			// 同组radio.
			var $radioSiblings = $el.siblings( ':radio[name=' + elName + ']:visible:enabled' ).andSelf();
			// 选中的radio.
			var $radioChecked = $radioSiblings.filter( ':checked' );
			/* 没有选中的. */
			if( 0 == $radioChecked.size() ) {
				elValue = '';
			}
			/* 有选中的. */
			else {
				var _id = $radioChecked.eq( 0 ).attr( 'id' );
				// 选中的值.
				elValue = $el.siblings( 'label[for=' + _id + ']' ).text();
			}

			// 删除同组元素的该事件.
			$radioSiblings.unbind( 'focusin' );
		}
		/*
		 * :checkbox.
		 */
		else if( $el.is( ':checkbox' ) ) {
			key = checkboxGroupName + checkboxGroupIndex++;
			// 同组checkbox.
			var $checkboxSiblings = $el.siblings( ':checkbox:visible:enabled' ).andSelf();
			// 选中的checkbox.
			var $checkboxChecked = $checkboxSiblings.filter( ':checked' );
			// 增加组名.
			$checkboxSiblings.attr( checkboxGroupKey, key );

			/* 没有选中的. */
			if( 0 == $checkboxChecked.size() ) {
				elValue = [];
			}
			/* 有选中的. */
			else {
				elValue = [];
				$checkboxChecked.each( function() {
					var _id = $( this ).attr( 'id' );
					// 选中的值.
					elValue.push( $el.siblings( 'label[for=' + _id + ']' ).text() );
				} );
			}

			// 删除同组元素的该事件.
			$checkboxSiblings.unbind( 'focusin' );
		}

		// 存放在map里.
		$map.data( key, elValue );

		// 第一次事件后删除该事件.
		$el.unbind( 'focusin' );

	} )

	/**
	 * 失去焦点时, 判断值是否更改.
	 */
	.focusout(
			function() {

				// 当前$元素.
				var $el = $( this );
				// 当前对象id.
				var elId = $el.attr( 'id' );
				// 原始值.
				var originalVal = undefined;
				// 原始值类型.
				var originalType = undefined;
				// 当前对象的值(可能为text()也可能为val()).
				var elValue = '';
				// map的key.
				var key = elId;

				/*
				 * select.
				 */
				if( $el.is( 'select' ) ) {
					elValue = $el.find( 'option:selected' ).text();
				}
				/*
				 * textarea || :text.
				 */
				else if( $el.is( 'textarea' ) || $el.is( ':text' ) ) {
					elValue = $el.val();
				}
				/*
				 * :radio.
				 * 
				 * 将name作为key.一般都是按name分组.
				 */
				else if( $el.is( ':radio' ) ) {
					var elName = $el.attr( 'name' );
					key = elName;

					// 同组radio.
					var $radioSiblings = $el.siblings( ':radio[name=' + elName + ']:visible:enabled' ).andSelf();
					// 选中的radio.
					var $radioChecked = $radioSiblings.filter( ':checked' );
					var _id = $radioChecked.eq( 0 ).attr( 'id' );
					// 选中的值.
					elValue = $el.siblings( 'label[for=' + _id + ']' ).text();
				}
				/*
				 * :checkbox.
				 * 
				 * 将组名作为key.
				 */
				else if( $el.is( ':checkbox' ) ) {
					elValue = [];
					var elGroupName = $el.attr( checkboxGroupKey );
					key = elGroupName;

					// 同组checkbox.
					var $checkboxSiblings = $el.siblings( ':checkbox:visible:enabled' ).andSelf();
					// 选中的checkbox.
					var $checkboxChecked = $checkboxSiblings.filter( ':checked' );
					$checkboxChecked.each( function() {
						var _id = $( this ).attr( 'id' );
						// 选中的值.
						elValue.push( $el.siblings( 'label[for=' + _id + ']' ).text() );
					} );
				}

				// 原始值.
				originalVal = $map.data( key );
				// 原始值类型.
				originalType = $.type( originalVal );

				/* string. */
				if( 'string' === originalType ) {
					/* 原始值和现在值不相等. */
					if( originalVal != elValue ) {
						if( 0 == $( 'span.' + comparePanelClass + '[_for=' + key + ']' ).size() ) {
							$el.parent().append(
									'<span class="' + comparePanelClass + '" _for="' + key + '" _content="'
											+ compareMsgPrefix + originalVal + '"></span>' );
						}
					}
					/* 相等. */
					else {
						$( 'span.' + comparePanelClass + '[_for=' + key + ']' ).remove();
					}
				}
				/* array */
				else if( 'array' === originalType ) {
					var mergeArray = mergeAndUniqueArray( originalVal, elValue );
					/* 原始值和现在值任意一个长度与合并后的长度不相等. */
					if( originalVal.length != mergeArray.length || elValue.length != mergeArray.length ) {
						if( 0 == $( 'span.' + comparePanelClass + '[_for=' + key + ']' ).size() ) {
							$el.parent().append(
									'<span class="' + comparePanelClass + '" _for="' + key + '" _content="'
											+ compareMsgPrefix + originalVal.join( '、' ) + '"></span>' );
						}
					}
					/* 相等. */
					else {
						$( 'span.' + comparePanelClass + '[_for=' + key + ']' ).remove();
					}
				}

			} );

	/**
	 * 消息提示元素.
	 */
	$( 'span.' + comparePanelClass ).live( 'mouseenter mouseleave', function( event ) {
		if( 'mouseenter' == event.type ) {
			showTooltip( event.pageX, event.pageY, $( this ).attr( '_content' ) );
		} else if( 'mouseleave' == event.type ) {
			$( 'div.' + compareTipClass ).remove();
		}
	} );

	/**
	 * 显示提示消息.
	 */
	function showTooltip( x, y, contents ) {
		$( '<div class="' + compareTipClass + '">' + contents + '</div>' ).css( {
			top: y + 5,
			left: x + 5
		} ).appendTo( 'body' ).fadeIn( time );
	}

	/**
	 * 合并并去掉重复值.
	 */
	function mergeAndUniqueArray( array1, array2 ) {
		var arrayMerge = $.map( array2, function( n ) {
			return n;
		} );
		for( var i = 0; i < array1.length; i++ ) {
			var v = array1[ i ];
			if( -1 === $.inArray( v, array2 ) ) {
				arrayMerge.push( v );
			}
		}
		return arrayMerge;
	}

} );