import React, { useState } from 'react';

const ComplaintRegister = () => {
  const [employeeName, setEmployeeName] = useState('');
  const [complaint, setComplaint] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    // Validation
    if (!employeeName.trim() || !complaint.trim()) {
      alert('Please complete all fields before submitting.');
      return;
    }

    // Generate unique Reference Number (e.g. REF-583921)
    const randomNum = Math.floor(100000 + Math.random() * 900000);
    const refNumber = `REF-${randomNum}`;

    // Display alert
    alert(
      `Complaint Registered Successfully.\nReference Number : ${refNumber}\nPlease use this number for future follow ups.`
    );

    // Clear form fields
    setEmployeeName('');
    setComplaint('');
  };

  return (
    <div className="ticket-app">
      <h1 className="app-title">Ticket Raising System</h1>
      <div className="form-container">
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label className="form-label" htmlFor="employeeName">
              Employee Name
            </label>
            <input
              type="text"
              id="employeeName"
              className="form-control"
              value={employeeName}
              onChange={(e) => setEmployeeName(e.target.value)}
              placeholder="Enter Employee Name"
            />
          </div>

          <div className="form-group">
            <label className="form-label" htmlFor="complaint">
              Complaint
            </label>
            <textarea
              id="complaint"
              className="form-control textarea-control"
              value={complaint}
              onChange={(e) => setComplaint(e.target.value)}
              placeholder="Describe your complaint"
            />
          </div>

          <div className="button-container">
            <button type="submit" className="submit-btn">
              Submit
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ComplaintRegister;
