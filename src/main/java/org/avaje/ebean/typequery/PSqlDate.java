package org.avaje.ebean.typequery;

import java.sql.Date;

/**
 * Property for java sql Date.
 */
public class PSqlDate<R> extends PBaseDate<R,Date> {

  public PSqlDate(String name, R root) {
    super(name, root);
  }

  public PSqlDate(String name, R root, String prefix) {
    super(name, root, prefix);
  }
}
