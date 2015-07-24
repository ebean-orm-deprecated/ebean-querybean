package org.avaje.ebean.typequery;

import com.avaje.ebean.ExpressionList;

public class TQProperty<R> {

  protected final String name;

  protected final R root;

  public TQProperty(String name, R root) {
    this(name, root, null);
  }

  public TQProperty(String name, R root, String prefix) {
    this.root = root;
    this.name = TQPath.add(prefix, name);
  }

  protected ExpressionList<?> expr() {
    return ((TQRootBean) root).peekExprList();
  }

  public R asc() {
    expr().order().asc(name);
    return root;
  }

  public R desc() {
    expr().order().desc(name);
    return root;
  }

}
