package com.example.bookstore.service;

import com.example.bookstore.entities.Library;
import com.example.bookstore.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public Library createLibrary(Library library) {
        return libraryRepository.save(library);
    }

    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    public Library getLibraryById(Long id) {
        return libraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + id));
    }

    public Library updateLibrary(Long id, Library libraryDetails) {
        Library library = getLibraryById(id);
        library.setName(libraryDetails.getName());
        library.setAddress(libraryDetails.getAddress());
        library.setCity(libraryDetails.getCity());
        library.setPhoneNumber(libraryDetails.getPhoneNumber());
        return libraryRepository.save(library);
    }

    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
    }
}
