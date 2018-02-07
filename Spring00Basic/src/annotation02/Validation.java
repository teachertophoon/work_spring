package annotation02;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)				// 애노테이션을 필드에 선언하겠다는 뜻
@Retention(RetentionPolicy.RUNTIME)		// 실행중에 사용된다는 뜻
public @interface Validation {
	int max() default Integer.MAX_VALUE; // 애노테이션 변수 max에 MAX_VALUE 값을 기본값으로 설정
	int min() default Integer.MIN_VALUE; // 애노테이션 변수 min에 MIN_VALUE 값을 기본값으로 설정
	int maxLength() default 50;			 // 애노테이션 변수 maxLength에 50을 기본값으로 설정
	boolean required() default false;	 // 애노테이션 변수 required에 false를 기본값으로 설정
}
