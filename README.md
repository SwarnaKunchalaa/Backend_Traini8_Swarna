
# Backend_Traini8_Swarna
1.Backend_Traini8 is SpringBoot Application that creates Training center and manages training centers. It Provides validations for input data training center

#Steps to Run
1. Clone the Repository
git clone https://github.com/SwarnaKunchalaa/Backend_Traini8_Swarna.git

2. Build & Run the Application

3. Open browser and go to: http://localhost:8082/api/trainingcenter

#APIS:
1. POST - http://localhost:8082/api/trainingcenter - create new Training center
2. GET  - http://localhost:8082/api/trainingcenter - Get list of Training centers
   Request Body:
   {
    "centerName": "Traini8 Academy",
    "centerCode": "A1TYUI23",
    "address": {
        "detailedAddress": "Hi Tech City",
        "city": "Hyderabad",
        "state": "Telangana",
        "pincode": "506001"
    },
    "capacity": 200,
    "coursesOffered": ["Java", "Spring Boot", "DevOps"],
    "contactEmail": "info@traini8.com",
    "contactPhone": "9876543210"
}
  Response:
  {
    "centerCode": "A1TYUI23",
    "centerName": "Traini8 Academy",
    "address": {
        "id": null,
        "detailedAddress": "Hi Tech City",
        "city": "Hyderabad",
        "state": "Telangana",
        "pincode": "506001"
    },
    "studentCapacity": null,
    "coursesOffered": [
        "Java",
        "Spring Boot",
        "DevOps"
    ],
    "createdOn": 1739705448,
    "contactEmail": "info@traini8.com",
    "contactPhone": "9876543210"
}




