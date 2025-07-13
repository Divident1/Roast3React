---

Develop a streamlined loan application form for a bank using ReactJS. The form should require applicants to provide essential details for the loan application and include routing based on the validation of these details.

Fields:
    Full Name: Text field
    Loan Amount: Number field
    Purpose of Loan: Dropdown with options (House, Car, Personal, Education)
    Tenure: Number field (in years)

Tasks:
    1. Create a loan application form component with the specified fields.
    2. Verify submission with all valid details filled in.
    3. Ensure the loan amount entered is within specified minimum and maximum limits(1000 and 1000000).
    4. Check that the tenure is within acceptable limits(1-30 years).
    5. Ensure proper error messages are shown for both valid and invalid entries.
    6. If validations pass, navigate to a welcome page using React Router.
    7. If validations fail, navigate to an Error Page using React Router.

You need to complete the following files:
    1. ./src/ErrorPage.js
    2. ./src/LoanForm.js
    3. ./src/WelcomePage.js

Notes:
1. Focus on functionality; UI styling is not required for this task.
2. Ensure the use of React Router for navigation.

Testing and Submitting your code:
    1. Click on WeCP Projects button to open the menu.
    2. Click on Start frontend server to start the react server.
    3. Click on Open preview to view the GUI.
    4. Click on Test & Submit app to test your code. You will be given a congratulations message if the task is completed perfectly.