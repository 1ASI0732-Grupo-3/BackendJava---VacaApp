package pe.upc.vacappbackend.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.vacappbackend.iam.domain.model.aggregates.User;
import pe.upc.vacappbackend.iam.domain.model.queries.GetUserByIdQuery;
import pe.upc.vacappbackend.iam.domain.services.UserQueryService;
import pe.upc.vacappbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query){
        return userRepository.findById(query.userId());
    }


}
