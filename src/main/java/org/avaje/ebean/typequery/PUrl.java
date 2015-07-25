package org.avaje.ebean.typequery;

import java.net.URL;

public class PUrl<R> extends PBaseValueEqual<R,URL> {

  public PUrl(String name, R root) {
    super(name, root);
  }

  public PUrl(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
