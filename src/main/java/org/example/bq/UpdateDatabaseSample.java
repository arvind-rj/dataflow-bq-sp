/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.bq;

// [START spanner_update_database]


import com.google.cloud.spanner.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UpdateDatabaseSample {


    static void updateDatabase(Student student) {
    // TODO(developer): Replace these variables before running the sample.
    final String projectId = "burner-aragp";
    final String instanceId = "spanner";
    final String databaseId = "spannerdb";

    updateDatabase(instanceId, databaseId, student);
  }

    public static void updateDatabase(String instanceId,String databaseId, Student student) {
        // [START init_client]
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();
        com.google.cloud.spanner.admin.database.v1.DatabaseAdminClient dbAdminClient = null;
        try {
            DatabaseId db = DatabaseId.of(options.getProjectId(), instanceId, databaseId);
            // [END init_client]
            // This will return the default project id based on the environment.
            String clientProject = spanner.getOptions().getProjectId();
            // Generate a backup id for the sample database.
            DatabaseClient dbClient = spanner.getDatabaseClient(db);
            dbAdminClient = spanner.createDatabaseAdminClient();

            // Use client here...
            // [END init_client]

            writeExampleDataWithTimestamp(dbClient, student);
            // [START init_client]
        } finally {
            if (dbAdminClient != null) {
                if (!dbAdminClient.isShutdown() || !dbAdminClient.isTerminated()) {
                    dbAdminClient.close();
                }
            }
            spanner.close();
        }
        // [END init_client]
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
}



// [END spanner_update_database]
