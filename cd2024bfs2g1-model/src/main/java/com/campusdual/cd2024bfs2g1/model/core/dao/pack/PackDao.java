package com.campusdual.cd2024bfs2g1.model.core.dao.pack;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("PackDao")
@ConfigurationFile(configurationFile = "dao/pack/PackDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class PackDao extends OntimizeJdbcDaoSupport {

    private String PCK_ID = "pck_id";
    private String PCK_NAME = "pck_name";
    private String PCK_DESCRIPTION = "pck_description";
    private String PCK_DATE_BEGIN = "pck_date_begin";
    private String PCK_DATE_END = "pck_date_end";
    private String PCK_ACTIVE = "pck_active";
    private String PCK_PRICE = "pck_price";
    private String PCK_PARTICIPANTS = "pck_participants";
    private String PCK_GUI_C_ID = "gui_c_id";
}
