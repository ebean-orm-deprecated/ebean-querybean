package org.avaje.ebean.typequery;

import java.math.BigInteger;

public class PBigInteger<R> extends PBaseNumber<R,BigInteger> {

  public PBigInteger(String name, R root) {
    super(name, root);
  }

  public PBigInteger(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
