package org.hbrs.se1.ws24.exercises.uebung2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Container {
    private List<Member> members;

    public Container() {
        members = new ArrayList<>();
    }

    // FA1: Member hinzufügen
    public void addMember(Member member) throws ContainerException {
        for (Member m : members) {
            if (Objects.equals(m.getID(), member.getID())) {
                throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
            }
        }
        members.add(member);
    }

    // FA2: Member löschen
    public String deleteMember(int id) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getID().equals(id)) {
                members.remove(i);
                return "Member mit ID " + id + " wurde gelöscht.";
            }
        }
        return "Kein Member mit der ID " + id + " gefunden.";
    }

    // FA3: Mitglieder ausgeben
    public void dump() {
        for (Member member : members) {
            System.out.println(member.toString());
        }
    }

    // FA4: Anzahl der Mitglieder
    public int size() {
        return members.size();
    }
}
