package com.campusdual.cd2024bfs2g1.model.core.dao.pack;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("ImagePackDao")
@ConfigurationFile(configurationFile = "dao/pack/ImagePackDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class ImagePackDao extends OntimizeJdbcDaoSupport {
    public static final  String IMP_ID = "imp_id";
    public static final String PCK_ID = "pck_id";
    public static final String IMG_ID = "img_id";
}
