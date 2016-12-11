package org.example.domain.finder;

import io.ebean.Finder;
import org.example.domain.Customer;
import org.example.domain.typequery.QCustomer;

/**
 */
public class CustomerFinder extends Finder<Long,Customer> {

  public CustomerFinder() {
    super(Customer.class);
  }

  public QCustomer typed() {
    return new QCustomer();
  }
}
