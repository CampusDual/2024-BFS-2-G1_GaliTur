package com.campusdual.cd2024bfs2g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


@Repository(value = "MerchantDao")
@Lazy
@ConfigurationFile(
        configurationFile = "dao/MerchantDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class MerchantDao extends OntimizeJdbcDaoSupport {

    public static final String MERCHANT_ID        = "merchant_id";
    public static final String USR_ID        = "usr_id";
}
