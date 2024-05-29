package org.example.bq;

import com.google.cloud.bigquery.*;

import java.sql.Timestamp;
import java.time.Instant;

// Sample to query in a table
public class BqReadStudentTable {

    public static void main(String... args) throws Exception {

        // Step 1: Initialize BigQuery service
        // Here we set our project ID and get the `BigQuery` service object
        // this is the interface to our BigQuery instance that
        // we use to execute jobs on
        BigQuery bigquery = BigQueryOptions.newBuilder().setProjectId("burner-aragp")
                .build().getService();

        // Step 2: Prepare query job
        // A "QueryJob" is a type of job that executes SQL queries
        // we create a new job configuration from our SQL query and
        int id = UpdateDatabaseSample.query();
        final String STUDENT_QUERY = String.format("SELECT * FROM `burner-aragp.studentDB.student` WHERE id > %d order by id LIMIT 10 ", id);
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(STUDENT_QUERY).build();

        // Step 3: Run the job on BigQuery
        // create a `Job` instance from the job configuration using the BigQuery service
        // the job starts executing once the `create` method executes
        Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).build());
        queryJob = queryJob.waitFor();
        // the waitFor method blocks until the job completes
        // and returns `null` if the job doesn't exist anymore
        if (queryJob == null) {
            throw new Exception("job no longer exists");
        }
        // once the job is done, check if any error occured
        if (queryJob.getStatus().getError() != null) {
            throw new Exception(queryJob.getStatus().getError().toString());
        }

        // Step 4: Display results
        // Print out a header line, and iterate through the
        // query results to print each result in a new line
        System.out.println("Querying  the Bq  for the results");
        TableResult result = queryJob.getQueryResults();
        for (FieldValueList row : result.iterateAll()) {
            // We can use the `get` method along with the column
            // name to get the corresponding row entry
            //String word = row.get("word").getStringValue();
            //int wordCount = row.get("word_count").getNumericValue().intValue();
           // System.out.println(row.get("StudentID").getNumericValue().intValue());
            Student student = new Student(row.get("name").getStringValue(), row.get("id").getNumericValue().intValue(), row.get("City").getStringValue(), row.get("timestamp").getStringValue());
            UpdateDatabaseSample.updateDatabase(student);
        }
    }
}
