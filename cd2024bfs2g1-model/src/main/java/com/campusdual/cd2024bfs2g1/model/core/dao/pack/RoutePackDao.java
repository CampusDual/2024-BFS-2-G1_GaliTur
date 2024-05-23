package com.campusdual.cd2024bfs2g1.model.core.dao.pack;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("RoutePackDao")
@ConfigurationFile(configurationFile = "dao/pack/RoutePackDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class RoutePackDao extends OntimizeJdbcDaoSupport {

    public static final String ROUTE_PACK_ID = "ROUTE_PACK_ID";
    public static final String ROUTE_ID = "ROUTE_ID";
    public static final String PCK_ID = "pck_id";
    public static final String ASSIGNED_DATE = "ASSIGNED_DATE";
}
