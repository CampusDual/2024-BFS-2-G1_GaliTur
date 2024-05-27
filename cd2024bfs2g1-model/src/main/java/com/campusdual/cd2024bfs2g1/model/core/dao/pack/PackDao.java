package com.campusdual.cd2024bfs2g1.model.core.dao.pack;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("PackDao")
@ConfigurationFile(configurationFile = "dao/pack/PackDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class PackDao extends OntimizeJdbcDaoSupport {

    public static String PCK_ID = "pck_id";
    public static String PCK_NAME = "pck_name";
    public static String PCK_DESCRIPTION = "pck_description";
    public static String PCK_PRICE = "pck_price";
    public static String PCK_PARTICIPANTS = "pck_participants";
    public static String PCK_GUI_C_ID = "gui_c_id";
    public static String PCK_MULTI_QUERY = "multi";
    public static String PCK_ALL_QUERY = "allPacks";
    public static String PCK_NEWEST_QUERY = "newest";
    public static String PCK_ACOORDING_PROVINCE_QUERY = "packsAccordingProvince";
}
