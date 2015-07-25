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

  public R equalTo(String value) {
    expr().eq(name, value);
    return root;
  }

  public R like(String value) {
    expr().like(name, value);
    return root;
  }

  public R startsWith(String value) {
    expr().startsWith(name, value);
    return root;
  }

  public R endsWith(String value) {
    expr().endsWith(name, value);
    return root;
  }

  public R contains(String value) {
    expr().contains(name, value);
    return root;
  }

  public R ilike(String value) {
    expr().ilike(name, value);
    return root;
  }

  public R istartsWith(String value) {
    expr().istartsWith(name, value);
    return root;
  }

  public R iendsWith(String value) {
    expr().iendsWith(name, value);
    return root;
  }

  public R icontains(String value) {
    expr().icontains(name, value);
    return root;
  }

}
