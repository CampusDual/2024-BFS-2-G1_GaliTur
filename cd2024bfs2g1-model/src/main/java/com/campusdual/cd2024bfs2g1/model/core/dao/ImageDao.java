package com.campusdual.cd2024bfs2g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository(value = "ImageDao")
@ConfigurationFile(
        configurationFile = "dao/ImageDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")

public class ImageDao extends OntimizeJdbcDaoSupport {
    public static final String ATTR_IMAGE_ID = "image_id";
    public static final String ATTR_IMAGE_CODE = "img_code";
    public static final String QUERY_IMAGE_CODE = "joinIdImage";

    public static final Integer DEFAULT_IMG_ID = 1;
}
