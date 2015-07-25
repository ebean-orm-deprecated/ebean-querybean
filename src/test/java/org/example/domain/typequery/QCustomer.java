package org.example.domain.typequery;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import org.avaje.ebean.typequery.PUtilDate;
import org.example.domain.Customer;
import org.avaje.ebean.typequery.TQRootBean;
import org.avaje.ebean.typequery.PLong;
import org.avaje.ebean.typequery.PString;
import org.example.domain.typequery.assoc.QAssocContact;


public class QCustomer extends TQRootBean<Customer,QCustomer> {

  public PLong<QCustomer> id;

  public PString<QCustomer> name;

  public PUtilDate<QCustomer> registered;

  public QAssocContact<QCustomer> contacts;

  public QCustomer() {
    this(Ebean.getDefaultServer());
  }

  public QCustomer(EbeanServer server) {
    super(Customer.class, server);
    setRoot(this);
    this.id = new PLong<>("id", this);
    this.name = new PString<>("name", this);
    this.registered = new PUtilDate<>("registered", this);
    this.contacts = new QAssocContact<>("contacts", this);
  }

}
