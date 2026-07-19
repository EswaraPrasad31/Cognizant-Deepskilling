import React from 'react';
import styles from './CohortDetails.module.css';

const CohortDetails = ({ cohort }) => {
  if (!cohort) return null;

  const { code, coach, trainer, startDate, status } = cohort;

  return (
    <div className={styles.card}>
      <h3 className={styles.code}>{code}</h3>
      <p className={styles.info}>
        <span className={styles.label}>Coach :</span> {coach}
      </p>
      <p className={styles.info}>
        <span className={styles.label}>Trainer :</span> {trainer}
      </p>
      <p className={styles.info}>
        <span className={styles.label}>Start Date :</span> {startDate}
      </p>
      <p className={styles.info}>
        <span className={styles.label}>Status :</span> {status}
      </p>
    </div>
  );
};

export default CohortDetails;
