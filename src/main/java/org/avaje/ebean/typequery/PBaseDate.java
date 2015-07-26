package org.avaje.ebean.typequery;

/**
 * Base property for date and date time types.
 *
 * @param <R> the root query bean type
 * @param <D> the date time type
 */
public abstract class PBaseDate<R,D> extends TQProperty<R> {

  /**
   * Construct with a property name and root instance.
   *
   * @param name property name
   * @param root the root query bean instance
   */
  public PBaseDate(String name, R root) {
    super(name , root);
  }

  /**
   * Construct with additional path prefix.
   */
  public PBaseDate(String name, R root, String prefix) {
    super(name, root, prefix);
  }

  /**
   * Is equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R equalTo(D value) {
    expr().eq(name, value);
    return root;
  }

  /**
   * Same as greater than.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R after(D value) {
    expr().gt(name, value);
    return root;
  }

  /**
   * Same as less than.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R before(D value) {
    expr().lt(name, value);
    return root;
  }

  /**
   * Is equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R eq(D value) {
    expr().eq(name, value);
    return root;
  }

  /**
   * Greater than.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R gt(D value) {
    expr().gt(name, value);
    return root;
  }

  /**
   * Less than.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R lt(D value) {
    expr().lt(name, value);
    return root;
  }

  /**
   * Greater than or equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R ge(D value) {
    expr().ge(name, value);
    return root;
  }

  /**
   * Less than or equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R le(D value) {
    expr().le(name, value);
    return root;
  }

  /**
   * Between lower and upper values.
   *
   * @param lower the lower bind value
   * @param upper the upper bind value
   * @return the root query bean instance
   */
  public R between(D lower, D upper) {
    expr().between(name, lower, upper);
    return root;
  }
}
