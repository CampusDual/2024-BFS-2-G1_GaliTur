package com.campusdual.cd2024bfs2g1.model.core.dao.business;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


@Lazy
@Repository(value = "GuideZoneDao")
@ConfigurationFile(
        configurationFile = "dao/business/GuideZoneDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class GuideZoneDao extends OntimizeJdbcDaoSupport {

    public static final String GUI_ID         = "gui_id";
    public static final String GUI_Z_ID        = "gui_z_id";
    public static final String GUI_Z_NAME         = "gui_z_name";


}
