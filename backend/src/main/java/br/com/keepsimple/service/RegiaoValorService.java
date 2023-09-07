package br.com.keepsimple.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.keepsimple.dto.RegiaoConsolidadaDTO;
import br.com.keepsimple.model.Agente;
import br.com.keepsimple.model.RegiaoValor;
import br.com.keepsimple.model.xml.AgenteXML;
import br.com.keepsimple.model.xml.AgentesXML;
import br.com.keepsimple.model.xml.RegiaoXML;
import br.com.keepsimple.repository.AgenteRepository;
import br.com.keepsimple.repository.RegiaoValorRepository;
import br.com.keepsimple.util.XmlUtil;


@Service
public class RegiaoValorService {

	@Autowired
    private AgenteRepository agenteRepository;
	
    @Autowired
    private RegiaoValorRepository regiaoValorRepository;
       
    public RegiaoConsolidadaDTO obterValorConsolidadePorRegiao(String sigla) {
    	RegiaoValor filtro = new RegiaoValor();    	
    	filtro.setSigla(sigla);
    	List<RegiaoValor> lista = regiaoValorRepository.findAll(Example.of(filtro));
    	return listaToDTO(sigla, lista);
    }
    
    @Transactional
    public void salvar(String conteudo) throws JsonProcessingException {       
    	AgentesXML agentesXML = XmlUtil.converterXml(conteudo);    	
    	if(agentesXML != null) {    		
    		System.out.print(" Código dos Agentes: ");
    		for(AgenteXML agenteXML : agentesXML.getAgente()) {
    			Long codigoAgente = Long.valueOf(agenteXML.getCodigo());
    			Optional<Agente> agenteOp = agenteRepository.findById(codigoAgente);
    			Agente agente = null;
    			if(!agenteOp.isPresent()) {
    				agente = new Agente();
        			agente.setCodigo(codigoAgente);
        			agente.setData(LocalDateTime.parse(agenteXML.getData(), DateTimeFormatter.ISO_DATE_TIME));
        			agenteRepository.save(agente);
    			}
    			else {
    				agente = agenteOp.get();
    			}
    			System.out.print(codigoAgente + "; ");
    			if(agenteXML.getRegiao() != null) {
    				for(RegiaoXML regiaoXML : agenteXML.getRegiao()) {
    					RegiaoValor regiaoValor = new RegiaoValor();
    					regiaoValor.setAgente(agente);
    					regiaoValor.setSigla(regiaoXML.getSigla());
    					regiaoValor.setGeracao(regiaoXML.getValorTotalGeracao());
    					regiaoValor.setCompra(regiaoXML.getValorTotalCompra());
    					regiaoValorRepository.save(regiaoValor);
    				}
    			}
    		}
    	}
    }
    
    private RegiaoConsolidadaDTO listaToDTO(String sigla, List<RegiaoValor> lista) {
    	RegiaoConsolidadaDTO dto = null;
    	if(sigla != null && lista != null) {
    		dto = new RegiaoConsolidadaDTO();
    		dto.setRegiao(sigla);
    		dto.setValorTotalCompra(lista.stream().mapToDouble(RegiaoValor::getCompra).sum());
    		dto.setValorTotalGeracao(lista.stream().mapToDouble(RegiaoValor::getGeracao).sum());
    	}
		return dto;
    }
}