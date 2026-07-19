import React, { useState } from 'react';
import ThemeContext from './context/ThemeContext';
import ThemeToggle from './components/ThemeToggle';
import EmployeeList from './components/EmployeeList';
import employees from './data/employees';
import './App.css';

const App = () => {
  const [theme, setTheme] = useState('light');

  const toggleTheme = () => {
    setTheme((prevTheme) => (prevTheme === 'light' ? 'dark' : 'light'));
  };

  return (
    <ThemeContext.Provider value={theme}>
      <div className={`app-wrapper ${theme}`}>
        <h1 className="app-heading">Employee Management System</h1>
        <ThemeToggle onToggle={toggleTheme} />
        <EmployeeList employees={employees} />
      </div>
    </ThemeContext.Provider>
  );
};

export default App;
