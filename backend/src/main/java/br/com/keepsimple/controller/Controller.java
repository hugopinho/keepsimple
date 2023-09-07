package br.com.keepsimple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.keepsimple.dto.RegiaoConsolidadaDTO;
import br.com.keepsimple.service.RegiaoValorService;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    RegiaoValorService regiaoValorService;

    @GetMapping("/regiao/{sigla}")
    public ResponseEntity<RegiaoConsolidadaDTO> obterValorConsolidado(@PathVariable String sigla) {
        RegiaoConsolidadaDTO resultado = regiaoValorService.obterValorConsolidadePorRegiao(sigla);
        return ResponseEntity.ok(resultado);
    }

    @SuppressWarnings("rawtypes")
	@PostMapping("/upload")
    @CrossOrigin
    public ResponseEntity carregarArquivo(@RequestParam("arquivo") String arquivo) {
        try{
        	regiaoValorService.salvar(arquivo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

