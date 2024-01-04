package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.entities.BookEntity;
import com.jornada.dev.eficiente.desafio1.domains.exceptions.NotFoundException;
import com.jornada.dev.eficiente.desafio1.domains.mappers.AuthorDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.mappers.BookDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.mappers.CategoryDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.repositories.AuthorRepository;
import com.jornada.dev.eficiente.desafio1.domains.repositories.BookRepository;
import com.jornada.dev.eficiente.desafio1.domains.repositories.CategoryRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.BookRegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookRegistrationServiceImpl implements BookRegistrationService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookDomainMapper bookMapper;
    private final AuthorDomainMapper authorMapper;
    private final CategoryDomainMapper categoryMapper;

    @Override
    @Transactional
    public BookDto save(BookDto bookDto) {

        AuthorDto authorEntity = getAuthor(bookDto);
        CategoryDto categoryEntity = getCategory(bookDto);

        bookDto.update(authorEntity);
        bookDto.update(categoryEntity);

        BookEntity savedBook = bookRepository.save(bookMapper.mapToEntity(bookDto));
        return bookMapper.mapToDto(savedBook);
    }

    private CategoryDto getCategory(BookDto bookDto) {
        return categoryMapper.mapToDto(categoryRepository.findByName(bookDto.category().name())
            .orElseThrow(
                () -> new NotFoundException(
                    "categoryName",
                    "Category not found with name: " + bookDto.category().name())));
    }

    private AuthorDto getAuthor(BookDto bookDto) {
        return authorMapper.mapToDto(authorRepository.findByEmail(bookDto.author().email())
            .orElseThrow(
                () -> new NotFoundException(
                    "authorEmail",
                    "Author not found with email: " + bookDto.author().email())));
    }
}
