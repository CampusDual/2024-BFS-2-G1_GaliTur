package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IMerchantService;
import com.campusdual.cd2024bfs2g1.model.core.dao.MerchantDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.UserRoleDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.services.user.UserInformation;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Lazy
@Service("MerchantService")
public class MerchantService implements IMerchantService {
    private final UserRoleDao userRoleDao;
    private final MerchantDao merchantDao;
    private final DefaultOntimizeDaoHelper daoHelper;
    private final UserAndRoleService userAndRoleService;

    @Autowired
    public MerchantService(UserRoleDao userRoleDao, MerchantDao merchantDao, DefaultOntimizeDaoHelper daoHelper, UserAndRoleService userAndRoleService) {
        this.userRoleDao = userRoleDao;
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
            } else if (insertMerchant.getCode() == EntityResult.OPERATION_SUCCESSFUL) {
                userIdMap.put(UserRoleDao.ROL_ID, 5);
                this.daoHelper.insert(this.userRoleDao, userIdMap);
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

    /**
     * Returns merchant id from logged user
     *
     * @return merchant_id
     */
    public int getMerchantId() {
        Object merchant = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        int userId = (int) ((UserInformation) merchant).getOtherData().get(UserDao.USR_ID);


        List<String> qKeys = new ArrayList<String>();
        qKeys.add(MerchantDao.MERCHANT_ID);

        Map<String, Object> emptyMap = new HashMap<>();
        emptyMap.put("M." + UserDao.USR_ID, userId);


        EntityResult merchantEr = merchantQuery(emptyMap, qKeys);
        ArrayList<Integer> al = (ArrayList<Integer>) merchantEr.get(MerchantDao.MERCHANT_ID);
        return al.get(0);
    }

}
