package com.campusdual.cd2024bfs2g1.model.core.dao.business;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


@Lazy
@Repository(value = "GuideLanguageDao")
@ConfigurationFile(
        configurationFile = "dao/business/GuideLanguageDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class GuideLanguageDao extends OntimizeJdbcDaoSupport {

    public static final String GUI_ID         = "gui_id";
    public static final String GUI_L_ID        = "gui_l_id";
    public static final String GUI_L_NAME         = "gui_l_name";


}
