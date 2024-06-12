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

    public static final String PBK_BOOKING_ID = "pbk_booking_id";
    public static final String CLIENT_ID = "client_id";
    public static final String PD_ID = "pd_id";
    public static final String PBK_BOOKING_DATE = "pbk_booking_date";
    public static final String PBK_DOWN_DATE = "pbk_down_date";
    public static final String QUERY_TOP_MOST_PACKS = "topMostPacks";
    public static final String QUERY_TOP_LEAST_PACKS = "topLeastPacks";
    public static final String PBK_RATING_DATE = "pbk_rating_date";
    public static final String PBK_STARS = "pbk_stars";
    public static final String PBK_COMMENT = "pbk_comment";



    public static final String PBK_BOOKING_CLIENT_USER_DATE_PACK_QUERY = "bookingClientUserDatePack";
}
