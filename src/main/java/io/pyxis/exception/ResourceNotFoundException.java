package io.pyxis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource with this parameters not found")
public class ResourceNotFoundException extends RuntimeException {
}
