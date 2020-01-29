package com.twu.biblioteca.application.loanable;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.borrowable.BorrowableItem;

import java.util.Set;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;

public final class BorrowableUtils {

    public static void listBorrowableItems(Set<BorrowableItem> items, ApplicationIO applicationIO) {
        if(items.isEmpty())
            applicationIO.print("There are no items to list." + LINE_BREAK);
        items.forEach(item -> applicationIO.print(item + LINE_BREAK));
    }
}