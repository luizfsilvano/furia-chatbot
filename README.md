
# FURIA Chatbot

This repository contains the complete solution for the first technical challenge of the FURIA Software Engineering recruitment process.


## Backend Overview
- Built with Spring Boot 3.4.5
- Integrates with Gemini AI API
- Fallback mechanism for Gemini API failures
- Short-term in-memory interaction logging
- Dockerized for container deployment

**Live Backend:**  
https://furia-chatbot-backend.onrender.com

**Documentation**:
https://documenter.getpostman.com/view/32846088/2sB2j1hCkh#f21941e0-1127-4dcd-88e9-229a9f5a8258
## Frontend Overview
- Built with Vite and React
- Chat interface with FURIA logo and simple custom styling
- Axios integration with backend
- Hosted on Vercel

**Live Frontend:**  
https://furia-chatbot-gamma.vercel.app## How to Run Locally

### 1. Clone the Repository

```bash
git clone https://github.com/luizfsilvano/furia-chatbot.git

```
```bash
cd furia-chatbot
```

### 2. Backend Setup
Navigate into the backend directory, build the project, and run the application:

```bash
cd backend
./mvnw clean package
java -jar target/*.jar
```
Make sure to set the required environment variable GEMINI_API_KEY before running the backend.

### 3. Frontend Setup
In another terminal window, navigate into the frontend directory:

```bash
cd frontend
npm install
npm run dev
```
The frontend will be available locally at http://localhost:5173.


## Environment Variables Required

| Component | Variable | Description |
|:----------|:---------|:------------|
| Backend | GEMINI_API_KEY | Gemini API key used to generate chatbot responses |
| Frontend | VITE_API_URL | URL pointing to the backend API |

### Challenges Encountered

- Managing the environment variables securely, especially with sensitive API keys.
- Handling CORS configuration properly to allow the frontend to communicate with the backend hosted on different domains.
- Ensuring fallback mechanisms worked reliably when the Gemini API was unavailable.
- Dealing with asynchronous errors and improving user feedback on the frontend when the backend service was temporarily down.
- Limitations of the free tier of the Gemini API, which restricted the ability to perform deep contextual searches dynamically.
- Inability to implement full contextual memory retrieval from a database due to lack of integration with a paid vector database service (e.g., Pinecone, Weaviate).
### Potential Improvements

- Enhance fallback responses with context-awareness instead of random selection.
- Implement Retrieval-Augmented Generation (RAG) to allow the chatbot to query an external knowledge base dynamically.
- Improve session management to support multi-turn conversations.
- Add persistent storage for chat history instead of in-memory storage.
- Integrate a WebSocket-based solution for real-time chat experience.
- Improve frontend styling to better align with FURIA's official brand guidelines.
- Add loading indicators and graceful error handling for better UX on network failures.
---
# FURIA Chatbot - Português

Este repositório contém a solução completa para o primeiro desafio técnico do processo seletivo de Estágio em Engenharia de Software da FURIA.

## Visão Geral do Backend

- Desenvolvido com Spring Boot 3.4.5
- Integração com a API Gemini AI
- Mecanismo de fallback para falhas na API Gemini
- Registro de interações em memória de curto prazo
- Preparado para implantação via Docker

**Backend Online:**  
https://furia-chatbot-backend.onrender.com

**Documentação da API:**  
https://documenter.getpostman.com/view/32846088/2sB2j1hCkh#f21941e0-1127-4dcd-88e9-229a9f5a8258

## Visão Geral do Frontend

- Desenvolvido com Vite e React
- Interface de chat utilizando o logo da FURIA e estilização personalizada
- Integração com o backend via Axios
- Implantado no Vercel

**Frontend Online:**  
https://furia-chatbot-gamma.vercel.app

## Como Rodar Localmente

### 1. Clonar o Repositório

```bash
git clone https://github.com/luizfsilvano/furia-chatbot.git
cd furia-chatbot
```

### 2. Configuração do Backend

Acesse o diretório `backend`, construa o projeto e execute a aplicação:

```bash
cd backend
./mvnw clean package
java -jar target/*.jar
```

Certifique-se de configurar a variável de ambiente `GEMINI_API_KEY` antes de iniciar o backend.

### 3. Configuração do Frontend

Em outro terminal, acesse o diretório `frontend`:

```bash
cd frontend
npm install
npm run dev
```

O frontend estará disponível localmente em [http://localhost:5173](http://localhost:5173).

## Variáveis de Ambiente Necessárias

| Componente | Variável        | Descrição |
|:-----------|:-----------------|:----------|
| Backend    | GEMINI_API_KEY    | Chave da API Gemini para geração de respostas do chatbot |
| Frontend   | VITE_API_URL      | URL apontando para a API do backend |

## Desafios Encontrados

- Gerenciamento seguro de variáveis de ambiente, especialmente com chaves sensíveis.
- Configuração adequada do CORS para permitir a comunicação entre frontend e backend hospedados em domínios diferentes.
- Garantir que o mecanismo de fallback funcionasse de maneira confiável em caso de indisponibilidade da API Gemini.
- Tratamento de erros assíncronos e melhoria no feedback do usuário no frontend em situações de indisponibilidade do backend.
- Limitações da versão gratuita da API Gemini, que restringiram a possibilidade de realizar buscas contextuais profundas.
- Impossibilidade de implementar recuperação completa de memória contextual a partir de banco de dados, devido à necessidade de integração com serviços pagos de banco vetorial (como Pinecone, Weaviate).

## Melhorias Potenciais

- Aprimorar as respostas de fallback com mecanismos de seleção baseados em contexto, em vez de seleção aleatória.
- Implementar Retrieval-Augmented Generation (RAG) para permitir que o chatbot consulte uma base de conhecimento externa dinamicamente.
- Melhorar o gerenciamento de sessões para suportar conversas de múltiplas interações (multi-turn conversations).
- Adicionar armazenamento persistente para o histórico de conversas, substituindo o armazenamento em memória.
- Integrar uma solução baseada em WebSocket para proporcionar uma experiência de chat em tempo real.
- Melhorar a estilização do frontend para alinhar-se melhor com as diretrizes oficiais da marca FURIA.
- Adicionar indicadores de carregamento e tratamento de erros mais elegante para melhorar a experiência do usuário em casos de falha de rede.

