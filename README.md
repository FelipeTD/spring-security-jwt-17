# Spring Security with Java 17
- Projeto com utilização de Java 17, Spring Security para gerar um JWT
- Vídeo de exemplo
  - https://www.youtube.com/watch?v=EjrlN_OQVDQ

## Configuração Inicial do Projeto
- Necessário adicionar as dependências
  - Spring security
  - JPA
  - Spring WEB
  - JJWT Impl
  - JJWT API
  - JJWT Jackson
- Precisa configurar um novo banco de dados
  - A configuração está no arquivo docker-compose.yml
- Precisa configurar o arquivo application.properties com as informações para conectar no banco
- Estrutura do projeto
  - config
  - controller
  - dto
  - entity
  - repository
  - service
- DTO
  - RequestResponse
    - @JsonIgnoreProperties(ignoreUnknown = true)
      - Ignora propriedades não conhecidas. Propriedade que não estão mapeadas na classe
    - @JsonInclude(JsonInclude.Include.NON_NULL)
      - Adiciona no JSON que será gerado todos os dados que não estão nulos
    - Users implements UserDetails
      - Implementar todos os métodos de UserDetails




17:23 Repository