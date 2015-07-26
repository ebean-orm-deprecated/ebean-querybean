package org.avaje.ebean.typequery;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Junction;
import com.avaje.ebean.Query;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Stack;

/**
 * Base root query bean.
 *
 * With code generation for each entity bean type a query bean is created that extends this.
 * <p>
 *   Provides common features for all root query beans
 * </p>
 * @param <T> the entity bean type (normal entity bean type e.g. Customer)
 * @param <R> the specific root query bean type (e.g. QCustomer)
 */
public abstract class TQRootBean<T, R> {

  /**
   * The underlying query.
   */
  private final Query<T> query;

  /**
   * The underlying expression lists as a stack. Pushed and popped based on conjunction/disjunction.
   */
  private final Stack<ExpressionList<T>> expressionListStack = new Stack<>();

  /**
   * The root query bean instance. Used to provide fluid query construction.
   */
  private R root;

  /**
   * Construct using the type of bean to query on and the default server.
   */
  public TQRootBean(Class<T> beanType) {
    this(beanType, Ebean.getDefaultServer());
  }

  /**
   * Construct using the type of bean to query on and a given server.
   */
  public TQRootBean(Class<T> beanType, EbeanServer server) {
    this.query = server.find(beanType);
  }

  /**
   * Construct using a query.
   */
  public TQRootBean(Query<T> query) {
    //this.beanType = beanType;
    //this.server = server;
    this.query = query;
  }

  /**
   * Sets the root query bean instance. Used to provide fluid query construction.
   */
  protected void setRoot(R root) {
    this.root = root;
  }

  /**
   * Marker that can be used to indicate that the order by clause is defined after this.
   *
   * <h2>Example: order by customer name, order date</h2>
   * <pre>{@code
   *   List<Order> orders =
   *          new QOrder()
   *            .customer.name.ilike("rob")
   *            .orderBy()
   *            .customer.name.asc()
   *            .orderDate.asc()
   *            .findList();
   *
   * }</pre>
   */
  public R orderBy() {
    // yes this does not actually do anything. You include it because style wise it makes
    // the query nicer to read and suggests that order by definitions are added after this
    return root;
  }

  /**
   * Begin a list of expressions added by 'OR'.
   * <p>
   *   This should have an associated call to endOr() to close the or expression list.
   * </p>
   */
  public R beginOr() {

    Junction<T> junction = peekExprList().disjunction();
    expressionListStack.push(junction);
    return root;
  }

  /**
   * Begin a list of expressions added by 'AND'.
   * <p>
   *   This should have an associated call to endAnd() to close the or expression list.
   * </p>
   */
  public R beginAnd() {

    Junction<T> junction = peekExprList().conjunction();
    expressionListStack.push(junction);
    return root;
  }

  /**
   * End a list of expressions added by 'OR'.
   */
  public R endOr() {
    expressionListStack.pop();
    return root;
  }

  /**
   * End to a list of expressions joined by 'AND'.
   */
  public R endAnd() {
    expressionListStack.pop();
    return root;
  }

  /**
   * Execute the query returning either a single bean or null (if no matching
   * bean is found).
   * <p>
   * If more than 1 row is found for this query then a PersistenceException is
   * thrown.
   * </p>
   * <p>
   * This is useful when your predicates dictate that your query should only
   * return 0 or 1 results.
   * </p>
   *
   * <pre>{@code
   *
   * // assuming the sku of products is unique...
   * Product product =
   *     new QProduct()
   *         .sku.equalTo("aa113")
   *         .findUnique();
   * ...
   * }</pre>
   *
   * <p>
   * It is also useful with finding objects by their id when you want to specify
   * further join information to optimise the query.
   * </p>
   *
   * <pre>{@code
   *
   * // Fetch order 42 and additionally fetch join its order details...
   * Order order =
   *     new QOrder()
   *         .fetch("details") // eagerly load the order details
   *         .id.equalTo(42)
   *         .findUnique();
   *
   * // the order details were eagerly loaded
   * List<OrderDetail> details = order.getDetails();
   * ...
   * }</pre>
   */
  @Nullable
  public T findUnique() {
    return query.findUnique();
  }

  public List<T> findList() {
    return query.findList();
  }

  /**
   * Return the current expression list that expressions should be added to.
   */
  protected ExpressionList<T> peekExprList() {

    if (expressionListStack.isEmpty()) {
      // empty so push on the queries base expression list
      expressionListStack.push(query.where());
    }
    // return the current expression list
    return expressionListStack.peek();
  }

}
