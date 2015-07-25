package org.avaje.ebean.typequery;

import java.math.BigDecimal;

public class PBigDecimal<R> extends PBaseNumber<R,BigDecimal> {

  public PBigDecimal(String name, R root) {
    super(name, root);
  }

  public PBigDecimal(String name, R root, String prefix) {
    super(name, root, prefix);
  }

}
