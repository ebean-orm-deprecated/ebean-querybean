package org.avaje.ebean.typequery;

public class PEnum<R,E> extends TQProperty<R> {

  public PEnum(String name, R root) {
    super(name, root);
  }

  public PEnum(String name, R root, String prefix) {
    super(name, root, prefix);
  }

  public R eq(E value) {
    expr().eq(name, value);
    return root;
  }

}
