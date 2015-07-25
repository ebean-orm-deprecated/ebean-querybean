package org.avaje.ebean.typequery;

import java.util.TimeZone;

public class PTimeZone<R> extends PBaseValueEqual<R,TimeZone> {

  public PTimeZone(String name, R root) {
    super(name, root);
  }

  public PTimeZone(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
