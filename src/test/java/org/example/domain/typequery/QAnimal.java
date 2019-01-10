package org.example.domain.typequery;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import org.example.domain.Animal;


public class QAnimal extends TQRootBean<Animal, QAnimal> {

  private static final QAnimal _alias = new QAnimal(true);

  public static QAnimal alias() {
    return _alias;
  }

  public PLong<QAnimal> id;

  public PString<QAnimal> name;

  public PLong<QAnimal> version;

  public QAnimal() {
    this(Ebean.getDefaultServer());
  }

  public QAnimal(EbeanServer server) {
    super(Animal.class, server);
    setRoot(this);
    this.id = new PLong<>("id", this);
    this.name = new PString<>("name", this);
    this.version = new PLong<>("version", this);
  }

  protected QAnimal(boolean aliasDummy) {
    super(aliasDummy);
    this.id = new PLong<>("id", this);
    this.name = new PString<>("name", this);
    this.version = new PLong<>("version", this);
  }
}
