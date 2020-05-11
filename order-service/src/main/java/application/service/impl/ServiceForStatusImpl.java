package application.service.impl;

import application.models.Status;
import application.repository.StatusDao;
import application.service.StatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceForStatusImpl implements StatusService{
    private final StatusDao statusDao;

    public ServiceForStatusImpl (StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    public Status getCheckStatus(Status status) {
        List<Status> statuses = statusDao.findAll();
        for (Status status1 : statuses) {
            if (status.equals(status1)) {
                return status1;
            }
        }
        return null;
    }

    @Override
    public boolean checkStatus(Status status) {
        List<Status> statuses = statusDao.findAll();
        for (Status status1 : statuses) {
            if (status.equals(status1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateStatus(Long id,Status status) {
        Status status1 = getStatusById(id);
        status1.setName(status.getName());
        statusDao.save(status1);
    }

    @Override
    public void deleteStatus(Status status) {
        statusDao.delete(status);
    }

    @Override
    public Status getStatusById(Long id) {
        return statusDao.getStatusById(id);
    }

    @Override
    public void createStatus(Status status) {
        statusDao.save(status);
    }

    @Override
    public List<Status> findAll() {
        return statusDao.findAll();
    }
}
