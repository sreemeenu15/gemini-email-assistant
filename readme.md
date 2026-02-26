# Gemini Email Assistant 

An AI-powered email reply backend built using Spring Boot and Google Gemini API.

---

## Overview

Gemini Email Assistant is a backend service designed to generate intelligent, context-aware email replies.

Instead of relying on predefined templates, the system analyzes the emotional tone and content of incoming messages and produces natural, human-like responses using the Gemini API.

The goal of this project is to combine backend engineering fundamentals with modern AI capabilities in a clean and production-ready structure.

---

## Tech Stack

- Java 17+
- Spring Boot
- WebClient (Reactive HTTP Client)
- Google Gemini API
- Maven

---

## Features

- REST API endpoint for generating email replies
- Context-aware response generation
- Hybrid tone handling (manual tone selection + AI tone analysis)
- Secure API key configuration using environment variables
- Clean layered architecture (Controller → Service → Config → DTO)

---

## API Endpoint

### POST `/generate`

**Request Body**

```json
{
  "emailContent": "Hello Beautiful!",
  "tone": "Friendly"
}
```


```json
{
  "reply": "Hi there! That just made my day . How are you doing?"
}

```

## Configuration

Set your Gemini API key as an environment variable.

Windows (PowerShell)
setx GEMINI_API_KEY "your_api_key_here"
Mac / Linux
export GEMINI_API_KEY="your_api_key_here"
Running the Application



## Clone the repository:

git clone https://github.com/sreemenu15/gemini-email-assistant.git
cd gemini-email-assistant

## Run the project:

mvn spring-boot:run

## Application runs at:

http://localhost:8080

## Learning Objectives

This project was built to:

1. Strengthen backend development skills using Spring Boot

2. Integrate and consume third-party AI APIs

3. Understand Git and GitHub workflows

4. Prepare for deployment and production-level systems

## Author

Built with focus, curiosity, and a commitment to continuous learning by doing.