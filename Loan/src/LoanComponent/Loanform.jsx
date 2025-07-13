import React, { useState } from 'react'
import { useNavigate } from "react-router-dom";
const Loanform = () => {
const navigate=useNavigate();
const [formData, setfomData]=useState({
     fullName: "",
    loanAmount: "",
    purpose: "House",
    tenure: "",
});

const[err,seterr] =useState({

})


const handleChange=(e)=>{
  setfomData({
    ...formData,[e.target.name]:e.target.value.trim()
  })
}

const handleSubmit=(e)=>{
  e.preventDefault();

  const err={}

  if(!formData.fullName) err.formData="Fullname is required"
  if(!formData.loanAmount) err.loanAmount="LoanAmount is required"
   if(!formData.purpose) err.purpose="Purpose is required"
    if(!formData.tenure) err.tenure="Tenure is required"

    seterr(err)

    if(Object.keys(err).length===0)
    {
      navigate("/welcome")
    }
    else{
      navigate("/error")
    }

}


  return (
    <div>
      
      <h1>Loan Form</h1>


      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="">Full Name:</label>
          <input onChange={handleChange} value={formData.fullName} type="text" placeholder='Enter the fullName' id='fullName' name='fullName' />
          {err && <p>{err.fullName}</p>}
        </div>


         <div className="form-group">
          <label htmlFor="">LoanAmount:</label>
          <input onChange={handleChange} value={formData.loanAmount} type="text" placeholder='Enter the LoanAmount' id='loanAmount' name='loanAmount' />
          {err && <p>{err.loanAmount}</p>}
        </div>


         <div className="form-group">
          <label htmlFor="">Purpose:</label>
         <select name="purpose" onChange={handleChange} value={formData.purpose} id="purpose">
          <option value="-Select-"></option>
          <option value="House">House</option>
          <option value="Car">Car</option>
          <option value="Personal">Personal</option>
          <option value="Education">Education</option>
         </select>
          {err && <p>{err.purpose}</p>}
        </div>


        <div className="form-group">
          <label htmlFor="">Tenure:</label>
         <input onChange={handleChange} value={formData.tenure} type="text" id='tenure'  name='tenure' placeholder='Enter the tenure'/>
          {err && <p>{err.tenure}</p>}
        </div>

        <button type='submit'>Submit</button>

      </form>
    </div>
  )
}

export default Loanform
