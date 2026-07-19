import React from 'react';
import CohortDetails from './components/CohortDetails';
import CohortData from './data/Cohort';
import './App.css';

function App() {
  return (
    <div className="dashboard">
      <h1 className="dashboard-title">Cohort Dashboard</h1>
      {CohortData.map((cohort) => (
        <CohortDetails key={cohort.id} cohort={cohort} />
      ))}
    </div>
  );
}

export default App;
