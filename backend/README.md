# Keep Simple

Backend de projeto desenvolvido com Java e Spring Boot que realiza a carga de dados através do upload de arquivo(s) XML.

## API

**(GET)** <br>
Trás dados consolidados por região: <br>
[http://localhost:8080/regiao/{sigla}](http://localhost:8080/regiao/{sigla})

**(POST)** <br>
Endpoint para recepcionar arquivos XML a serem carregados: <br>
[http://localhost:8080/upload](http://localhost:8080/upload)


## Banco de Dados

[http://localhost:8080/h2-console/](http://localhost:8080/h2-console/) <br>

string de conexão: jdbc:h2:mem:keepsimple <br>
usuário:sa <br>
senha:sa<br>