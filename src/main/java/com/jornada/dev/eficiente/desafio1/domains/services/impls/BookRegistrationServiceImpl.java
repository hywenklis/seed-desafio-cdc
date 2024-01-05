package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.BookEntity;
import com.jornada.dev.eficiente.desafio1.domains.exceptions.NotFoundException;
import com.jornada.dev.eficiente.desafio1.domains.mappers.BookDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.BookRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.AuthorFindService;
import com.jornada.dev.eficiente.desafio1.domains.services.BookRegistrationService;
import com.jornada.dev.eficiente.desafio1.domains.services.CategoryFindService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookRegistrationServiceImpl implements BookRegistrationService {

    private final BookRepository bookRepository;
    private final BookDomainMapper bookMapper;
    private final AuthorFindService authorFindService;
    private final CategoryFindService categoryFindService;

    @Override
    @Transactional
    public BookDto save(BookDto bookDto) {

        AuthorDto author = authorFindService.findAuthorByEmail(bookDto.author().email())
            .orElseThrow(() -> new NotFoundException(
                "authorEmail",
                "Author not found with email: " + bookDto.author().email()
            ));

        CategoryDto category = categoryFindService.findCategoryByName(bookDto.category().name())
            .orElseThrow(() -> new NotFoundException(
                "categoryName",
                "Category not found with name: " + bookDto.category().name()
            ));

        bookDto.update(author);
        bookDto.update(category);

        BookEntity savedBook = bookRepository.save(bookMapper.mapToEntity(bookDto));
        return bookMapper.mapToDto(savedBook);
    }
}
