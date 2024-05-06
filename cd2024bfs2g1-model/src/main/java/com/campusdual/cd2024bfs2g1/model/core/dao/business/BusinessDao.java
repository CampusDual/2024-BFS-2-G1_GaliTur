package com.campusdual.cd2024bfs2g1.model.core.dao.business;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;


@Lazy
@Repository(value = "BusinessDao")
@ConfigurationFile(
        configurationFile = "dao/business/BusinessDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class BusinessDao extends OntimizeJdbcDaoSupport {

    public static final String BSN_ID        = "bsn_id";
    public static final String MERCHANT_ID         = "merchant_id";
    public static final String NAME         = "bsn_name";
    public static final String TYPE      = "bsn_type";
    public static final String DESCRIPTION          = "bsn_description";
    public static final String CIF       = "bsn_cif";
    public static final String ADDRESS = "bsn_address";
    public static final String PHONE     = "bsn_phone";
    public static final String EMAIL         = "bsn_email";
    public static final String CREATION_DATE         = "bsn_creation_date";
    public static final String DOWN_DATE         = "bsn_down_date";
    public static final String PHOTOS  = "bsn_photos";
    public static final String WEBSITE  = "bsn_website";
    public static final String SCHEDULE  = "bsn_schedule";


}
