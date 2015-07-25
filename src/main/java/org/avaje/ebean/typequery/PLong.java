package org.avaje.ebean.typequery;

public class PLong<R> extends PBaseNumber<R,Long> {

  public PLong(String name, R root) {
    super(name , root);
  }

  public PLong(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
