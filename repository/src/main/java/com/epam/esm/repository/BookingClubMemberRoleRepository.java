package com.epam.esm.repository;

import com.epam.esm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingClubMemberRoleRepository extends JpaRepository<Role, Long> {

}
