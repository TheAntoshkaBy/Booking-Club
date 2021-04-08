package com.epam.esm.exception.constant;

public class ErrorTextMessageConstants {

    public static final String NAME_FIELD = "The name field must contain "
        + "more than 3 characters and less than 90";
    public static final String TAG_NAME_FIELD_IS_EXIST = "This tag name "
        + "field is exist, Tag name must be unique ";
    public static final String CERTIFICATE_ID = "Certificate Id must be more than 0";
    public static final String ORDER_ID = "Order Id must be more than 0";
    public static final String TAG_ID = "Tag Id must be more than 0";
    public static final String USER_ID = "User Id must be more than 0";
    public static final String CERTIFICATE_ID_MORE_THAN = "Certificate Id must be more than 0";
    public static final String USER_LOGIN_FIELD_IS_EXIST = "This user "
        + "login is exist, User login must be unique ";
    public static final String USER_EMAIL_FIELD_IS_EXIST = "This user "
        + "email is exist, User email must be unique ";
    public static final String PRICE_FIELD = "The price field must not be negative";
    public static final String MODIFICATION_DATE_FIELD = "This operation "
        + "wait null modification date";
    public static final String CREATION_DATE_FIELD = "This operation wait null modification date";
    public static final String NOT_FOUND_CERTIFICATE = "Certificate "
        + "with given id does not exist, enter another id";
    public static final String NOT_FOUND_ORDER = "Order "
        + "with given id does not exist, enter another id";
    public static final String NOT_FOUND_TAG = "Tag with given id does not exist, enter another id";
    public static final String NOT_FOUND_USER = "User with given id "
        + "does not exist, enter another id";
    public static final String NOT_FOUND_CERTIFICATE_OR_TAG_BY_ID =
        "Certificate or Tag with given " +
            "id does not exist, enter another Certificate id or Tag id";
    public static final String FILTER_TYPE_NOT_EXIST = "This filtering "
        + "method is not provided by our program.";
    public static final String SORT_TYPE_NOT_EXIST = "This sorting method "
        + "is not provided by our program.";
    public static final String EMPTY_DATA = "Data is empty!";
    public static final String TAG_DUPLICATE_NAME = "Duplicate tag name, please input new name";
    public static final String ROLE_NOT_SUPPORTED_OPERATION = "This operation "
        + "in Role entity not supported!";
}
