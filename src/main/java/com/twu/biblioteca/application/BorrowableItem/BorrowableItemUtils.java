package com.twu.biblioteca.application.BorrowableItem;

import com.twu.biblioteca.application.ApplicationIO;
import com.twu.biblioteca.domain.borrowable.BorrowableItem;

import java.util.Set;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;

public final class BorrowableItemUtils {

    public static void listBorrowableItems(Set<BorrowableItem> borrowableItems, ApplicationIO applicationIO) {
        if(borrowableItems.isEmpty())
            applicationIO.print("There are no items to show." + LINE_BREAK);
        borrowableItems.forEach(item -> applicationIO.print(item + LINE_BREAK));
    }
}