package org.avaje.ebean.typequery;

import java.util.UUID;

public class PUuid<R> extends PBaseValueEqual<R,UUID> {

  public PUuid(String name, R root) {
    super(name , root);
  }

  public PUuid(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
