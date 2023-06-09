package kz.job4j;

public final class DCLSingleton {
    private static volatile DCLSingleton inst;

    private DCLSingleton() {
    }

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    public static void main(String[] args) {
        DCLSingleton dclSingleton = DCLSingleton.instOf();
        System.out.println(dclSingleton);
        DCLSingleton dclSingleton1 = DCLSingleton.instOf();
        System.out.println(dclSingleton1);
    }
}
