package id.kostfinder.app.user.service;

import id.kostfinder.app.response.GenericResponse;
import id.kostfinder.app.response.Response;
import id.kostfinder.app.user.dto.request.CreateEndUserRequestDTO;
import id.kostfinder.app.user.model.EndUser;
import id.kostfinder.app.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    public void generateDummyDataEndUser(int amount);

    public User findByEmail(String email);
    public void generateDummyDataPropertyOwner();
    public GenericResponse getUsers(Pageable pageable);
    public GenericResponse createEndUser(CreateEndUserRequestDTO dto);
    public GenericResponse getUser(Long id);
    public GenericResponse deleteUser(Long id);
    public GenericResponse getTotalUsers();
}
