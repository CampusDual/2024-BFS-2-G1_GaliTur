package com.campusdual.cd2024bfs2g1.model.core.dao.business;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


@Lazy
@Repository(value = "GuideCitiesDao")
@ConfigurationFile(
        configurationFile = "dao/business/GuideCitiesDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class GuideCitiesDao extends OntimizeJdbcDaoSupport {

    public static final String GUI_C_ID         = "gui_c_id";
    public static final String GUI_Z_ID        = "gui_z_id";
    public static final String GUI_C_NAME         = "gui_c_name";

    }
