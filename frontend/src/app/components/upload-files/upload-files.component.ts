import { Component, OnInit } from '@angular/core';
import { Arquivo } from 'src/app/models/Arquivo';
import { UploadService } from 'src/app/services/upload.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-upload-files',
  templateUrl: './upload-files.component.html',
  styleUrls: ['./upload-files.component.css']
})
export class UploadFilesComponent {

  arquivos: Arquivo[] = [];
  carregando: boolean = false;
  quantidade: number = 0;
  mensagem: string ="";

  constructor(private uploadService: UploadService,
    private _snackBar: MatSnackBar) { }

  async carregaLimpaArquivo(file: File) {
    var reader = new FileReader();
    var xmlSemPreco: string;
    reader.onload = () => {
      const parser = new DOMParser();
      let xml = reader.result ? reader.result.toString() : "";
      const doc = parser.parseFromString(xml, "application/xml");
      var precoMedio = doc.getElementsByTagName("precoMedio");
      for (let i = 0; i < precoMedio.length; i++) {
        precoMedio[i].innerHTML = "";
      }
      const serializer = new XMLSerializer();
      xmlSemPreco = serializer.serializeToString(doc);
      console.log("XML: "+xmlSemPreco)
    };
    reader.onloadend = async () => {
      var arquivo: Arquivo = new Arquivo();
      arquivo.nome = file.name;
      arquivo.xml = xmlSemPreco;
      this.arquivos.push(arquivo);

      if (this.quantidade == this.arquivos.length)
        this.carregando = false;
    }
    reader.readAsText(file);
  }

  selecionaArquivos(event: any): void {
    this.arquivos = [];
    this.quantidade = event.target.files.length
    this.carregando = true;
    if (event.target.files) {
      for (let i = 0; i < this.quantidade; i++) {
        this.carregaLimpaArquivo(event.target.files[i]);
      }
    }
  }

  async enviaArquivos() {
    this.carregando = true;
    this.mensagem="";
    for (let i = 0; i < this.arquivos.length; i++) {
      try {
        	let result = await this.uploadService.upload(this.arquivos[i]);
          this.mensagem += `O arquivo ${this.arquivos[i].nome} foi enviado com sucesso.\n`;
      }
      catch {
        this.mensagem += `Não foi possível enviar o arquivo ${this.arquivos[i].nome}.\n`;
      }
    }
    this.alerta();
    this.limpar();
  }

  async limpar() {
    this.arquivos = [];
    this.carregando = false;
    this.quantidade = 0;
  }

  alerta() {
    if(this.mensagem)
      this._snackBar.open(this.mensagem, 'Fechar',{
        duration: 5000,
        panelClass: ['alerta-snackbar']
      });
  }
}
