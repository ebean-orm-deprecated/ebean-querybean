package org.avaje.ebean.typequery;

public class PLong<R> extends TQProperty<R> {

  public PLong(String name, R root) {
    super(name , root);
  }

  public PLong(String name, R root, String prefix) {
    super(name, root, prefix);
  }

  public R eq(long value) {
    expr().eq(name, value);
    return root;
  }

  public R gt(long value) {
    expr().gt(name, value);
    return root;
  }

  public R lt(long value) {
    expr().lt(name, value);
    return root;
  }

}
