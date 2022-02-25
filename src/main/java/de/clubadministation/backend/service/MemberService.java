package de.clubadministation.backend.service;

import com.vaadin.flow.component.Component;
import de.clubadministation.backend.entity.Member;
import de.clubadministation.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements CrudListener<Member> {

    private final MemberRepository memberRepository;

    @Override
    public Collection<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member add(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member update(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void delete(Member member) {
        memberRepository.delete(member);
    }

    public List<Member> findByName(String searchTerm) {
        return memberRepository.findByName(searchTerm);
    }

    public long countMembers() {
        return memberRepository.count();
    }
}
