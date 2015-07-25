package org.avaje.ebean.typequery;

import java.time.ZoneOffset;

public class PZoneOffset<R> extends PBaseValueEqual<R,ZoneOffset> {

  public PZoneOffset(String name, R root) {
    super(name, root);
  }

  public PZoneOffset(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
