package org.avaje.ebean.typequery;

public abstract class PBaseDate<R,D> extends TQProperty<R> {

  public PBaseDate(String name, R root) {
    super(name , root);
  }

  public PBaseDate(String name, R root, String prefix) {
    super(name, root, prefix);
  }

  public R equalTo(D value) {
    expr().eq(name, value);
    return root;
  }

  /**
   * Same as greater than.
   */
  public R after(D value) {
    expr().gt(name, value);
    return root;
  }

  /**
   * Same as less than.
   */
  public R before(D value) {
    expr().lt(name, value);
    return root;
  }

  public R eq(D value) {
    expr().eq(name, value);
    return root;
  }

  public R gt(D value) {
    expr().gt(name, value);
    return root;
  }

  public R lt(D value) {
    expr().lt(name, value);
    return root;
  }

  public R ge(D value) {
    expr().ge(name, value);
    return root;
  }

  public R le(D value) {
    expr().le(name, value);
    return root;
  }

  public R between(D lower, D upper) {
    expr().between(name, lower, upper);
    return root;
  }
}
