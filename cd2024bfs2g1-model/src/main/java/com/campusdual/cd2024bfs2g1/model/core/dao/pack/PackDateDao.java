package com.campusdual.cd2024bfs2g1.model.core.dao.pack;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("PackDateDao")
@ConfigurationFile(configurationFile = "dao/pack/PackDateDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class PackDateDao extends OntimizeJdbcDaoSupport {
    public static String PD_ID = "pd_id";
    public static String PD_DATE_BEGIN = "pd_date_begin";
    public static String PD_DATE_END = "pd_date_end";
    public static String PCS_ID = "pcs_id";
    public static String PCK_ID = "pck_id";
}
