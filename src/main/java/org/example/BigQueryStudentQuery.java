package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BigQueryStudentQuery {
    public static void main(String[] args) {
        // Create and configure the pipeline options
        StreamingOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(StreamingOptions.class);
        options.setStreaming(false);

        // Create the pipeline
        Pipeline pipeline = Pipeline.create(options);

        // Define the BigQuery SQL query
        String query = "SELECT * FROM `your-project-id.your-dataset-id.your-table-id`";

        // Apply transformations to the pipeline
        pipeline
                .apply("ReadFromBigQuery", BigQueryIO.readTableRows().fromQuery(query).usingStandardSql())
                .apply("ProcessRows", MapElements.into(TypeDescriptor.of(String.class)).via((TableRow row) -> {
                    // Example processing: Convert TableRow to a String
                    return row.toString();
                }))
                .apply("WriteToConsole", MapElements.into(TypeDescriptor.of(Void.class)).via((String rowString) -> {
                    // Example output: Print the row to the console
                    System.out.println(rowString);
                    return null;
                }));

        // Run the pipeline
        pipeline.run().waitUntilFinish();
    }
}