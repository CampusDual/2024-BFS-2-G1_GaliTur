package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IMerchantService;
import com.campusdual.cd2024bfs2g1.model.core.dao.MerchantDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Lazy
@Service("MerchantService")
public class MerchantService implements IMerchantService {
    private final MerchantDao merchantDao;
    private final DefaultOntimizeDaoHelper daoHelper;
    private final UserAndRoleService userAndRoleService;

    @Autowired
    public MerchantService(MerchantDao merchantDao, DefaultOntimizeDaoHelper daoHelper, UserAndRoleService userAndRoleService) {

        this.merchantDao = merchantDao;
        this.daoHelper = daoHelper;
        this.userAndRoleService = userAndRoleService;
    }


    @Override
    public EntityResult merchantQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {

        return this.daoHelper.query(this.merchantDao, keyMap, attrList);
    }

    // userIdMap casting warning
    @SuppressWarnings("unchecked")
    @Override
    public EntityResult merchantInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        EntityResult entityResult = userAndRoleService.userInsert(attrMap);

        if (entityResult.getCode() == EntityResult.OPERATION_SUCCESSFUL) {
            Map<String, Object> userIdMap = (Map<String, Object>) entityResult;
            EntityResult insertMerchant = this.daoHelper.insert(this.merchantDao, userIdMap);
            if (insertMerchant.getCode() == EntityResult.OPERATION_WRONG) {
                this.userAndRoleService.userDelete(userIdMap);
            }
            return insertMerchant;

        }

        return entityResult;
    }

    @Override
    public EntityResult merchantUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.merchantDao, attrMap, keyMap);
    }

    @Override
    public EntityResult merchantDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.merchantDao, keyMap);
    }

}
