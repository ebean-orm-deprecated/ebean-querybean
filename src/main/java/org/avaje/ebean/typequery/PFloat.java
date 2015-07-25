package org.avaje.ebean.typequery;

public class PFloat<R> extends PBaseNumber<R,Float> {

  public PFloat(String name, R root) {
    super(name, root);
  }

  public PFloat(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
