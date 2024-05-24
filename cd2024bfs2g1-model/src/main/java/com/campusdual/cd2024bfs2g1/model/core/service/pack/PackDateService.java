package com.campusdual.cd2024bfs2g1.model.core.service.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackDateService;
import com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ImageDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.GuideCitiesDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackDateDao;
import com.campusdual.cd2024bfs2g1.model.core.service.ClientService;
import com.campusdual.cd2024bfs2g1.model.core.service.ImageService;
import com.campusdual.cd2024bfs2g1.model.core.service.business.GuideCitiesService;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Lazy
@Service("PackDateService")
public class PackDateService implements IPackDateService {

    private final DefaultOntimizeDaoHelper daoHelper;
    private final PackDateDao packDateDao;

    @Autowired
    public PackDateService(DefaultOntimizeDaoHelper daoHelper, PackDateDao packDateDao) {
        this.daoHelper = daoHelper;
        this.packDateDao = packDateDao;
    }

    @Override
    public EntityResult packDateQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packDateDao, keyMap, attrList);
    }

    @Override
    public EntityResult packDateInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException, ParseException {
        return this.daoHelper.insert(this.packDateDao, attrMap);
    }

    @Override
    public EntityResult packDateUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.packDateDao, attrMap, keyMap);
    }

    @Override
    public EntityResult packDateDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.packDateDao, keyMap);
    }
}
