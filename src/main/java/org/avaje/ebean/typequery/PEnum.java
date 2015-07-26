package org.avaje.ebean.typequery;

/**
 * BigDecimal property.
 *
 * @param <E> the enum specific type
 * @param <R> the root query bean type
 */
public class PEnum<R,E> extends TQProperty<R> {

  /**
   * Construct with a property name and root instance.
   *
   * @param name property name
   * @param root the root query bean instance
   */
  public PEnum(String name, R root) {
    super(name, root);
  }

  /**
   * Construct with additional path prefix.
   */
  public PEnum(String name, R root, String prefix) {
    super(name, root, prefix);
  }

  /**
   * Is equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R equalTo(E value) {
    expr().eq(name, value);
    return root;
  }

  /**
   * Is not equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R notEqualTo(E value) {
    expr().ne(name, value);
    return root;
  }

  /**
   * Is equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R eq(E value) {
    expr().eq(name, value);
    return root;
  }

  /**
   * Is not equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R ne(E value) {
    expr().ne(name, value);
    return root;
  }

  /**
   * Is in a list of values.
   *
   * @param values the list of enum values for the predicate
   * @return the root query bean instance
   */
  public R in(E... values) {
    expr().in(name, values);
    return root;
  }

//  public R notIn(E... value) {
//    expr().notIn(name, value);
//    return root;
//  }

}
