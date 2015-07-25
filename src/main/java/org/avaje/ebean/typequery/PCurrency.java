package org.avaje.ebean.typequery;

import java.util.Currency;

public class PCurrency<R> extends PBaseValueEqual<R,Currency> {

  public PCurrency(String name, R root) {
    super(name, root);
  }

  public PCurrency(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
