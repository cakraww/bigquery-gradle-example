import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.Test;

public class MainTest {

  @Test
  public void test_DefaultCredentials() {
    BigQuery bigQuery = BigQueryOptions.getDefaultInstance().getService();
    bigQuery.listDatasets().iterateAll().forEach(dataset -> System.out.println(dataset));
  }

  @Test
  public void test_PassPrivateKeyAsJsonString() throws IOException {
    String privateKeyContent = System.getenv("PRIVATE_KEY");
    if (privateKeyContent == null) {
      throw new IllegalArgumentException("set env variable PRIVATE_KEY");
    }

    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
        privateKeyContent.getBytes(UTF_8));

    ServiceAccountCredentials credentials = ServiceAccountCredentials
        .fromStream(byteArrayInputStream);

    BigQuery bigquery = BigQueryOptions.newBuilder()
        .setCredentials(credentials)
        .setProjectId(credentials.getProjectId())
        .build().getService();
    bigquery.listDatasets().iterateAll().forEach(dataset -> System.out.println(dataset));
  }

}