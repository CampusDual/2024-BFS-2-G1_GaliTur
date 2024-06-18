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
    public static final String ATTR_ID = "landmark_id";
    public static final String ATTR_NAME = "name";
    public static final String ATTR_DESCRIPTION = "description";
    public static final String ATTR_OPENING_TIME = "opening_time";
    public static final String ATTR_CLOSING_TIME = "closing_time";
    public static final String ATTR_LATITUDE = "latitude";
    public static final String ATTR_LONGITUDE = "longitude";
    public static final String QUERY_LANDMARK_NAME = "getLandmarksDetail";
    public static final String QUERY_LANDMARKS = "getLandmarks";
    public static final String QUERY_NUMBER_LANDMARK = "getNumberOfLandmarks";

}
