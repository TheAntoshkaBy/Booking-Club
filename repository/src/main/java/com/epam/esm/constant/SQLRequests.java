package com.epam.esm.constant;

public class SQLRequests {

    public static final String ADD_TAG_TO_CERTIFICATE =
          "INSERT INTO rest_api_basics.many_certificates_to_many_tags\n"
        + "VALUES (:id_certificate, :id_tag)";

    public static final String DELETE_TAG_FROM_CERTIFICATE =
              "DELETE FROM rest_api_basics.many_certificates_to_many_tags "
            + "WHERE id_certificate = :id_certificate "
            + "AND id_tag = :id_tag";

    public static final String FIND_ALL_CERTIFICATES_WHERE_ID_MORE_THAN_PARAMETER =
        "SELECT c FROM certificate c WHERE id > ?1";

    public static final String FIND_COUNT_ALL_CERTIFICATES_WHERE_ID_MORE_THAN_PARAMETER =
        "SELECT count(c) FROM certificate c WHERE id > ?1";

    public static final String FIND_CERTIFICATE_BY_TAG_NAME =
            "SELECT c " +
            "FROM certificate c " +
            "JOIN c.tags tg " +
            "WHERE tg.name = ?1";

    public static final String FIND_CERTIFICATE_BY_ID =
        "select * from rest_api_basics.certificate where id_certificate = :id";

    public static final String FIND_ALL_CERTIFICATES = "select c from certificate c";

    public static final String FIND_COUNT_OF_CERTIFICATE = "select count(c) from certificate c";

    public static final String FIND_COUNT_OF_TAG = "select count(t) from tag t";

    public static final String FIND_COUNT_OF_USER = "select count(t) from shop_user t";

    public static final String FIND_COUNT_OF_ORDER = "select count(t) from certificate_order t";

    public static final String FIND_COUNT_OF_ORDER_BY_OWNER = "select count(t) from "
        + "certificate_order t where t.owner.id = ?1";

    public static final String FIND_ALL_CERTIFICATES_WITH_LIMIT_OFFSET =
          "select c "
        + "from certificate c order by c.id";

    public static final String FIND_ALL_ORDERS_WITH_LIMIT_OFFSET =
          "select o "
        + "from certificate_order o order by o.id";

    public static final String FIND_ALL_CERTIFICATES_BY_DATE =
        "select c from certificate as c order by c.creationDate";

    public static final String ADD_CERTIFICATE =
        "INSERT INTO certificate (\n" +
        "            name,\n" +
        "            description,\n" +
        "            date_of_creation,\n" +
        "            date_of_modification,\n" +
        "            duration_days,\n" +
        "            price,\n" +
        "            certificate_id\n" +
        "            )\n" +
        "            VALUES (?,?,?,NULL,?,?, DEFAULT );";

    public static final String UPDATE_CERTIFICATE =
        "UPDATE certificate SET " +
        "name = ?, " +
        "description = ?, " +
        "date_of_creation = ?, " +
        "date_of_modification = ?, " +
        "duration_days = ?, " +
        "price = ? " +
        "WHERE id_certificate = ?";

    public static final String DELETE_CERTIFICATE =
        "DELETE FROM certificate WHERE id = ?1";

    public static final String FIND_TAGS_BELOW_CONCRETE_CERTIFICATE =
             "SELECT tag.id_tag, tag.tag_name\n"
           + "FROM rest_api_basics.many_certificates_to_many_tags\n"
           + "RIGHT JOIN rest_api_basics.tag ON many_certificates_to_many_tags.id_tag = tag.id_tag\n"
           + "WHERE id_certificate = :id";

    public static final String FIND_ALL_TAGS = "select t from tag t order by t.id";

    public static final String FIND_ROLE_BY_NAME = "select r from role r where r.name = ?1";

    public static final String FIND_ALL_USERS = "select u from shop_user u";

    public static final String FIND_ALL_ORDERS = "select o from certificate_order o";

    public static final String FIND_ALL_ORDERS_BY_OWNER = "select o "
        + "from certificate_order o where o.owner.id = ?1 order by o.id";

    public static final String FIND_TAG_BY_ID = "select * "
        + "from rest_api_basics.tag where id_tag = :id";

    public static final String ADD_TAG =
        "INSERT INTO rest_api_basics.tag (tag_name, id_tag) VALUES (:tag_name, DEFAULT )";

    public static final String DELETE_TAG_BY_ID = "DELETE FROM tag where id = ?1";

    public static final String FIND_TAG_BY_NAME = "SELECT t FROM tag t where t.name = ?1";

    public static final String DELETE_ORDER_BY_ID = "DELETE FROM certificate_order where id = ?1";

    public static final String DELETE_USER_BY_ID = "DELETE FROM shop_user where id = ?1";

    public static final String FIND_USER_BY_LOGIN = "SELECT u FROM shop_user u where login = ?1";

    public static final String FIND_USER_BY_EMAIL = "SELECT u FROM shop_user u where email = ?1";

    public static final String FIND_BY_PART_OF_NAME = "SELECT * FROM return_t_certificate(?)";

    public static final String DELETE_ALL_CERTIFICATES = "DELETE FROM rest_api_basics.certificate";

    public static final String DELETE_ALL_TAGS = "DELETE FROM rest_api_basics.tag";

    public static final String DELETE_ALL_RELATIONSHIPS =
        "DELETE FROM rest_api_basics.many_certificates_to_many_tags;";
}
