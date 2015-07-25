package org.avaje.ebean.typequery;

public abstract class PBaseValueEqual<R,T> extends TQProperty<R> {

  public PBaseValueEqual(String name, R root) {
    super(name , root);
  }

  public PBaseValueEqual(String name, R root, String prefix) {
    super(name, root, prefix);
  }

  public R equalTo(T value) {
    expr().eq(name, value);
    return root;
  }

  public R eq(T value) {
    expr().eq(name, value);
    return root;
  }

}
