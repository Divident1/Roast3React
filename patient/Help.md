---

Develop a segment of a patient management platform using ReactJS tailored for healthcare staff. The platform should enable the registration of new patients and facilitate the retrieval of detailed patient information using a unique ID.

Sample db.json:
json { "patients": [ { "patientID": "P001", "name": "John Doe", "age": "30", "gender": "Male", "condition": "Hypertension", "lastVisit": "2021-08-15" }, { "patientID": "P002", "name": "Jane Smith", "age": "25", "gender": "Female", "condition": "Asthma", "lastVisit": "2021-09-01" } // ... More patients can be added ] } 

Tasks:
1. Develop a ReactJS application for patient management in healthcare settings.
2. Implement a form that allows healthcare staff to register a new patient with attributes like name, age, gender, current medical condition, and date of last visit. 
3. Include validations for each input (e.g., age as a positive integer, date format as YYYY-MM-DD).
3. Create an interface for users to input a patientID, which then fetches and displays the respective patient's detailed information.

You need to complete the following files:
1. ./src/PatientInformation.js
2. ./src/PatientRegistrationForm.js
3. ./src/PatientService.js

Notes:
1. Do not change the structure of the db.json file.
2. Focus on functionality; UI styling is not required for this task.

Testing and Submitting your code:
    1. Click on WeCP Projects button to open the menu.
    2. Click on Start frontend server to start the react server.
    3. Click on Open preview to view the GUI.
    4. Click on Test & Submit app to test your code. You will be given a congratulations message if the task is completed perfectly.