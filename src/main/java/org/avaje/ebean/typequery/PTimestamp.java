package org.avaje.ebean.typequery;

import java.sql.Timestamp;

/**
 * Property for java sql Timestamp.
 */
public class PTimestamp<R> extends PBaseDate<R,Timestamp> {

  public PTimestamp(String name, R root) {
    super(name, root);
  }

  public PTimestamp(String name, R root, String prefix) {
    super(name, root, prefix);
  }
}
