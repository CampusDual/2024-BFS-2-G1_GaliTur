package com.campusdual.cd2024bfs2g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository(value = "RouteLandmarkDao")
@Lazy
@ConfigurationFile(
        configurationFile = "dao/RouteLandmark.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class RouteLandmarkDao extends OntimizeJdbcDaoSupport {
    public static final String ROUTE_LANDMARK_ID = "route_landmark_id";
    public static final String ROUTE_ID = "route_id";
    public static final String LANDMARK_ID = "landmark_id";
    public static final String ACTIVED = "actived";


}
