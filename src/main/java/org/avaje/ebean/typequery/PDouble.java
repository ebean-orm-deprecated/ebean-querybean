package org.avaje.ebean.typequery;

public class PDouble<R> extends PBaseNumber<R,Double> {

  public PDouble(String name, R root) {
    super(name, root);
  }

  public PDouble(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
