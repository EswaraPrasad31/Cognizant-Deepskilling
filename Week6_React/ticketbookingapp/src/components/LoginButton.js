import React from 'react';

const LoginButton = ({ onLogin }) => {
  return (
    <button className="app-btn" onClick={onLogin}>
      Login
    </button>
  );
};

export default LoginButton;
