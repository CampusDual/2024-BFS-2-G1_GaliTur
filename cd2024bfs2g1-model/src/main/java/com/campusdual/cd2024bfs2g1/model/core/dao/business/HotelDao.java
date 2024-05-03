package com.campusdual.cd2024bfs2g1.model.core.dao.business;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


@Lazy
@Repository(value = "HotelDao")
@ConfigurationFile(
        configurationFile = "dao/business/HotelDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class HotelDao extends OntimizeJdbcDaoSupport {

    public static final String BSN_ID        = "bsn_id";
    public static final String HTL_ID         = "htl_id";


}
