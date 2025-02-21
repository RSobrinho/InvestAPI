# InvestAPI

A **InvestAPI** é uma aplicação para a criação de fórmulas de investimentos complexas baseados nos dados das empresas da bolsa de valores brasileira. A API integra dados financeiros com a BRAPI e a OpenAI, permitindo obter cotações de ações, insights e análises avançadas.

---

## Funcionalidades

- **Consulta de Cotações:** Retorna informações de ações com base em parâmetros de setor, limite e tipo.  
- **Exportação de Dados:** Gera um arquivo CSV com os dados de investimentos.  
- **Calculo automatizado:** utilizando Prompt Engineering + GenAI para a criação das fórmulas a partir dos dados obtidos.
- **Integração com BRAPI:** Consome dados de mercado e cotações em tempo real.  
- **Integração com OpenAI:** Gera análises e insights sobre os investimentos.

---

## Tecnologias Utilizadas

- **Linguagem:** Java  
- **Framework:** Spring + Spring Boot + Hibernate (MySQL)
- **Gerenciamento de Dependências:** Maven
- **Integrações Externas:** BRAPI, OpenAI

---

## Integrações

### BRAPI  
A integração com a BRAPI possibilita a obtenção de dados atualizados de mercado, facilitando a recuperação de cotações e informações sobre ações.  
**Configuração:**  
- Defina as variáveis de ambiente ou propriedades em `application.properties`:
```properties
brapi.api.key=SEU_BRAPI_API_KEY
brapi.api.url=https://api.brapi.com
```

### OpenAI  
Utilizada para gerar insights e análises sobre os investimentos, a integração com a OpenAI aprimora a experiência do usuário com respostas inteligentes.  
**Configuração:**  
- Configure sua chave de API e URL:
```properties
openai.api.key=SEU_OPENAI_API_KEY
openai.api.url=https://api.openai.com
```

---

## Endpoints

### 1. **GET** `/api/quote`  
Recupera informações de cotações de ações com base nos parâmetros informados.

**Parâmetros de Consulta:**
- `sector` (string): Setor de atuação das ações.  
- `limit` (string): Quantidade máxima de resultados (ações por consulta).  
- `type` (string): Tipo ou filtro específico para a consulta (stock ou fund).

**Exemplo de Requisição:**
```http
GET /api/quote?sector=tecnologia&limit=10&type=dividendos
```

**Resposta:**  
Um objeto JSON contendo os dados das ações, por exemplo:
```json
{
  "stocks": [
    {
      "stockName": "ABCD4",
      "stockFormulasResponse": {
        "rawFormulas": {}
      }
    }
  ]
}
```

---

### 2. **GET** `/api/export`  
Exporta os dados de investimentos para um arquivo CSV.

**Exemplo de Requisição:**
```http
GET /api/export
```

**Resposta:**  
Um arquivo CSV é retornado para download com os seguintes cabeçalhos:
- `Content-Type`: `text/csv`  
- `Content-Disposition`: `attachment; filename="data.csv"`

---

## Configuração do Projeto

### Pré-requisitos

- **Java JDK 21** (ou superior)  
- **Maven**
- Configuração das variáveis de ambiente para as integrações:  
- **BRAPI:** `brapi.api.key`, `brapi.api.url`  
- **OpenAI:** `openai.api.key`, `openai.api.url`

### Instalação
- TBD

---

## Testes

Para executar os testes unitários e de integração, utilize:
```bash
mvn test
```

---

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).