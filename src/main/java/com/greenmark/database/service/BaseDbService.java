package com.greenmark.database.service;

public class BaseDbService {

    static final String DB_DOWN_TEMPLATE = "%s database access issue.";

    static final String CREATED_SUCCESS_TEMPLATE = "%s with symbol [%s] was created.";
    static final String FOUND_SUCCESS_TEMPLATE = "%s with symbol [%s] found.";
    static final String FOUND_ACTIVE_SUCCESS_TEMPLATE = "%s found [%s] items.";
    static final String UPDATED_SUCCESS_TEMPLATE = "%s with symbol [%s] updated.";
    static final String DELETED_SUCCESS_TEMPLATE = "%s with symbol [%s] deleted.";

    static final String CREATED_FAILURE_TEMPLATE = "%s with symbol [%s] not created.";
    static final String CREATED_ALREADY_TEMPLATE = "%s with symbol [%s] is already created.";

    static final String FOUND_FAILURE_TEMPLATE = "%s with symbol [%s] not found.";
    static final String UPDATED_FAILURE_TEMPLATE = "%s with symbol [%s] not updated.";
    static final String DELETED_FAILURE_TEMPLATE = "%s with symbol [%s] not deleted.";

    public String thisName;


    public BaseDbService(String name) {
        thisName = name;
    }

    public String getDbAccessMessage(String symbol) {
        return String.format(DB_DOWN_TEMPLATE, thisName);
    }

    // ////////////////////////////////////////////////////////
    // CREATED
    // ////////////////////////////////////////////////////////
    public String getCreatedMessage(String symbol) {
        return String.format(CREATED_SUCCESS_TEMPLATE, thisName, symbol);
    }

    public String getCreatedFailureMessage(String symbol) {
        return String.format(CREATED_FAILURE_TEMPLATE, thisName, symbol);
    }

    public String getCreatedAlreadyMessage(String symbol) {
        return String.format(CREATED_ALREADY_TEMPLATE, thisName, symbol);
    }

    // ////////////////////////////////////////////////////////
    // FOUND
    // ////////////////////////////////////////////////////////
    public String getFoundActiveMessage(Integer count) {
        return String.format(FOUND_ACTIVE_SUCCESS_TEMPLATE, thisName, count);
    }

    public String getFoundMessage(String symbol) {
        return String.format(FOUND_SUCCESS_TEMPLATE, thisName, symbol);
    }

    public String getFoundFailureMessage(String symbol) {
        return String.format(FOUND_FAILURE_TEMPLATE, thisName, symbol);
    }

    // ////////////////////////////////////////////////////////
    // UPDATED
    // ////////////////////////////////////////////////////////
    public String getUpdatedMessage(String symbol) {
        return String.format(UPDATED_SUCCESS_TEMPLATE, thisName, symbol);
    }

    public String getUpdatedFailureMessage(String symbol) {
        return String.format(UPDATED_FAILURE_TEMPLATE, thisName, symbol);
    }

    // ////////////////////////////////////////////////////////
    // DELETED
    // ////////////////////////////////////////////////////////
    public String getDeletedMessage(String symbol) {
        return String.format(DELETED_SUCCESS_TEMPLATE, thisName, symbol);
    }

    public String getDeletedFailureMessage(String symbol) {
        return String.format(DELETED_FAILURE_TEMPLATE, thisName, symbol);
    }
}
