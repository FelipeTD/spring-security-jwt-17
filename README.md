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
- Repository
  - Criou as duas classes de repository
  - ProductRepository 
    - Não precisou adicionar nenhum método 
  - UserRepository
    - Criou um método `findByEmail`
- JWTUtils
  - Classe com métodos que serão utilizados para utilização do JWT
  - Possui os métodos:
    - `generateToken`
      - Responsável por gerar o token
    - `generateRefreshToken`
      - Responsável por atualizar o token
    - `extractUsername`
      - Utilizado no método `isTokenValid` para validar se o nome de utilizador é válido
    - `extractClaims`
      - Utilizado nos métodos `extractUsername` e `isTokenExpired`
      - O primeiro é para pegar o nome de utilizador
      - O segundo é para pegar a data de expiração
    - `isTokenValid`
      - Faz a validação se o token está valido
      - É validado o nome de utilizador e a data de expiração do token
    - `isTokenExpired`
      - Utilizado no método `isTokenValid` para validar se o token está expirado




17:23 Repository