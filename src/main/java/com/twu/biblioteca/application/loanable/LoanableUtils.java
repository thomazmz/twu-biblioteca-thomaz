package com.twu.biblioteca.application.loanable;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.loanable.Loanable;
import com.twu.biblioteca.domain.loanable.LoanableService;

import java.util.Set;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;

public final class LoanableUtils {

    public static void listLoanables(Set<Loanable> loanables, ApplicationIO applicationIO) {
        if(loanables.isEmpty())
            applicationIO.print("There are no items to list." + LINE_BREAK);
        loanables.forEach(loanable -> applicationIO.print(loanable + LINE_BREAK));
    }
}