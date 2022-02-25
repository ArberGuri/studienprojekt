package de.clubadministation.backend.service;

import de.clubadministation.backend.entity.Group;
import de.clubadministation.backend.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService implements CrudListener<Group> {

    private final GroupRepository groupRepository;

    @Override
    public Collection<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group add(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group update(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void delete(Group group) {
        groupRepository.delete(group);
    }

    public List<Group> findByGroupName(String searchTerm) {
        return groupRepository.findByGroupName(searchTerm);
    }

    public long countGroups() {
        return groupRepository.count();
    }
}
