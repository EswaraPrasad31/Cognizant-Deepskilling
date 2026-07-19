import React from 'react';
import { courses, showCourses } from '../data';

// Conditional Rendering: Ternary Operator (Method 2) at top level
const CourseDetails = () => {
  return (
    <div>
      <h2 className="panel-heading">Course Details</h2>
      {/* Method 2: Ternary Operator */}
      {showCourses ? (
        courses.map((course) => (
          <div key={course.id} style={{ marginBottom: '20px' }}>
            <p style={{ fontSize: '26px', fontWeight: 'bold', margin: '0' }}>
              {course.courseName}
            </p>
            <p style={{ fontSize: '16px', margin: '4px 0 0 0' }}>
              {course.date}
            </p>
          </div>
        ))
      ) : (
        <p>No courses available.</p>
      )}
    </div>
  );
};

export default CourseDetails;
