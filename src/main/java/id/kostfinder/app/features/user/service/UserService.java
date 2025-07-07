package id.kostfinder.app.features.user.service;

import id.kostfinder.app.response.GenericResponse;
import id.kostfinder.app.features.user.dto.request.CreateEndUserRequestDTO;
import id.kostfinder.app.features.user.model.User;
import org.springframework.data.domain.Pageable;

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
