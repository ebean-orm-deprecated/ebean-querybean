package org.example.domain.finder;

import com.avaje.ebean.Model;
import org.example.domain.Customer;
import org.example.domain.typequery.QCustomer;

/**
 */
public class CustomerFinder extends Model.Finder<Long,Customer> {

  public CustomerFinder() {
    super(Customer.class);
  }

  public QCustomer typed() {
    return new QCustomer();
  }
}
