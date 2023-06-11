package kz.job4j.cash;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public final class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        AtomicBoolean result = new AtomicBoolean(false);
        getById(account.id()).ifPresentOrElse(
                accountAlreadyExist -> {
                    result.set(false);
                },
                () -> {
                    accounts.put(account.id(), account);
                    result.set(true);
                }
        );
        return result.get();
    }

    public synchronized boolean update(Account account) {
        AtomicBoolean result = new AtomicBoolean(false);
        getById(account.id()).ifPresentOrElse(
                accountToUpdate -> {
                    accounts.put(account.id(), account);
                    result.set(true);
                },
                () -> {
                    result.set(false);
                }
        );
        return result.get();
    }

    public synchronized boolean delete(int id) {
        AtomicBoolean result = new AtomicBoolean(false);
        getById(id).ifPresentOrElse(
                accountToDelete -> {
                    accounts.remove(id);
                    result.set(true);
                },
                () -> {
                    result.set(false);
                }
        );
        return result.get();
    }

    public synchronized Optional<Account> getById(int id) {
        if (accounts.containsKey(id)) {
            return Optional.of(accounts.get(id));
        }
        return Optional.empty();
    }

    public synchronized Map<Integer, Account> getAll() {
        return accounts;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        AtomicBoolean result = new AtomicBoolean(false);
        getById(fromId).ifPresentOrElse(
                accountFrom -> {
                    getById(toId).ifPresentOrElse(
                            accountTo -> {
                                result.set(doTransfer(accountFrom, accountTo, amount));
                            },
                            () -> {
                                System.out.println("Account not found: " + toId);
                                result.set(false);
                            }
                    );
                },
                () -> {
                    System.out.println("Account not found: " + fromId);
                    result.set(false);
                }
        );
        return result.get();
    }

    public synchronized boolean doTransfer(Account accountFrom, Account accountTo, int amount) {
        AtomicBoolean result = new AtomicBoolean(false);
        if (amount >= 0 && accountFrom.amount() >= amount) {
            Account accountFromUpdated = new Account(accountFrom.id(), accountFrom.amount() - amount);
            Account accountToUpdated =  new Account(accountTo.id(), accountTo.amount() + amount);
            update(accountFromUpdated);
            update(accountToUpdated);
            result.set(true);
        } else {
            System.out.println("Incorrect amount: " + amount);
            result.set(false);
        }
        return result.get();
    }
}
