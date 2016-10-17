package org.avaje.ebean.typequery;

/**
 * BigDecimal property.
 *
 * @param <E> the enum specific type
 * @param <R> the root query bean type
 */
public class PEnum<R,E extends Enum<?>> extends TQProperty<R> {

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
    expr().eq(name, value.name());
    return root;
  }

  /**
   * Is not equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R notEqualTo(E value) {
    expr().ne(name, value.name());
    return root;
  }

  /**
   * Is equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R eq(E value) {
    expr().eq(name, value.name());
    return root;
  }

  /**
   * Is not equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public R ne(E value) {
    expr().ne(name, value.name());
    return root;
  }

  /**
   * Is in a list of values.
   *
   * @param values the list of enum values for the predicate
   * @return the root query bean instance
   */
  public R in(E... values) {
    String[] enumNames = new String[values.length];
    for (int i = 0; i < values.length; i++) {
        enumNames[i] = values[i].name();
    }
    expr().in(name, enumNames);
    return root;
  }

  /**
   * Is NOT in a list of values.
   *
   * @param values the list of enum values for the predicate
   * @return the root query bean instance
   */
  public R notIn(E... values) {
    String[] enumNames = new String[values.length];
    for (int i = 0; i < values.length; i++) {
        enumNames[i] = values[i].name();
    }
    expr().notIn(name, enumNames);
    return root;
  }

}
