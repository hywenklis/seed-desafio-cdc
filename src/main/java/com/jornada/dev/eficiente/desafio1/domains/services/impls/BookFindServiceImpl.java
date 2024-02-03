package com.jornada.dev.eficiente.desafio1.domains.services.impls;

import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDetailsDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.BookDto;
import com.jornada.dev.eficiente.desafio1.domains.dtos.SocialMediaDto;
import com.jornada.dev.eficiente.desafio1.domains.mappers.BookDomainMapper;
import com.jornada.dev.eficiente.desafio1.domains.properties.SocialMediaProperty;
import com.jornada.dev.eficiente.desafio1.domains.repositories.BookRepository;
import com.jornada.dev.eficiente.desafio1.domains.services.BookFindService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookFindServiceImpl implements BookFindService {

    private final BookRepository bookRepository;
    private final BookDomainMapper bookMapper;
    private final SocialMediaProperty socialMediaProperty;

    @Override
    public Optional<BookDto> findBookByTitle(String title) {
        return bookRepository.findByTitle(title).map(bookMapper::mapToDto);
    }

    @Override
    public Optional<BookDto> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).map(bookMapper::mapToDto);
    }

    @Override
    public Optional<List<BookDto>> findAll() {
        return Optional.of(bookRepository.findAll().stream().map(bookMapper::mapToDto).toList());
    }

    @Override
    public Optional<BookDto> findBookDetails(UUID id) {
        return bookRepository.findById(id)
            .map(bookEntity -> {
                BookDto bookDto = bookMapper.mapToDto(bookEntity);
                return addBookDetails(bookDto);
            });
    }

    private BookDto addBookDetails(BookDto bookDto) {
        SocialMediaDto socialMediaDto = createSocialMediaDto(bookDto);
        BigDecimal ebookAndPrintedBookPrice = calculateTotalPrice(bookDto);
        BookDetailsDto bookDetailsDto =
            new BookDetailsDto("Você terá acesso às futuras atualizações do livro",
                ebookAndPrintedBookPrice,
                socialMediaDto
            );
        return bookDto.toBuilder().bookDetails(bookDetailsDto).build();
    }

    private SocialMediaDto createSocialMediaDto(BookDto bookDto) {
        return SocialMediaDto.fromConfig(socialMediaProperty, bookDto);
    }

    private BigDecimal calculateTotalPrice(BookDto bookDto) {
        return bookDto.ebookPrice().add(bookDto.printedBookPrice());
    }
}

