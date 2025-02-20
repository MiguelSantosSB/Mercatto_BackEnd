import org.mercatto.mercatto_backend.dto.request.UserRequest;
import org.mercatto.mercatto_backend.dto.response.UserResponse;
import org.mercatto.mercatto_backend.factory.UserFactory;
import org.mercatto.mercatto_backend.mapper.UserMapper;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.mercatto.mercatto_backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final Map<String, UserFactory> userFactories;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, PasswordEncoder encoder, Map<String, UserFactory> userFactories) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
        this.userFactories = userFactories;
    }


    @Override
    public UserResponse create(UserRequest request) {
        String factoryKey = request.getRole() != null ? request.getRole().toLowerCase() + "UserFactory" : "regularUserFactory";
        UserFactory factory = userFactories.get(factoryKey);
        if (factory == null) {
            throw new IllegalArgumentException("Invalid user factory");
        }

        UserModel user = factory.createUser(request);
        user.setPassword(encoder.encode(request.getPassword()));
        UserModel saved = repository.save(user);

        return mapper.toResponse(saved);
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Long findIdByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
    }
}