import React, { useContext } from 'react';
import ThemeContext from '../context/ThemeContext';

const EmployeeCard = ({ employee }) => {
  // Consume theme from Context — no prop drilling
  const theme = useContext(ThemeContext);

  return (
    <div className={`employee-card ${theme}`}>
      <h3 className="emp-name">{employee.name}</h3>
      <div className="emp-details">
        <p><span className="emp-label">Designation :</span> {employee.designation}</p>
        <p><span className="emp-label">Department :</span> {employee.department}</p>
        <p><span className="emp-label">Email :</span> {employee.email}</p>
        <p><span className="emp-label">Phone :</span> {employee.phone}</p>
        <p><span className="emp-label">Experience :</span> {employee.experience}</p>
      </div>
      <div className="emp-actions">
        <button className={`emp-btn ${theme}`}>View Profile</button>
        <button className={`emp-btn ${theme}`}>Contact</button>
      </div>
    </div>
  );
};

export default EmployeeCard;
