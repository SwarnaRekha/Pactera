/**
 * 
 */
package core.com.pactera.TestNG;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestInfo {
	
	
	String tcid();
	String dataFileName() default "";
	boolean recordTest() default false;
	String description() default "";
	

}
