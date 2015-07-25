package org.avaje.ebean.typequery;

public class PShort<R> extends PBaseNumber<R,Short> {

  public PShort(String name, R root) {
    super(name, root);
  }

  public PShort(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
