package com.campusdual.cd2024bfs2g1.model.core.dao.business;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


@Lazy
@Repository(value = "HotelRoomsDao")
@ConfigurationFile(
        configurationFile = "dao/business/HotelRoomsDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class HotelRoomsDao extends OntimizeJdbcDaoSupport {

    public static final String RM_ID        = "rm_id";
    public static final String HTL_ID         = "htl_id";
    public static final String TYPE         = "rm_type";
    public static final String COST         = "rm_cost";

}
