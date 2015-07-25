package org.avaje.ebean.typequery;

public class PInteger<R> extends PBaseNumber<R,Integer> {

  public PInteger(String name, R root) {
    super(name, root);
  }

  public PInteger(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
