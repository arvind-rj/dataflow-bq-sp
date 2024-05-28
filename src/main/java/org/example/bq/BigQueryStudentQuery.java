package org.example.bq;

import com.google.api.services.bigquery.model.TableRow;

import javax.swing.text.TableView;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BigQueryStudentQuery {
    public static void main(String[] args) {
        // Create and configure the pipeline options
//        StreamingOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(StreamingOptions.class);
//        options.setStreaming(false);
//
//        // Create the pipeline
//        Pipeline pipeline = Pipeline.create(options);
//
//        // Define the BigQuery SQL query
//        String query = "SELECT * FROM `burner-aragp.studentDB.student` LIMIT 100";
//
//        // Read the last commit timestamp from Spanner
//       // Optional<String> lastCommitTimestamp = SpannerTimestampReader.readLastCommitTimestamp(burner-aragp, instanceId, databaseId);
//
//        // Define the BigQuery SQL query using the timestamp
//       // String query = "SELECT * FROM `your-project-id.your-dataset-id.your-table-id`" +
//        //        (lastCommitTimestamp.isPresent() ? " WHERE timestamp_column > '" + lastCommitTimestamp.get() + "'" : "");
//
//        // Apply transformations to the pipeline
//        pipeline
//                .apply("ReadFromBigQuery", BigQueryIO.readTableRows().fromQuery(query).usingStandardSql())
//                .apply("ProcessRows", MapElements.into(TypeDescriptor.of(String.class)).via((TableRow row) -> {
//                    // Example processing: Convert TableRow to a String
//                    return row.toString();
//                }))
//                .apply("WriteToConsole", MapElements.into(TypeDescriptor.of(Void.class)).via((String rowString) -> {
//                    // Example output: Print the row to the console
//                    System.out.println(rowString);
//                    return null;
//                }));
//
//        // Run the pipeline
//        pipeline.run();
    }



}