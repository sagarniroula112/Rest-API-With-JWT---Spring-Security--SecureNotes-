**UserController Endpoints**

1. **Login**
   `POST /login`
   Request Body: `User` (email, password)
   Response: JWT token or "Login Failed"

2. **Register**
   `POST /register`
   Request Part: `User` + `MultipartFile` (imgFile)
   Response: "User created successfully"

3. **Get All Users** (Admin only)
   `GET /admin/users`
   Response: List of all users

4. **Get All Notes** (Admin only)
   `GET /admin/notes`
   Response: List of all notes

5. **Get User Image by ID** (Admin only)
   `GET /user/{id}/image`
   Response: Image data of user with headers

---

**NoteController Endpoints**

6. **Get Logged-In User's Notes**
   `GET /api/notes`
   Response: List of user's notes (DTO)

7. **Get Specific Note by ID**
   `GET /api/note/{id}`
   Response: Note DTO

8. **Save a New Note**
   `POST /api/notes`
   Request Body: `Note`
   Response: Created note

9. **Update Note by ID**
   `PUT /api/note/{id}`
   Request Body: `Note`
   Response: "Note updated" or "Failed to update"

10. **Delete Note by ID**
    `DELETE /api/note/{id}`
    Response: "Note deleted successfully" or "Deletion failed"
