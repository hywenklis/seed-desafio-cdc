package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.mappers.BookDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.BookRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.BookFindService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookFindServiceImpls implements BookFindService {

    private final BookRepository bookRepository;
    private final BookDomainMapper bookMapper;

    @Override
    public Optional<BookDto> findBookByTitle(String title) {
        return bookRepository.findByTitle(title).map(bookMapper::mapToDto);
    }

    @Override
    public Optional<BookDto> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).map(bookMapper::mapToDto);
    }
}
