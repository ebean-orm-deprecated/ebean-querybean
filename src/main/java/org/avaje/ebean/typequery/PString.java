package org.avaje.ebean.typequery;

public class PString<R> extends TQProperty<R> {

  public PString(String name, R owner) {
    super(name, owner);
  }

  public PString(String name, R owner, String prefix) {
    super(name, owner, prefix);
  }

  public R eq(String value) {
    expr().eq(name, value);
    return root;
  }

  public R like(String value) {
    expr().like(name, value);
    return root;
  }
}
