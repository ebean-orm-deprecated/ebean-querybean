package org.avaje.ebean.typequery;

public class TQPath {

  public static String add(String prefix, String name) {
    return (prefix == null) ? name : prefix+"."+name;
  }
}
