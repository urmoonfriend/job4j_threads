package kz.job4j.cash;

public class Main {
    public static void main(String[] args) {
        AccountStorage accountStorage = new AccountStorage();
        Account p1 = new Account(1, 200);
        Account p2 = new Account(2, 100);
        accountStorage.add(p1);
        accountStorage.add(p2);
        boolean result = accountStorage.transfer(p1.id(), p2.id(), 150);

        accountStorage.getAll().values().stream()
                .forEach(account -> {
                    System.out.println(account);
                });
    }
}
