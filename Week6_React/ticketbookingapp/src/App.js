import React, { useState } from 'react';
import Greeting from './components/Greeting';
import LoginButton from './components/LoginButton';
import LogoutButton from './components/LogoutButton';
import FlightDetails from './components/FlightDetails';
import BookingSection from './components/BookingSection';
import './App.css';

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  return (
    <div style={{ marginLeft: '40px', marginTop: '30px' }}>
      <Greeting isLoggedIn={isLoggedIn} />

      {isLoggedIn ? (
        <LogoutButton onLogout={handleLogout} />
      ) : (
        <LoginButton onLogin={handleLogin} />
      )}

      <FlightDetails />

      {isLoggedIn && <BookingSection />}
    </div>
  );
};

export default App;
