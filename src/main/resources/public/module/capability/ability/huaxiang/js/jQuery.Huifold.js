/* =======================================================================
 * jQuery.Huifold.js v2.1 折叠
 * http://www.h-ui.net/
 * Created & Modified by guojunhui
 * Date modified 2012.10.12
 * Copyright 2017-2020 郭俊辉 All rights reserved.
 * Licensed under MIT license.
 * http://opensource.org/licenses/MIT
 * ========================================================================*/
!function($) {
	$.fn.Huifold = function(options){
		var defaults = {
			item: '.item',
			titCell:'.Huifold-header',
			mainCell:'.Huifold-body',
			type:1,//1	只打开一个，可以全部关闭;2	必须有一个打开;3	可打开多个
			trigger:'click',
			className:"selected",
			speed:'normal',
			openKeys: []
		}
		var options = $.extend(defaults, options);
		this.each(function() {
			var that = $(this);
			if(options.openKeys && options.openKeys.length > 0) {
				for(var i=0;i<options.openKeys.length; i++) {
					
					var $item = that.find(options.item).eq(options.openKeys[i]);
					$item.find(options.titCell).addClass(options.className);
					$item.find(options.mainCell).show();
					if ($item.find(options.titCell).find("b")) {
						$item.find(options.titCell).find("b").html("-");
					}
				}
			}

			that.find(options.titCell).on(options.trigger,function(){
				if ($(this).next().is(":visible")) {
					if (options.type == 2) {
						return false;
					} else {
						$(this).next().slideUp(options.speed).end().removeClass(options.className);
						if ($(this).find("b")) {
							$(this).find("b").html("+");
						}
					}
				}else {
					if (options.type == 3) {
						$(this).next().slideDown(options.speed).end().addClass(options.className);
						if ($(this).find("b")) {
							$(this).find("b").html("-");
						}
					} else {
						that.find(options.mainCell).slideUp(options.speed);
						that.find(options.titCell).removeClass(options.className);
						if (that.find(options.titCell).find("b")) {
							that.find(options.titCell).find("b").html("+");
						}
						$(this).next().slideDown(options.speed).end().addClass(options.className);
						if ($(this).find("b")) {
							$(this).find("b").html("-");
						}
					}
				}
			});
			
		});
	}
} (window.jQuery);