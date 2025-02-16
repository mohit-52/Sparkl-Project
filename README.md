# 🎯 **Online Quiz System** (Spring Boot + JWT + Flutter)  

An **Online Quiz System** built with **Spring Boot** for the backend and **Flutter** for the frontend.  
It features **user authentication, quiz management, and secure quiz attempts** with a well-structured API.  

---

## 🚀 **Features**  
✅ **User Authentication** – Secure JWT-based login & role-based access  
✅ **Admin Panel** – Create quizzes, add questions, view participants  
✅ **User Panel** – Start quizzes, submit answers, and view results  
✅ **Clean API Responses** – DTOs used to prevent infinite recursion  
✅ **Database Integration** – Supports MySQL & PostgreSQL  
✅ **Secure APIs** – Spring Security & role-based access control  

---

## 🛠️ **Tech Stack**  
- **Backend:** Spring Boot, Spring Security, Spring Data JPA, Hibernate  
- **Database:** MySQL / PostgreSQL  
- **Security:** JWT Authentication  
- **Frontend:** Flutter *(to be implemented)*  

---

## 📌 **Getting Started**  
### 1️⃣ **Clone the Repository**  
```sh
git clone https://github.com/yourusername/quiz-app.git
cd quiz-app
```

---

## 🔗 **API Endpoints**  

### 🔐 **Authentication**  
| Method | Endpoint | Description |
|--------|---------|------------|
| **POST** | `/api/auth/login` | Login and get JWT token |

### 👑 **Admin APIs**  
| Method | Endpoint | Description |
|--------|---------|------------|
| **GET** | `/api/admin/quizzes` | Fetch all quizzes |
| **POST** | `/api/admin/quizzes` | Create a new quiz |
| **POST** | `/api/admin/quizzes/{id}/questions` | Add questions to a quiz |
| **GET** | `/api/admin/quizzes/{id}/participants` | View quiz participants |

### 📖 **User APIs**  
| Method | Endpoint | Description |
|--------|---------|------------|
| **GET** | `/api/user/quizzes` | Fetch available quizzes |
| **POST** | `/api/user/quizzes/{id}/start` | Start a quiz |
| **POST** | `/api/user/quizzes/{id}/submit` | Submit answers |
| **GET** | `/api/user/quizzes/{id}/response` | View quiz response |

---

## 🎯 **Next Steps**  
- Implement **Flutter frontend** for a seamless user experience
