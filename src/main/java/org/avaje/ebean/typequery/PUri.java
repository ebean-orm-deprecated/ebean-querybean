package org.avaje.ebean.typequery;

import java.net.URI;

public class PUri<R> extends PBaseValueEqual<R,URI> {

  public PUri(String name, R root) {
    super(name, root);
  }

  public PUri(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
