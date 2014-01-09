package pl.cocktails.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  if field have annotation, we must delete associated blob in blobstore
 * @author Tomek
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Blobkey {
	public String getterName();
}
