package org.avaje.ebean.typequery;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Junction;
import com.avaje.ebean.PersistenceContextScope;
import com.avaje.ebean.Query;
import com.avaje.ebean.QueryEachConsumer;
import com.avaje.ebean.QueryEachWhileConsumer;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.Transaction;
import com.avaje.ebeaninternal.server.util.ArrayStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * Base root query bean.
 *
 * With code generation for each entity bean type a query bean is created that extends this.
 * <p>
 *   Provides common features for all root query beans
 * </p>
 *
 * <h2>Example - QCustomer extends TQRootBean</h2>
 * <p>
 *   These 'query beans' like QCustomer are generated using the <code>avaje-ebeanorm-typequery-generator</code>.
 * </p>
 * <pre>{@code
 *
 *   public class QCustomer extends TQRootBean<Customer,QCustomer> {
 *
 *     // properties
 *     public PLong<QCustomer> id;
 *
 *     public PString<QCustomer> name;
 *     ...
 *
 * }</pre>
 *
 * <h2>Example - usage of QCustomer</h2>
 * <pre>{@code
 *
 *    Date fiveDaysAgo = ...
 *
 *    List<Customer> customers =
 *        new QCustomer()
 *          .name.ilike("rob")
 *          .status.equalTo(Customer.Status.GOOD)
 *          .registered.after(fiveDaysAgo)
 *          .contacts.email.endsWith("@foo.com")
 *          .orderBy()
 *            .name.asc()
 *            .registered.desc()
 *          .findList();
 *
 * }</pre>
 *
 * <h2>Resulting SQL where</h2>
 *
 * <pre>{@code sql
 *
 *     where lower(t0.name) like ?  and t0.status = ?  and t0.registered > ?  and u1.email like ?
 *     order by t0.name, t0.registered desc;
 *
 *     --bind(rob,GOOD,Mon Jul 27 12:05:37 NZST 2015,%@foo.com)
 * }</pre>
 *
 *
 * @param <T> the entity bean type (normal entity bean type e.g. Customer)
 * @param <R> the specific root query bean type (e.g. QCustomer)
 */
public abstract class TQRootBean<T, R> {

  /**
   * The underlying query.
   */
  private final Query<T> query;

  /**
   * The underlying expression lists held as a stack. Pushed and popped based on and/or (conjunction/disjunction).
   */
  private final ArrayStack<ExpressionList<T>> expressionListStack = new ArrayStack<>();

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
    this(server.find(beanType));
  }

  /**
   * Construct using a query.
   */
  public TQRootBean(Query<T> query) {
    this.query = query;
  }

  /**
   * Sets the root query bean instance. Used to provide fluid query construction.
   */
  protected void setRoot(R root) {
    this.root = root;
  }

  /**
   * Return the underlying query.
   * <p>
   *   Generally it is not expected that you will need to do this but typically use
   *   the find methods available on this 'root query bean' instance like findList().
   * </p>
   */
  public Query<T> query() {
    return query;
  }

  /**
   * Set the maximum number of rows to return in the query.
   *
   * @param maxRows
   *          the maximum number of rows to return in the query.
   */
  public R setMaxRows(int maxRows) {
    query.setMaxRows(maxRows);
    return root;
  }

  /**
   * Set the first row to return for this query.
   *
   * @param firstRow the first row to include in the query result.
   */
  public R setFirstRow(int firstRow) {
    query.setFirstRow(firstRow);
    return root;
  }

  /**
   * Explicitly specify whether to use Autofetch for this query.
   * <p>
   * If you do not call this method on a query the "Implicit Autofetch mode" is
   * used to determine if Autofetch should be used for a given query.
   * </p>
   * <p>
   * Autofetch can add additional fetch paths to the query and specify which
   * properties are included for each path. If you have explicitly defined some
   * fetch paths Autofetch will not remove.
   * </p>
   */
  public R setAutofetch(boolean autofetch) {
    query.setAutofetch(autofetch);
    return root;
  }

  /**
   * A hint which for JDBC translates to the Statement.fetchSize().
   * <p>
   * Gives the JDBC driver a hint as to the number of rows that should be
   * fetched from the database when more rows are needed for ResultSet.
   * </p>
   */
  public R setBufferFetchSizeHint(int fetchSize) {
    query.setBufferFetchSizeHint(fetchSize);
    return root;
  }

  /**
   * Set whether this query uses DISTINCT.
   */
  public R setDistinct(boolean distinct) {
    query.setDistinct(distinct);
    return root;
  }

  /**
   * executed the select with "for update" which should lock the record
   * "on read"
   */
  public R setForUpdate(boolean forUpdate) {
    query.setForUpdate(forUpdate);
    return root;
  }

  /**
   * Set the Id value to query. This is used with findUnique().
   * <p>
   * You can use this to have further control over the query. For example adding
   * fetch joins.
   * </p>
   *
   * <pre>{@code
   *
   * Order order =
   *   new QOrder()
   *     .setId(1)
   *     .fetch("details")
   *     .findUnique();
   *
   * // the order details were eagerly fetched
   * List<OrderDetail> details = order.getDetails();
   *
   * }</pre>
   */
  public R setId(Object id) {
    query.setId(id);
    return root;
  }

  /**
   * Set the default lazy loading batch size to use.
   * <p>
   * When lazy loading is invoked on beans loaded by this query then this sets the
   * batch size used to load those beans.
   *
   * @param lazyLoadBatchSize the number of beans to lazy load in a single batch
   */
  public R setLazyLoadBatchSize(int lazyLoadBatchSize) {
    query.setLazyLoadBatchSize(lazyLoadBatchSize);
    return root;
  }

  /**
   * When set to true all the beans from this query are loaded into the bean
   * cache.
   */
  public R setLoadBeanCache(boolean loadBeanCache) {
    query.setLoadBeanCache(loadBeanCache);
    return root;
  }

  /**
   * Set the property to use as keys for a map.
   * <p>
   * If no property is set then the id property is used.
   * </p>
   *
   * <pre>{@code
   *
   * // Assuming sku is unique for products...
   *
   * Map<?,Product> productMap =
   *     new QProduct()
   *     // use sku for keys...
   *     .setMapKey("sku")
   *     .findMap();
   *
   * }</pre>
   *
   * @param mapKey
   *          the property to use as keys for a map.
   */
  public R setMapKey(String mapKey) {
    query.setMapKey(mapKey);
    return root;
  }

  /**
   * Specify the PersistenceContextScope to use for this query.
   * <p/>
   * When this is not set the 'default' configured on {@link com.avaje.ebean.config.ServerConfig#setPersistenceContextScope(PersistenceContextScope)}
   * is used - this value defaults to {@link com.avaje.ebean.PersistenceContextScope#TRANSACTION}.
   * <p/>
   * Note that the same persistence Context is used for subsequent lazy loading and query join queries.
   * <p/>
   * Note that #findEach uses a 'per object graph' PersistenceContext so this scope is ignored for
   * queries executed as #findIterate, #findEach, #findEachWhile.
   *
   * @param scope The scope to use for this query and subsequent lazy loading.
   */
  public R setPersistenceContextScope(PersistenceContextScope scope) {
    query.setPersistenceContextScope(scope);
    return root;
  }

  /**
   * Set RawSql to use for this query.
   */
  public R setRawSql(RawSql rawSql) {
    query.setRawSql(rawSql);
    return root;
  }

  /**
   * When set to true when you want the returned beans to be read only.
   */
  public R setReadOnly(boolean readOnly) {
    query.setReadOnly(readOnly);
    return root;
  }

  /**
   * Set this to true to use the bean cache.
   * <p>
   * If the query result is in cache then by default this same instance is
   * returned. In this sense it should be treated as a read only object graph.
   * </p>
   */
  public R setUseCache(boolean useCache) {
    query.setUseCache(useCache);
    return root;
  }

  /**
   * Set this to true to use the query cache.
   */
  public R setUseQueryCache(boolean useCache) {
    query.setUseQueryCache(useCache);
    return root;
  }

  /**
   * Set a timeout on this query.
   * <p>
   * This will typically result in a call to setQueryTimeout() on a
   * preparedStatement. If the timeout occurs an exception will be thrown - this
   * will be a SQLException wrapped up in a PersistenceException.
   * </p>
   *
   * @param secs
   *          the query timeout limit in seconds. Zero means there is no limit.
   */
  public R setTimeout(int secs) {
    query.setTimeout(secs);
    return root;
  }

  /**
   * Marker that can be used to indicate that the order by clause is defined after this.
   * <p>
   *   order() and orderBy() are synonyms and both exist for historic reasons.
   * </p>
   *
   * <h2>Example: order by customer name, order date</h2>
   * <pre>{@code
   *   List<Order> orders =
   *          new QOrder()
   *            .customer.name.ilike("rob")
   *            .orderBy()
   *              .customer.name.asc()
   *              .orderDate.asc()
   *            .findList();
   *
   * }</pre>
   */
  public R orderBy() {
    // Yes this does not actually do anything! We include it because style wise it makes
    // the query nicer to read and suggests that order by definitions are added after this
    return root;
  }

  /**
   * Marker that can be used to indicate that the order by clause is defined after this.
   * <p>
   *   order() and orderBy() are synonyms and both exist for historic reasons.
   * </p>
   *
   * <h2>Example: order by customer name, order date</h2>
   * <pre>{@code
   *   List<Order> orders =
   *          new QOrder()
   *            .customer.name.ilike("rob")
   *            .order()
   *              .customer.name.asc()
   *              .orderDate.asc()
   *            .findList();
   *
   * }</pre>
   */
  public R order() {
    // Yes this does not actually do anything! We include it because style wise it makes
    // the query nicer to read and suggests that order by definitions are added after this
    return root;
  }

  /**
   * Begin a list of expressions added by 'OR'.
   * <p>
   *   This should have an associated call to endOr() to close the 'OR' expression list.
   * </p>
   * <h2>Example</h2>
   * <p>
   *   This example uses an 'OR' expression list with an inner 'AND' expression list.
   * </p>
   * <pre>{@code
   *
   *    List<Customer> customers =
   *          new QCustomer()
   *            .status.equalTo(Customer.Status.GOOD)
   *            .or()
   *              .id.greaterThan(1000)
   *              .and()
   *                .name.startsWith("super")
   *                .registered.after(fiveDaysAgo)
   *              .endAnd()
   *            .endOr()
   *            .orderBy().id.desc()
   *            .findList();
   *
   * }</pre>
   * <h2>Resulting SQL where clause</h2>
   * <pre>{@code sql
   *    where t0.status = ?  and (t0.id > ?  or (t0.name like ?  and t0.registered > ? ) )
   *    order by t0.id desc;
   *
   *    --bind(GOOD,1000,super%,Wed Jul 22 00:00:00 NZST 2015)
   *
   * }</pre>
   */
  public R or() {

    Junction<T> junction = peekExprList().disjunction();
    expressionListStack.push(junction);
    return root;
  }

  /**
   * Begin a list of expressions added by 'AND'.
   * <p>
   *   This should have an associated call to endAnd() to close the 'AND' expression list.
   * </p>
   * <p>
   *   Note that typically the AND expression is only used inside an outer 'OR' expression.
   *   This is because the top level expression list is an 'AND' expression list.
   * </p>
   * <h2>Example</h2>
   * <p>
   *   This example uses an 'OR' expression list with an inner 'AND' expression list.
   * </p>
   * <pre>{@code
   *
   *    List<Customer> customers =
   *          new QCustomer()
   *            .status.equalTo(Customer.Status.GOOD)
   *            .or() // OUTER 'OR'
   *              .id.greaterThan(1000)
   *              .and()  // NESTED 'AND' expression list
   *                .name.startsWith("super")
   *                .registered.after(fiveDaysAgo)
   *              .endAnd()
   *            .endOr()
   *            .orderBy().id.desc()
   *            .findList();
   *
   * }</pre>
   * <h2>Resulting SQL where clause</h2>
   * <pre>{@code sql
   *    where t0.status = ?  and (t0.id > ?  or (t0.name like ?  and t0.registered > ? ) )
   *    order by t0.id desc;
   *
   *    --bind(GOOD,1000,super%,Wed Jul 22 00:00:00 NZST 2015)
   *
   * }</pre>
   */
  public R and() {

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

  /**
   * Execute the query returning the list of objects.
   * <p>
   * This query will execute against the EbeanServer that was used to create it.
   * </p>
   *
   * <pre>{@code
   *
   * List<Customer> customers =
   *     new QCustomer()
   *       .name.ilike("rob%")
   *       .findList();
   *
   * }</pre>
   *
   * @see EbeanServer#findList(Query, Transaction)
   */
  public List<T> findList() {
    return query.findList();
  }

  /**
   * Execute the query returning a map of the objects.
   * <p>
   * This query will execute against the EbeanServer that was used to create it.
   * </p>
   * <p>
   * You can use setMapKey() so specify the property values to be used as keys
   * on the map. If one is not specified then the id property is used.
   * </p>
   *
   * <pre>{@code
   *
   * Map<?, Product> map =
   *   ebeanServer.find(Product.class)
   *     .setMapKey("sku")
   *     .findMap();
   *
   * }</pre>
   *
   * @see EbeanServer#findMap(Query, Transaction)
   */
  public Map<?,T> findMap() {
    return query.findMap();
  }
  /**
   * Return a typed map specifying the key property and type.
   */
  public <K> Map<K, T> findMap(String keyProperty, Class<K> keyType) {
    return query.findMap(keyProperty, keyType);
  }

  /**
   * Execute the query processing the beans one at a time.
   * <p>
   * This method is appropriate to process very large query results as the
   * beans are consumed one at a time and do not need to be held in memory
   * (unlike #findList #findSet etc)
   * </p>
   * <p>
   * Note that internally Ebean can inform the JDBC driver that it is expecting larger
   * resultSet and specifically for MySQL this hint is required to stop it's JDBC driver
   * from buffering the entire resultSet. As such, for smaller resultSets findList() is
   * generally preferable.
   * </p>
   * <p>
   * Compared with #findEachWhile this will always process all the beans where as
   * #findEachWhile provides a way to stop processing the query result early before
   * all the beans have been read.
   * </p>
   * <p>
   * This method is functionally equivalent to findIterate() but instead of using an
   * iterator uses the QueryEachConsumer (SAM) interface which is better suited to use
   * with Java8 closures.
   * </p>
   *
   * <pre>{@code
   *
   *  new QCustomer()
   *     .status.equalTo(Status.NEW)
   *     .orderBy().id.asc()
   *     .findEach((Customer customer) -> {
   *
   *       // do something with customer
   *       System.out.println("-- visit " + customer);
   *     });
   *
   * }</pre>
   *
   * @param consumer
   *          the consumer used to process the queried beans.
   */
  public void findEach(QueryEachConsumer<T> consumer) {
    query.findEach(consumer);
  }

  /**
   * Execute the query using callbacks to a visitor to process the resulting
   * beans one at a time.
   * <p>
   * This method is functionally equivalent to findIterate() but instead of using an
   * iterator uses the QueryEachWhileConsumer (SAM) interface which is better suited to use
   * with Java8 closures.
   * </p>

   *
   * <pre>{@code
   *
   *  new QCustomer()
   *     .status.equalTo(Status.NEW)
   *     .order().id.asc()
   *     .findEachWhile((Customer customer) -> {
   *
   *       // do something with customer
   *       System.out.println("-- visit " + customer);
   *
   *       // return true to continue processing or false to stop
   *       return (customer.getId() < 40);
   *     });
   *
   * }</pre>
   *
   * @param consumer
   *          the consumer used to process the queried beans.
   */
  public void findEachWhile(QueryEachWhileConsumer<T> consumer) {
    query.findEachWhile(consumer);
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
