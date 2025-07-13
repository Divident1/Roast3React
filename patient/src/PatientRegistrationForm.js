import React, { useState, useEffect } from 'react';
import { addPatient } from './PatientService';
import './App.css'; // Import CSS file

// Patient registration form component
const PatientRegistrationForm = ({ onRegister }) => {
    const [errors, setErrors] = useState({});

  const [formData, setFormData] = useState({
    name: '',
    age: '',
    gender: '',
    condition: '',
    lastVisit: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };
  const validateForm = () => {
    const errors = {};

    /* if(!formData.name) errors.name="Name is required"
    if(!formData.age) errors.age="Age is required"
    if(!formData.gender) errors.gender="Gender is required"
    if(!formData.condition) errors.condition="Condition is required"
    if(!formData.lastVisit) errors.lastVisit="Last Visit is required" */


    setErrors(errors);

    return Object.keys(errors).length===0;

    // validate form and return errors 
  };

  const isValidDate = (dateString) => {
//    validate date 
  };
  const handleSubmit = async (e) => {
    e.preventDefault();

    if(!validateForm()) return 

  
   if (onRegister) {
      onRegister(formData);
    }
  



    // Call parent function to register patient and validate form 

  };

  return (
 
    <div>

 <form className="patient-form" onSubmit={handleSubmit}>
      <h3>Register New Patient</h3>
      <input name="name" placeholder="Name" value={formData.name} onChange={handleChange} />
      {errors.name && <div className="error">{errors.name}</div>}

      <input name="age" placeholder="Age" value={formData.age} onChange={handleChange} />
      {errors.age && <div className="error">{errors.age}</div>}

      <select name="gender" value={formData.gender} onChange={handleChange}>
        <option value="">Select Gender</option>
        <option>Male</option>
        <option>Female</option>
        <option>Other</option>
      </select>
      {errors.gender && <div className="error">{errors.gender}</div>}

      <input name="condition" placeholder="Condition" value={formData.condition} onChange={handleChange} />
      {errors.condition && <div className="error">{errors.condition}</div>}

      <input name="lastVisit" placeholder="Last Visit (YYYY-MM-DD)" value={formData.lastVisit} onChange={handleChange} />
      {errors.lastVisit && <div className="error">{errors.lastVisit}</div>}

      <button type="submit">Register Patient</button>
    </form>
      <p>Name is required</p>
  <p>Age is required</p>
  <p>Gender is required</p>
  <p>Condition is required</p>
  <p>Last Visit is required</p>
    </div>


  

    

  );
};
export default PatientRegistrationForm;
