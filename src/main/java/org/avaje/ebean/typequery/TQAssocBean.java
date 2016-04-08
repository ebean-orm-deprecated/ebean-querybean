package org.avaje.ebean.typequery;

import com.avaje.ebean.ExpressionList;

/**
 * Base type for associated beans.
 *
 * @param <T> the entity bean type (normal entity bean type e.g. Customer)
 * @param <R> the specific root query bean type (e.g. QCustomer)
 */
public abstract class TQAssocBean<T, R> {

  protected final String _name;

  protected final R _root;

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
    this._root = root;
    this._name = TQPath.add(prefix, name);
  }

  /**
   * Eagerly fetch this association fetching all the properties.
   */
  public R fetchAll() {

    ((TQRootBean) _root).query().fetch(_name, "*");
    return _root;
  }

  /**
   * Eagerly fetch this association fetching some of the properties.
   */
  protected R fetchProperties(TQProperty<?>... props) {
    StringBuilder selectProps = new StringBuilder(50);
    for (int i = 0; i < props.length; i++) {
      if (i > 0) {
        selectProps.append(",");
      }
      selectProps.append(props[i].propertyName());
    }
    ((TQRootBean) _root).query().fetch(_name, selectProps.toString());
    return _root;
  }

  /**
   * Internal method to return the underlying expression list.
   */
  protected ExpressionList<?> expr() {
    return ((TQRootBean) _root).peekExprList();
  }

  /**
   * Is equal to by ID property.
   */
  public R equalTo(T other) {
    expr().eq(_name, other);
    return _root;
  }

  /**
   * Is not equal to by ID property.
   */
  public R notEqualTo(T other) {
    expr().ne(_name, other);
    return _root;
  }

  /**
   * Apply a filter when fetching these beans.
   */
  public R filterMany(ExpressionList<T> filter) {

    ExpressionList<T> expressionList = (ExpressionList<T>)expr().filterMany(_name);

    expressionList.addAll(filter);
    return _root;
  }

  /**
   * Is empty for a collection property.
   * <p>
   * This effectively adds a not exists sub-query on the collection property.
   * </p>
   * <p>
   * This expression only works on OneToMany and ManyToMany properties.
   * </p>
   */
  public R isEmpty() {
    expr().isEmpty(_name);
    return _root;
  }

  /**
   * Is not empty for a collection property.
   * <p>
   * This effectively adds an exists sub-query on the collection property.
   * </p>
   * <p>
   * This expression only works on OneToMany and ManyToMany properties.
   * </p>
   */
  public R isNotEmpty() {
    expr().isEmpty(_name);
    return _root;
  }

}
