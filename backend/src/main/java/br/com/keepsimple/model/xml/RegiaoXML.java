package br.com.keepsimple.model.xml;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegiaoXML {
    
	@JacksonXmlProperty(isAttribute = true)
    private String sigla;

    @JacksonXmlProperty(localName = "geracao")
    private List<GeracaoXML> geracao;

    @JacksonXmlProperty(localName = "compra")
    private List<CompraXML> compra;
    
    @JacksonXmlProperty(localName = "precoMedio")
    private List<PrecoMedioXML> precoMedio;
    
    public Double getValorTotalGeracao() {
    	if(geracao != null) {
    		return geracao.stream().mapToDouble(GeracaoXML::getValorDouble).sum();
    	}
    	return 0D;
    }
    
    public Double getValorTotalCompra() {
    	if(compra != null) {
    		return compra.stream().mapToDouble(CompraXML::getValorDouble).sum();
    	}
    	return 0D;
    }
}