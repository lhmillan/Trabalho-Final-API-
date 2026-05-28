package br.com.serratec.trabfinal_api.errors;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleRegistroNaoEncontrado(RegistroNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(CepInvalidoException.class)
    public ResponseEntity<ErroResponse> handleCepInvalido(CepInvalidoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(BancoDeDadosException.class)
    public ResponseEntity<ErroResponse> handleBancoError(BancoDeDadosException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErroResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidationErrors(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult()
                      .getFieldErrors()
                      .stream()
                      .map(err -> err.getField() + ": " + err.getDefaultMessage())
                      .findFirst()
                      .orElse("Erro de validação");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), msg));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGeneric(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErroResponse(LocalDateTime.now(), 500, "Ocorreu um erro inesperado"));
    }

    @ExceptionHandler(PecaException.class)
    public ResponseEntity<ErroResponse> handlePecaException(PecaException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}