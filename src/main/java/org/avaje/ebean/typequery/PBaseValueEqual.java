package org.avaje.ebean.typequery;

/**
 * Base property for types that primarily have equal to.
 *
 * @param <R> the root query bean type
 * @param <T> the number type
 */
public abstract class PBaseValueEqual<R,T> extends TQProperty<R> {

  /**
   * Construct with a property name and root instance.
   *
   * @param name property name
   * @param root the root query bean instance
   */
  public PBaseValueEqual(String name, R root) {
    super(name , root);
  }

  /**
   * Construct with additional path prefix.
   */
  public PBaseValueEqual(String name, R root, String prefix) {
    super(name, root, prefix);
  }

  /**
   * Is equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R equalTo(T value) {
    expr().eq(name, value);
    return root;
  }

  /**
   * Is equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R eq(T value) {
    expr().eq(name, value);
    return root;
  }

  /**
   * Is in a list of values.
   *
   * @param values the list of values for the predicate
   * @return the root query bean instance
   */
  public R in(T... values) {
    expr().in(name, values);
    return root;
  }

  /**
   * Is NOT in a list of values.
   *
   * @param values the list of values for the predicate
   * @return the root query bean instance
   */
  public R notIn(T... values) {
    expr().notIn(name, values);
    return root;
  }
}
