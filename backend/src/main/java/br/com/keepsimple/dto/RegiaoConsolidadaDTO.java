package br.com.keepsimple.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegiaoConsolidadaDTO {
	public String regiao;
	public Double valorTotalCompra;
	public Double valorTotalGeracao;
}
