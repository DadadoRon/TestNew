package org.example.testnew.importation.service;



public interface CompanyUserService {
    Long findOrCreateUserIdByLogin(String login);
}
