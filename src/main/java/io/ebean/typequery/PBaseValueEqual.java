package io.ebean.typequery;

import java.util.Collection;

/**
 * Base property for types that primarily have equal to.
 *
 * @param <R> the root query bean type
 * @param <T> the number type
 */
public abstract class PBaseValueEqual<R, T> extends TQPropertyBase<R> {

  /**
   * Construct with a property name and root instance.
   *
   * @param name property name
   * @param root the root query bean instance
   */
  public PBaseValueEqual(String name, R root) {
    super(name, root);
  }

  /**
   * Construct with additional path prefix.
   */
  public PBaseValueEqual(String name, R root, String prefix) {
    super(name, root, prefix);
  }

  /**
   * Set the property as the map key for a <code>findMap</code> query.
   *
   * <pre>{@code
   *
   *   Map<String, Customer> map =
   *     new QCustomer()
   *       .organisation.id.equalTo(42)
   *       .email.asMapKey() // email property as map key
   *       .findMap();
   *
   * }</pre>
   *
   * @return the root query bean instance
   */
  public final R asMapKey() {
    expr().setMapKey(_name);
    return _root;
  }

  /**
   * Is equal to or Null.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public final R equalToOrNull(T value) {
    expr().eqOrNull(_name, value);
    return _root;
  }

  /**
   * Is equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public final R equalTo(T value) {
    expr().eq(_name, value);
    return _root;
  }

  /**
   * Is equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public final R eq(T value) {
    expr().eq(_name, value);
    return _root;
  }

  /**
   * Is not equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public final R notEqualTo(T value) {
    expr().ne(_name, value);
    return _root;
  }

  /**
   * Is not equal to.
   *
   * @param value the equal to bind value
   * @return the root query bean instance
   */
  public final R ne(T value) {
    expr().ne(_name, value);
    return _root;
  }

  /**
   * Is in a list of values.
   *
   * @param values the list of values for the predicate
   * @return the root query bean instance
   */
  @SafeVarargs
  public final R in(T... values) {
    expr().in(_name, (Object[]) values);
    return _root;
  }

  /**
   * Is NOT in a list of values.
   *
   * @param values the list of values for the predicate
   * @return the root query bean instance
   */
  @SafeVarargs
  public final R notIn(T... values) {
    expr().notIn(_name, (Object[]) values);
    return _root;
  }

  /**
   * Is in a list of values. Synonym for in().
   *
   * @param values the list of values for the predicate
   * @return the root query bean instance
   */
  @SafeVarargs
  public final R isIn(T... values) {
    expr().in(_name, (Object[]) values);
    return _root;
  }

  /**
   * Is in a list of values.
   *
   * @param values the list of values for the predicate
   * @return the root query bean instance
   */
  public final R in(Collection<T> values) {
    expr().in(_name, values);
    return _root;
  }

  /**
   * Is NOT in a list of values.
   *
   * @param values the list of values for the predicate
   * @return the root query bean instance
   */
  public final R notIn(Collection<T> values) {
    expr().notIn(_name, values);
    return _root;
  }

  /**
   * Is in a list of values. Synonym for in().
   *
   * @param values the list of values for the predicate
   * @return the root query bean instance
   */
  public final R isIn(Collection<T> values) {
    expr().in(_name, values);
    return _root;
  }
}
