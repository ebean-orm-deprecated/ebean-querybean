package org.avaje.ebean.typequery;

import java.util.Date;

/**
 * Property for java util Date.
 */
public class PUtilDate<R> extends PBaseDate<R,Date> {

  public PUtilDate(String name, R root) {
    super(name, root);
  }

  public PUtilDate(String name, R root, String prefix) {
    super(name, root, prefix);
  }
}
