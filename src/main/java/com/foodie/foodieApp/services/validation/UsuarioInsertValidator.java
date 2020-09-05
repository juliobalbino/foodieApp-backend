package com.foodie.foodieApp.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.foodie.foodieApp.dto.UsuarioNewDTO;
import com.foodie.foodieApp.entities.Usuario;
import com.foodie.foodieApp.repositories.UsuarioRepository;
import com.foodie.foodieApp.resources.exception.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		Usuario aux = usuarioRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
