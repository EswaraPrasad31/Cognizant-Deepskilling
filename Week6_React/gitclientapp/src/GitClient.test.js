import axios from 'axios';
import GitClient from './GitClient';

jest.mock('axios');

describe('Git Client Tests', () => {
  test('should return repository names for techiesyed', async () => {
    const dummyRepos = [
      { name: 'React' },
      { name: 'Angular' },
      { name: 'NodeJS' },
    ];

    axios.get.mockResolvedValue({ data: dummyRepos });

    const repos = await GitClient.getRepositories('techiesyed');

    expect(repos).toEqual(['React', 'Angular', 'NodeJS']);
    expect(axios.get).toHaveBeenCalledTimes(1);
    expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/techiesyed/repos');
  });
});
