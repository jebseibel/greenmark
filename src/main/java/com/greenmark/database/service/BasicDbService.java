package com.greenmark.database.service;

public class BasicDbService {

    static final String DB_DOWN_TEMPLATE = "%s1 database access issue.";

    static final String CREATED_SUCCESS_TEMPLATE = "%s with extid [%s2] was created.";
    static final String FOUND_SUCCESS_TEMPLATE = "%s with extid [%s2] found.";
    static final String FOUND_ACTIVE_SUCCESS_TEMPLATE = "%s found [%s2] items.";
    static final String UPDATED_SUCCESS_TEMPLATE = "%s with extid [%s2] updated.";
    static final String DELETED_SUCCESS_TEMPLATE = "%s with extid [%s2] deleted.";

    static final String CREATED_FAILURE_TEMPLATE = "%s with extid [%s2] not created.";
    static final String CREATED_ALREADY_TEMPLATE = "%s with extid [%s2] is already created.";

    static final String FOUND_FAILURE_TEMPLATE = "%s with extid [%s2] not found.";
    static final String UPDATED_FAILURE_TEMPLATE = "%s with extid [%s2] not updated.";
    static final String DELETED_FAILURE_TEMPLATE = "%s with extid [%s2] not deleted.";

    public String thisName;


    public BasicDbService(String name) {
        thisName = name;
    }

    public String getDbAccessMessage(String extid) {
        return String.format(DB_DOWN_TEMPLATE, thisName);
    }

    // ////////////////////////////////////////////////////////
    // CREATED
    // ////////////////////////////////////////////////////////
    public String getCreatedMessage(String extid) {
        return String.format(CREATED_SUCCESS_TEMPLATE, thisName, extid);
    }

    public String getCreatedFailureMessage(String extid) {
        return String.format(CREATED_FAILURE_TEMPLATE, thisName, extid);
    }

    public String getCreatedAlreadyMessage(String extid) {
        return String.format(CREATED_ALREADY_TEMPLATE, thisName, extid);
    }

    // ////////////////////////////////////////////////////////
    // FOUND
    // ////////////////////////////////////////////////////////
    public String getFoundActiveMessage(Integer count) {
        return String.format(FOUND_ACTIVE_SUCCESS_TEMPLATE, thisName, count);
    }

    public String getFoundMessage(String extid) {
        return String.format(FOUND_SUCCESS_TEMPLATE, thisName, extid);
    }

    public String getFoundFailureMessage(String extid) {
        return String.format(FOUND_FAILURE_TEMPLATE, thisName, extid);
    }

    // ////////////////////////////////////////////////////////
    // UPDATED
    // ////////////////////////////////////////////////////////
    public String getUpdatedMessage(String extid) {
        return String.format(UPDATED_SUCCESS_TEMPLATE, thisName, extid);
    }

    public String getUpdatedFailureMessage(String extid) {
        return String.format(UPDATED_FAILURE_TEMPLATE, thisName, extid);
    }

    // ////////////////////////////////////////////////////////
    // DELETED
    // ////////////////////////////////////////////////////////
    public String getDeletedMessage(String extid) {
        return String.format(DELETED_SUCCESS_TEMPLATE, thisName, extid);
    }

    public String getDeletedFailureMessage(String extid) {
        return String.format(DELETED_FAILURE_TEMPLATE, thisName, extid);
    }
}
