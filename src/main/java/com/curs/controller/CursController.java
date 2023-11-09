package com.curs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curs.entities.Cursos;
import com.curs.services.CursServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Cursos", description = "API REST DE GERENCIAMENTO DE CURSOS")
@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "*")
public class CursController {
	
	private final CursServico cursServico;
	
	@Autowired
	public CursController(CursServico cursServico) {
		this.cursServico = cursServico;
	}
	@GetMapping("/{id}")
	@Operation (summary = "Localiza curso por ID")
	public ResponseEntity<Cursos> buscaCursosControlId(@PathVariable Long id){
		Cursos cursos = cursServico.buscaCursosId(id);
		if(cursos != null) {
			return ResponseEntity.ok(cursos);
		}
		else{
			return ResponseEntity.notFound().build();
	
		}
	}
	@GetMapping
	@Operation (summary = "Apresenta todos os cursos")
	public ResponseEntity<List<Cursos>> buscaTodosCursossControl(){
		List<Cursos> cursos = cursServico.buscaTodosCursoss();
		return ResponseEntity.ok(cursos);
	}
	@PostMapping
	@Operation (summary = "Cadastra um curso")
	public ResponseEntity<Cursos> salvaCursosControl (@RequestBody @Valid Cursos cursos){
		Cursos salvaCursos = cursServico.salvaCursos(cursos);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCursos);
	}
	@PutMapping("/{id}")
	@Operation (summary = "Altera curso")
	public ResponseEntity<Cursos> alteraCursosControl (@PathVariable Long id, @RequestBody @Valid Cursos cursos){
		Cursos alteraCursos = cursServico.alterarCursos(id, cursos);
		if(alteraCursos != null) {
			return ResponseEntity.ok(cursos);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Exclui curso")
	public ResponseEntity<Cursos> apagaCursosControl (@PathVariable Long id){
		boolean apagar = cursServico.apagarCursos(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}

