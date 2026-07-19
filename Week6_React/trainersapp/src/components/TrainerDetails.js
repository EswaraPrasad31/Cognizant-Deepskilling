import React from 'react';
import { useParams, Link } from 'react-router-dom';

const TrainerDetails = ({ trainers }) => {
  const { id } = useParams();
  const trainer = trainers.find((t) => t.TrainerId === parseInt(id));

  if (!trainer) {
    return (
      <div>
        <h2>Trainers Details</h2>
        <p>Trainer Not Found</p>
        <Link to="/trainers">← Back to Trainers List</Link>
      </div>
    );
  }

  return (
    <div>
      <h2>Trainers Details</h2>
      <p>
        <strong>
          {trainer.Name} ({trainer.Technology})
        </strong>
      </p>
      <p><strong>Trainer ID:</strong> {trainer.TrainerId}</p>
      <p><strong>Email:</strong> {trainer.Email}</p>
      <p><strong>Phone:</strong> {trainer.Phone}</p>
      <p><strong>Skills:</strong></p>
      <ul>
        {trainer.Skills.map((skill, index) => (
          <li key={index}>{skill}</li>
        ))}
      </ul>
      <Link to="/trainers">← Back to Trainers List</Link>
    </div>
  );
};

export default TrainerDetails;
