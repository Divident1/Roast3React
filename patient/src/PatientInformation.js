import React, { useState, useEffect } from 'react';
import { getPatients } from './PatientService';
import axios from 'axios';
import './App.css'; // Import CSS file

export const PatientInformation = ({ patientID }) => {
    const [patient, setPatient] = useState(null);


    useEffect(()=>{
      const fetchData=async()=>{
        const patient=await getPatients();
        const data=patient.find(p=>p.patientID ===patientID);
        setPatient(data||null)
      }
      fetchData();
    },[patientID])
  
    // Function to retrieve patient information based on patient ID
    // use react hook to fetch patient information on ID change
        // fetch all patients and find patient by patient ID
   
  
    return (
      <div className="patient-info-container">
        {patient && (
          <div className="patient-card">
          <h3>Patient Details</h3>
          <p>Patient ID: P001</p>
          <p>Name: {patient.name}</p>
          <p>Age: {patient.age}</p>
          <p>Gender: {patient.gender}</p>
          <p>Condition: {patient.condition}</p>
          <p>Last Visit: {patient.lastVisit}</p>
        </div>
        )}
      </div>
    );
};
