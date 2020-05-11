package application.service;

import application.models.Status;

import java.util.List;

public interface StatusService {

    Status getCheckStatus(Status status);

    boolean checkStatus(Status status);

    void updateStatus(Long id,Status status);

    void deleteStatus(Status status);

    Status getStatusById(Long id);

    void createStatus(Status status);

    List<Status> findAll();
}
