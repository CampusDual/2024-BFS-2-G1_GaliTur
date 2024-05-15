package com.campusdual.cd2024bfs2g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository(value = "ImageLandmarkDao")
@ConfigurationFile(
        configurationFile = "dao/ImageLandmarkDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")

public class ImageLandmarkDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_IMAGE_LANDMARK_ID = "image_landmark_id";
    public static final String ATTR_LANDMARK_ID = "landmark_id";
    public static final String ATTR_IMAGE_ID = "image_id";


}
