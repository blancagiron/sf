package es.blanca.springboot_fundamentals.infrastructure.api.exception;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomError {
	private LocalDate timestamp;
	private int httpCode;
	private String message;
}
