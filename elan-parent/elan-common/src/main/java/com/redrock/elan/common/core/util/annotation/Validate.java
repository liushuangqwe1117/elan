/**
 * 版权所有(C) 2015 深圳市雁联计算系统有限公司
 * 创建:lyg 2015-8-24
 */

package com.redrock.elan.common.core.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/** 
 * @author lyg
 * @date 2015-8-24
 * @description：TODO
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface Validate {
//是否可以为空
boolean nullable() default false;
//最大长度
int maxLength() default 0;
//最小长度
int minLength() default 0;
//提供几种常用的正则验证
RegexType regexType() default RegexType.NONE;
//自定义正则验证
String regexExpression() default "";
//参数或者字段描述,这样能够显示友好的异常信息
String description() default "";
}
