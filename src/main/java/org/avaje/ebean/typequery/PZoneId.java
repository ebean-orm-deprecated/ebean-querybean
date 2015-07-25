package org.avaje.ebean.typequery;

import java.time.ZoneId;

public class PZoneId<R> extends PBaseValueEqual<R,ZoneId> {

  public PZoneId(String name, R root) {
    super(name, root);
  }

  public PZoneId(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
