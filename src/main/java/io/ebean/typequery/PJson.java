package io.ebean.typequery;


/**
 * JSON document type.
 * <p>
 * Type that is JSON content mapped to database types such as Postgres JSON/JSONB and otherwise Varchar,Clob and Blob.
 * </p>
 * <p>
 * The expressions on this type are valid of Postgres and Oracle.
 * </p>
 *
 * <p>
 * The path can reference a nested property in the JSON document using dot notation -
 * for example "documentMeta.score" where "score" is an embedded attribute of "documentMeta"
 * </p>
 *
 * @param <R> the root query bean type
 */
public class PJson<R> extends TQProperty<R> {

  /**
   * Construct with a property name and root instance.
   *
   * @param name property name
   * @param root the root query bean instance
   */
  public PJson(String name, R root) {
    super(name, root);
  }

  /**
   * Construct with additional path prefix.
   */
  public PJson(String name, R root, String prefix) {
    super(name, root, prefix);
  }

  /**
   * Path exists - for the given path in a JSON document.
   *
   * <pre>{@code
   *
   *   new QSimpleDoc()
   *    .content.jsonExists("meta.title")
   *    .findList();
   *
   * }</pre>
   *
   * @param path the nested path in the JSON document in dot notation
   */
  public R jsonExists(String path) {
    expr().jsonExists(name, path);
    return root;
  }

  /**
   * Path does not exist - for the given path in a JSON document.
   *
   * <pre>{@code
   *
   *   new QSimpleDoc()
   *    .content.jsonNotExists("meta.title")
   *    .findList();
   *
   * }</pre>
   *
   * @param path the nested path in the JSON document in dot notation
   */
  public R jsonNotExists(String path) {
    expr().jsonNotExists(name, path);
    return root;
  }

  /**
   * Value at the given JSON path is equal to the given value.
   *
   *
   * <pre>{@code
   *
   *   new QSimpleDoc()
   *    .content.jsonEqualTo("title", "Rob JSON in the DB")
   *    .findList();
   *
   * }</pre>
   *
   * <pre>{@code
   *
   *   new QSimpleDoc()
   *    .content.jsonEqualTo("path.other", 34)
   *    .findList();
   *
   * }</pre>
   *
   * @param path the dot notation path in the JSON document
   * @param value the equal to bind value
   */
  public R jsonEqualTo(String path, Object value) {
    expr().jsonEqualTo(name, path, value);
    return root;
  }

  /**
   * Not Equal to - for the given path in a JSON document.
   *
   * @param path the nested path in the JSON document in dot notation
   * @param value the value used to test equality against the document path's value
   */
  public R jsonNotEqualTo(String path, Object value) {
    expr().jsonNotEqualTo(name, path, value);
    return root;
  }

  /**
   * Greater than - for the given path in a JSON document.
   *
   * @param path the nested path in the JSON document in dot notation
   * @param value the value used to test against the document path's value
   */
  public R jsonGreaterThan(String path, Object value) {
    expr().jsonGreaterThan(name, path, value);
    return root;
  }

  /**
   * Greater than or equal to - for the given path in a JSON document.
   *
   * @param path the nested path in the JSON document in dot notation
   * @param value the value used to test against the document path's value
   */
  public R jsonGreaterOrEqual(String path, Object value) {
    expr().jsonGreaterOrEqual(name, path, value);
    return root;
  }

  /**
   * Less than - for the given path in a JSON document.
   *
   * @param path the nested path in the JSON document in dot notation
   * @param value the value used to test against the document path's value
   */
  public R jsonLessThan(String path, Object value) {
    expr().jsonLessThan(name, path, value);
    return root;
  }

  /**
   * Less than or equal to - for the given path in a JSON document.
   *
   * @param path the nested path in the JSON document in dot notation
   * @param value the value used to test against the document path's value
   */
  public R jsonLessOrEqualTo(String path, Object value) {
    expr().jsonLessOrEqualTo(name, path, value);
    return root;
  }
}
