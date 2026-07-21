## 🔗 API 명세서 (API Documentation)

> 💡 **[Postman API Document Web Link](https://documenter.getpostman.com/view/55853783/2sBY4PQ1WD#ff517860-1468-4791-b3e5-22aadc646fb5)**

---

### 1. User (사용자)

| 기능 | HTTP Method | URL Path | Request Body | Response Body (Success) |
| :--- | :---: | :--- | :--- | :--- |
| **사용자 등록** | `POST` | `/users` | `{"username", "email", "password"}` | `200 OK`<br>`{"id", "username", "email", "createdAt"}` |
| **전체 사용자 검색** | `GET` | `/users` | None | `200 OK`<br>`[{"id", "username", "email", "createdAt", "modifiedAt"}, ...]` |
| **특정 사용자 검색** | `GET` | `/users/{userId}` | None | `200 OK`<br>`{"id", "username", "email", "createdAt", "modifiedAt"}` |
| **사용자 정보 변경** | `PUT` | `/users/{userId}` | `{"username", "email", "password"}` | `200 OK`<br>`{"id", "username", "email", "createdAt", "modifiedAt"}` |
| **사용자 삭제** | `DELETE` | `/users/{userId}` | `{"password"}` | `204 No Content` |

---

### 2. Schedule (일정)

| 기능 | HTTP Method | URL Path | Request Body | Response Body (Success) |
| :--- | :---: | :--- | :--- | :--- |
| **일정 추가** | `POST` | `/users/{userId}/schedules` | `{"username", "title", "content"}` | `200 OK`<br>`{"id", "title", "content", "username", "createdAt"}` |
| **전체 일정 검색** | `GET` | `/schedules` | None | `200 OK`<br>`{"content": [...], "pageable": {...}, ...}` *(Page)* |
| **특정 사용자의 전체 일정 검색** | `GET` | `/users/{userId}/schedules` | None | `200 OK`<br>`[{"id", "title", "content", "username", "commentCount", ...}]` |
| **일정 변경** | `PUT` | `/users/{userId}/schedules/{scheduleId}` | `{"title", "content"}` | `200 OK`<br>`{"title", "content", "modifiedAt"}` |
| **일정 삭제** | `DELETE` | `/users/{userId}/schedules/{scheduleId}` | None | `204 No Content` |

---

### 3. Comment (댓글)

| 기능 | HTTP Method | URL Path | Request Body | Response Body (Success) |
| :--- | :---: | :--- | :--- | :--- |
| **댓글 추가** | `POST` | `/schedules/{scheduleId}/comments` | `{"content"}` | `200 OK`<br>`{"id", "content", "createdAt"}` |
| **댓글 검색** | `GET` | `/schedules/{scheduleId}/comments` | None | `200 OK`<br>`[{"id", "content", "createdAt"}, ...]` |

<br>

<img width="421" height="1266" alt="image" src="https://github.com/user-attachments/assets/02c13149-e69b-4e88-8854-6bcc113c8154" />
