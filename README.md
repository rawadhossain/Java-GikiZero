# 🌱 Giki Zero – Personal Carbon Footprint Tracker


> A modern backend built with Spring Boot to power the Personal Carbon Footprint Tracker.  
> Provides APIs for survey submission, carbon emission calculation, dashboard insights, and AI-powered eco-friendly suggestions.

---

## 🌟 Features

### 🔐 Authentication
- Google OAuth (handled in frontend – Next.js)  
- Backend secured with JWT-based auth support (optional if you extend later)

### 📊 Lifestyle Input & Carbon Score
- REST APIs for survey submission and history
- Categories covered:
  - Transportation
  - Energy & water usage
  - Diet habits
  - Clothing & electronics
  - Appliance usage
  - Air travel
  - Waste management
- Emission calculation services integrated with database
- Store scores and responses in PostgreSQL

### 📈 Dashboard & Insights
- Endpoints to fetch personalized dashboard data
- Breakdown by lifestyle category
- Weekly/monthly trends
- Categorization into *Low / Moderate / High impact*

### 🤖 AI Suggestions (Optional Extension)
- AI-powered eco-tips integration (e.g., Gemini API or OpenAI)  
- Context-aware suggestions returned by API  
- Future support for saving tips into reports

### 📄 Report Generation
- API endpoint to generate PDF report (Spring Boot + iText or JasperReports)
- Report includes:
  - Profile details
  - Lifestyle survey summary
  - Carbon emission score
  - Breakdown charts
  - AI suggestions (if enabled)

---

## 📁 Project Structure
```
java-giki-zero/
backend/
├── src/main/java/com/example/gikizero
│ ├── model/                    # Entity classes (User, Survey, EmissionScore)
│ ├── repository/               # Spring Data JPA repositories
│ ├── service/                  # Business logic & emission calculation
│ ├── controller/               # REST API endpoints
│ └── GikiZeroApplication.java  # Main entry point
├── src/main/resources/
│ ├── application.properties    # DB & app config
│ └── static/                   # (Optional) static assets
└── pom.xml                     # Maven dependencies
|
frontend/
├── src/
│   ├── app/                    # App Router structure
│   │   ├── dashboard/          # User dashboard
│   │   ├── survey/             # Lifestyle survey form
│   │   └── auth/               # Authentication routes
│   ├── components/             # UI Components
│   ├── lib/                    # Utility functions (AI, scoring)
│   ├── styles/                 # Tailwind & globals
│   └── types/                  # TypeScript types
└── public/                     # Assets and images
```

## 🛠️ Tech Stack

### 🧑‍💻 Frontend

- **Next.js (App Router)** – Full-stack React framework
- **TypeScript** – Type-safe development
- **Tailwind CSS** – Utility-first styling
- **Chart.js / Recharts** – Graphs and charts

### 🔗 Backend & Database

- **Next.js Server Actions**
- **NextAuth.js** – Google & Email-based login
- **Spring Boot 3.x** – Core framework
- **Spring Web** – REST API
- **Spring Data JPA** – Database integration
- **PostgreSQL** – Database
- **Postgres Database Provider** - NeonDB
- **Lombok** – Reduce boilerplate

### 🤖 AI & PDF

- **Gemini API** – AI suggestion engine
- **iText / JasperReports** – PDF generation

### 📦 Dev Tools

- **ESLint & Prettier** – Code formatting and linting
- **Vercel** – Frontend Hosting
- **Railway** – Backend Hosting
- **React Hook Form** – Form management
- **Maven** – Build & dependency management




<br>

## 📐 Setup Instructions

###  Clone the Repository
```bash
git clone https://github.com/rawadhossain/Java-GikiZero.git
```

### Configure Database
```
spring.datasource.url=jdbc:postgresql://localhost:5432/gikizero
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```



###   Set Environment Variables
Create a ```.env``` file in the root directory and fill in:
```
# Database
DATABASE_URL="postgresql://your-database-url"

# NextAuth Configuration
NEXTAUTH_URL="http://localhost:3000"
NEXTAUTH_SECRET="your_secret"

# Google OAuth
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret

# Gemini AI
GEMINI_API_KEY=your_gemini_api_key
```

###  Install Dependencies
```
npm install
```

### Install Java dependencies
```
mvn clean install
mvn spring-boot:run
```

###  Run the Development Server
```
npm run dev
```

<br>


## 🌐 API Endpoints
### Emission Survey
- ```POST /api/survey/submit``` – Submit lifestyle answers

- ```GET /api/survey/history``` – Get previous submissions

### Dashboard & Tips
- ```GET /api/dashboard/stats``` – User emission data

- ```POST /api/tips/generate``` – Get AI-generated tips

### Reports
- ```GET /api/report/pdf``` – Generate and download report


