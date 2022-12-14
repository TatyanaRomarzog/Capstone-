package com.kenzie.appserver.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.File;
import java.security.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;



    //Application Class:
    //UUID applicationId *Partition key*
    //Timestamp timeStamp (time that application was most recently edited)
    //File Resume
    //List<String> workHistory
    //List<String> references
    //Criteria jobCriteria

    //constructor for all arguments
    //getters, no setters
    @DynamoDBTable(tableName = "Application")
    public class ApplicationRecord {
        private UUID applicationId;
        private Timestamp timestamp;
        private File resume;
        private List<String> references;
        private List <String> workHistory;
        private List<String> jobCriteria;
        private Criteria criteria;

        @DynamoDBHashKey(attributeName = "applicationId")
        public UUID getApplicationId() {
            return applicationId;
        }

        @DynamoDBAttribute(attributeName = "timestamp")
        public Timestamp timestamp() {
            return timestamp;
        }

        @DynamoDBAttribute(attributeName = "Resume")
        public File getResume() {
            return resume;
        }

        @DynamoDBAttribute(attributeName = "References")
        public List<String> getReferences() {
            return references;
        }

        @DynamoDBAttribute(attributeName = "WorkHistory")
        public List<String> getWorkHistory() {
            return workHistory;
        }

        @DynamoDBAttribute(attributeName = "JobCriteria")
        public List<String> getJobCriteria() {
            return jobCriteria;
        }
        @DynamoDBAttribute(attributeName = "Criteria")
        public Criteria getCriteria() {
            return Criteria;
        }

        public void setApplicationId(String applicationId) {
            this.applicationId = UUID.fromString(applicationId);
        }

        public void setTimeStamp(String timeStamp) {
            this.timestamp = timestamp;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public void setReferrerId(String referrerId) {
            this.referrerId = referrerId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CustomerRecord that = (CustomerRecord) o;
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

}
