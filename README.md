# InvestAPI

**InvestAPI** is an application for creating complex investment formulas based on data from companies listed on the Brazilian stock exchange. The API integrates financial data with BRAPI and OpenAI, allowing you to obtain stock quotes, insights, and advanced analyses.

---

## Features

- **Quote Query:** Returns stock information based on sector, limit, and type parameters.
- **Data Export:** Generates a CSV file with investment data.
- **Automated Calculation:** Uses Prompt Engineering + GenAI to create formulas from the obtained data.
- **BRAPI Integration:** Consumes real-time market data and quotes.
- **OpenAI Integration:** Generates analyses and insights about investments.

---

## Technologies Used

- **Language:** Java
- **Framework:** Spring + Spring Boot + Hibernate (MySQL)
- **Dependency Management:** Maven
- **External Integrations:** BRAPI, OpenAI

---

## Integrations

### BRAPI
Integration with BRAPI enables the retrieval of up-to-date market data, making it easy to get quotes and stock information.
**Configuration:**
- Set the environment variables or properties in `application.properties`:
```properties
brapi.api.key=YOUR_BRAPI_API_KEY
brapi.api.url=https://api.brapi.com
```

### OpenAI
Used to generate insights and analyses about investments, the integration with OpenAI enhances the user experience with intelligent responses.
**Configuration:**
- Set your API key and URL:
```properties
openai.api.key=YOUR_OPENAI_API_KEY
openai.api.url=https://api.openai.com
```

---

## Endpoints

### 1. **GET** `/api/quote`
Retrieves stock quote information based on the provided parameters.

**Query Parameters:**
- `sector` (string): Stock sector.
- `limit` (string): Maximum number of results (stocks per query).
- `type` (string): Specific type or filter for the query (stock or fund).

**Request Example:**
```http
GET /api/quote?sector=technology&limit=10&type=dividends
```

**Response:**
A JSON object containing stock data, for example:
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
Exports investment data to a CSV file.

**Request Example:**
```http
GET /api/export
```

**Response:**
A CSV file is returned for download with the following headers:
- `Content-Type`: `text/csv`
- `Content-Disposition`: `attachment; filename="data.csv"`

---

## Project Setup

### Prerequisites

- **Java JDK 21** (or higher)
- **Maven**
- Environment variable configuration for integrations:
- **BRAPI:** `brapi.api.key`, `brapi.api.url`
- **OpenAI:** `openai.api.key`, `openai.api.url`

### Installation
- TBD

---

## Tests

To run unit and integration tests, use:
```bash
mvn test
```

---

## License

This project is licensed under the [MIT License](LICENSE). 