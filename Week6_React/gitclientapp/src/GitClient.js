import axios from 'axios';

const GitClient = {
  async getRepositories(username) {
    try {
      const response = await axios.get(`https://api.github.com/users/${username}/repos`);
      if (Array.isArray(response.data)) {
        return response.data.map((repo) => repo.name);
      }
      return [];
    } catch (error) {
      return [];
    }
  },
};

export default GitClient;
