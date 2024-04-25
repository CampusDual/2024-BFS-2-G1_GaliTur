package com.campusdual.cd2024bfs2g1.model.core.dao.business;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


@Lazy
@Repository(value = "AgencyGuideDao")
@ConfigurationFile(
        configurationFile = "dao/business/AgencyGuideDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class AgencyGuideDao extends OntimizeJdbcDaoSupport {

    public static final String BSN_ID        = "bsn_id";
    public static final String GUI_ID         = "gui_id";
    public static final String LANGUAGE         = "gui_language";
    public static final String ZONE      = "gui_zone";
    public static final String CITY          = "gui_city";

}
