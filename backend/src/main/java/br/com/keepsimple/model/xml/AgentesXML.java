package br.com.keepsimple.model.xml;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JacksonXmlRootElement(localName = "agentes")
public class AgentesXML {
    
	@JacksonXmlProperty(isAttribute = true)
    private String versao;

	@JacksonXmlProperty(localName = "agente")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AgenteXML> agente;
}