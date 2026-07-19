import React from 'react';
import { BrowserRouter, Routes, Route, Link, Navigate } from 'react-router-dom';
import Home from './components/Home';
import TrainersList from './components/TrainersList';
import TrainerDetails from './components/TrainerDetails';
import TrainersMock from './data/TrainersMock';
import './styles/App.css';

const App = () => {
  return (
    <BrowserRouter>
      <div className="app-container">
        <div className="app-title">My Academy Trainers App</div>
        <nav className="navbar">
          <Link to="/home">Home</Link>
          <span>|</span>
          <Link to="/trainers">Show Trainers</Link>
        </nav>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<Home />} />
          <Route path="/trainers" element={<TrainersList trainers={TrainersMock} />} />
          <Route path="/trainers/:id" element={<TrainerDetails trainers={TrainersMock} />} />
          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
};

export default App;
