import React from 'react';
import CalculateScore from './Components/CalculateScore';

const App = () => {
  return (
    <div>
      <CalculateScore
        Name={"Eswar"}
        School={"DNV Public School"}
        total={284}
        goal={3}
      />
    </div>
  );
};

export default App;
