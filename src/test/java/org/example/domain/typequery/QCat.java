package org.example.domain.typequery;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import org.example.domain.Animal;


public class QCat extends TQRootBean<Animal, QCat> {

  private static final QCat _alias = new QCat(true);

  public static QCat alias() {
    return _alias;
  }

  public PLong<QCat> id;

  public PString<QCat> name;

  public PLong<QCat> version;

  public QCat() {
    this(Ebean.getDefaultServer());
  }

  public QCat(EbeanServer server) {
    super(Animal.class, server);
    setRoot(this);
    this.id = new PLong<>("id", this);
    this.name = new PString<>("name", this);
    this.version = new PLong<>("version", this);
  }

  protected QCat(boolean aliasDummy) {
    super(aliasDummy);
    this.id = new PLong<>("id", this);
    this.name = new PString<>("name", this);
    this.version = new PLong<>("version", this);
  }
}
