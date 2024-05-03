package com.campusdual.cd2024bfs2g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository(value = "ImageRouteDao")
@ConfigurationFile(
        configurationFile = "dao/ImageRouteDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")

public class ImageRouteDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_IMAGE_ROUTE_ID = "IMAGE_ROUTE_ID";
    public static final String ATTR_ROUTE_ID = "ROUTE_ID";
    public static final String ATTR_IMAGE_ID = "IMAGE_ID";


}
