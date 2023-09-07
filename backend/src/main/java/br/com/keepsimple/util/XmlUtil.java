package br.com.keepsimple.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.keepsimple.model.xml.AgentesXML;

public class XmlUtil {
	
	private XmlUtil() {}
	
	public static AgentesXML converterXml(String xml) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();    
		AgentesXML agente = xmlMapper.readValue(xml, AgentesXML.class);
		return agente;
    }
}
