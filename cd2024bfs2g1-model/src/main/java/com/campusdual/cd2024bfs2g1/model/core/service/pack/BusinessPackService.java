package com.campusdual.cd2024bfs2g1.model.core.service.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IBusinessPackService;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.BusinessPackDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("BusinessPackService")
public class BusinessPackService implements IBusinessPackService {

    private final DefaultOntimizeDaoHelper daoHelper;
    private final BusinessPackDao businessPackDao;


    @Autowired
    public BusinessPackService(DefaultOntimizeDaoHelper daoHelper, BusinessPackDao businessPackDao) {
        this.daoHelper = daoHelper;
        this.businessPackDao = businessPackDao;
    }
    @Override
    public EntityResult businessPackQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {


        return this.daoHelper.query(this.businessPackDao, keyMap, attrList,BusinessPackDao.PCK_BSN_MULTI_QUERY);
    }


    @Override
    public EntityResult packBusinessQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.businessPackDao, keyMap, attrList,BusinessPackDao.PCK_BSN_QUERY);
    }


    @Override
    public EntityResult businessPackInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.businessPackDao, attrMap);
    }

    @Override
    public EntityResult businessPackUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.businessPackDao, attrMap, keyMap);
    }

    @Override
    public EntityResult businessPackDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.businessPackDao, keyMap);
    }
}
