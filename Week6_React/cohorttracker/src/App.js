import React from 'react';
import CohortDetails from './components/CohortDetails';
import cohorts from './data/cohorts';

function App() {
  return (
    <div style={{ padding: '20px' }}>
      <h1>Cohorts Details</h1>
      <div>
        {cohorts.map((cohort) => (
          <CohortDetails key={cohort.id} cohort={cohort} />
        ))}
      </div>
    </div>
  );
}

export default App;
