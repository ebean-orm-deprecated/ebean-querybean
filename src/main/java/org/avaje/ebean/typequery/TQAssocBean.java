package org.avaje.ebean.typequery;

import com.avaje.ebean.ExpressionList;

/**
 * Base type for associated beans.
 *
 * @param <T> the entity bean type (normal entity bean type e.g. Customer)
 * @param <R> the specific root query bean type (e.g. QCustomer)
 */
public abstract class TQAssocBean<T, R> {

  protected final String name;

  protected final R root;

  /**
   * Construct with a property name and root instance.
   *
   * @param name the name of the property
   * @param root the root query bean instance
   */
  public TQAssocBean(String name, R root) {
    this(name, root, null);
  }

  /**
   * Construct with additional path prefix.
   */
  public TQAssocBean(String name, R root, String prefix) {
    this.root = root;
    this.name = TQPath.add(prefix, name);
  }

  /**
   * Internal method to return the underlying expression list.
   */
  protected ExpressionList<?> expr() {
    return ((TQRootBean) root).peekExprList();
  }

  /**
   * Is equal to by ID property.
   */
  public R equalTo(T other) {
    expr().eq(name, other);
    return root;
  }

  /**
   * Is not equal to by ID property.
   */
  public R notEqualTo(T other) {
    expr().ne(name, other);
    return root;
  }

  /**
   * Apply a filter when fetching these beans.
   */
  public R filterMany(ExpressionList<T> filter) {

    ExpressionList<T> expressionList = (ExpressionList<T>)expr().filterMany(name);

    expressionList.addAll(filter);
    return root;
  }
}
