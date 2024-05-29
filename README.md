gcloud config set project burner-aragp

gcloud services enable dataflow compute_component logging storage_component storage_api cloudresourcemanager.googleapis.com artifactregistry.googleapis.com cloudbuild.googleapis.com

gcloud auth application-default login

git clone https://github.com/arvind-rj/dataflow-bq-sp.git

mvn clean package

gcloud artifacts repositories create dataflow-bq --repository-format=docker --location=us-central1
gcloud auth configure-docker us-central1-docker.pkg.dev

gcloud dataflow flex-template build gs://burner-aragp_cloudbuild/getting_started-java.json --image-gcr-path "us-central1-docker.pkg.dev/burner-aragp/dataflow/dataflow-bq:latest" --sdk-language "JAVA" --flex-template-base-image JAVA11 --jar "target/dataflow-bq-sp-1.0-SNAPSHOT.jar" --env FLEX_TEMPLATE_JAVA_MAIN_CLASS="org.example.bq.BqReadStudentTable"


gcloud dataflow flex-template run "dataflow-bq-`date +%Y%m%d-%H%M%S`" --template-file-gcs-location "gs://burner-aragp_cloudbuild/getting_started-java.json" --parameters output="gs://burner-aragp_cloudbuild/output-" --region "us-central1"