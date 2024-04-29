package com.campusdual.cd2024bfs2g1.model.core.dao;


import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository(value = "LandmarkDao")
@ConfigurationFile(
        configurationFile = "dao/LandmarkDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class LandmarkDao  extends OntimizeJdbcDaoSupport {
    public static final String ATTR_ID = "LANDMARK_ID";
    public static final String ATTR_NAME = "NAME";
    public static final String ATTR_DESCRIPTION = "DESCRIPTION";
    public static final String ATTR_OPENING_TIME = "OPENING_TIME";
    public static final String ATTR_CLOSING_TIME = "CLOSING_TIME";
    public static final String ATTR_LATITUDE = "LATITUDE";
    public static final String ATTR_LONGITUDE = "LONGITUDE";
}
