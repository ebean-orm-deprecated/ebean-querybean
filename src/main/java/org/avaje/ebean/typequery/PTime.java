package org.avaje.ebean.typequery;

import java.sql.Time;

public class PTime<R> extends PBaseNumber<R,Time> {

  public PTime(String name, R root) {
    super(name, root);
  }

  public PTime(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
