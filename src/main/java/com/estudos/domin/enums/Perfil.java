package com.estudos.domin.enums;

public enum Perfil {

	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");

	private Integer codigo;
	private String descricao;

	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Perfil toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (Perfil perfils : Perfil.values()) {
			if (codigo.equals(perfils.getCodigo())) {
				return perfils;
			}
		}

		throw new IllegalArgumentException("Perfil inválido!");
	}
}
