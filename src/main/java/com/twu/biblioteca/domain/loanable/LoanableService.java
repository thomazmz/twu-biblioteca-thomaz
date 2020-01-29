package com.twu.biblioteca.domain.loanable;

import com.twu.biblioteca.domain.UnavailableResourceException;
import com.twu.biblioteca.domain.UnregisteredEntityIdException;

import java.util.Set;


public class LoanableService {

    private LoanableRepository<Loanable> loanableRepository;

    public LoanableService(LoanableRepository loanableRepository) {
        this.loanableRepository = loanableRepository;
    }

    public Loanable getById(Long id) throws UnregisteredEntityIdException {
        return this.loanableRepository.getById(id);
    }

    public Set<Loanable> getAll() {
        return this.loanableRepository.getAll();
    }

    public Set<Loanable> getAvailables() {
        return this.loanableRepository.getAvailables();
    }

    public Set<Loanable> getUnavailables() {
        return this.loanableRepository.getUnavailables();
    }

    public void checkOut(Long id) throws UnregisteredEntityIdException, UnavailableResourceException {
        Loanable loanable = this.loanableRepository.getById(id);

        if(!loanable.isAvailable())
            throw new UnavailableResourceException();

        loanable.checkOut();
    }

    public void checkIn(Long id) throws UnregisteredEntityIdException, UnavailableResourceException {
        Loanable loanable = this.loanableRepository.getById(id);

        if(loanable.isAvailable())
            throw new UnavailableResourceException();

        loanable.checkIn();
    }
}