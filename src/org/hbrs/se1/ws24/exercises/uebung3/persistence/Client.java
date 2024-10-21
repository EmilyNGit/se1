package org.hbrs.se1.ws24.exercises.uebung3.persistence;


import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.solutions.uebung2.Member;

import java.util.List;

public class Client {

    public void readOut() {
        Container container = Container.getInstance();
        for(int i = 0; i < 6; i++) {
            ConcreteMember member1 = new ConcreteMember(i);
        }
        List<Member> member = container.getCurrentList();
        MemberView.dump(member);

    }

}
