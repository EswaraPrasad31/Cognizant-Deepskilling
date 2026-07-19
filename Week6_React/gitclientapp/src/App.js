import React, { useState } from 'react';
import GitClient from './GitClient';
import './App.css';

function App() {
  const [username, setUsername] = useState('techiesyed');
  const [repositories, setRepositories] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(false);

  const handleFetch = async () => {
    if (!username.trim()) return;

    setLoading(true);
    setError(false);
    setRepositories([]);

    try {
      const repos = await GitClient.getRepositories(username.trim());
      if (repos && repos.length > 0) {
        setRepositories(repos);
      } else {
        setError(true);
      }
    } catch (err) {
      setError(true);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="git-app">
      <h1 className="app-title">GitHub Repository Viewer</h1>
      <div className="container">
        <div className="form-group">
          <label className="form-label" htmlFor="username">
            GitHub Username
          </label>
          <input
            type="text"
            id="username"
            className="form-control"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Enter GitHub Username"
          />
        </div>

        <button className="fetch-btn" onClick={handleFetch}>
          Fetch Repositories
        </button>

        {loading && <div className="loading-text">Loading...</div>}

        {error && !loading && (
          <div className="error-text">Unable to fetch repositories.</div>
        )}

        {!loading && !error && repositories.length > 0 && (
          <div className="repo-section">
            <h2 className="repo-heading">Repositories</h2>
            <ul className="repo-list">
              {repositories.map((repo, index) => (
                <li key={index}>{repo}</li>
              ))}
            </ul>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
