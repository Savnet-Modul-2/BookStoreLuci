package com.example.bookstore.mapper;

import com.example.bookstore.dto.LibrarianDTO;
import com.example.bookstore.entities.Librarian;

public class LibrarianMapper {
    public static Librarian librarianDTO2Librarian(LibrarianDTO librarianDTO) {
        Librarian librarian = new Librarian();
        librarian.setName(librarianDTO.getName());
        librarian.setEmail(librarianDTO.getEmail());
        librarian.setPassword(librarianDTO.getPassword());
        if (librarianDTO.getLibrary() != null) {
            librarian.setLibrary(LibraryMapper.libraryDTO2Library(librarianDTO.getLibrary()));
        }
        return librarian;
    }

    public static LibrarianDTO librarian2LibrarianDTO(Librarian librarian) {
        LibrarianDTO librarianDTO = new LibrarianDTO();
        librarianDTO.setId(librarian.getId());
        librarianDTO.setName(librarian.getName());
        librarianDTO.setEmail(librarian.getEmail());
        librarianDTO.setPassword(librarian.getPassword());
        if (librarian.getLibrary() != null) {
            librarianDTO.setLibrary(LibraryMapper.library2LibraryDTO(librarian.getLibrary()));
        }
        return librarianDTO;
    }

    public static Librarian librarianDTO2LibrarianWithoutLibrary(LibrarianDTO librarianDTO) {
        Librarian librarian = new Librarian();
        librarian.setName(librarianDTO.getName());
        librarian.setEmail(librarianDTO.getEmail());
        librarian.setPassword(librarianDTO.getPassword());
        return librarian;
    }

    public static LibrarianDTO librarian2LibrarianDTOWithoutLibrary(Librarian librarian) {
        LibrarianDTO librarianDTO = new LibrarianDTO();
        librarianDTO.setId(librarian.getId());
        librarianDTO.setName(librarian.getName());
        librarianDTO.setEmail(librarian.getEmail());
        librarianDTO.setPassword(librarian.getPassword());
        return librarianDTO;
    }
}
