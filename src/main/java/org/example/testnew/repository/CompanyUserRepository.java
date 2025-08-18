package org.example.testnew.repository;

import org.example.testnew.entity.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long> {

    CompanyUser findByLogin(String login);

}
