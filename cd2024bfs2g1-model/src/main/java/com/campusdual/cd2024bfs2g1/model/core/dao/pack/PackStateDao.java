package com.campusdual.cd2024bfs2g1.model.core.dao.pack;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("PackStateDao")
@ConfigurationFile(configurationFile = "dao/pack/PackStateDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class PackStateDao extends OntimizeJdbcDaoSupport {
    public static final  String PCS_ID = "pcs_id";
    public static final String PCS_STATE = "pcs_state";
}
