package kz.job4j.cash;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public final class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized Map<Integer, Account> getAll() {
        return new HashMap<>(accounts);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        var accountFromOpt = getById(fromId);
        var accountToOpt = getById(toId);
        if (accountFromOpt.isPresent() && accountToOpt.isPresent()) {
            if (amount >= 0 && accountFromOpt.get().amount() >= amount) {
                update(new Account(accountFromOpt.get().id(), accountFromOpt.get().amount() - amount));
                update(new Account(accountToOpt.get().id(), accountToOpt.get().amount() + amount));
                return true;
            }
            return false;
        }
        return false;
    }

}
