package br.com.keepsimple.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompraXML {
    
	@JacksonXmlProperty(localName = "valor")
    private String valor;
	
	public Double getValorDouble() {
    	if(valor != null) {
    		return Double.valueOf(valor);
    	}
    	return 0D;
    }
}
