import React from 'react';
import GuestPage from './GuestPage';
import UserPage from './UserPage';

const Greeting = ({ isLoggedIn }) => {
  if (isLoggedIn) {
    return <UserPage />;
  }
  return <GuestPage />;
};

export default Greeting;
