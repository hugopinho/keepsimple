package br.com.keepsimple.model.xml;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgenteXML {
    
	@JacksonXmlProperty(localName = "codigo")
	private String codigo;
    
	@JacksonXmlProperty(localName = "data")
	private String data;

	@JacksonXmlProperty(localName = "regiao")
    @JacksonXmlElementWrapper(useWrapping = false)
	private List<RegiaoXML> regiao;
}
