package atividade.gestao.dto;

import atividade.gestao.entities.Participante;

public class ParticipanteDTO {
	
	 private Long id;
	 private String nome;
	 private String email;
	 private Integer telefone;
	
	
	public ParticipanteDTO() {
	}


	public ParticipanteDTO(Long id, String nome, String email, Integer telefone) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public ParticipanteDTO(Participante participante) {
		this.id = participante.getId();
		this.nome = participante.getNome();
		this.email = participante.getEmail();
		this.telefone = participante.getTelefone();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getTelefone() {
		return telefone;
	}


	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
	
	

}
