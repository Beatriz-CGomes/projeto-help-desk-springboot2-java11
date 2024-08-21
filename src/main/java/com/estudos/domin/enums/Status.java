package com.estudos.domin.enums;

public enum Status {

	ABERTO(0, "ROLE_ABERTO"), ANDAMENTO(1, "ROLE_ANDAMENTO"), ENCERRADO(2, "ROLE_ENCERRADO");

	private Integer codigo;
	private String descricao;

	private Status(Integer codigo, String descricao) {
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

	public static Status toEnumStatus(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (Status s : Status.values()) {
			if (codigo.equals(s.getCodigo())) {
				return s;
			}
		}

		throw new IllegalArgumentException("Status inexistente");
	}
}
