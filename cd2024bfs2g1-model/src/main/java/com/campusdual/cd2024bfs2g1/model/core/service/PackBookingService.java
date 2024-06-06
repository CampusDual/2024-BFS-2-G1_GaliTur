package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IPackBookingService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ClientDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.PackBookingDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackDateDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackStateDao;
import com.campusdual.cd2024bfs2g1.model.core.service.pack.PackDateService;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEEException;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.services.mail.IMailService;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("PackBookingService")
public class PackBookingService implements IPackBookingService {

    private DefaultOntimizeDaoHelper daoHelper;

    private PackBookingDao packBookingDao;

    private PackDateService packDateService;

    private ClientService clientService;

    private IMailService mailService;

    @Autowired
    public PackBookingService(DefaultOntimizeDaoHelper daoHelper, PackBookingDao packBookingDao,
                              PackDateService packDateService, ClientService clientService, IMailService mailService) {
        this.daoHelper = daoHelper;
        this.packBookingDao = packBookingDao;
        this.packDateService = packDateService;
        this.clientService = clientService;
        this.mailService = mailService;
    }

    @Override
    public EntityResult packBookingQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packBookingDao, keysValues, attributes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EntityResult packBookingInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        EntityResult erUpdatePackDate = this.packDateService.packDateUpdate(
                Map.of(PackDateDao.PCS_ID, 2),
                Map.of(PackDateDao.PD_ID, keysValues.get(PackDateDao.PD_ID))
        );
        if (erUpdatePackDate.getCode() != EntityResult.OPERATION_SUCCESSFUL) return erUpdatePackDate;

        Integer usrId = (Integer) keysValues.remove(ClientDao.USR_ID);
        String usrEmail = (String) keysValues.remove(UserDao.EMAIL);
        EntityResult erClientSingleQuery = this.clientService.clientSingleQuery(Map.of(ClientDao.USR_ID, usrId), List.of(ClientDao.CLIENT_ID));
        if (erClientSingleQuery.getCode() != EntityResult.OPERATION_SUCCESSFUL) return erClientSingleQuery;

        keysValues.put(PackBookingDao.CLIENT_ID, ((ArrayList<?>) erClientSingleQuery.get(ClientDao.CLIENT_ID)).get(0));
        EntityResult erInsertPackBooking = this.daoHelper.insert(this.packBookingDao, keysValues);
        if (erInsertPackBooking.getCode() == EntityResult.OPERATION_SUCCESSFUL) sendBookingEmail(usrEmail);
        return erInsertPackBooking;
    }

    @Override
    public EntityResult packBookingUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.packBookingDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult packBookingDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {

        EntityResult packDate = this.packBookingQuery(keysValues, List.of("pd_id"));
        this.daoHelper.delete(this.packBookingDao, keysValues);

        Map<String, Object> mapaPackDelete = new HashMap<>();
        mapaPackDelete.put(PackStateDao.PCS_ID, 1);

        return this.packDateService.packDateUpdate(mapaPackDelete, packDate.getRecordValues(0));

    }

    private void sendBookingEmail(String receiver) {
        sendEmail(receiver, "GaliTur: Booking confirmation", ".."); // TODO: Change to real body
    }

    private void sendBookingCancelEmail(String receiver) {
        sendEmail(receiver, "GaliTur: Booking cancel confirmation", "...");  // TODO: Change to real body
    }

    private void sendEmail(String receiver, String subject, String body) {

        try {
            this.mailService.sendMailWithoutAttach("my.mail@example.com", List.of(receiver), subject,
                    body);
        } catch (OntimizeJEEException e) { }
    }
}
