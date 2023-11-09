package com.curs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curs.entities.Cursos;
import com.curs.repository.CursRepository;

@Service
public class CursServico {
	
	private final CursRepository cursRepository;
	
	@Autowired
	public CursServico (CursRepository cursRepository) {
		this.cursRepository = cursRepository;
	}
	public List<Cursos> buscaTodosCursoss(){
		return cursRepository.findAll();
	}
	public Cursos buscaCursosId(Long id) {
		Optional <Cursos> Cursos = cursRepository.findById(id);
		return Cursos.orElse(null);
	}
	public Cursos alterarCursos (Long id, Cursos alterarCursos) {
		Optional <Cursos> existeCursos = cursRepository.findById(id);
		if(existeCursos.isPresent()) {
			alterarCursos.setId(id);
			return cursRepository.save(alterarCursos);
		}
		return null;
	}
	public boolean apagarCursos(Long id) {
		Optional <Cursos> existeCursos = cursRepository.findById(id);
		if(existeCursos.isPresent()) {
		cursRepository.deleteById(id);
		return true;
	}
	return false;
	}
	public Cursos salvaCursos(Cursos cursos) {
		return cursRepository.save(cursos);
	}
}

