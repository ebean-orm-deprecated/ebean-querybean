package org.avaje.ebean.typequery;

public class PEnum<R,E> extends TQProperty<R> {

  public PEnum(String name, R root) {
    super(name, root);
  }

  public PEnum(String name, R root, String prefix) {
    super(name, root, prefix);
  }

  public R equalTo(E value) {
    expr().eq(name, value);
    return root;
  }

  public R notEqualTo(E value) {
    expr().ne(name, value);
    return root;
  }

  public R eq(E value) {
    expr().eq(name, value);
    return root;
  }

  public R ne(E value) {
    expr().ne(name, value);
    return root;
  }

  public R in(E... value) {
    expr().in(name, value);
    return root;
  }

//  public R notIn(E... value) {
//    expr().notIn(name, value);
//    return root;
//  }

}
