import React from 'react';
import BookDetails from './components/BookDetails';
import BlogDetails from './components/BlogDetails';
import CourseDetails from './components/CourseDetails';
import './App.css';

const App = () => {
  return (
    <div className="main-container">
      {/* Panel 1: Course Details */}
      <div className="panel">
        <CourseDetails />
      </div>

      {/* Vertical Separator */}
      <div className="separator"></div>

      {/* Panel 2: Book Details */}
      <div className="panel">
        <BookDetails />
      </div>

      {/* Vertical Separator */}
      <div className="separator"></div>

      {/* Panel 3: Blog Details */}
      <div className="panel">
        <BlogDetails />
      </div>
    </div>
  );
};

export default App;
