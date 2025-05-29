package id.kostfinder.app.user.service;

import id.kostfinder.app.response.GenericResponse;
import id.kostfinder.app.response.Response;
import id.kostfinder.app.user.dto.request.CreateEndUserRequestDTO;
import id.kostfinder.app.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    public void generateDummyDataEndUser(int amount);

    public void generateDummyDataPropertyOwner();
    public GenericResponse getUsers();
    public GenericResponse createEndUser(CreateEndUserRequestDTO dto);


}
