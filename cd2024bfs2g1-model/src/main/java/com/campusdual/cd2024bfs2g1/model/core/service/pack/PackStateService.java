package com.campusdual.cd2024bfs2g1.model.core.service.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackStateService;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackStateDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("PackStateService")
public class PackStateService implements IPackStateService {
    private final DefaultOntimizeDaoHelper daoHelper;
    private final PackStateDao packStateDao;

    @Autowired
    public PackStateService(DefaultOntimizeDaoHelper daoHelper, PackStateDao packStateDao) {
        this.daoHelper = daoHelper;
        this.packStateDao = packStateDao;
    }

    @Override
    public EntityResult packStateQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packStateDao, keyMap, attrList);
    }

    @Override
    public EntityResult packStateInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.packStateDao, attrMap);
    }

    @Override
    public EntityResult packStateUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.packStateDao, attrMap, keyMap);
    }

    @Override
    public EntityResult packStateDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.packStateDao, keyMap);
    }
}
