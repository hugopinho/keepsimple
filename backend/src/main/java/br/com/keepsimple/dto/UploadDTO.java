package br.com.keepsimple.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadDTO {
    
	private int id;
    
	private String nomeArquivo;
}