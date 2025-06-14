package htw_berlin.webtech.repository;


import htw_berlin.webtech.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUserId(Long userId);
    Optional<Admin> findByAdminCode(String adminCode);
}
