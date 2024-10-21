package org.hbrs.se1.ws24.exercises.uebung3.persistence;

public class Main {
    public static void main(String[] args) {
        Container.getInstance().setStrategy(new PersistenceStrategyStream<Member>());
        Client client = new Client();
        client.readOut();
    }
}
