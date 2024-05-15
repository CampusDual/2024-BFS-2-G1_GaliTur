package com.campusdual.cd2024bfs2g1.model.core.dao.pack;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("PackDao")
@ConfigurationFile(configurationFile = "dao/pack/PackDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class PackDao extends OntimizeJdbcDaoSupport {
    public static final String PCK_ID = "pck_id";
    public static final String PCK_NAME = "pck_name";
    public static final String PCK_DESCRIPTION = "pck_description";
    public static final String PCK_DATE_BEGIN = "pck_date_begin";
    public static final String PCK_DATE_END = "pck_date_end";
    public static final String PCK_ACTIVE = "pck_active";
    public static final String PCK_PRICE = "pck_price";
    public static final String PCK_PARTICIPANTS = "pck_participants";
    public static final String PCK_GUI_C_ID = "gui_c_id";
}
