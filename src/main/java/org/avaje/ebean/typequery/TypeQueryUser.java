package org.avaje.ebean.typequery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to denote a class that uses type query beans.
 * <p>
 * Classes that use type query beans can be modified via a java agent to reduce the cost of using
 * type queries by replacing the public field access with a dynamic property access. This means the
 * type query bean properties are only created on demand and at minimal cost.
 * </p>
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeQueryUser {

}
