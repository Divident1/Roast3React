import React, { useState } from "react";

function ShipmentForm() {
  const [formData, setFormData] = useState({
    sender: "",
    receiver: "",
    origin: "",
    destination: "",
    status: "",
    expectedDelivery: "",
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const validateForm = () => {
    const newErrors = {};
    let isValid = true;

    if (!formData.sender.trim()) {
      newErrors.sender = "Sender is required";
      isValid = false;
    }
    if (!formData.receiver.trim()) {
      newErrors.receiver = "Receiver is required";
      isValid = false;
    }
    if (!formData.origin.trim()) {
      newErrors.origin = "Origin is required";
      isValid = false;
    }
    if (!formData.destination.trim()) {
      newErrors.destination = "Destination is required";
      isValid = false;
    }
    if (!formData.status.trim()) {
      newErrors.status = "Status is required";
      isValid = false;
    }
    if (!formData.expectedDelivery.trim()) {
      newErrors.expectedDelivery = "Expected Delivery is required";
      isValid = false;
    }

    setErrors(newErrors);
    return isValid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validateForm()) {
      setErrors({});
      setFormData({
        sender: "",
        receiver: "",
        origin: "",
        destination: "",
        status: "",
        expectedDelivery: "",
      });
    }
  };

  return (
    <div className="shipment-form-container">
      <h2>Register New Shipment</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="sender">Sender:</label>
        <input
          id="sender"
          name="sender"
          type="text"
          value={formData.sender}
          onChange={handleChange}
        />
        {errors.sender && <p style={{ color: "red" }}>{errors.sender}</p>}

        <label htmlFor="receiver">Receiver:</label>
        <input
          id="receiver"
          name="receiver"
          type="text"
          value={formData.receiver}
          onChange={handleChange}
        />
        {errors.receiver && <p style={{ color: "red" }}>{errors.receiver}</p>}

        <label htmlFor="origin">Origin:</label>
        <input
          id="origin"
          name="origin"
          type="text"
          value={formData.origin}
          onChange={handleChange}
        />
        {errors.origin && <p style={{ color: "red" }}>{errors.origin}</p>}

        <label htmlFor="destination">Destination:</label>
        <input
          id="destination"
          name="destination"
          type="text"
          value={formData.destination}
          onChange={handleChange}
        />
        {errors.destination && <p style={{ color: "red" }}>{errors.destination}</p>}

        <label htmlFor="status">Status:</label>
        <input
          id="status"
          name="status"
          type="text"
          value={formData.status}
          onChange={handleChange}
        />
        {errors.status && <p style={{ color: "red" }}>{errors.status}</p>}

        <label htmlFor="expectedDelivery">Expected Delivery:</label>
        <input
          id="expectedDelivery"
          name="expectedDelivery"
          type="text"
          value={formData.expectedDelivery}
          onChange={handleChange}
        />
        {errors.expectedDelivery && <p style={{ color: "red" }}>{errors.expectedDelivery}</p>}

        <button type="submit">Register Shipment</button>
      </form>
    </div>
  );
}

export default ShipmentForm;
