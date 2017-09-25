import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;

/**
 * // TODO Comment
 */
public class Main {
  public static void main(String[] args) {
    BigQuery bigQuery = BigQueryOptions.getDefaultInstance().getService();
    bigQuery.listDatasets().iterateAll().forEach(dataset -> System.out.println(dataset));
  }
}
