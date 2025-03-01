package com.traini8.traini8.advice;

import com.traini8.traini8.dtos.ErrorDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.View;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {

    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleInvalidInput(IllegalArgumentException ex){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorMessage(ex.getMessage());
        errorDto.setErrorCode("400");
        return new ResponseEntity<>(errorDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDto>> MethodArgumentNotValid(MethodArgumentNotValidException ex){
        List<ErrorDto> errorDtos = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            ErrorDto errorDto = new ErrorDto();
            errorDto.setErrorMessage(
                    error.getDefaultMessage()
            );
            errorDto.setErrorCode(error.getCode());
            errorDtos.add(errorDto);
        });
        return new ResponseEntity<>(errorDtos, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerException(NullPointerException ex){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorMessage(ex.getMessage());
        errorDto.setErrorCode("400");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
