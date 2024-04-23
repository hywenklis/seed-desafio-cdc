package com.jornada.dev.eficiente.desafio1.domains.book.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.author.dtos.AuthorDto;
import com.jornada.dev.eficiente.desafio1.domains.author.services.AuthorFindService;
import com.jornada.dev.eficiente.desafio1.domains.book.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.book.entities.BookEntity;
import com.jornada.dev.eficiente.desafio1.domains.book.mappers.BookDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.book.repositories.BookRepository;
import com.jornada.dev.eficiente.desafio1.domains.book.services.BookRegistrationService;
import com.jornada.dev.eficiente.desafio1.domains.category.dtos.CategoryDto;
import com.jornada.dev.eficiente.desafio1.domains.category.services.CategoryFindService;
import com.jornada.dev.eficiente.desafio1.domains.exceptions.NotFoundException;
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
        AuthorDto author = findAuthor(bookDto);
        CategoryDto category = findCategory(bookDto);
        BookEntity bookEntity = createBookEntity(bookDto, author, category);
        BookEntity savedBook = bookRepository.save(bookEntity);

        return bookMapper.mapToDto(savedBook);
    }

    private CategoryDto findCategory(BookDto bookDto) {
        return categoryFindService.findCategoryByName(bookDto.category().name())
            .orElseThrow(() -> new NotFoundException(
                "categoryName",
                "Category not found with name: " + bookDto.category().name()
            ));
    }

    private AuthorDto findAuthor(BookDto bookDto) {
        return authorFindService.findAuthorByEmail(bookDto.author().email())
            .orElseThrow(() -> new NotFoundException(
                "authorEmail",
                "Author not found with email: " + bookDto.author().email()
            ));
    }

    private BookEntity createBookEntity(BookDto bookDto, AuthorDto author, CategoryDto category) {
        return bookMapper.mapToEntity(bookDto.update(author, category));
    }
}
