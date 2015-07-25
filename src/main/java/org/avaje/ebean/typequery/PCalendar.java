package org.avaje.ebean.typequery;

import java.util.Calendar;

/**
 * Property for java Calendar.
 */
public class PCalendar<R> extends PBaseDate<R,Calendar> {

  public PCalendar(String name, R root) {
    super(name, root);
  }

  public PCalendar(String name, R root, String prefix) {
    super(name, root, prefix);
  }
}
