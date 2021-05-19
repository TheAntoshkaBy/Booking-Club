package com.epam.esm.repository;

import com.epam.esm.entity.Role;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Override
    Optional<Role> findById(Long aLong);
}
