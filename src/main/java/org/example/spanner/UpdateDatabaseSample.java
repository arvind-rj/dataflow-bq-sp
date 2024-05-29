package org.example.spanner;

import com.google.cloud.spanner.*;
import org.example.model.Student;

import java.util.ArrayList;
import java.util.List;

public class UpdateDatabaseSample {
    static final String instanceId = "spanner";
    static final String databaseId = "spannerdb";

    public static void updateDatabase(Student student) {
        // [START init_client]
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();
        com.google.cloud.spanner.admin.database.v1.DatabaseAdminClient dbAdminClient = null;
        try {
            DatabaseId db = DatabaseId.of(options.getProjectId(), instanceId, databaseId);
            String clientProject = spanner.getOptions().getProjectId();
            DatabaseClient dbClient = spanner.getDatabaseClient(db);
            dbAdminClient = spanner.createDatabaseAdminClient();

            writeExampleDataWithTimestamp(dbClient, student);
        } finally {
            if (dbAdminClient != null) {
                if (!dbAdminClient.isShutdown() || !dbAdminClient.isTerminated()) {
                    dbAdminClient.close();
                }
            }
            spanner.close();
        }
        System.out.println("Closed client");
    }

    static void writeExampleDataWithTimestamp(DatabaseClient dbClient, Student student) {

        List<Mutation> mutations = new ArrayList<>();
        mutations.add(
                Mutation.newInsertBuilder("Students")
                        .set("name")
                        .to(student.getName())
                        .set("city")
                        .to(student.getCity())
                        .set("id")
                        .to(student.getId())
                        .set("timestamp")
                        .to(student.getTimestamp())
                        .build());
        dbClient.write(mutations);
    }


    public static int query() {
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();
        com.google.cloud.spanner.admin.database.v1.DatabaseAdminClient dbAdminClient = null;
        DatabaseId db = DatabaseId.of(options.getProjectId(), instanceId, databaseId);
        String clientProject = spanner.getOptions().getProjectId();
        DatabaseClient dbClient = spanner.getDatabaseClient(db);
        dbAdminClient = spanner.createDatabaseAdminClient();
        int id = 0;
        try (ResultSet resultSet =
                     dbClient
                             .singleUse() // Execute a single read or query against Cloud Spanner.
                             .executeQuery(Statement.of("SELECT id FROM students order by id desc LIMIT 1"))) {

            while (resultSet.next()) {
                id = (int) resultSet.getLong(0);
            }
        } finally {
            if (dbAdminClient != null) {
                if (!dbAdminClient.isShutdown() || !dbAdminClient.isTerminated()) {
                    dbAdminClient.close();
                }
            }
            spanner.close();
        }
            return id;
    }
}

