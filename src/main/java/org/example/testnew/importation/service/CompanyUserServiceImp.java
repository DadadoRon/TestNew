package org.example.testnew.importation.service;


import org.example.testnew.entity.CompanyUser;
import org.example.testnew.repository.CompanyUserRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyUserServiceImp implements CompanyUserService {
    private final CompanyUserRepository companyUserRepository;

    public CompanyUserServiceImp(CompanyUserRepository companyUserRepository) {
        this.companyUserRepository = companyUserRepository;
    }

    public Long findOrCreateUserIdByLogin(String login) {
        CompanyUser existingUser = companyUserRepository.findByLogin(login);
        if (existingUser != null) {
            return existingUser.getId();
        } else {
            CompanyUser newUser = new CompanyUser();
            newUser.setLogin(login);
            companyUserRepository.save(newUser);
            return newUser.getId();
        }
    }
}
