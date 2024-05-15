package com.campusdual.cd2024bfs2g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


@Lazy
@Repository(value = "PackBookingDao")
@ConfigurationFile(
        configurationFile = "dao/PackBookingDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class PackBookingDao extends OntimizeJdbcDaoSupport {

    public static final String PBK_BOOKING_ID        = "pbk_booking_id";
    public static final String PCK_ID         = "pck_id";
    public static final String CLIENT_ID         = "client_id";
    public static final String PBK_BOOKING_DATE      = "pbk_booking_date";
    public static final String PBK_DOWN_DATE          = "pbk_down_date";


}