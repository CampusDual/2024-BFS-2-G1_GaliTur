package com.campusdual.cd2024bfs2g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("ClientDao")
@ConfigurationFile(configurationFile = "dao/ClientDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class ClientDao extends OntimizeJdbcDaoSupport {
    public static final String CLIENT_ID = "client_id";
    public static final String USR_ID = "usr_id";
    public static final String BIRTH_DATE = "birth_date";

}
