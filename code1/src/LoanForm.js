import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import './App.css';
const LoanForm = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    fullName: "",
    loanAmount: "",
    purpose: "House",
    tenure: "",
  });
  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value.trim() });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const validationErrors = {};
  
    let tenure = parseInt(formData.tenure);
    let amount =parseFloat(formData.loanAmount);
    if(!formData.fullName) validationErrors.fullName="Please enter your full name";
    
   if(isNaN(amount) || (amount<1000 && amount> 1000000)) validationErrors.loanAmount="loan amount must be between 1000 and 1000000"

 
 if (isNaN(tenure) || tenure < 1 || tenure > 30) validationErrors.tenure = "Tenure must be between 1 and 30 years";

    setErrors(validationErrors);

    if(Object.keys(validationErrors).length===0)
    {
      navigate('/welcome')
    }
    navigate('/error')

  };

  return (
    <div>
      <h1 className="header">Bank Loan Form</h1>

<form onSubmit={handleSubmit}>


      <div>
        <label htmlFor="fullName">full name</label>
        <input type="text" onChange={handleChange} value={formData.fullName}  id="fullName"/>
        {errors.fullName && <p>{errors.fullName}</p>}
      </div>


<div>
        <label htmlFor="loanAmount">loan amount</label>
        <input type="text" onChange={handleChange} value={formData.loanAmount}  id="loanAmount"/>
        {errors.loanAmount && <p>{errors.loanAmount}</p>}
      </div>



<div>
        <label htmlFor="tenure">tenure</label>
        <input type="number" onChange={handleChange} value={formData.tenure}  id="tenure"/>
        {errors.tenure && <p>{errors.tenure}</p>}
      </div>
      {/* Create Loan Form HTML */}
      <button type="submit">submit</button>
      </form>
    </div>
  );
};

export default LoanForm;
