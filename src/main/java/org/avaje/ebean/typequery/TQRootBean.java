package org.avaje.ebean.typequery;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Junction;
import com.avaje.ebean.Query;

import java.util.List;
import java.util.Stack;

public class TQRootBean<T, R> {

  //private final EbeanServer server;

  //private final Class<T> beanType;

  private final Query<T> query;

  private final Stack<ExpressionList<T>> expressionListStack = new Stack<>();

  private R root;

  public TQRootBean(Class<T> beanType) {
    this(beanType, Ebean.getDefaultServer());
  }

  public TQRootBean(Class<T> beanType, EbeanServer server) {
    //this.beanType = beanType;
    //this.server = server;
    this.query = server.find(beanType);
  }

  protected void setRoot(R root) {
    this.root = root;
  }

  public R orderBy() {
    return root;
  }

  public R or() {

    Junction<T> junction = peekExprList().disjunction();
    expressionListStack.push(junction);
    return root;
  }

  public R and() {

    Junction<T> junction = peekExprList().conjunction();
    expressionListStack.push(junction);
    return root;
  }

  public R endOr() {
    expressionListStack.pop();
    return root;
  }

  public R endAnd() {
    expressionListStack.pop();
    return root;
  }

  public T findUnique() {
    return query.findUnique();
  }

  public List<T> findList() {
    return query.findList();
  }


  protected ExpressionList<T> peekExprList() {

    if (expressionListStack.isEmpty()) {
      expressionListStack.push(query.where());
    }

    return expressionListStack.peek();
  }

}
