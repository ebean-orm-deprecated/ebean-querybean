package org.example.domain.typequery.assoc;

import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.TQPath;

public class QAssocContact<R> {

  public PLong<R> id;

  public PString<R> name;

  public PString<R> email;

  public QAssocContactNote<R> notes;

  public QAssocContact(String name, R root) {
    this(name, root, null);
  }
  public QAssocContact(String name, R root, String prefix) {

    String path = TQPath.add(prefix, name);
    this.id = new PLong<>("id", root);
    this.name = new PString<>("name", root, path);
    this.email = new PString<>("email", root, path);
    this.notes = new QAssocContactNote<>("notes", root, path);
  }

}
