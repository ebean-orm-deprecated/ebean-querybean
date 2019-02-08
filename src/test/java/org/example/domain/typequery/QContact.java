package org.example.domain.typequery;

import io.ebean.typequery.PArray;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.TQRootBean;
import org.example.domain.Contact;
import org.example.domain.typequery.assoc.QAssocContactNote;

public class QContact extends TQRootBean<Contact,QContact> {

  private static final QContact _alias = new QContact(true);

  public static QContact alias() {
    return _alias;
  }

  public PLong<QContact> id;
  public PArray<QContact,String> phoneNumbers;
  public PString<QContact> firstName;
  public PString<QContact> lastName;
  public PString<QContact> email;
  public QAssocContactNote<QContact> notes;

  public QContact() {
    super(Contact.class);
    setRoot(this);
    this.id = new PLong<>("id", this);
    this.phoneNumbers = new PArray<>("phoneNumbers", this);
    this.firstName = new PString<>("firstName", this);
    this.lastName = new PString<>("lastName", this);
    this.email = new PString<>("email", this);
    this.notes = new QAssocContactNote<>("notes", this);
  }

  private QContact(boolean alias) {
    super(alias);
    this.id = new PLong<>("id", this);
    this.phoneNumbers = new PArray<>("phoneNumbers", this);
    this.firstName = new PString<>("firstName", this);
    this.lastName = new PString<>("lastName", this);
    this.email = new PString<>("email", this);
    this.notes = new QAssocContactNote<>("notes", this);
  }

  public static class Alias {
    public static PLong<QContact> id = _alias.id;
    public static PString<QContact> firstName = _alias.firstName;
    public static PString<QContact> lastName = _alias.lastName;
    public static PString<QContact> email = _alias.email;
  }
}
