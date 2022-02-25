package de.clubadministation.backend.service;

import de.clubadministation.backend.entity.Group;
import de.clubadministation.backend.entity.Location;
import de.clubadministation.backend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService implements CrudListener<Location> {

    private final LocationRepository repository;

    @Override
    public Collection<Location> findAll() {
        return repository.findAll();
    }

    @Override
    public Location add(Location location) {
        return repository.save(location);
    }

    @Override
    public Location update(Location location) {
        return repository.save(location);
    }

    @Override
    public void delete(Location location) {
        repository.delete(location);
    }

    public List<Location> findByCityName(String searchTerm) {
        return repository.findByCityName(searchTerm);
    }
}
