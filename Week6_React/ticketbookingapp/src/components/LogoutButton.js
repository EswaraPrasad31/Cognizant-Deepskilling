import React from 'react';

const LogoutButton = ({ onLogout }) => {
  return (
    <button className="app-btn" onClick={onLogout}>
      Logout
    </button>
  );
};

export default LogoutButton;
