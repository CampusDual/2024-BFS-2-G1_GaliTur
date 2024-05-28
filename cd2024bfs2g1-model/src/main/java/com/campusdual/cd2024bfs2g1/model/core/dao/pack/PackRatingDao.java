package com.campusdual.cd2024bfs2g1.model.core.dao.pack;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("PackRatingDao")
@ConfigurationFile(configurationFile = "dao/pack/PackRatingDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class PackRatingDao extends OntimizeJdbcDaoSupport {
    public static String PCR_ID = "pd_id";
    public static String PCR_STARS = "pd_date_begin";
    public static String PCR_COMMENT = "pd_date_end";
    public static String PCK_ID = "pck_id";
    public static String CLIENT_ID = "client_id";
}
