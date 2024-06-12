package com.campusdual.cd2024bfs2g1.model.core.dao.pack;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("BusinessPackDao")
@ConfigurationFile(configurationFile = "dao/pack/BusinessPackDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class BusinessPackDao extends OntimizeJdbcDaoSupport {

    public static final String BSN_PACK_ID = "BSN_PACK_ID";
    public static final String BSN_ID = "BSN_ID";
    public static final String PCK_ID = "pck_id";
    public static final String ASSIGNED_DATE = "ASSIGNED_DATE";
    public static String PCK_BSN_MULTI_QUERY = "multi";
    public static String PCK_BSN_QUERY = "businessPacks";

}
