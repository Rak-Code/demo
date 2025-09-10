# API Testing Guide

This document provides comprehensive testing information for the Demo Spring Boot application APIs.

## Base URL
```
http://localhost:8080
```

## Prerequisites
- Application should be running on port 8080
- MySQL database should be running on localhost:3306
- Database `demo_db` will be created automatically

## Course API Endpoints

### 1. Create Course
**POST** `/courses`

**Request Body:**
```json
{
  "courseName": "Computer Science"
}
```

**Expected Response:**
```json
{
  "id": 1,
  "courseName": "Computer Science",
  "students": []
}
```

### 2. Get All Courses
**GET** `/courses`

**Expected Response:**
```json
[
  {
    "id": 1,
    "courseName": "Computer Science",
    "students": []
  },
  {
    "id": 2,
    "courseName": "Mathematics",
    "students": []
  }
]
```

### 3. Get Course by ID
**GET** `/courses/{id}`

**Expected Response:**
```json
{
  "id": 1,
  "courseName": "Computer Science",
  "students": []
}
```

### 4. Update Course
**PUT** `/courses/{id}`

**Request Body:**
```json
{
  "courseName": "Advanced Computer Science"
}
```

**Expected Response:**
```json
{
  "id": 1,
  "courseName": "Advanced Computer Science",
  "students": []
}
```

### 5. Delete Course
**DELETE** `/courses/{id}`

**Expected Response:** No content (204 status)

## Student API Endpoints

### 1. Create Student
**POST** `/students/course/{courseId}`

**Request Body:**
```json
{
  "studentName": "John Doe"
}
```

**Expected Response:**
```json
{
  "id": 1,
  "studentName": "John Doe",
  "course": {
    "id": 1,
    "courseName": "Computer Science",
    "students": null
  }
}
```

### 2. Get All Students
**GET** `/students`

**Expected Response:**
```json
[
  {
    "id": 1,
    "studentName": "John Doe",
    "course": {
      "id": 1,
      "courseName": "Computer Science",
      "students": null
    }
  },
  {
    "id": 2,
    "studentName": "Jane Smith",
    "course": {
      "id": 1,
      "courseName": "Computer Science",
      "students": null
    }
  }
]
```

### 3. Get Student by ID
**GET** `/students/{id}`

**Expected Response:**
```json
{
  "id": 1,
  "studentName": "John Doe",
  "course": {
    "id": 1,
    "courseName": "Computer Science",
    "students": null
  }
}
```

### 4. Update Student
**PUT** `/students/{id}`

**Request Body:**
```json
{
  "studentName": "John Smith"
}
```

**Expected Response:**
```json
{
  "id": 1,
  "studentName": "John Smith",
  "course": {
    "id": 1,
    "courseName": "Computer Science",
    "students": null
  }
}
```

### 5. Delete Student
**DELETE** `/students/{id}`

**Expected Response:** No content (204 status)

## Testing Scenarios

### Scenario 1: Complete CRUD Operations for Courses

1. **Create a new course:**
```json
POST /courses
{
  "courseName": "Mathematics"
}
```

2. **Retrieve all courses:**
```
GET /courses
```

3. **Update the course:**
```json
PUT /courses/1
{
  "courseName": "Advanced Mathematics"
}
```

4. **Delete the course:**
```
DELETE /courses/1
```

### Scenario 2: Complete CRUD Operations for Students

1. **First, create a course:**
```json
POST /courses
{
  "courseName": "Physics"
}
```

2. **Create a student for the course:**
```json
POST /students/course/1
{
  "studentName": "Alice Johnson"
}
```

3. **Retrieve all students:**
```
GET /students
```

4. **Update the student:**
```json
PUT /students/1
{
  "studentName": "Alice Williams"
}
```

5. **Delete the student:**
```
DELETE /students/1
```

### Scenario 3: Testing Relationships

1. **Create multiple courses:**
```json
POST /courses
{
  "courseName": "Biology"
}

POST /courses
{
  "courseName": "Chemistry"
}
```

2. **Create students for different courses:**
```json
POST /students/course/1
{
  "studentName": "Bob Brown"
}

POST /students/course/2
{
  "studentName": "Carol Davis"
}

POST /students/course/1
{
  "studentName": "David Wilson"
}
```

3. **Verify relationships by retrieving students:**
```
GET /students
```

## Sample Test Data

### Course Test Data
```json
[
  {"courseName": "Computer Science"},
  {"courseName": "Mathematics"},
  {"courseName": "Physics"},
  {"courseName": "Chemistry"},
  {"courseName": "Biology"}
]
```

### Student Test Data
```json
[
  {"studentName": "John Doe"},
  {"studentName": "Jane Smith"},
  {"studentName": "Bob Johnson"},
  {"studentName": "Alice Brown"},
  {"studentName": "Charlie Wilson"},
  {"studentName": "Diana Davis"},
  {"studentName": "Eve Miller"},
  {"studentName": "Frank Garcia"}
]
```

## Error Scenarios

### 1. Creating Student with Non-existent Course
**POST** `/students/course/999`

**Request Body:**
```json
{
  "studentName": "Test Student"
}
```
**Expected:** Error response indicating course not found

### 2. Retrieving Non-existent Course
**GET** `/courses/999`

**Expected:** Error response indicating course not found

### 3. Retrieving Non-existent Student
**GET** `/students/999`

**Expected:** Error response indicating student not found

## Postman Collection

You can import these requests into Postman by creating a new collection and adding the following requests:

1. **Course Management**
   - Create Course (POST)
   - Get All Courses (GET)
   - Get Course by ID (GET)
   - Update Course (PUT)
   - Delete Course (DELETE)

2. **Student Management**
   - Create Student (POST)
   - Get All Students (GET)
   - Get Student by ID (GET)
   - Update Student (PUT)
   - Delete Student (DELETE)

## Notes

- The application uses MySQL database with automatic table creation
- All IDs are auto-generated
- Students must be associated with an existing course
- Deleting a course will cascade delete all associated students
- The application runs on port 8080 by default
- Content-Type should be set to `application/json` for POST and PUT requests