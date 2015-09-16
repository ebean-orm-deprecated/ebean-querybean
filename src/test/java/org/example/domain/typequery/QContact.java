package org.example.domain.typequery;

import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.example.domain.Contact;
import org.example.domain.typequery.assoc.QAssocContactNote;

public class QContact extends TQRootBean<Contact,QContact> {

  private static final QContact _alias = new QContact(true);

  public static QContact alias() {
    return _alias;
  }

  public PLong<QContact> id;
  public PString<QContact> firstName;
  public PString<QContact> lastName;
  public PString<QContact> email;
  public QAssocContactNote<QContact> notes;

  public QContact() {
    super(Contact.class);
    setRoot(this);
    this.id = new PLong<>("id", this);
    this.firstName = new PString<>("firstName", this);
    this.lastName = new PString<>("lastName", this);
    this.email = new PString<>("email", this);
    this.notes = new QAssocContactNote<>("notes", this);
  }

  private QContact(boolean alias) {
    super(alias);
    this.id = new PLong<>("id", this);
    this.firstName = new PString<>("firstName", this);
    this.lastName = new PString<>("lastName", this);
    this.email = new PString<>("email", this);
    this.notes = new QAssocContactNote<>("notes", this);
  }

}
