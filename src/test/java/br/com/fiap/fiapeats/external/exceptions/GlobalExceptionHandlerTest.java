package br.com.fiap.fiapeats.external.exceptions;

import br.com.fiap.fiapeats.external.api.contracts.response.ErroResponse;
import br.com.fiap.fiapeats.external.api.exceptions.GlobalExceptionHandler;
import br.com.fiap.fiapeats.external.api.exceptions.RestErrorResponse;
import br.com.fiap.fiapeats.usecases.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleNotFoundException() {
        NotFoundException exception = new NotFoundException("Recurso não encontrado");

        ResponseEntity<Object> response = globalExceptionHandler.handle(exception);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        ErroResponse erroResponse = (ErroResponse) response.getBody();
        assertNotNull(erroResponse);
        assertEquals(erroResponse.getMensagem(), "Recurso não encontrado");
    }

    @Test
    void handleValidationExceptions() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(
                new FieldError("objectName", "fieldName", "defaultMessage")
        ));

        RestErrorResponse response = globalExceptionHandler.handleValidationExceptions(exception);

        assertEquals(response.getMessage(), "A validação do payload retornou erro(s)");
        assertThat(response.getErrors()).containsExactly("fieldName: defaultMessage");
    }
}
