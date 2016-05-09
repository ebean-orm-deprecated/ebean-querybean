package org.example.domain.typequery.assoc;

import org.avaje.ebean.typequery.PArray;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.avaje.ebean.typequery.TQAssocBean;
import org.avaje.ebean.typequery.TQPath;
import org.avaje.ebean.typequery.TQProperty;
import org.example.domain.Contact;
import org.example.domain.typequery.QContact;

public class QAssocContact<R> extends TQAssocBean<Contact,R> {

  public PLong<R> id;
  public PArray<R,String> phoneNumbers;
  public PString<R> firstName;
  public PString<R> lastName;
  public PString<R> email;
  public QAssocContactNote<R> notes;

  @SafeVarargs
  public final R fetch(TQProperty<QContact>... props) {
    return fetchProperties(props);
  }

  public QAssocContact(String name, R root, int depth) {
    this(name, root, null, depth);
  }
  public QAssocContact(String name, R root, String prefix, int depth) {
    super(name, root, prefix);
    String path = TQPath.add(prefix, name);
    this.id = new PLong<>("id", root);
    this.phoneNumbers = new PArray<>("phoneNumbers", root, path);
    this.firstName = new PString<>("firstName", root, path);
    this.lastName = new PString<>("lastName", root, path);
    this.email = new PString<>("email", root, path);
    this.notes = new QAssocContactNote<>("notes", root, path);
  }
}
