package com.algaworks.devcontainers.examples.meteorology.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

/*
* Ponto único para tratamento de exceções de forma global.
*
* ResponseEntityExceptionHandler = estende essa classe que por padrão
* já trata as mensagens de erro de acordo com a RFC 7807.
* Ref.: https://www.rfc-editor.org/rfc/rfc7807
*/

@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MSG_ERROR_GENERIC_FINAL_USER =
            "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o "
                    + "problema persistir, entre em contato com o administrador do sistema.";

    // Para ter acesso ao recurso de mensagens personalizadas de validação (messages.properties).
    private final MessageSource messageSource;

    /*
    * Método para personalizar a resposta de acordo com a RFC 7807,
    * para a exceção de MethodArgumentNotValidException.
    */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        problemDetail.setType(URI.create("https://algadevcontainers.com/erros/campos-invalidos"));

        // Extrai da exceção um mapa com os campos associados a suas respectivas mensagens de erro.
        // Com MessageSource: Recupera a mensagem específica de acordo com o code do objeto de erro.
        Map<String, String> fields = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                        //DefaultMessageSourceResolvable::getDefaultMessage));
                        objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));

        // Cria uma propriedade personalizada no corpo do detalhamento de erro
        // passando o mapeamento dos campos e mensagens de erro.
        problemDetail.setProperty("fields", fields);

        // Deve repassar uma instância de ProblemDetail (do pacote org.springframework.http)
        // para que a alteração tenha efeito.
        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = MSG_ERROR_GENERIC_FINAL_USER;

        // Para fins de desenvolvimento
        //ex.printStackTrace();
        log.error(ex.getMessage(), ex);

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(detail);
        problemDetail.setType(URI.create("https://algadevcontainers.com/erros/erro-de-sistema"));

        return handleExceptionInternal(ex, problemDetail, null, status, request);
    }
}
