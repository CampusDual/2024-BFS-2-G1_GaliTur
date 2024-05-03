package com.campusdual.cd2024bfs2g1.model.core.dao.business;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


@Lazy
@Repository(value = "HotelServicesDao")
@ConfigurationFile(
        configurationFile = "dao/business/HotelServicesDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class HotelServicesDao extends OntimizeJdbcDaoSupport {

    public static final String SRV_ID        = "srv_id";
    public static final String HTL_ID         = "htl_id";
    public static final String TYPE         = "srv_type";
    public static final String COST         = "srv_cost";

}
