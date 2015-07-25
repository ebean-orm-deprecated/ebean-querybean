package org.avaje.ebean.typequery;

public abstract class PBaseNumber<R,T> extends TQProperty<R> {

  public PBaseNumber(String name, R root) {
    super(name , root);
  }

  public PBaseNumber(String name, R root, String prefix) {
    super(name, root, prefix);
  }

  public R eq(T value) {
    expr().eq(name, value);
    return root;
  }

  public R gt(T value) {
    expr().gt(name, value);
    return root;
  }

  public R lt(T value) {
    expr().lt(name, value);
    return root;
  }

  public R between(T lower, T upper) {
    expr().between(name, lower, upper);
    return root;
  }

  public R equalTo(T value) {
    expr().eq(name, value);
    return root;
  }

  public R greaterThan(T value) {
    expr().gt(name, value);
    return root;
  }

  public R lessThan(T value) {
    expr().lt(name, value);
    return root;
  }

  // Additional int versions -- seems the right thing to do at the moment

  public R equalTo(int value) {
    expr().eq(name, value);
    return root;
  }

  public R greaterThan(int value) {
    expr().gt(name, value);
    return root;
  }

  public R lessThan(int value) {
    expr().lt(name, value);
    return root;
  }

  public R eq(int value) {
    expr().eq(name, value);
    return root;
  }

  public R gt(int value) {
    expr().gt(name, value);
    return root;
  }

  public R lt(int value) {
    expr().lt(name, value);
    return root;
  }

  public R between(int lower, int upper) {
    expr().between(name, lower, upper);
    return root;
  }
}
