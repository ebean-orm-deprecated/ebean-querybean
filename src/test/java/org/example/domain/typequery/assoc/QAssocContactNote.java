package org.example.domain.typequery.assoc;

import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.TQPath;

public class QAssocContactNote<R> {

  public PLong<R> id;

  public PString<R> title;

  public PString<R> note;

  public QAssocContactNote(String name, R root) {
    this(name, root, null);
  }

  public QAssocContactNote(String name, R root, String prefix) {

    String path = TQPath.add(prefix, name);
    this.id = new PLong<>("id", root, path);
    this.title = new PString<>("title", root, path);
    this.note = new PString<>("note", root, path);
  }

}
