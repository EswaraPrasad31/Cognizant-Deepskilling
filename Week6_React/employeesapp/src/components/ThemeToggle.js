import React, { useContext } from 'react';
import ThemeContext from '../context/ThemeContext';

const ThemeToggle = ({ onToggle }) => {
  const theme = useContext(ThemeContext);

  return (
    <div style={{ textAlign: 'center', marginBottom: '30px' }}>
      <button
        onClick={onToggle}
        className={`toggle-btn ${theme}`}
      >
        Toggle Theme
      </button>
      <span style={{
        marginLeft: '15px',
        fontSize: '15px',
        color: theme === 'dark' ? '#fff' : '#333',
      }}>
        Current: {theme.toUpperCase()}
      </span>
    </div>
  );
};

export default ThemeToggle;
