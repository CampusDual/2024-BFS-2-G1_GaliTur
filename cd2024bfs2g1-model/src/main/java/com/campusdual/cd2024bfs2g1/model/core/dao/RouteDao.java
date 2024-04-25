package com.campusdual.cd2024bfs2g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository(value = "RouteDao")
@ConfigurationFile(
        configurationFile = "dao/RouteDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")

public class RouteDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_ID = "ROUTE_ID";
    public static final String ATTR_NAME = "NAME";
    public static final String ATTR_DESCRIPTION = "DESCRIPTION";
    public static final String ATTR_ESTIMATED_DURATION = "ESTIMATED_DURATION";
    public static final String ATTR_DIFFICULTY = "DIFFICULTY";

}
