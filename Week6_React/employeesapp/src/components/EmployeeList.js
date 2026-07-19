import React from 'react';
import EmployeeCard from './EmployeeCard';

// Receives only employee data — NO theme prop
const EmployeeList = ({ employees }) => {
  return (
    <div className="employee-list">
      {employees.map((employee) => (
        <EmployeeCard key={employee.id} employee={employee} />
      ))}
    </div>
  );
};

export default EmployeeList;
