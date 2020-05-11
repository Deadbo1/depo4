package application.controller;

import application.models.Status;
import application.service.impl.ServiceForStatusImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerForStatus {
    private final ServiceForStatusImpl serviceForStatusImpl;

    public ControllerForStatus (ServiceForStatusImpl serviceForStatusImpl) {
        this.serviceForStatusImpl = serviceForStatusImpl;
    }

    @GetMapping("/status")
    public List<Status> getAllStatus() {
        return serviceForStatusImpl.findAll();
    }

    @GetMapping("/status/{id}")
    public Status getStatusById(@PathVariable("id") Long id) {
        return serviceForStatusImpl.getStatusById(id);
    }

    @PostMapping("/status")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStatus(@RequestBody Status status) {
        serviceForStatusImpl.createStatus(status);
    }

    @PutMapping("/status/{id}")
    public void updateStatus(@PathVariable("id") Long id, @RequestBody Status status) {
        serviceForStatusImpl.updateStatus(id,status);
    }

    @DeleteMapping("/status/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStatus(@PathVariable("id") Long id) {
        serviceForStatusImpl.deleteStatus(getStatusById(id));
    }
}
