package org.avaje.ebean.typequery;

public class PBoolean<R> extends TQProperty<R> {

  public PBoolean(String name, R owner) {
    super(name, owner);
  }

  public PBoolean(String name, R owner, String prefix) {
    super(name, owner, prefix);
  }

  public R is(boolean value) {
    expr().eq(name, value);
    return root;
  }

  public R eq(boolean value) {
    expr().eq(name, value);
    return root;
  }
}
