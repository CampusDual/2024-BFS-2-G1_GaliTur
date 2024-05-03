package com.campusdual.cd2024bfs2g1.model.core.dao.business;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


@Lazy
@Repository(value = "RestaurantDao")
@ConfigurationFile(
        configurationFile = "dao/business/RestaurantDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class RestaurantDao extends OntimizeJdbcDaoSupport {

    public static final String BSN_ID        = "bsn_id";
    public static final String REST_ID         = "rest_id";
    public static final String MENU         = "rest_menu";


}
