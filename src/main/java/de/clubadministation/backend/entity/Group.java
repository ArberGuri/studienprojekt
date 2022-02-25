package de.clubadministation.backend.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_group")
public class Group {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    private String type;

    private String street;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

   @OneToMany(mappedBy = "group")
    private List<Member> members = new LinkedList<>();

   @Formula("(select count(m.id) from Member m where m.group_id = id)")
   private int memberCount;

    public int getMemberCount(){
        return memberCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Group group = (Group) o;
        return id != null && Objects.equals(id, group.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public List<Member> getMembers() {
        return members;
    }
}
