package com.campusdual.cd2024bfs2g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository(value = "Image_routeDao")
@ConfigurationFile(
        configurationFile = "dao/Image_routeDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")

public class Image_routeDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_IMAGE_ROUTE_ID = "IMAGE_ROUTE_ID";
    public static final String ATTR_ROUTE_ID = "ROUTE_ID";
    public static final String ATTR_IMAGE_ID = "IMAGE_ID";


}
